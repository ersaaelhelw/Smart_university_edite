package com.example.momen.smart_university.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.momen.smart_university.R;
import com.example.momen.smart_university.firebase.Student.Subjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AmrGamal on 15/06/2019.
 */

public class DegreesAdapter  extends RecyclerView.Adapter<DegreesAdapter.recyclerVH> {

    List<Subjects> date = new ArrayList<>();
    Context context;

    public DegreesAdapter(List <Subjects> name, Context context) {
        this.date = name;
        this.context = context;
    }

    @NonNull
    @Override
    public DegreesAdapter.recyclerVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.student_degree_item, viewGroup, false);
        return new DegreesAdapter.recyclerVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DegreesAdapter.recyclerVH holder, final int position) {
        TextView subjectName = holder.itemView.findViewById(R.id.subject_name);
        subjectName.setText(date.get(position).getLecture().getName());
        TextView absence_degree = holder.itemView.findViewById(R.id.abs_degree);
        absence_degree.setText(String.valueOf(date.get(position).getDegree().getAttendance_degree()));
        TextView quiz_degree = holder.itemView.findViewById(R.id.quiz_degree);
        quiz_degree.setText(String.valueOf(date.get(position).getDegree().getQuiz_degree()));


    }

    @Override
    public int getItemCount() {
        return date.size();
    }

    class recyclerVH extends RecyclerView.ViewHolder {
        public recyclerVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}