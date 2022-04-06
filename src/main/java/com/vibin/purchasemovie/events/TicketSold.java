package com.vibin.purchasemovie.events;

import lombok.*;
import org.springframework.stereotype.Component;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Component
public class TicketSold {

    private int id;

    private int ticketsSold;

}
