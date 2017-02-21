package com.humboshot.todowithsqlite.Model;

import java.util.Date;

/**
 * Created by Christian on 16-02-2017.
 */

public class TodoTask {
    private int id;
    private String todoName;
    private Date timestamp;

    public TodoTask(String todoName) {
        super();
        this.todoName = todoName;
    }

    public TodoTask(){
        this.todoName = todoName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTodoName() {
        return todoName;
    }

    public void setTodoName(String todoName) {
        todoName = todoName;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString(){
        return this.getTodoName();
    }
}
