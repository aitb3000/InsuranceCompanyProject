package app.Controllers.Salesman;

import app.Main;
import app.connection.loggerAPI;
import app.connection.sqlConnection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable {

    private Pane paneCustomers;
    private Pane paneOverview;
    private Pane paneSettings;
    private Pane paneNewInsurance;


    @FXML
    private Button btnNewInsurance;

    @FXML
    private Label txtUserFullName;

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    @FXML
    private Button btnExit;

    @FXML
    private StackPane spHome;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtUserFullName.setText(Main.AppUser.getUserName());

        try {
            paneCustomers = FXMLLoader.load(Main.class.getResource("/app/View/Salesman/Customers.fxml"));
            paneNewInsurance = FXMLLoader.load(Main.class.getResource("/app/View/Salesman/NewInsurance.fxml"));
            paneOverview = FXMLLoader.load(Main.class.getResource("/app/View/Salesman/Overview.fxml"));
            paneSettings = FXMLLoader.load(Main.class.getResource("/app/View/Salesman/Settings.fxml"));
            spHome.getChildren().addAll(paneOverview, paneCustomers, paneNewInsurance, paneSettings);
            paneOverview.toFront();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnCustomers) {
            //paneCustomers.setStyle("-fx-background-color : #1620A1");
            paneCustomers.toFront();
        }
        if (actionEvent.getSource() == btnOverview) {
            //paneOverview.setStyle("-fx-background-color : #02030A");
            paneOverview.toFront();
        }
        if(actionEvent.getSource()==btnSettings)
        {
            //paneOrders.setStyle("-fx-background-color : #464F67");
            paneSettings.toFront();
        }
        if(actionEvent.getSource()==btnNewInsurance)
        {
            //paneNewInsurance.setStyle("-fx-background-color : #53639F");
            paneNewInsurance.toFront();
        }
    }


    public void Signout(ActionEvent actionEvent)
    {
        try
        {
            sqlConnection.getInstance().CloseConnection();
            loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.getUserName(),"Sign out.");
            loggerAPI.getInstance().CloseLogger();
            Main.ShowLogin();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    public void Exit(ActionEvent actionEvent)
    {
        sqlConnection.getInstance().CloseConnection();
        loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.getUserName(),"Exit from application.");
        loggerAPI.getInstance().CloseLogger();
        Platform.exit();
    }
}
