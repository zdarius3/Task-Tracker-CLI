package logic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private int id;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static int idCounter = 0;
    public static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public Task(String description) {
        setId(++idCounter);
        setDescription(description);
        setStatus();
        setCreatedAt(LocalDateTime.now());
        setUpdatedAt(LocalDateTime.now());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.replaceAll(" ", "").equals("")) {
            throw new IllegalArgumentException("Description can't be empty...");
        }
        this.description = description.replaceAll("\"", "");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus() {
        this.status = "todo";
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime dateTime) {
        this.createdAt = dateTime;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime dateTime) {
        this.updatedAt = dateTime;
    }

    public void update(String description) {
        setDescription(description);
        this.updatedAt = LocalDateTime.now();
    }

    public void changeStatus(String command) {
        if (command.equals("mark-in-progress")) {
            this.status = "in-progress";
        }
        else if (command.equals("mark-done")) {
            this.status = "done";
        }
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        String res = "id: " + id + "\ndescription: " + description.toLowerCase() +
        "\nstatus: " + status.toLowerCase() + "\ncreated at: " + createdAt;
        if (updatedAt != null) {
            res = res.concat("\nupdated at: " + updatedAt);
        } 
        return res;
    }
}