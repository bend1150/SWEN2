package at.fhtw.swen2.tutorial.service.impl;
import at.fhtw.swen2.tutorial.service.MapQuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpStatus;
import javafx.scene.image.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.FileOutputStream;
/*
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
*/


@Service
@Transactional
public class MapQuestServiceImpl implements MapQuestService {

    private static final Logger logger = LoggerFactory.getLogger(MapQuestServiceImpl.class);


    @Override
    public Image getImage(String origin, String destination) throws IOException{
        //String origin = "Wien";
        //String destination ="Paris";
        byte[] imageBytes = getImageBytes(origin, destination);
        Image image = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
            image = new Image(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    private byte[] getImageBytes (String origin, String destination) throws IOException {
        String apiURL ="https://www.mapquestapi.com/staticmap/v5/map?start=%s&end=%s&size=%d,%d@2x&format=png&key=%s";
        //String apiURL ="https://www.mapquestapi.com/staticmap/v5/map?start=%s&end=%s&size=%d,%d@2x&&key=%s";
        String key ="I0bgoaXyiDu6NYAjhuhA1cSv4jU7nQEv";
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(apiURL, origin, destination, 500, 170, key);
        logger.info("Sending request to {}", url);

        byte[] imageBytes = restTemplate.getForObject(url, byte[].class);
        logger.info("Received {} bytes", imageBytes.length);

        // Test: Save img to file
        FileOutputStream fos = new FileOutputStream("map.png");
        fos.write(imageBytes);
        fos.close();



        return imageBytes;

    }

}