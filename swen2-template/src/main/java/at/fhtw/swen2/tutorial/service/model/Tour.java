package at.fhtw.swen2.tutorial.service.model;

import lombok.Builder;
import lombok.Cleanup;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Validated
public class Tour {

    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String origin;
    @NotBlank
    private String destination;
    @NotBlank
    private String transportType;
    @NotNull
    private float distance;
    @NotNull
    private float time;
    //private String routeInformation;


}
