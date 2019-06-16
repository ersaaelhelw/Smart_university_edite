package com.example.momen.smart_university.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.momen.smart_university.Activites.AddSubjectActivity;
import com.example.momen.smart_university.R;
import com.example.momen.smart_university.firebase.Student.Subjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Momen on 6/12/2019.
 */

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectVH> {

    private List<Subjects> subjects;
    private Context context;


    public SubjectAdapter (Context context, List<Subjects> subjects){
        this.context = context;
        this.subjects = subjects;
        AddSubjectActivity.subjectsAdded = new ArrayList<>();
    }


    @NonNull
    @Override
    public SubjectAdapter.SubjectVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.subject_check_item,parent,false);
        return new SubjectVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectAdapter.SubjectVH holder, int position) {
        holder.name.setText(subjects.get(position).getLecture().getName());
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public class SubjectVH extends RecyclerView.ViewHolder {
        TextView name;
        CheckBox chk ;
        public SubjectVH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.subNameItem);
            chk = itemView.findViewById(R.id.chk);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (chk.isChecked()){
                        chk.setChecked(false);
                        AddSubjectActivity.subjectsAdded.remove(subjects.get(getAdapterPosition()));
                    }else {
                        chk.setChecked(true);
                        AddSubjectActivity.subjectsAdded.add(subjects.get(getAdapterPosition()));

                    }
                }
            });
        }
    }
}
