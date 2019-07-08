package app.Controllers;

import app.Main;
import app.connection.sqlConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
    private PasswordField txtNewPass1;

    @FXML
    private PasswordField txtNewPass2;

    @FXML
    private Label lblError;


    @FXML
    void CancelSettings(ActionEvent event)
    {
        txtClientSettingFirst.setText(Main.AppUser.getFirstName());
        txtClientSettingLast.setText(Main.AppUser.getLastName());
        txtClientSettingPhone.setText(Main.AppUser.getPhone());
        txtClientSettingStatus.setText(Main.AppUser.getStatus());
        txtClientSettingAddress.setText(Main.AppUser.getAddress());
        txtNewPass1.clear();
        txtNewPass2.clear();
    }

    @FXML
    void SaveSettings(ActionEvent event)
    {
        // Checking if needs to send update query with updated password
        if ((!txtNewPass1.getText().isEmpty()) && (!txtNewPass2.getText().isEmpty()))
        {
            // Checking is them match
            if (txtNewPass1.getText().compareTo(txtNewPass2.getText()) == 0)
            {
                ShowError(false);
                //TODO: Create an UPDATE query with password.
                sqlConnection.getInstance().SendQuery("UPDATE");
            }
            else // If not match present an error
            {
                ShowError(true);
            }

        }
        else // When need to send update query without changing password.
        {
            //TODO: Create an UPDATE query without password.
            sqlConnection.getInstance().SendQuery("UPDATE");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        txtClientSettingFirst.setText(Main.AppUser.getFirstName());
        txtClientSettingLast.setText(Main.AppUser.getLastName());
        txtClientSettingPhone.setText(Main.AppUser.getPhone());
        txtClientSettingStatus.setText(Main.AppUser.getStatus());
        txtClientSettingAddress.setText(Main.AppUser.getAddress());

        ShowError(false);
    }

    private void ShowError(boolean newStatus)
    {
        lblError.setVisible(newStatus);

    }

}
