package logic;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class TaskManager {
    private ArrayList<Task> tasks;
    private final Path DATA_PATH = Path.of("data/data.json");

    public TaskManager() {
        tasks = loadTasks();
    }

    private ArrayList<Task> loadTasks() {
        ArrayList<Task> loadedTasks = new ArrayList<>();

        if (!Files.exists(DATA_PATH)) {
            return loadedTasks;
        }

        try {
            String content = Files.readString(DATA_PATH);
            if (!content.equals("")) {
                String[] taskList = content.replace("[", "")
                        .replace("]", "")
                        .split("},");

                for (String taskJson : taskList) {
                    if (!taskJson.endsWith("}")) {
                        taskJson += "}";
                    }
                    loadedTasks.add(JsonManager.createTaskFromJson(taskJson));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loadedTasks;
    }

    public void saveTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");

        for (int i = 0; i < tasks.size(); i++) {
            sb.append(JsonManager.exportTaskToJson(tasks.get(i)));
            if (i < tasks.size() - 1) {
                sb.append(",\n");
            }
        }
        sb.append("\n]");

        String jsonContent = sb.toString();
        try {
            Files.writeString(DATA_PATH, jsonContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    private Task searchTask(int id) {
        int i = 0;
        boolean found = false;
        Task res = null;

        while (i < tasks.size() && !found) {
            if (tasks.get(i).getId() == id) {
                res = tasks.get(i);
                found = true;
            }
            i++;
        }

        return res;
    }

    public void addTask(String description) {
        Task newTask = new Task(description);
        tasks.add(newTask);
    }

    public void updateTask(int taskId, String newDesc) {
        searchTask(taskId).update(newDesc);
    }

    public void updateTaskStatus(int taskId, String newStatus) {
        searchTask(taskId).changeStatus(newStatus);
    }

    public void deleteTask(int taskId) {
        tasks.remove(searchTask(taskId));
    }

    public ArrayList<Task> getTasksByStatus(String status) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getStatus().equals(status)) {
                foundTasks.add(t);
            }
        }
        return foundTasks;
    }
}