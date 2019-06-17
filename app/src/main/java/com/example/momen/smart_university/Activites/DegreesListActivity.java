package com.example.momen.smart_university.Activites;

import android.os.RecoverySystem;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.momen.smart_university.Adapter.DegreesListAdapter;
import com.example.momen.smart_university.R;
import com.example.momen.smart_university.firebase.Doctor.DoctorName;
import com.example.momen.smart_university.firebase.Doctor.QuizDegreesList;
import com.example.momen.smart_university.firebase.Doctor.subject;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DegreesListActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,databaseReference2;
    List<QuizDegreesList> degreesList =new ArrayList<>();
    RecyclerView recyclerView;
    DegreesListAdapter degreesListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degrees_list);
        firebaseDatabase=FirebaseDatabase.getInstance();
        recyclerView=findViewById(R.id.degree_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        degreesListAdapter =new DegreesListAdapter(degreesList,this);
        recyclerView.setAdapter(degreesListAdapter);
        databaseReference=firebaseDatabase.getReference().child("Doctors").child(DoctorName.doctorName).child("Subjects");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                subject subject= dataSnapshot.getValue(com.example.momen.smart_university.firebase.Doctor.subject.class);
                databaseReference2=firebaseDatabase.getReference().child("Degrees").child(subject.getName());
                databaseReference2.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        QuizDegreesList  quizDegreesList =dataSnapshot.getValue(QuizDegreesList.class);
                        degreesList.add(quizDegreesList);
                        degreesListAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}

