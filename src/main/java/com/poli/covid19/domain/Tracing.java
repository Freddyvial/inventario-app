package com.poli.covid19.domain;

public class Tracing {
    private String id;
    private Patient patient;
    private Medical medical;
    private String observation;
    private State state;
    private String date;
    private DetailTracing detailTracing;

    public DetailTracing getDetailTracing() {
        return detailTracing;
    }

    public void setDetailTracing(DetailTracing detailTracing) {
        this.detailTracing = detailTracing;
    }

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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medical getMedical() {
        return medical;
    }

    public void setMedical(Medical medical) {
        this.medical = medical;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
