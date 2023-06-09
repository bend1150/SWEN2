package at.fhtw.swen2.tutorial.presentation.view;


import at.fhtw.swen2.tutorial.presentation.viewmodel.SearchViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@Scope("prototype")
@Slf4j
public class SearchController {

    public static final int PAGE_ITEMS_COUNT = 10;

    @Autowired
    private SearchViewModel searchViewModel;

    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;

    @FXML
    private void initialize() {
        searchTextField.textProperty().bindBidirectional(searchViewModel.searchStringProperty());
    }

    private void loadData() {
        searchViewModel.search();
    }

    public void searchRoutes(ActionEvent actionEvent) {
        searchViewModel.search();
    }
}
