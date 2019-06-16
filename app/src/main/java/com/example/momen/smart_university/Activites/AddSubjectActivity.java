package com.example.momen.smart_university.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.momen.smart_university.Adapter.SubjectAdapter;
import com.example.momen.smart_university.R;
import com.example.momen.smart_university.firebase.Student.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddSubjectActivity extends AppCompatActivity{

    public static List<Subjects> subjectsAdded;

    RecyclerView subRV;
    List<Subjects> subjects;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);
        subRV = findViewById(R.id.subRV);
        subjects = new ArrayList<>();
        final SubjectAdapter subjectAdapter = new SubjectAdapter(this,subjects);
        subRV.setLayoutManager(new LinearLayoutManager(this));
        subRV.setHasFixedSize(false);
        subRV.setAdapter(subjectAdapter);

        FirebaseDatabase.getInstance().getReference().child("Subjects").child(StudentName.year).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot subSnapshot : dataSnapshot.getChildren()){
                    Subjects subjects1 = subSnapshot.getValue(Subjects.class);
                    subjects.add(subjects1);
                    subjectAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void save(View view) {
        for (Subjects subs : subjectsAdded){
            databaseReference.child("Students").child(StudentName.year).child(StudentName.name)
                    .child("subjects").child(subs.getLecture().getName()).setValue(subs);
        }
        Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
    }
}