package at.fhtw.swen2.tutorial.service.impl;
import at.fhtw.swen2.tutorial.service.MapQuestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpStatus;



public class MapQuestServiceImpl implements MapQuestService {
    @Value("${mapquest.apikey}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getDirections(String origin, String destination) throws ApiException {
        String url = String.format("https://www.mapquestapi.com/directions/v2/route?key=%s&from=%s&to=%s&outFormat=json",
                apiKey, origin, destination);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new ApiException("Failed to get directions from MapQuest API");

        }
        return response.getBody();
    }

}
