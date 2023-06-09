package at.fhtw.swen2.tutorial.service.model;

import lombok.Builder;
import lombok.Data;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Validated
public class TourLog {

    private long id;

    private long tourId;
    @NotBlank
    private String date;
    @NotNull
    private Float time;
    @NotBlank
    private String comment;
    @NotBlank
    private String difficulty;
    @NotNull
    private Float totalTime;
    @NotNull
    private Float rating;

}
