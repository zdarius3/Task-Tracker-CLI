package logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class TaskManager {
    private final ArrayList<Task> tasks;
    private final Path DATA_PATH = Path.of("data/data.json");

    public TaskManager() {
        tasks = loadTasks();
    }

    @SuppressWarnings("CallToPrintStackTrace")
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedTasks;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void saveTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");

        //save each task separately
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(JsonManager.exportTaskToJson(tasks.get(i)));
            if (i < tasks.size() - 1) {
                sb.append(",\n"); //only put a comma if its the last one
            }
        }
        sb.append("\n]");

        String jsonContent = sb.toString();
        try {
            Files.writeString(DATA_PATH, jsonContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> getAllTasks() {
        if (tasks.isEmpty()) {
            throw new IllegalStateException("There are no registered tasks...");
        }
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
        Task foundTask = searchTask(taskId);
        if(foundTask == null) {
            throw new IllegalArgumentException("You can't update a non-existent task...");
        }
        foundTask.update(newDesc);
    }

    public void updateTaskStatus(int taskId, String newStatus) {
        Task foundTask = searchTask(taskId);
        if(foundTask == null) {
            throw new IllegalArgumentException("You can't change a non-existent task's status...");
        }
        foundTask.changeStatus(newStatus);
    }

    public void deleteTask(int taskId) {
        Task foundTask = searchTask(taskId);
        if (foundTask == null) {
            throw new IllegalArgumentException("You can't delete a non-existent task...");
        }
        tasks.remove(foundTask);
    }

    public ArrayList<Task> getTasksByStatus(String status) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getStatus().equals(status)) {
                foundTasks.add(t);
            }
        }
        if (foundTasks.isEmpty()) {
            throw new IllegalStateException ("There isn't any existing task with the status \"" + status + "\"...");
        }
        return foundTasks;
    }
}