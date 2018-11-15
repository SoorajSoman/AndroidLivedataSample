package com.project.androidlivedatasample.model.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * @author Sooraj Soman on 11/14/2018
 */
@Entity
public class Todos {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "todoName")
    public String todoName;
    @ColumnInfo(name = "todoDesc")
    public String todoDescription;
    @ColumnInfo(name = "todoDate")
    public Date todoDate;
    @ColumnInfo(name = "color")
    public int color;
    @ColumnInfo(name = "active")
    public int active;

    public int getId() {
        return id;
    }

    public String getTodoName() {
        return todoName;
    }

    public String getTodoDescription() {
        return todoDescription;
    }

    public Date getTodoDate() {
        return todoDate;
    }

    public int getColor() {
        return color;
    }

    public int getActive() {
        return active;
    }

    public void setTodoName(String todoName) {
        this.todoName = todoName;
    }

    public void setTodoDescription(String todoDescription) {
        this.todoDescription = todoDescription;
    }

    public void setTodoDate(Date todoDate) {
        this.todoDate = todoDate;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
