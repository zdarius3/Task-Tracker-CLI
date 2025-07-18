package logic;

import java.time.LocalDateTime;

public class Task {
    private static int idCounter = 1;

    private int id;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task(String description) {
        setId(idCounter++);
        setDescription(description);
        setStatus();
        setCreatedAt();
        updatedAt = null;
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
        this.description = description;
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

    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
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
