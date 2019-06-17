package com.example.momen.smart_university.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.momen.smart_university.R;
import com.example.momen.smart_university.firebase.Doctor.Questions;
import com.example.momen.smart_university.firebase.Doctor.QuizDegreesList;
import com.example.momen.smart_university.firebase.Doctor.QuizModel;
import com.example.momen.smart_university.firebase.Doctor.subject;
import com.example.momen.smart_university.firebase.Student.Degree;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Answer_question extends AppCompatActivity {
    private float degree=0f;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private List<String> attendances;
    private List<Questions> questionsList;
    private List<QuizModel> quizModelList;
    private RadioButton radioButton;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private RadioGroup radioGroup;
    private TextView tv_question;
    private Button next;
    private int count;
    private boolean checkItem;
    String docName;
    String subName;
    QuizDegreesList quizDegreesList;
    DatabaseReference degree_reference;
    DatabaseReference student_degree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_question);
        setTitle("Task");
        //attach with design
        radioButton=findViewById(R.id.radioButton);
        radioButton2=findViewById(R.id.radioButton2);
        radioButton3=findViewById(R.id.radioButton3);
        radioButton4=findViewById(R.id.radioButton4);
        radioGroup = findViewById(R.id.rg);
        tv_question=findViewById(R.id.question);
        next=findViewById(R.id.next);
        count=0;
        docName = getIntent().getStringExtra("docName");
        subName = getIntent().getStringExtra("subName");
        quizDegreesList=new QuizDegreesList(StudentName.name,0f);
        //firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference().child("Doctors").child(docName).child("Subjects");
        degree_reference=firebaseDatabase.getReference().child("Degrees").child(subName);
        student_degree=firebaseDatabase.getReference().child("Students").child(StudentName.year).child(StudentName.name).child("subjects")
                .child(subName);
        attendances = new ArrayList<>();
        questionsList = new ArrayList<>();
        quizModelList = new ArrayList<>();



        reference.child(subName).child("Student_Absence").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String attend = dataSnapshot.getValue(String.class);
                attendances.add(attend);
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

        reference.child(subName).child("QuizModel").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    QuizModel quizModel = snapshot.getValue(QuizModel.class);
                    if (quizModel.getPushed()) {
                        quizModelList.add(quizModel);
                        QuizModel model = quizModelList.get(getIndex(StudentName.name)%quizModelList.size());
                        questionsList = model.getQuestions();
                        degree=model.getTotal_degree()/questionsList.size();
                        populateUI();
                    }

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //QuizModel quizModel =  quizModelList.get(getIndex(StudentName.name)%(quizModelList.size()-1));
        //questionsList = quizModel.getQuestions();
        //Toast.makeText(this, String.valueOf(questionsList.size()), Toast.LENGTH_SHORT).show();





        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked();

                if(checkItem)
                    quizDegreesList.setStudent_degree(quizDegreesList.getStudent_degree()+degree);
                else
                if (count==questionsList.size()-2||questionsList.size()==1)
                {
                    next.setText("Finish");
                }

                if (count==questionsList.size()-1) {
                    degree_reference.push().setValue(quizDegreesList);
                    student_degree.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                            Degree degree = dataSnapshot.getValue(Degree.class);
                            if (degree != null) {
                                if (degree.getQuiz_degree() == 0) {
                                    student_degree.child("degree").child("quiz_degree").setValue(
                                            quizDegreesList.getStudent_degree());
                                } else {
                                    student_degree.child("degree").child("quiz_degree").setValue((degree.getQuiz_degree() +
                                            quizDegreesList.getStudent_degree()) / 2);

                                }
                            }else
                                Toast.makeText(Answer_question.this, "null", Toast.LENGTH_SHORT).show();
                            finish();
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
                else
                {
                    count++;
                    reset();
                    populateUI();
                    //Toast.makeText(Answer_question.this, String.valueOf(count)+" "+String.valueOf(questionsList.size()-1), Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    public int getIndex(String name)
    {
        for(int i=0; i<attendances.size(); i++){
            if(name.equals(attendances.get(i)))
                return i;
        }
        return -1;
    }

    public void reset(){
        checkItem=false;
        radioButton.setChecked(false);
        radioButton2.setChecked(false);
        radioButton3.setChecked(false);
        radioButton4.setChecked(false);
    }

    public void checked( ) {
        if (radioButton.isChecked())
        {

            if (radioButton.getText().toString().equals(questionsList.get(count).correctAnswer))
                checkItem=true;
        }
        else if (radioButton2.isChecked()){
            if (radioButton2.getText().toString().equals(questionsList.get(count).correctAnswer))
                checkItem=true;

        }
        else if (radioButton3.isChecked()){
            if (radioButton3.getText().toString().equals(questionsList.get(count).correctAnswer))
                checkItem=true;

        }
        else if (radioButton4.isChecked()){
            if (radioButton4.getText().toString().equals(questionsList.get(count).correctAnswer))
                checkItem=true;

        }


    }
    public void populateUI (){
        tv_question.setText(questionsList.get(count).getQuestion());
        radioButton.setText(questionsList.get(count).getAnswer1());
        radioButton2.setText(questionsList.get(count).getAnswer2());
        radioButton3.setText(questionsList.get(count).getAnswer3());
        radioButton4.setText(questionsList.get(count).getAnswer4());
        if(questionsList.size()==1)
        {
            next.setText("Finish");
        }


    }
}