package com.example.momen.smart_university.firebase.Student;

public class Degree {
    public float attendance_degree;
    public float quiz_degree;

    public Degree(){}

    public Degree (float attendance_degree,float quiz_degree){
        this.attendance_degree = attendance_degree;
        this.quiz_degree = quiz_degree;
    }

    public void setAttendance_degree(float attendance_degree) {
        this.attendance_degree = attendance_degree;
    }

    public void setQuiz_degree(float quiz_degree) {
        this.quiz_degree = quiz_degree;
    }

    public float getAttendance_degree() {
        return attendance_degree;
    }

    public float getQuiz_degree() {
        return quiz_degree;
    }
}
