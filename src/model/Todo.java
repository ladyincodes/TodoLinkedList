package model;

import java.io.Serializable;

public class Todo implements Serializable {
    String title;
    boolean isComplete;

    public Todo(String title) {
        this.title = title;
        this.isComplete = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    @Override
    public String toString() {
        return "title: '" + title + '\'' +
                ", done: " + isComplete;
    }
}
