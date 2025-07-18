package tests;

import logic.*;

public class TaskManagerTest {
    public static void main(String[] args) {
        TaskManager tm = new TaskManager();
        // adding some test tasks
        tm.addTask("testing task 1");
        tm.addTask("testing task 2");
        tm.addTask("testing task 3");

        for (Task t : tm.getTasks()) {
            System.out.println(t);
        }

        // editing two of the test tasks
        tm.updateTask(1, "updating task 1");
        tm.updateTask(3, "updating task 2");

        for (Task t : tm.getTasks()) {
            System.out.println(t);
        }

    }
}
