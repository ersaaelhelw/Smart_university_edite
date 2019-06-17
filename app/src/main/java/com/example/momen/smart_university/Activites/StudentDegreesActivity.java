package com.example.momen.smart_university.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.momen.smart_university.Adapter.DegreesAdapter;
import com.example.momen.smart_university.R;
import com.example.momen.smart_university.firebase.Student.Students;
import com.example.momen.smart_university.firebase.Student.Subjects;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

public class StudentDegreesActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<Subjects> subjects=new ArrayList<>();
    DegreesAdapter degreesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_degrees);

        firebaseDatabase =FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().
                child("Students").
                child(StudentName.year).
                child(StudentName.name).
                child("subjects");
        final RecyclerView recyclerStudent_degree = findViewById(R.id.rv_degree);
        recyclerStudent_degree.setLayoutManager(new LinearLayoutManager(this));
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot Snapshot : dataSnapshot.getChildren()) {
                    subjects.add(Snapshot.getValue(Subjects.class));
                }
                degreesAdapter=new DegreesAdapter(subjects,getApplicationContext());
                recyclerStudent_degree.setAdapter(degreesAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}