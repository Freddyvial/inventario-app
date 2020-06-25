package com.poli.covid19.domain;

public class ChartResultByDepartment {
    private String nameDepartment;
    private int highProbability;
    private  int lowProbability;

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public int getHighProbability() {
        return highProbability;
    }

    public void setHighProbability(int highProbability) {
        this.highProbability = highProbability;
    }

    public int getLowProbability() {
        return lowProbability;
    }

    public void setLowProbability(int lowProbability) {
        this.lowProbability = lowProbability;
    }
}
