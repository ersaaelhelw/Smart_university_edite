package com.example.momen.smart_university.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.momen.smart_university.R;

public class QuizOrAttendeActivity extends AppCompatActivity {

    Button quize,attend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_or_attende);
        quize = findViewById(R.id.quize_btn);
        attend = findViewById(R.id.attendance_btn);

        final String type = getIntent().getStringExtra("type");
        final String docName = getIntent().getStringExtra("docName");
        final String subName = getIntent().getStringExtra("subName");
        quize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals("stu")){
                    Intent intent = new Intent(QuizOrAttendeActivity.this,Student_absent.class);
                    Toast.makeText(QuizOrAttendeActivity.this, subName, Toast.LENGTH_SHORT).show();
                    intent.putExtra("docName",docName);
                    intent.putExtra("subName",subName);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(QuizOrAttendeActivity.this,AllowAttendance.class);
                    Toast.makeText(QuizOrAttendeActivity.this, subName, Toast.LENGTH_SHORT).show();
                    intent.putExtra("docName",docName);
                    intent.putExtra("subName",subName);
                    startActivity(intent);
                }
            }
        });

        quize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals("stu")){
                    Intent intent = new Intent(QuizOrAttendeActivity.this,Answer_question.class);
                    intent.putExtra("docName",docName);
                    intent.putExtra("subName",subName);
                    Toast.makeText(QuizOrAttendeActivity.this, docName, Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(QuizOrAttendeActivity.this,Quiz.class);
                    intent.putExtra("docName",docName);
                    intent.putExtra("subName",subName);
                    startActivity(intent);
                }
            }
        });

    }
}
