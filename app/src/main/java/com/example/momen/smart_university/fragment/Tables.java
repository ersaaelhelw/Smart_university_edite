package com.example.momen.smart_university.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.momen.smart_university.Activites.StudentName;
import com.example.momen.smart_university.R;
import com.example.momen.smart_university.firebase.Doctor.Doctor;
import com.example.momen.smart_university.firebase.Doctor.subject;
import com.example.momen.smart_university.firebase.Student.Degree;
import com.example.momen.smart_university.firebase.Student.Lecture;
import com.example.momen.smart_university.firebase.Student.Subjects;
import com.example.momen.smart_university.firebase.Table.Table_model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Momen on 6/9/2019.
 */

public class Tables extends Fragment {

    Spinner spec,doc,year,day;
    EditText from,to,secNum,subject,room;
    Button save;
    DatabaseReference referenceDoc,referenceTable,referenceSubject;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Doctors");
    List<String> doctorsName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.table_fragment,container,false);
        spec = view.findViewById(R.id.spinner_spec);
        doc = view.findViewById(R.id.spinner_doc);
        year = view.findViewById(R.id.spinner_year);
        day = view.findViewById(R.id.spinner_day);
        from = view.findViewById(R.id.from);
        to = view.findViewById(R.id.to);
        secNum = view.findViewById(R.id.secNum);
        subject = view.findViewById(R.id.sub);
        room = view.findViewById(R.id.roomNum);
        save = view.findViewById(R.id.save_table);

        doctorsName = new ArrayList<>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Doctor doctor = snapshot.getValue(Doctor.class);
                    if (doctor != null)
                        doctorsName.add(doctor.getName());
                    else Toast.makeText(getActivity(), "null", Toast.LENGTH_SHORT).show();
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,doctorsName);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                doc.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                referenceDoc = FirebaseDatabase.getInstance().getReference().child("Doctors").child(doc.getSelectedItem().toString()).child("Subjects");
                referenceTable = FirebaseDatabase.getInstance().getReference().child("Table");
                referenceSubject = FirebaseDatabase.getInstance().getReference().child("Subjects");

                referenceTable.child(subject.getText().toString()).setValue(new Table_model(doc.getSelectedItem().toString(),subject.getText().toString(),
                        Integer.parseInt(room.getText().toString()),Float.parseFloat(from.getText().toString()),Float.parseFloat(to.getText().toString())
                        ,Integer.parseInt(secNum.getText().toString()),year.getSelectedItem().toString(),day.getSelectedItem().toString()));


                referenceDoc.child(subject.getText().toString()).setValue(new subject(subject.getText().toString(),0,
                        year.getSelectedItem().toString(),
                        spec.getSelectedItem().toString(),false,null,0,true,
                        null,null));

                referenceSubject.child(year.getSelectedItem().toString()).child(subject.getText().toString()).setValue(new Subjects(
                        new Lecture(subject.getText().toString(),null,null,0),
                        new Degree(0,0),null));

            }
        });
        return view;
    }
}
