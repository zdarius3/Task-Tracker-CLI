package tests;

import logic.*;

public class TaskManagerTest {
    public static void main(String[] args) {
        TaskManager tm = new TaskManager();

        System.out.println("Adding some test tasks...");
        tm.addTask("testing task 1");
        tm.addTask("testing task 2");
        tm.addTask("testing task 3");
        showTasks(tm);

        System.out.println("Editing two of the test tasks...");
        tm.updateTask(1, "updating task 1");
        tm.updateTask(3, "updating task 2");
        showTasks(tm);

        System.out.println("Changing some tasks' statuses...");
        tm.updateTaskStatus(1, "in-progress");
        tm.updateTaskStatus(3, "in-progress");
        showTasks(tm);

        System.out.println("Deleting the first 2 tasks...");
        tm.deleteTask(2);
        tm.deleteTask(1);
        showTasks(tm);

        System.out.println("Adding 2 new tasks...");
        tm.addTask("testing tasks 4");
        tm.addTask("testing tasks 5");
        showTasks(tm);

        System.out.println("Editing one of the 2 new tasks...");
        tm.updateTask(4, "updating task 4");
        tm.updateTaskStatus(4, "done");
        showTasks(tm);

        System.out.println("Showing tasks with 'in-progress' status...");
        for (Task t: tm.getTasksByStatus("in-progress")) {
            System.out.println(t + "\n");
        }
    }

    private static void showTasks(TaskManager tm) {
        for (Task t : tm.getAllTasks()) {
            System.out.println(t + "\n");
        }
        System.out.println("----------------------");
    }
}
