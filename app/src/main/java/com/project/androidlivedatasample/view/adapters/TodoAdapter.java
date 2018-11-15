package com.project.androidlivedatasample.view.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.androidlivedatasample.R;
import com.project.androidlivedatasample.model.entities.Todos;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Sooraj Soman on 11/15/2018
 */
public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private final Context context;
    private List<Todos> items;

    public TodoAdapter(List<Todos> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new TodoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        Todos item = items.get(position);
        holder.set(item);
    }

    public void setWords(List<Todos> todo) {
        items = todo;
        this.notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.description)
        TextView description;

        public TodoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void set(Todos item) {
            imageView.setBackgroundColor(item.getColor());
            description.setText(item.getTodoDescription());
            title.setText(item.getTodoName());
            //UI setting code
        }
    }
}