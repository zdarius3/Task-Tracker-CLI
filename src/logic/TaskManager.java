package logic;

import java.io.File;
import java.time.LocalDate;
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

    public void addTask(String description) {
        tasks.add(new Task(description, LocalDate.now()));
    }

    
}
