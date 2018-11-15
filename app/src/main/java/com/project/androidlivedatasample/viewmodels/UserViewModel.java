package com.project.androidlivedatasample.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.project.androidlivedatasample.model.dao.UserDao;
import com.project.androidlivedatasample.model.database.RoomDb;
import com.project.androidlivedatasample.model.entities.Todos;
import com.project.androidlivedatasample.model.entities.User;
import com.project.androidlivedatasample.repository.UserRespository;

/**
 * @author Sooraj Soman on 11/15/2018
 */
public class UserViewModel extends AndroidViewModel {

    UserDao userDao;

    UserRespository userRespository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRespository = new UserRespository(getApplication());
    }

    public LiveData<User> getLiveData(String userEmail, String password) {
        return userDao.checkUser(userEmail, password);
    }

    public void insert(User user) {
        userRespository.insertUser(user);
    }

    public void delete(User user) {
        userRespository.deleteUser(user);
    }


}
