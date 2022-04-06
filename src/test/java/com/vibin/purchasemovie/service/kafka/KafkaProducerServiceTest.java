package com.vibin.purchasemovie.service.kafka;

import com.vibin.purchasemovie.events.MovieAdded;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.SettableListenableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest

class KafkaProducerServiceTest {

    @Autowired
    KafkaProducerService producerService;

    @MockBean
    private KafkaTemplate<String, MovieAdded> kafkaTemplateMovieAdded;


    @Test
    void publishMovieAddedTest() {

        SettableListenableFuture<SendResult<String, Object>> future = new SettableListenableFuture<>();
        future.setException(new RuntimeException());


        MovieAdded movieAdded = new MovieAdded();
        movieAdded.setMovieId(1);
        movieAdded.setMovieTitle("Jerry appas favorite movie");
        movieAdded.setMovieNumberOfTickets(10);

        // mock
       // when(kafkaTemplateMovieAdded.send("addedmovie_topic", "movieAdded", movieAdded)).thenReturn(future);

        // call the actual method
        String actualResult = producerService.publishMovieAdded(movieAdded);

        assertEquals("Added movie successfully", actualResult);

    }

    @Test
    void publishMovieRemovedTest() {
    }

    @Test
    void publishTitleCorrectedTest() {
    }

    @Test
    void publishShowtimesUpdatedTest() {
    }


}