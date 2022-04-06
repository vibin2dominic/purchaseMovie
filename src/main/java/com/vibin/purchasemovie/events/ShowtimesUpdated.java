package com.vibin.purchasemovie.events;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
@ToString
public class ShowtimesUpdated {

    private int movieId;

    private String movieTimeSlot1;

    private String movieTimeSlot2;
}
