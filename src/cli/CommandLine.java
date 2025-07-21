package cli;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import logic.Task;
import logic.TaskManager;

public class CommandLine {
    private static final TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        startCommandLine();
    }

    public static void startCommandLine() {
        System.out.println("Welcome to Task Manager, where you can manage all your tasks.");
        System.out.println("Type \"help\" to show all commands.");
        askInput();
    }

    private static void askInput() {
        String input;
        do {
            System.out.print("\u001B[32m" + "task-cli > " + "\u001B[0m");
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();

            // this splits the input in 3 parts:
            // command - task id (if exists) - description (if exists)
            Pattern pattern = Pattern.compile("^(\\w+(?:-\\w+)*)(?:\\s+" +
                    "(\\w+(?:-\\w+)*))?(?:\\s+\"(.+?)\")?$");
            Matcher matcher = pattern.matcher(input);

            int id = -1;

            if (matcher.matches()) {
                String command = matcher.group(1);
                String description = "\"" + matcher.group(3) + "\"";
                String idStr = matcher.group(2);
                if (idStr != null && idStr.matches("^[^\\p{L}]*$")) {
                    id = Integer.parseInt(idStr);
                }

                switch (command) {
                    case "help":
                        showCommands();
                        break;
                    case "add":
                        taskManager.addTask(description);
                        break;
                    case "update":
                        taskManager.updateTask(id, description);
                        break;
                    case "mark-in-progress":
                    case "mark-done":
                        taskManager.updateTaskStatus(id, command);
                        break;
                    case "delete":
                        taskManager.deleteTask(id);
                        break;
                    case "list":
                        if (idStr != null && (idStr.equals("in-progress") ||
                                idStr.equals("done") || idStr.equals("todo"))) {
                            printTaskList(taskManager.getTasksByStatus(idStr));
                        }
                        else {
                            printTaskList(taskManager.getAllTasks());
                        }   break;
                    case "clear":
                        System.out.print("\033[H\033[2J");
                        System.out.flush(); //clean the terminal
                        break;
                    case "exit":
                        System.out.println("See you later!");
                        break;
                    default:
                        System.out.println("Invalid command...");
                        break;
                }
            } else {
                System.out.println("Invalid format...");
            }
        } while (!input.equals("exit"));

    }

    public static void showCommands() {
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
    }

    public static void printTaskList(ArrayList<Task> tasks) {
        for (Task t : tasks) {
            System.out.println(t + "\n");
        }
    }
}
