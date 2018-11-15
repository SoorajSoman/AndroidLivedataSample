package com.project.androidlivedatasample.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.project.androidlivedatasample.view.fragments.CreateTodo;
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
    private TodoAdapter todoAdapter;

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
         todoAdapter = new TodoAdapter(todosList, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(todoAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        todoViewModel.getTodoList().observe(this, new Observer<List<Todos>>() {
            @Override
            public void onChanged(@Nullable List<Todos> todos) {

                todosList.clear();
                todosList.addAll(todos);
                todoAdapter.setWords(todosList);

            }
        });
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition(); //get position which is swipe
            if (direction == ItemTouchHelper.LEFT) {    //if swipe left
                AlertDialog.Builder builder = new AlertDialog.Builder(TodoListActivity.this); //alert for confirm to delete
                builder.setMessage("Are you sure to delete?");    //set message
                builder.setPositiveButton("REMOVE", new DialogInterface.OnClickListener() { //when click on DELETE
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        todoAdapter.notifyItemRemoved(position);    //item removed from recylcerview
                        todoViewModel.delete(todosList.get(position));
                        todosList.remove(position);  //then remove item
                        return;
                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {  //not removing items if cancel is done
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        todoAdapter.notifyItemRemoved(position + 1);    //notifies the RecyclerView Adapter that data in adapter has been removed at a particular position.
                        todoAdapter.notifyItemRangeChanged(position, todoAdapter.getItemCount());   //notifies the RecyclerView Adapter that positions of element in adapter has been changed from position(removed element index to end of list), please update it.
                        return;
                    }
                }).show();  //show alert dialog
            }
        }
    };




}
