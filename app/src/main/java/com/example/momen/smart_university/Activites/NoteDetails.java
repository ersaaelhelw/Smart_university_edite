package com.example.momen.smart_university.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.momen.smart_university.R;

public class NoteDetails extends AppCompatActivity {

    TextView doc,body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        doc = findViewById(R.id.noteDoctor);
        body = findViewById(R.id.body);

        doc.setText(getIntent().getStringExtra("doc"));
        body.setText(getIntent().getStringExtra("body"));
    }
}
