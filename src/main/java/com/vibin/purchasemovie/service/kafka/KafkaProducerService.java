package com.vibin.purchasemovie.service.kafka;

import com.vibin.purchasemovie.events.MovieAdded;
import com.vibin.purchasemovie.events.MovieRemoved;
import com.vibin.purchasemovie.events.ShowtimesUpdated;
import com.vibin.purchasemovie.events.TitleCorrected;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate <String, MovieAdded> kafkaTemplateMovieAdded;

    @Autowired
    private KafkaTemplate<String, MovieRemoved> kafkaTemplateMovieRemoved;

    @Autowired
    private KafkaTemplate<String, TitleCorrected> kafkaTemplateTitleCorrected;

    @Autowired
    private KafkaTemplate<String, ShowtimesUpdated> kafkaShowtimesUpdated;


    //Movie Added Event
    public String publishMovieAdded(MovieAdded movieAdded){

        try{
            this.kafkaTemplateMovieAdded.send("addedmovie_topic", "movieAdded", movieAdded);

        }catch (Exception e){
            e.printStackTrace();
        }

        return "Added movie successfully";

    }

    // Remove Movie Event
    public String publishMovieRemoved(MovieRemoved movieRemoved){

        try{
            this.kafkaTemplateMovieRemoved.send("moviedeleted_topic","movieDeleted", movieRemoved);
        } catch (Exception e){
            return "failed to delete movie ";
        }

        return "Deleted movie successfully";
    }


    // Title Corrected Event
    public String publishTitleCorrected (TitleCorrected titleCorrected){

        try{
            this.kafkaTemplateTitleCorrected.send("titlecorrected_topic", "titleCorrected", titleCorrected);
        }catch (Exception e){
            return "Failed to correct title";
        }

        return "Title corrected successfully!!";
    }

    // Showtimes Updated Event
    public String publishShowtimesUpdated (ShowtimesUpdated showtimesUpdated){

        try{
            this.kafkaShowtimesUpdated.send("showtimesupdated_topic", "showtimeUpdated", showtimesUpdated);
        } catch (Exception e){
            return "Failed to update showtime(s)...";
        }

        return "Showtimes updated correctly!!";
    }
}
