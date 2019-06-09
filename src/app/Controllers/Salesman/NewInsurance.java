package app.Controllers.Salesman;

import app.Main;
import app.connection.sqlConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class NewInsurance {

    @FXML
    private Pane pnlNewInsurance;

    @FXML
    private Label lblClientName;

    @FXML
    private Label lblNewInsuranceCheckLField;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private ChoiceBox<?> cbInsurance;

    @FXML
    private Button btnSendNew;

    @FXML
    private Button btnCancelNew;


    @FXML
    void CancelNewInsuranceForm(ActionEvent event)
    {
        txtFirstName.setText(Main.AppUser.getFirstName());
        txtLastName.setText(Main.AppUser.getLastName());
        txtId.setText(Main.AppUser.getId());
    }

    @FXML
    void SendNewInsuranceForm(ActionEvent event)
    {
        sqlConnection.getInstance().SendQuery("");
    }

}
