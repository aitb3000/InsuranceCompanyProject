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

import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable {

    private Pane paneInsurance;
    private Pane paneNewInsurance;
    private Pane paneOverview;
    private Pane paneSettings;

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
    private StackPane spHome;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        lblUserFullName.setText(Main.AppUser.getUserName());

        try
        {
            paneInsurance = FXMLLoader.load(Main.class.getResource("/app/View/Client/Insurances.fxml"));
            paneOverview = FXMLLoader.load(Main.class.getResource("/app/View/Client/Overview.fxml"));
            paneSettings = FXMLLoader.load(Main.class.getResource("/app/View/Settings.fxml"));
            spHome.getChildren().addAll(paneOverview,paneInsurance,paneSettings);
            paneOverview.toFront();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnInsurences)
        {
            paneInsurance.setStyle("-fx-background-color : #030a12");
            paneInsurance.toFront();
            loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.getUserName(),"Enter to his Insurances view.");
        }
        if (actionEvent.getSource() == btnOverview)
        {
            paneOverview.setStyle("-fx-background-color : #030a12");
            paneOverview.toFront();
            loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.getUserName(),"Enter to Overview view.");
        }
        if (actionEvent.getSource() == btnSetting)
        {
            paneSettings.setStyle("-fx-background-color : #030a12");
            paneSettings.toFront();
            loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.getUserName(),"Enter to Settings view.");
        }
    }

    @FXML
    private void Signout(ActionEvent actionEvent)
    {
        try
        {
            sqlConnection.getInstance().CloseConnection();
            loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.getUserName(),"Sign out.");
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
        loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.getUserName(),"Exit from application.");
        loggerAPI.getInstance().CloseLogger();
        Platform.exit();
    }
}
