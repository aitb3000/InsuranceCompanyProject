package app.Controllers.Client;

import app.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Overview implements Initializable {

    @FXML
    private Pane pnlOverview;

    @FXML
    private Label lblTotalOrders;

    @FXML
    private Label lblTotalDone;

    @FXML
    private Label lblPending;

    @FXML
    private Label lblClientPhone;

    @FXML
    private Label lblClientAddress;

    @FXML
    private Label lblClientLastName;

    @FXML
    private Label lblClientStatus;

    @FXML
    private Label lblClientFirstName;

    @FXML
    private Label lblClientId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblClientId.setText(Main.AppUser.getId());
        lblClientFirstName.setText(Main.AppUser.getFirstName());
        lblClientLastName.setText(Main.AppUser.getLastName());
        lblClientPhone.setText(Main.AppUser.getPhone());
        lblClientStatus.setText(Main.AppUser.getStatus());
        lblClientAddress.setText(Main.AppUser.getAddress());

        //TODO: Adding sql question to get total insurances and their status.
        //TODO: Updating the labels with the statistics.
    }
}
