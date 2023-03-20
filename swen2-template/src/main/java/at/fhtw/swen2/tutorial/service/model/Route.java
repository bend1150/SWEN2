package at.fhtw.swen2.tutorial.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Route {

    private Long id;
    private String name;
    private String description;
    private String from;
    private String to;
    private String transportType;
    private float distance;
    private float time;
    private String routeInformation;

}
