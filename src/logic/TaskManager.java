package logic;

import java.io.File;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;
    private File json;

    public TaskManager() {
        tasks = new ArrayList<>();
        json = new File("data/data.json");
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    private Task searchTask(int id) {
        int i = 0;
        boolean found = false;
        Task res = null;

        while(i < tasks.size() && !found) {
            if(tasks.get(i).getId() == id) {
                res = tasks.get(i);
                found = true;
            }
            i++;
        }

        return res;
    }

    public void addTask(String description) {
        tasks.add(new Task(description));
    }

    public void updateTask(int taskId, String newDesc) {
        searchTask(taskId).update(newDesc);
    }

    public void updateTaskStatus(int taskId, String newStatus) {
        searchTask(taskId).changeStatus(newStatus);
    }
}
