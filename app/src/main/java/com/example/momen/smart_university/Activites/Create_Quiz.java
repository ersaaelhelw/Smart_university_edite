package com.example.momen.smart_university.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.momen.smart_university.R;

public class Create_Quiz extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Create Quiz");
        setContentView(R.layout.activity_create__quiz);
        editText  = findViewById(R.id.quiz_name);
        Button button=findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Create_Quiz.this,Create_Question.class);
                if(!editText.getText().equals(null)||!editText.getText().equals("")) {
                    intent.putExtra("QuizName", editText.getText().toString());
                    intent.putExtra("subject",getIntent().getStringExtra("subName"));
                    startActivity(intent);
                }
                else Toast.makeText(Create_Quiz.this, "Enter the name of Quize", Toast.LENGTH_SHORT).show();
            }
        });
    }
}