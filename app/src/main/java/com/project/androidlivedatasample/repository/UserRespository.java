package com.project.androidlivedatasample.repository;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.project.androidlivedatasample.model.dao.UserDao;
import com.project.androidlivedatasample.model.database.RoomDb;
import com.project.androidlivedatasample.model.entities.User;

/**
 * @author Sooraj Soman on 11/14/2018
 */
public class UserRespository extends AndroidViewModel {

    public User user;
    public UserDao userDao;

    public UserRespository(@NonNull Application application) {
        super(application);
        RoomDb roomDb = RoomDb.getDatabase(application);
        userDao = roomDb.userDao();

    }

    public LiveData<User> checkUser(String userName, String password) {
        return userDao.checkUser(userName, password);
    }

    public void insertUser(User user) {
        userDao.insert(user);
    }

    public void deleteUser(User user) {
        userDao.delete(user);
    }


}
