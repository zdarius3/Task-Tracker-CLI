package cli;

import java.util.Scanner;

public class CommandLine {
    public static void main(String[] args) {
        startCommandLine();
    }
    public static void startCommandLine() {
        System.out.println("---------------\n" +
        "commands: \n" + 
        "---------------\n" +
        "add (task description) -> add a new task.\n" + 
        "update (task id) (new description) -> updates an existing task.\n" + 
        "mark-in-progress (task id) -> marks a specific task as in-progress.\n" + 
        "mark-done (task id) -> marks a specific task as done.\n" + 
        "delete (task id) -> deletes a specific task.\n" + 
        "clear -> cleans the terminal.\n" + 
        "---------------\n");
        askInput();
    }

    private static void askInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("task-cli ");
        String command = scanner.nextLine();

    }

    private boolean validateCommand(String command) {
        boolean res = false;
        if (command.contains("add")) {
           // propertites = command.split(" ");

        }
        else {
             
        }
        return res;
    }
}
