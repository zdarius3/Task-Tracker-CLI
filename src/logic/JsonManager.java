package logic;

import java.time.LocalDateTime;


public class JsonManager {
    public static Task createTaskFromJson(String json) {
        json = json.replace("{", "")
                .replace("}", "")
                .replace("\"", "");
        String[] json1 = json.split(",");

        String id = json1[0].split(":")[1].strip();
        String description = json1[1].split(":")[1].strip();
        String status = json1[2].split(":")[1].strip();
        String createdAtStr = json1[3].split("[a-z]:")[1].strip();
        String updatedAtStr = json1[4].split("[a-z]:")[1].strip();

        Task task = new Task(description);
        task.setId(Integer.parseInt(id));
        task.changeStatus(status);
        task.setCreatedAt(LocalDateTime.parse(createdAtStr, Task.formatter));
        task.setUpdatedAt(LocalDateTime.parse(updatedAtStr, Task.formatter));

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
