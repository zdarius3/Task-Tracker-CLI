package logic;

import java.time.LocalDateTime;

public class Task {
    private static int idCounter = 1;

    private int id;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task(String description, LocalDateTime createdAt) {
        setId(idCounter++);
        setDescription(description);
        setStatus();
        setCreatedAt(createdAt);
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void update(String description, LocalDateTime updatedAt) {
        setDescription(description);
        this.updatedAt = updatedAt;
    }

    public void changeStatus(String newStatus) {
        if (newStatus.equals("todo")) {
            this.status = "todo";
        }
        else if (newStatus.equals("in-progress")) {
            this.status = "in-progress";
        }
        else if (newStatus.equals("done")) {
            this.status = "done";
        }
    }

    @Override
    public String toString() {
        String res = "id: " + id + " - description: " + description.toLowerCase() +
        " - status: " + status.toLowerCase() + " - created at: " + createdAt;
        if (updatedAt != null) {
            res = res.concat(" - updated at: " + updatedAt);
        } 
        return res;
    }
}
