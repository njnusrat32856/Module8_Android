package com.nusrat.mytodolistapp;

import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TaskAdapter adapter;
    List<Task> taskList;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        dbHelper = new DBHelper(this);

        taskList = dbHelper.getAllTasks();

        adapter = new TaskAdapter(taskList, new TaskAdapter.OnTaskClickListener() {
            @Override
            public void onEditClick(Task task) {
                showEditDialog(task);
            }

            @Override
            public void onDeleteClick(Task task) {
                dbHelper.deleteTask(task.getId());
                refreshTaskList();
            }

            @Override
            public void onCompleteClick(Task task) {
                dbHelper.completeTask(task.getId());
                refreshTaskList();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btn_add_task).setOnClickListener(v -> showAddTaskDialog());
    }

    private void refreshTaskList() {
        taskList.clear();
        taskList.addAll(dbHelper.getAllTasks());
        adapter.notifyDataSetChanged();
    }

    private void showAddTaskDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Task");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String taskText = input.getText().toString();
            if (!taskText.isEmpty()) {
                dbHelper.addTask(taskText);
                refreshTaskList();
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void showEditDialog(final Task task) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Task");

        final EditText input = new EditText(this);
        input.setText(task.getTask());
        builder.setView(input);

        builder.setPositiveButton("Save", (dialog, which) -> {
            String taskText = input.getText().toString();
            if (!taskText.isEmpty()) {
                dbHelper.updateTask(task.getId(), taskText);
                refreshTaskList();
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }
}
