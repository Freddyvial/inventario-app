package com.poli.covid19.domain;

public class DetailTracing {
    private String id;
    private String checkObservation;
    private String medication;
    private String evolutionPatient;
    private String tracing;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCheckObservation() {
        return checkObservation;
    }

    public void setCheckObservation(String checkObservation) {
        this.checkObservation = checkObservation;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getEvolutionPatient() {
        return evolutionPatient;
    }

    public void setEvolutionPatient(String evolutionPatient) {
        this.evolutionPatient = evolutionPatient;
    }

    public String getTracing() {
        return tracing;
    }

    public void setTracing(String tracing) {
        this.tracing = tracing;
    }
}
