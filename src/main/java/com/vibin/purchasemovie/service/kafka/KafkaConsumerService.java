package com.vibin.purchasemovie.service.kafka;

import com.vibin.purchasemovie.events.TicketSold;
import com.vibin.purchasemovie.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private MovieService movieService;


    private Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);


    @KafkaListener(groupId = "ticketSoldGroup", topics = "ticketsold_topic", properties = {"enable.auto.commit:false", "auto.offset.reset:latest"})
    public void ticketSold(TicketSold ticketSold){

        logger.info("Inside KafkaConsumer ticketSold()");
        movieService.updateAvailableTickets(ticketSold);
    }

}
