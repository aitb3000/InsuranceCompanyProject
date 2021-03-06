package app.Controllers.CustomerService;

import app.Main;
import app.connection.loggerAPI;
import app.connection.sqlConnection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable {

    private Pane paneClaims;
    private Pane paneOverview;
    private Pane paneSettings;
    private Pane  paneInsurance;

    @FXML
    private Label txtUserFullName;

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnNewClaim;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    @FXML
    private Button btnExit;

    @FXML
    private StackPane spHome;

    @FXML
    private Button btnInsurences;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        txtUserFullName.setText(Main.AppUser.GetCurrentAppUser().getUserName());

        try
        {
            paneClaims = FXMLLoader.load(Main.class.getResource("/app/View/CustomerService/Claims.fxml"));
            paneInsurance = FXMLLoader.load(Main.class.getResource("/app/View/CustomerService/Insurances.fxml"));
            paneOverview = FXMLLoader.load(Main.class.getResource("/app/View/CustomerService/Overview.fxml"));
            paneSettings = FXMLLoader.load(Main.class.getResource("/app/View/Settings.fxml"));
            spHome.getChildren().addAll(paneOverview, paneInsurance, paneClaims, paneSettings);
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
        if (actionEvent.getSource() == btnNewClaim)
        {
            paneClaims.toFront();
            loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.GetCurrentAppUser().getUserName(),"Enter to Claims view.");
        }
        if (actionEvent.getSource() == btnOverview)
        {
            paneOverview.toFront();
            loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.GetCurrentAppUser().getUserName(),"Enter to his Overview view.");
        }
        if(actionEvent.getSource()==btnSettings)
        {
            paneSettings.toFront();
            loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.GetCurrentAppUser().getUserName(),"Enter to Settings view.");
        }
    }


    public void Signout(ActionEvent actionEvent)
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
    public void Exit(ActionEvent actionEvent)
    {
        sqlConnection.getInstance().CloseConnection();
        loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.GetCurrentAppUser().getUserName(),"Exit from application.");
        loggerAPI.getInstance().CloseLogger();
        Platform.exit();
    }
}
