package com.vibin.purchasemovie.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="movies")
@EqualsAndHashCode
@Data
@ToString
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

}
