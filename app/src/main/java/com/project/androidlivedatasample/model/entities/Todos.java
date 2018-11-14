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
    public String color;
    @ColumnInfo(name = "active")
    public int active;




}
