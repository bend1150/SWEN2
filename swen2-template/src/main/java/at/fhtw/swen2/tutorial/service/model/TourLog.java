package at.fhtw.swen2.tutorial.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TourLog {

    private long id;

    private long tourId;
    private String date;
    private Float time;
    private String comment;
    private String difficulty;
    private Float totalTime;
    private Float rating;

}
