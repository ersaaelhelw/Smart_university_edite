package com.example.momen.smart_university.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.momen.smart_university.R;
import com.example.momen.smart_university.database.NoteEntry;

import java.util.List;

/**
 * Created by Momen on 6/15/2019.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteVM> {

    private List<NoteEntry> notes;
    private final NoteClickListener noteClickListener;

    public interface NoteClickListener{
        void onNoteItemClick(int position);
    }

    public NoteAdapter (List<NoteEntry> notes,NoteClickListener noteClickListener){
        this.notes = notes;
        this.noteClickListener = noteClickListener;
    }

    @NonNull
    @Override
    public NoteAdapter.NoteVM onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        return new NoteVM(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NoteVM holder, int position) {
        String body;
        if (notes.get(position).getNoteBody().length() >= 20)
            body = notes.get(position).getNoteBody().substring(0, 19) + "\u2026";
        else
            body = notes.get(position).getNoteBody();
        holder.noteDoc.setText(notes.get(position).getDoctorName());
        holder.noteBody.setText(body);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class NoteVM extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView noteDoc,noteBody;
        public NoteVM(@NonNull View itemView) {
            super(itemView);
            noteDoc = itemView.findViewById(R.id.note_doc);
            noteBody = itemView.findViewById(R.id.note_body);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            noteClickListener.onNoteItemClick(getAdapterPosition());
        }
    }
}
