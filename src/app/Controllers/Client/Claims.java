package app.Controllers.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Claims implements Initializable {

    @FXML
    private Pane pnlInsurences;

    @FXML
    private TextField txtSearchInsurance;

    @FXML
    private Button btnShowAll;

    @FXML
    private TableView<?> tvInsurence;

    @FXML
    private TableColumn<?, ?> tciid;

    @FXML
    private TableColumn<?, ?> tcType;

    @FXML
    private TableColumn<?, ?> tcStatus;

    @FXML
    private TableColumn<?, ?> tcClientId;

    @FXML
    private TableColumn<?, ?> tcClientFname;

    @FXML
    private TableColumn<?, ?> tcClientLname;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }


    @FXML
    void MouseSearchInsurance(MouseEvent event) {

    }

    @FXML
    void SearchInsurance(KeyEvent event) {

    }

    @FXML
    void ShowAll(ActionEvent event) {

    }


}
