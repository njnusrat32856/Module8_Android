package com.nusrat.mytodolistapp;

public class Task {

    private int id;
    private String task;
    private boolean isCompleted;

    public Task(int id, String task, boolean isCompleted) {
        this.id = id;
        this.task = task;
        this.isCompleted = isCompleted;
    }

    public Task() {

    }

    public int getId() {
        return id;
    }
    public String getTask() {
        return task;
    }
    public boolean isCompleted() {
        return isCompleted;
    }
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
