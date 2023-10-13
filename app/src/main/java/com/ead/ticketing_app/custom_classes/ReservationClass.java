package com.ead.ticketing_app.custom_classes;

public class ReservationClass {

    //variable initialization
    String startFrom;
    String endFrom;
    String date;
    String noOfTickets;
    String trainClass;
    String price;

    //constructor
    public ReservationClass(String startFrom, String endFrom, String date, String noOfTickets, String trainClass, String price) {
        this.startFrom = startFrom;
        this.endFrom = endFrom;
        this.date = date;
        this.noOfTickets = noOfTickets;
        this.trainClass = trainClass;
        this.price = price;
    }

    //=== GETTERS AND SETTERS ==============
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStartFrom() {
        return startFrom;
    }

    public void setStartFrom(String startFrom) {
        this.startFrom = startFrom;
    }

    public String getEndFrom() {
        return endFrom;
    }

    public void setEndFrom(String endFrom) {
        this.endFrom = endFrom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNoOfTickets() {
        return noOfTickets;
    }

    public void setNoOfTickets(String noOfTickets) {
        this.noOfTickets = noOfTickets;
    }

    public String getTrainClass() { return trainClass; }

    public void setTrainClass(String trainClass) {
        this.trainClass = trainClass;
    }
}
