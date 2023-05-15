package at.fhtw.swen2.tutorial.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TourLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long tourId;

    private String date;
    private Float time;
    private String comment;
    private String difficulty;
    private Float totalTime;
    private Float rating;

    @ManyToOne
    @JoinColumn(name = "tour_report_id")
    private TourReportEntity tourReport;
}
