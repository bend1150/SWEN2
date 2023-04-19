package at.fhtw.swen2.tutorial.service;

import at.fhtw.swen2.tutorial.service.model.Person;
import at.fhtw.swen2.tutorial.service.model.Tour;

import java.util.List;

public interface RouteService {
    List<Tour> getRouteList();

    Tour addNew(Tour tour);

    Tour getByName(String name);
}