package app.Controllers;

import app.Main;
import app.connection.sqlConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

import static app.Models.UserInterface.TypeUserToInteger;

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
        txtClientSettingFirst.setText(Main.AppUser.GetCurrentAppUser().getFirstName());
        txtClientSettingLast.setText(Main.AppUser.GetCurrentAppUser().getLastName());
        txtClientSettingPhone.setText(Main.AppUser.GetCurrentAppUser().getPhone());
        txtClientSettingStatus.setText(Main.AppUser.GetCurrentAppUser().getStatus());
        txtClientSettingAddress.setText(Main.AppUser.GetCurrentAppUser().getAddress());
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

                boolean pass = sqlConnection.getInstance().SendQueryExecute(String.format("UPDATE users SET " +
                                "userFirstName='%s', " +
                                "userLastName='%s', " +
                                "userPassword='%s', " +
                                "userAddress='%s', " +
                                "userPhone='%s', " +
                                "userStatus='%s' WHERE userId='%s'",
                        txtClientSettingFirst.getText(),
                        txtClientSettingLast.getText(),
                        txtNewPass1.getText(),
                        txtClientSettingAddress.getText(),
                        txtClientSettingPhone.getText(),
                        txtClientSettingStatus.getText(),
                        Main.AppUser.GetCurrentAppUser().getId()));


                if (pass)
                {
                    Main.ShowAlert(Alert.AlertType.CONFIRMATION,"Information updated.");
                }
                else
                {
                    Main.ShowAlert(Alert.AlertType.ERROR,"Information didn't update, try again later.");
                }

            }
            else // If not match present an error
            {
                ShowError(true);
            }

        }
        else // When need to send update query without changing password.
        {
            boolean pass = sqlConnection.getInstance().SendQueryExecute(String.format("UPDATE users SET " +
                            "userFirstName='%s', " +
                            "userLastName='%s', " +
                            "userType='%d', " +
                            "userAddress='%s', " +
                            "userPhone='%s', " +
                            "userStatus='%s' WHERE userId='%s'",
                    txtClientSettingFirst.getText(),
                    txtClientSettingLast.getText(),
                    TypeUserToInteger(Main.AppUser.GetCurrentAppUser()),
                    txtClientSettingAddress.getText(),
                    txtClientSettingPhone.getText(),
                    txtClientSettingStatus.getText(),
                    Main.AppUser.GetCurrentAppUser().getId()));

            if (pass)
            {
                Main.ShowAlert(Alert.AlertType.CONFIRMATION,"Information updated.");
            }
            else
            {
                Main.ShowAlert(Alert.AlertType.ERROR,"Information didn't update, try again later.");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        lblClientSettingId.setText(Main.AppUser.GetCurrentAppUser().getId());
        txtClientSettingFirst.setText(Main.AppUser.GetCurrentAppUser().getFirstName());
        txtClientSettingLast.setText(Main.AppUser.GetCurrentAppUser().getLastName());
        txtClientSettingPhone.setText(Main.AppUser.GetCurrentAppUser().getPhone());
        txtClientSettingStatus.setText(Main.AppUser.GetCurrentAppUser().getStatus());
        txtClientSettingAddress.setText(Main.AppUser.GetCurrentAppUser().getAddress());

        ShowError(false);
    }

    private void ShowError(boolean newStatus)
    {
        lblError.setVisible(newStatus);

    }

}
