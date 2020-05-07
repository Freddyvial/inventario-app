package com.poli.covid19.domain;

public class Question {
    private int id;
    private String content;
    private int positiveAnswer;
    private int negativeAnswer;
    private String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPositiveAnswer() {
        return positiveAnswer;
    }

    public void setPositiveAnswer(int positiveAnswer) {
        this.positiveAnswer = positiveAnswer;
    }

    public int getNegativeAnswer() {
        return negativeAnswer;
    }

    public void setNegativeAnswer(int negativeAnswer) {
        this.negativeAnswer = negativeAnswer;
    }



    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
