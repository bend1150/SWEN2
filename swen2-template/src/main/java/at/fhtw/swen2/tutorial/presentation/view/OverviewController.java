package at.fhtw.swen2.tutorial.presentation.view;

import com.fasterxml.jackson.core.util.Instantiatable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import org.springframework.stereotype.Component;

import javax.websocket.OnError;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class OverviewController implements Initializable {
    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL location, ResourceBundle rb){

    }
}
