package com.project.androidlivedatasample.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.project.androidlivedatasample.model.entities.User;

import java.util.List;

/**
 * @author Sooraj Soman on 11/14/2018
 */
@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("DELETE FROM User")
    void deleteAll();

    @Delete
    void delete(User user);

    @Query("SELECT * FROM User WHERE email = :email and password = :password")
    User checkUser(String email,String password);

}
