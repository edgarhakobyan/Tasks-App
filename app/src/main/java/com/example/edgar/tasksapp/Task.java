package com.example.edgar.tasksapp;

/**
 * Created by edgar on 1/28/18.
 */

public class Task {
    private String title;
    private String description;
    private String status;
    private String statusColor;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = "To Do";
        this.statusColor = "#ff0099cc";
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(String status) {
        this.status = status;
        switch (status) {
            case "In Progress":
                setStatusColor("#ffff8800");
                break;
            case "Done":
                setStatusColor("#ff669900");
                break;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatusColor(String statusColor) {
        this.statusColor = statusColor;
    }

    public String getStatusColor() {
        return statusColor;
    }

}
