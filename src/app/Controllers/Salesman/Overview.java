package app.Controllers.Salesman;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Overview {

    @FXML
    private Pane pnlOverview;

    @FXML
    private TextField txtSearch;

    @FXML
    private Label lblTotalOrders;

    @FXML
    private Label lblTotalDone;

    @FXML
    private Label lblPending;

    @FXML
    private VBox pnItems;

}
