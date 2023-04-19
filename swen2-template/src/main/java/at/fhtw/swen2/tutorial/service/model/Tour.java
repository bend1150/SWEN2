package at.fhtw.swen2.tutorial.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tour {

    private Long id;
    private String name;
    private String description;
    private String origin;
    private String destination;
    private String transportType;
    private float distance;
    private float time;
    //private String routeInformation;

}
