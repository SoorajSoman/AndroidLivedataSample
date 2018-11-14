package com.project.androidlivedatasample.model.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.project.androidlivedatasample.model.converters.DateConverter;
import com.project.androidlivedatasample.model.dao.TodoDao;
import com.project.androidlivedatasample.model.dao.UserDao;
import com.project.androidlivedatasample.model.entities.Todos;
import com.project.androidlivedatasample.model.entities.User;

/**
 * @author Sooraj Soman on 11/14/2018
 */

@Database(entities = {User.class, Todos.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class RoomDb extends RoomDatabase{
    public abstract UserDao userDao();
    public abstract TodoDao todoDao();

    private static volatile RoomDb INSTANCE;

   public static RoomDb getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomDb.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDb.class, "word_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
