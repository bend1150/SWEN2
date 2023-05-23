package at.fhtw.swen2.tutorial.service;
import java.util.List;
import at.fhtw.swen2.tutorial.service.model.Tour;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javafx.scene.image.Image;
import java.io.IOException;




public interface MapQuestService {

    Image getImage(String origin, String destination) throws IOException;   //kommentier aus falls eventhandler funkt


}