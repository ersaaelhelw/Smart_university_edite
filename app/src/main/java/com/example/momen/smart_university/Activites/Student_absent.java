package com.example.momen.smart_university.Activites;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.momen.smart_university.R;
import com.example.momen.smart_university.firebase.Doctor.Doctor;
import com.example.momen.smart_university.firebase.Doctor.subject;
import com.example.momen.smart_university.firebase.Student.Subjects;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Student_absent extends AppCompatActivity {

    private DatabaseReference reference;
    private Button btn;
    private EditText editText;
    private TextView textView;
    private int code = 0;
    private String name = "";
    List<String> attendance;
    String sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_absent);
        setTitle("Absence");

        textView =  findViewById(R.id.code);
        btn = findViewById(R.id.finish) ;
        editText = findViewById(R.id.editText);

        String name = getIntent().getStringExtra("docName");
        sub = getIntent().getStringExtra("subName");


        reference = FirebaseDatabase.getInstance().getReference().child("Doctors").child(name).child("Subjects");

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                subject subject = dataSnapshot.getValue(subject.class);
                if (subject != null){
                    if (subject.getName().equals(sub)){
                        if (subject.getAvailable()){
                            code = subject.getCode();
                            btn.setVisibility(View.VISIBLE);
                        }
                        else {
                            btn.setVisibility(View.INVISIBLE);
                        }
                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                subject subject = dataSnapshot.getValue(subject.class);
                if (subject != null){
                    if (subject.getName().equals(sub)){
                        if (subject.getAvailable()){
                            code = subject.getCode();
                            btn.setVisibility(View.VISIBLE);
                        }
                        else {
                            btn.setVisibility(View.INVISIBLE);
                        }
                    }
                }

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

    public void done(View view){

        if(Integer.parseInt(editText.getText().toString()) == code)
        {
            Toast.makeText(this, "you are attendance", Toast.LENGTH_SHORT).show();
            reference.child(sub).child("Student_Absence").push().setValue(StudentName.name);
            final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Students").child(StudentName.year).child(StudentName.name)
                    .child("subjects");
            databaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Subjects subjects = dataSnapshot.getValue(Subjects.class);
                    if (subjects != null) {
                        if (subjects.getLecture().getName().equals(sub)) {
                            attendance = subjects.getLecture().getAttendances();
                            if (attendance == null){
                                attendance = new ArrayList<>();
                                @SuppressLint("SimpleDateFormat") String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
                                attendance.add(date);
                            }else {
                                @SuppressLint("SimpleDateFormat") String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
                                attendance.add(date);
                            }
                            databaseReference.child(sub).child("lecture").child("attendances").setValue(attendance);
                        }
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
        }

        finish();

    }
}
