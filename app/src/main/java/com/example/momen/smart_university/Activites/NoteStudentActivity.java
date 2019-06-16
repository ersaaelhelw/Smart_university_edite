package com.example.momen.smart_university.Activites;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.momen.smart_university.Adapter.NoteAdapter;
import com.example.momen.smart_university.R;
import com.example.momen.smart_university.database.GetNoteModel;
import com.example.momen.smart_university.database.NoteEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteStudentActivity extends AppCompatActivity implements NoteAdapter.NoteClickListener {

    RecyclerView noteRV;
    List<NoteEntry> noteList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_student);
        noteRV = findViewById(R.id.noteRV);
        noteRV.setHasFixedSize(false);
        noteRV.setLayoutManager(new LinearLayoutManager(this));
        GetNoteModel getNoteModel = ViewModelProviders.of(this).get(GetNoteModel.class);
        getNoteModel.getNote().observe(this, new Observer<List<NoteEntry>>() {
            @Override
            public void onChanged(@Nullable List<NoteEntry> noteEntries) {
                NoteAdapter noteAdapter = new NoteAdapter(noteEntries,NoteStudentActivity.this);
                for (NoteEntry noteEntry : noteEntries){
                    noteList.add(noteEntry);
                }
                noteRV.setAdapter(noteAdapter);
            }
        });
    }

    @Override
    public void onNoteItemClick(int position) {
        Intent intent = new Intent(this,NoteDetails.class);
        intent.putExtra("doc",noteList.get(position).getDoctorName());
        intent.putExtra("body",noteList.get(position).getNoteBody());
        startActivity(intent);
    }
}
