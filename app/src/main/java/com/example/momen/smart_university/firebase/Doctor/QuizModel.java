package com.example.momen.smart_university.firebase.Doctor;

import java.util.List;

/**
 * Created by Momen on 12/3/2018.
 */

public class QuizModel {
    private boolean pushed;
    private  String quiz_name;
    private List<Questions> questions;
    private float time;
    private  int total_degree;
    public QuizModel(){
    }

    public QuizModel(boolean pushed,List<Questions> questions,float time,int total_degree,String quiz_name){
        this.time=time;
        this.quiz_name=quiz_name;
        this.pushed = pushed;
        this.questions =questions;
        this.total_degree=total_degree;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public int getTotal_degree() {
        return total_degree;
    }

    public void setTotal_degree(int total_degree) {
        this.total_degree = total_degree;
    }

    public List<Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }

    public void setPushed(boolean pushed) {
        this.pushed = pushed;
    }

    public boolean getPushed() {
        return pushed;
    }

    public String getQuiz_name() {
        return quiz_name;
    }

    public void setQuiz_name(String quiz_name) {
        this.quiz_name = quiz_name;
    }
}