package tests;

import logic.*;

public class TaskManagerTest {
    public static void main(String[] args) {
        TaskManager tm = new TaskManager();
        tm.addTask("testing task 1");
        tm.addTask("testing task 2");
        tm.addTask("testing task 3");

        for (Task t: tm.getTasks()) {
            System.out.println(t);
        }
    }
}
