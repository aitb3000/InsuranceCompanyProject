package app.Controllers.Salesman;

import app.Main;
import app.Models.Insurance;
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
    private ChoiceBox<String> cbInsurance;

    @FXML
    private Button btnSendNew;

    @FXML
    private Button btnCancelNew;


    @FXML
    void CancelNewInsuranceForm(ActionEvent event)
    {
        txtFirstName.clear();
        txtLastName.clear();
        txtId.clear();
    }

    @FXML
    void SendNewInsuranceForm(ActionEvent event)
    {
        sqlConnection.getInstance().SendQuery("INSERT INTO [dbo].insurances (istatus,itype,ucid,usid) VALUES ('" + Insurance.getInsuranceStatus((byte)0) + "','" + cbInsurance.getValue() + "','" + txtId.getText()+"','" + Main.AppUser.getId() + "')");
    }

}
