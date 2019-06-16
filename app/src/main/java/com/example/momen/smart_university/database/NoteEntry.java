package com.example.momen.smart_university.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Momen on 6/15/2019.
 */
@Entity(tableName = "note_entry")

public class NoteEntry {
    @PrimaryKey (autoGenerate = true)
    private  int noteId;
    private String doctorName;
    private String noteBody;

    public NoteEntry (int noteId,String doctorName,String noteBody){
        this.noteId = noteId;
        this.doctorName = doctorName;
        this.noteBody = noteBody;
    }

    @Ignore
    public NoteEntry (String doctorName,String noteBody){
        this.doctorName = doctorName;
        this.noteBody = noteBody;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getNoteBody() {
        return noteBody;
    }
}
