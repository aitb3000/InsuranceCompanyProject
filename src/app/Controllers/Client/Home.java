package app.Controllers.Client;

import app.Main;
import app.connection.loggerAPI;
import app.connection.sqlConnection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable {

    private Pane paneInsurance;
    private Pane paneNewInsurance;
    private Pane paneOverview;
    private Pane paneSettings;
    private Pane paneClaims;

    @FXML
    private VBox vBoxMenu;

    @FXML
    private Label lblUserFullName;

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnInsurences;

    @FXML
    private Button btnSetting;

    @FXML
    private Button btnSignout;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnClaims;

    @FXML
    private StackPane spHome;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        lblUserFullName.setText(Main.AppUser.GetCurrentAppUser().getUserName());

        try
        {
            paneInsurance = FXMLLoader.load(Main.class.getResource("/app/View/Client/Insurances.fxml"));
            paneOverview = FXMLLoader.load(Main.class.getResource("/app/View/Client/Overview.fxml"));
            paneSettings = FXMLLoader.load(Main.class.getResource("/app/View/Settings.fxml"));
            paneClaims = FXMLLoader.load(Main.class.getResource("/app/View/Client/Claims.fxml"));
            spHome.getChildren().addAll(paneOverview,paneInsurance,paneSettings,paneClaims);
            paneOverview.toFront();

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnInsurences)
        {
            paneInsurance.toFront();
            loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.GetCurrentAppUser().getUserName(),"Enter to his Insurances view.");
        }
        if (actionEvent.getSource() == btnOverview)
        {
            paneOverview.toFront();
            loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.GetCurrentAppUser().getUserName(),"Enter to Overview view.");
        }
        if (actionEvent.getSource() == btnSetting)
        {
            paneSettings.toFront();
            loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.GetCurrentAppUser().getUserName(),"Enter to Settings view.");
        }
        if (actionEvent.getSource() == btnClaims)
        {
            paneClaims.toFront();
            loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.GetCurrentAppUser().getUserName(),"Enter to Claims view.");
        }
    }

    @FXML
    private void Signout(ActionEvent actionEvent)
    {
        try
        {
            sqlConnection.getInstance().CloseConnection();
            loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.GetCurrentAppUser().getUserName(),"Sign out.");
            Main.ShowLogin();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    private void Exit(ActionEvent actionEvent)
    {
        sqlConnection.getInstance().CloseConnection();
        loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.GetCurrentAppUser().getUserName(),"Exit from application.");
        loggerAPI.getInstance().CloseLogger();
        Platform.exit();
    }
}
