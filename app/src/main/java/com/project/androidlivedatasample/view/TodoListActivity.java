package com.project.androidlivedatasample.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.project.androidlivedatasample.CreateTodo;
import com.project.androidlivedatasample.R;
import com.project.androidlivedatasample.model.entities.Todos;
import com.project.androidlivedatasample.view.adapters.TodoAdapter;
import com.project.androidlivedatasample.viewmodels.TodoViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodoListActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    List<Todos>todosList;

   public TodoViewModel todoViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        todosList=new ArrayList<>();
       todoViewModel= ViewModelProviders.of(this).get(TodoViewModel.class);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                CreateTodo createTodo=new CreateTodo();
                fragmentTransaction.replace(R.id.parentView,createTodo).addToBackStack(null).commit();

            }
        });
        populateRecyclerView();
    }

    private void populateRecyclerView() {
        final TodoAdapter todoAdapter=new TodoAdapter(todosList,this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(todoAdapter);
        todoViewModel.getTodoList().observe(this, new Observer<List<Todos>>() {
            @Override
            public void onChanged(@Nullable List<Todos> todos) {

                todosList.clear();
                todosList.addAll(todos);
                todoAdapter.setWords(todosList);

            }
        });
    }


}
