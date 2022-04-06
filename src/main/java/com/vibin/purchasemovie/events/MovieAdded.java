package com.vibin.purchasemovie.events;

import lombok.*;
import org.springframework.stereotype.Component;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Component
public class MovieAdded {

    private int movieId;

    private String movieTitle;

    private String movieDuration;

    private String movieRating;

    private String movieTimeSlot1;

    private String movieTimeSlot2;

    private int movieNumberOfTickets;

}
