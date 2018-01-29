package com.example.edgar.tasksapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        Intent intent = getIntent();
        final int position = intent.getIntExtra("position", 0);
        final Task currentTask = MainActivity.myTasks.get(position);


        TextView titleTextView = (TextView) findViewById(R.id.titleAct);
        TextView descriptionTextView = (TextView) findViewById(R.id.descriptionAct);
        final Spinner status = (Spinner) findViewById(R.id.statusList);
        Button saveTask = (Button) findViewById(R.id.save);

        titleTextView.setText(currentTask.getTitle());
        descriptionTextView.setText(currentTask.getDescription());


        saveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentStatus = status.getSelectedItem().toString();
                currentTask.setStatus(currentStatus);


                Intent addTaskIntent = new Intent(TaskActivity.this, MainActivity.class);
                startActivity(addTaskIntent);
            }
        });
    }
}
