package com.example.momen.smart_university.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.momen.smart_university.Adapter.QuizAdapter;
import com.example.momen.smart_university.R;
import com.example.momen.smart_university.firebase.Doctor.QuizModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
public class Quiz extends AppCompatActivity implements QuizAdapter.QuizClickListener{
    QuizAdapter quizAdapter;
    List <String> quizNames;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String subName,docName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);
        final RecyclerView recyclerView = findViewById(R.id.recycler_quiz);
        recyclerView.setLayoutManager(new LinearLayoutManager(Quiz.this, LinearLayoutManager.VERTICAL, false));
        subName = getIntent().getStringExtra("subName");
        docName = getIntent().getStringExtra("docName");
        quizNames=new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("Doctors").child(docName).child("Subjects").child(subName).child("QuizModel");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                QuizModel quizModel = dataSnapshot.getValue(QuizModel.class);
                if (quizModel != null) {
                    quizNames.add(quizModel.getQuiz_name());
                    quizAdapter = new QuizAdapter(quizNames, Quiz.this,Quiz.this);
                    recyclerView.setAdapter(quizAdapter);
                    Toast.makeText(Quiz.this, String.valueOf(quizNames.size()) , Toast.LENGTH_SHORT).show();
                }
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
        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent   = new Intent(Quiz.this,Create_Quiz.class);
                intent.putExtra("docName",docName);
                intent.putExtra("subName",subName);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onListItemClick(int position) {
        databaseReference.child(quizNames.get(position)).child("pushed").setValue(true);
        Toast.makeText(this, quizNames.get(position), Toast.LENGTH_SHORT).show();
    }
}