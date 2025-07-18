package logic;

import java.time.LocalDate;
import java.util.jar.Attributes;

public class Task {
    private static int idCounter = 1;

    private int id;
    private String description;
    private String status;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public Task(String description, LocalDate createdAt) {
        setId(idCounter++);
        setDescription(description);
        setStatus();
        setCreatedAt(createdAt);
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void update(String description, LocalDate updatedAt) {
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
        return "id: " + id + " - description: " + description.toLowerCase() +
        " - status: " + status.toLowerCase() + " - created at: " + createdAt;
    }
}
