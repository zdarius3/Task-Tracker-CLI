package logic;

import java.time.LocalDateTime;


public class JsonManager {
    public static Task createTaskFromJson(String json) {
        //delete braces and quotation marks
        json = json.replace("{", "")
                .replace("}", "")
                .replace("\"", "");
        //separate each attribute, all of them are separated by commas
        String[] jsonSeparated = json.split(",");

        //assign the value at the right of the ":" for each attribute
        String id = jsonSeparated[0].split(":")[1].strip();
        String description = jsonSeparated[1].split(":")[1].strip();
        String status = jsonSeparated[2].split(":")[1].strip();
        String createdAtStr = jsonSeparated[3].split("[a-z]:")[1].strip();
        String updatedAtStr = jsonSeparated[4].split("[a-z]:")[1].strip();

        //create the task and assign its attributes
        Task task = new Task(description);
        task.setId(Integer.parseInt(id));
        task.changeStatus(status);
        task.setCreatedAt(LocalDateTime.parse(createdAtStr, Task.formatter));
        task.setUpdatedAt(LocalDateTime.parse(updatedAtStr, Task.formatter));

        //update the idCounter
        if (Integer.parseInt(id) > Task.idCounter) {
            Task.idCounter = Integer.parseInt(id);
        }

        return task;
    }

    public static String exportTaskToJson(Task task) {
        return "{\"id\":\"" + task.getId() + 
        "\", \"description\": \"" + task.getDescription().strip() + 
        "\", \"status\":\"" + task.getStatus() + 
        "\", \"createdAt\":\"" + task.getCreatedAt().format(Task.formatter) + 
        "\", \"updatedAt\":\"" + task.getUpdatedAt().format(Task.formatter) + "\"}";
    }

}
