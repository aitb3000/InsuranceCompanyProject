package app.Controllers.Salesman;

import app.Main;
import app.connection.sqlConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Settings implements Initializable {

    @FXML
    private Pane pnlSettings;

    @FXML
    private Label lblClientSettingId;

    @FXML
    private TextField txtClientSettingFirst;

    @FXML
    private TextField txtClientSettingLast;

    @FXML
    private TextField txtClientSettingStatus;

    @FXML
    private TextField txtClientSettingAddress;

    @FXML
    private TextField txtClientSettingPhone;

    @FXML
    private Button btnSaveSettings;

    @FXML
    private Button btnCancelSettings;

    @FXML
    void CancelSettings(ActionEvent event)
    {
        txtClientSettingFirst.setText(Main.AppUser.getFirstName());
        txtClientSettingLast.setText(Main.AppUser.getLastName());
        txtClientSettingPhone.setText(Main.AppUser.getPhone());
        txtClientSettingStatus.setText(Main.AppUser.getStatus());
        txtClientSettingAddress.setText(Main.AppUser.getAddress());
    }

    @FXML
    void SaveSettings(ActionEvent event) {
        sqlConnection.getInstance().SendQuery("UPDATE");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtClientSettingFirst.setText(Main.AppUser.getFirstName());
        txtClientSettingLast.setText(Main.AppUser.getLastName());
        txtClientSettingPhone.setText(Main.AppUser.getPhone());
        txtClientSettingStatus.setText(Main.AppUser.getStatus());
        txtClientSettingAddress.setText(Main.AppUser.getAddress());
    }

}
