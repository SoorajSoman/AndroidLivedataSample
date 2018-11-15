package com.project.androidlivedatasample.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.project.androidlivedatasample.model.entities.Todos;

import java.util.List;


/**
 * @author Sooraj Soman on 11/14/2018
 */
@Dao
public interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Todos t);

    @Query("DELETE FROM Todos")
    void deleteAll();

    @Query("SELECT * from Todos ORDER BY todoDate ASC")
    LiveData<List<Todos>> getAllTodos();

    @Delete
    void delete(Todos todos);
}
