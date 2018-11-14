package com.project.androidlivedatasample.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.project.androidlivedatasample.model.entities.Todos;
import com.project.androidlivedatasample.repository.TodoRepository;

import java.util.List;

/**
 * @author Sooraj Soman on 11/14/2018
 */
public class TodoViewModel extends AndroidViewModel {

    TodoRepository todoRepository;
    LiveData<List<Todos>> todoList;

    public TodoViewModel(@NonNull Application application) {
        super(application);
        todoRepository = new TodoRepository(application);
        todoList = todoRepository.getAllTodos();

    }

    public LiveData<List<Todos>> getTodoList() {
        return todoList;
    }

    public void insert(Todos todos) {
        todoRepository.insert(todos);
    }

    public void delete(Todos todo) {
        todoRepository.delete(todo);
    }
    public void deleteAll() {
        todoRepository.deleteAll();


    }
}
