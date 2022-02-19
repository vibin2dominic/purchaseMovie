package com.vibin.purchasemovie.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name="vibin")
@EqualsAndHashCode
@Data
public class Movie {

    @Column(name = "title")
    private String title;

    @Column(name = "duration")
    private String duration;

    @Column(name = "rating")
    private String rating;

    @Column(name = "timeslot_1")
    private String timeSlot1;

    @Column(name = "timeslot_2")
    private String timeSlot2;

    @Column(name = "number_of_tickets")
    private int numberOfTickets;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    public Movie (){}

    public Movie(String title, String duration, String rating, String timeSlot1, String timeSlot2, int numberOfTickets) {
        this.title = title;
        this.duration = duration;
        this.rating = rating;
        this.timeSlot1 = timeSlot1;
        this.timeSlot2 = timeSlot2;
        this.numberOfTickets = numberOfTickets;
    }
/*
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTimeSlot1() {
        return timeSlot1;
    }

    public void setTimeSlot1(String timeSlot1) {
        this.timeSlot1 = timeSlot1;
    }

    public String getTimeSlot2() {
        return timeSlot2;
    }

    public void setTimeSlot2(String timeSlot2) {
        this.timeSlot2 = timeSlot2;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

*/
}
