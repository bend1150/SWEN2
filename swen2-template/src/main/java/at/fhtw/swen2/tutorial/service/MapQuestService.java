package at.fhtw.swen2.tutorial.service;
import java.util.List;
import at.fhtw.swen2.tutorial.service.model.Tour;



public interface MapQuestService {
    String getDirection(String origin, String destination) throws ApiException;
