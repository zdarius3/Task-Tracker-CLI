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

    private static void startCommandLine() {
        System.out.println("Welcome to Task Manager, where you can manage all your tasks.");
        System.out.println("Type \"help\" to show all commands.");
        askInput();
    }

    @SuppressWarnings("resource")
    private static void askInput() {
        String input;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("\u001B[32m" + "task-cli > " + "\u001B[0m");
            input = scanner.nextLine();

            // this splits the input in 3 parts:
            // command - task id (if exists) - description (if exists)
            Pattern pattern = Pattern.compile("^(\\w+(?:-\\w+)*)" + // first word (add, mark-done, update, etc)
                    "(?:\\s+(\\w+(?:-\\w+)*))?" + // second word or id (update 1, list todo, etc)
                    "(?:\\s+\"(.+?)\")?" + // description between quotation marks
                    "$"); // forces the coincidence to be on the entire line

            Matcher matcher = pattern.matcher(input);

            int id = -1;

            if (matcher.matches()) {
                String command = matcher.group(1);
                String description = "\"" + matcher.group(3) + "\"";
                String idStr = matcher.group(2);
                if (idStr != null && idStr.matches("^[^\\p{L}]*$")) {
                    id = Integer.parseInt(idStr);
                }

                try {
                    switch (command) {
                        case "help" -> showCommands();
                        case "add" -> add(description);
                        case "update" -> update(id, description);
                        case "mark-in-progress", "mark-done" ->
                            updateStatus(id, command);
                        case "delete" -> delete(id);
                        case "list" -> showList(idStr);
                        case "clear" -> clearTerminal();
                        case "exit" -> System.out.println("See you later!");
                        default -> System.out.println("Invalid command...");
                    }
                    taskManager.saveTasks();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Invalid format...");
            }
        } while (!input.equals("exit"));
    }

    private static void showCommands() {
        System.out.println("""
                ---------------
                commands:
                ---------------
                add "(task description)" -> add a new task.
                update (task id) "(new description)" -> updates an existing task.
                mark-in-progress (task id) -> marks a specific task as in-progress.
                mark-done (task id) -> marks a specific task as done.
                delete (task id) -> deletes a specific task.
                clear -> cleans the terminal.
                exit -> close the program.
                ---------------
                """);
    }

    private static void add(String description) {
        taskManager.addTask(description);
        System.out.println("Task added succesfully.");
    }

    private static void update(int taskId, String description) {
        taskManager.updateTask(taskId, description);
        System.out.println("Task updated succesfully.");
    }

    private static void updateStatus(int taskId, String newStatus) {
        taskManager.updateTaskStatus(taskId, newStatus);
        System.out.println("Task status updated succesfully.");
    }

    private static void delete(int taskId) {
        taskManager.deleteTask(taskId);
        System.out.println("Task deleted succesfully.");
    }

    private static void showList(String filter) {
        if (filter != null && (filter.equals("in-progress") ||
                filter.equals("done") || filter.equals("todo"))) {
            System.out.println("Showing tasks with the status (" + filter + "):");
            printTaskList(taskManager.getTasksByStatus(filter));
        } else {
            System.out.println("Showing all the tasks:");
            printTaskList(taskManager.getAllTasks());
        }

    }

    private static void printTaskList(ArrayList<Task> tasks) {
        for (Task t : tasks) {
            System.out.println(t + "\n");
        }
    }

    private static void clearTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
