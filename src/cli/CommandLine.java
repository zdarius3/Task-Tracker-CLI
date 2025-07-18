package cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import logic.Task;
import logic.TaskManager;

public class CommandLine {
    private static TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        startCommandLine();
    }

    public static void startCommandLine() {
        System.out.println("---------------\n" +
                "commands: \n" +
                "---------------\n" +
                "add \"(task description)\" -> add a new task.\n" +
                "update (task id) \"(new description)\" -> updates an existing task.\n" +
                "mark-in-progress (task id) -> marks a specific task as in-progress.\n" +
                "mark-done (task id) -> marks a specific task as done.\n" +
                "delete (task id) -> deletes a specific task.\n" +
                "clear -> cleans the terminal.\n" +
                "exit -> close the program.\n" +
                "---------------\n");
        while (true) {
            askInput();
        }
    }

    private static void askInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("task-cli > ");
        String input = scanner.nextLine();
        validateInput(input);
    }

    private static boolean validateInput(String input) {
        boolean res = false;
        // this splits the input in 3 parts:
        // command - task id (if exists) - description (if exists)
        Pattern pattern = Pattern.compile("^(\\w+(?:-\\w+)*)(?:\\s+(\\w+(?:-\\w+)*))?(?:\\s+\"(.+?)\")?$");
        Matcher matcher = pattern.matcher(input);

        String command = "";
        String description = "";
        String idStr = "";
        int id = -1;

        if (matcher.matches()) {
            command = matcher.group(1);
            description = "\"" + matcher.group(3) + "\"";
            idStr = matcher.group(2);
            if (idStr != null && idStr.matches("^[^\\p{L}]*$")) {
                id = Integer.parseInt(idStr);
            }

            if (command.equals("add")) {
                taskManager.addTask(description);
            }

            else if (command.equals("update")) {
                taskManager.updateTask(id, description);
            }

            else if (command.equals("mark-in-progress") || command.equals("mark-done")) {
                taskManager.updateTaskStatus(id, command);
            }

            else if (command.equals("delete")) {
                taskManager.deleteTask(id);
            }

            else if (command.equals("list")) {
                if (idStr != null && (idStr.equals("in-progress") ||
                idStr.equals("done") || idStr.equals("todo"))) {
                    printTaskList(taskManager.getTasksByStatus(idStr));
                }
                else {
                    printTaskList(taskManager.getAllTasks());
                }
            }
            
            else {
                System.out.println("Invalid command...");
            }
        } else {
            System.out.println("Invalid format...");
        }
        return res;
    }

    public static void printTaskList(ArrayList<Task> tasks) {
        for (Task t: tasks) {
            System.out.println(t + "\n");
        }
    }
}
