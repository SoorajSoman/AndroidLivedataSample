package com.project.androidlivedatasample.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.project.androidlivedatasample.model.dao.TodoDao;
import com.project.androidlivedatasample.model.database.RoomDb;
import com.project.androidlivedatasample.model.entities.Todos;

import java.util.List;

/**
 * @author Sooraj Soman on 11/14/2018
 */
public class TodoRepository {
    private TodoDao todoDao;
    private LiveData<List<Todos>> mTodoList;

   public TodoRepository(Application application) {
        RoomDb db = RoomDb.getDatabase(application);
        todoDao = db.todoDao();
        mTodoList = todoDao.getAllTodos();
    }

    public  LiveData<List<Todos>> getAllTodos() {
        return mTodoList;
    }

    public void insert(Todos todo) {
        new insertAsyncTask(todoDao).execute(todo);
    }

    public void delete(Todos todo) {
        new deleteAsyncTask(todoDao).execute(todo);
    }

    public void deleteAll() {
        new deleteAllAsyncTask(todoDao).execute();
    }

    private static class insertAsyncTask extends AsyncTask<Todos, Void, Void> {

        private TodoDao mAsyncTaskDao;

        insertAsyncTask(TodoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Todos... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }


    private static class deleteAsyncTask extends AsyncTask<Todos, Void, Void> {

        private TodoDao mAsyncTaskDao;

        deleteAsyncTask(TodoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Todos... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private TodoDao mAsyncTaskDao;

        deleteAllAsyncTask(TodoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
}
