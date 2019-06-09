package app.Controllers.Client;

import app.Main;
import app.connection.loggerAPI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
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
    private Button btnNewInsurance;

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
            paneNewInsurance  = FXMLLoader.load(Main.class.getResource("/app/View/Client/NewInsurance.fxml"));
            paneOverview = FXMLLoader.load(Main.class.getResource("/app/View/Client/Overview.fxml"));
            paneSettings = FXMLLoader.load(Main.class.getResource("/app/View/Client/Settings.fxml"));
            spHome.getChildren().addAll(paneOverview,paneInsurance,paneNewInsurance,paneSettings);
            paneOverview.toFront();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }


        Node[] nodes = new Node[10];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("/app/temp/Item.fxml"));

                //give the items some effect

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #0A0E3F");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #02030A");
                });
                //pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnOverview) {
            //paneOverview.setStyle("-fx-background-color : #02030A");
            paneOverview.toFront();
            loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.getUserName(),"Enter to Overview view.");
        }
        if (actionEvent.getSource() == btnInsurences) {
            //paneInsurance.setStyle("-fx-background-color : #1620A1");
            paneInsurance.toFront();
            loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.getUserName(),"Enter to his Insurances view.");
        }
        if (actionEvent.getSource() == btnNewInsurance) {
            //paneNewInsurance.setStyle("-fx-background-color : #53639F");
            paneNewInsurance.toFront();
            loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.getUserName(),"Enter to New Insurance view.");
        }

        if (actionEvent.getSource() == btnSetting) {
            //paneSettings.setStyle("-fx-background-color : #464F67");
            paneSettings.toFront();
            loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.getUserName(),"Enter to Settings view.");
        }
    }

    @FXML
    private void Signout(ActionEvent actionEvent)
    {
        try
        {
            loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.getUserName(),"Sign out.");
            loggerAPI.getInstance().CloseLogger();
            Main.ShowLogin();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    @FXML
    private void Exit(ActionEvent actionEvent)
    {
        loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.getUserName(),"Exit from application.");
        loggerAPI.getInstance().CloseLogger();
        Platform.exit();
    }
}
