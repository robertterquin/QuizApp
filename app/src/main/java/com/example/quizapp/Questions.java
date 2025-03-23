package com.example.quizapp;

import java.util.ArrayList;

public class Questions {
    private String question;
    private ArrayList<String> options;
    private int correctAnswerIndex;



    public Questions(String question, ArrayList<String> options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestions() {
        return question;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}
