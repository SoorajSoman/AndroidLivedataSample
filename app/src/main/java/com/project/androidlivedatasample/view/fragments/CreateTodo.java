package com.project.androidlivedatasample.view.fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.project.androidlivedatasample.R;
import com.project.androidlivedatasample.model.entities.Todos;
import com.project.androidlivedatasample.view.TodoListActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import petrov.kristiyan.colorpicker.ColorPicker;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateTodo extends Fragment {


    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.desc)
    EditText desc;
    @BindView(R.id.date)
    EditText date;
    @BindView(R.id.color)
    ImageView colorView;
    @BindView(R.id.choose)
    Button choose;
    @BindView(R.id.save)
    Button save;
    Unbinder unbinder;
    private int mYear, mMonth, mDay;
    private int colorv;

    public CreateTodo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_todo, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.choose, R.id.save, R.id.date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.date:
                showDatepicker();

                break;
            case R.id.choose:
                chooseColor();

                break;
            case R.id.save:
                saveData();
                getActivity().getSupportFragmentManager().popBackStackImmediate();
                break;
        }
    }

    private void showDatepicker() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void saveData() {
        Todos todos = new Todos();
        if (!TextUtils.isEmpty(name.getText().toString())) {
            todos.setTodoName(name.getText().toString());
        }
        if (!TextUtils.isEmpty(desc.getText().toString())) {
            todos.setTodoDescription(desc.getText().toString());
        }
        if (!TextUtils.isEmpty(date.getText().toString())) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date1 = simpleDateFormat.parse(date.getText().toString());
                todos.setTodoDate(date1);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        todos.setActive(1);
        todos.setColor(colorv);
        ((TodoListActivity) getActivity()).todoViewModel.insert(todos);


    }

    private void chooseColor() {
        ColorPicker colorPicker = new ColorPicker(getActivity());
        colorPicker.show();
        colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
            @Override
            public void onChooseColor(int position, int color) {
                colorView.setBackgroundColor(color);
                colorv = color;
            }

            @Override
            public void onCancel() {
            }
        });
    }
}
