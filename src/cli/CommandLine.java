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
        while (true) {
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
            askInput();
        }
    }

    private static void askInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("task-cli ");
        String input = scanner.nextLine();
        validateInput(input);
        for (Task t : taskManager.getAllTasks()) {
            System.out.println(t);
        }
    }

    private static boolean validateInput(String input) {
        boolean res = false;
        Pattern pattern = Pattern.compile("^(\\w+)\\s+\"(.+?)\"$");
        Matcher matcher = pattern.matcher(input);

        String command = "";
        if (matcher.matches()) {
            command = matcher.group(1);
        }

        if (command.equals("add")) {
            String description = "\"" + matcher.group(2) + "\"";
            taskManager.addTask(description);
        } else if (input.contains("update")) {
            /*int id = Integer.parseInt(matcher.group(2));
            String newDescription = matcher.group(3);
            taskManager.updateTask(id, newDescription);*/
        } else {
            System.out.println("Invalid command...");
        }

        return res;
    }
}
