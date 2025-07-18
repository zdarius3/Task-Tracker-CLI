package logic;

import java.io.File;
import java.time.LocalDateTime;
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
        Task res = null;
        for (Task t: tasks) {
            if (t.getId() == id) {
                res = t;
                break;
            }
        }
        return res;
    }

    public void addTask(String description) {
        tasks.add(new Task(description, LocalDateTime.now()));
    }

    public void updateTask(int taskId, String newDesc) {
        searchTask(taskId).update(newDesc, LocalDateTime.now());
    }
}
