package app.Controllers.Client;

import app.Main;
import app.Models.*;
import app.connection.sqlConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Overview implements Initializable {

    private int insuranceTotal = 0;
    private int insurancePending = 0;
    private int insuranceDone = 0;
    private int claimTotal = 0;
    private int claimPending = 0;
    private int claimApproved = 0;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Label lblTotalOrders;

    @FXML
    private Label lblTotalDone;

    @FXML
    private Label lblPending;

    @FXML
    private Label lblClientPhone;

    @FXML
    private Label lblClientAddress;

    @FXML
    private Label lblClientLastName;

    @FXML
    private Label lblClientStatus;

    @FXML
    private Label lblClientFirstName;

    @FXML
    private Label lblClientId;

    @FXML
    private Label lblTotalClaims;

    @FXML
    private Label lvlTotalApproved;

    @FXML
    private Label lvlPedingClaims;

    @FXML
    private PieChart pieChartClaims;

    @FXML
    private PieChart pieChartSales;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Get all insurances of a client.
        if (((Client)Main.AppUser).ClientInsurances.isEmpty())
        {
            ((Client)Main.AppUser).ClientInsurances = sqlConnection.getInstance().GetClientInsurances();
        }

        if (((Client)Main.AppUser).ClientInsuranceClaim.isEmpty())
        {
            ((Client)Main.AppUser).ClientInsuranceClaim = sqlConnection.getInstance().GetDataClientInsuranceClaim("SELECT * FROM claims WHERE claims.clientId = '" + Main.AppUser.getId() +"'");
        }

        lblClientId.setText(Main.AppUser.getId());
        lblClientFirstName.setText(Main.AppUser.getFirstName());
        lblClientLastName.setText(Main.AppUser.getLastName());
        lblClientPhone.setText(Main.AppUser.getPhone());
        lblClientStatus.setText(Main.AppUser.getStatus());
        lblClientAddress.setText(Main.AppUser.getAddress());

        for (ClientInsurance insurance :((Client)Main.AppUser).ClientInsurances)
        {
            if (insurance.getInsuranceStatus().compareTo(Insurance.getInsuranceStatus((byte)1)) == 0)
                insuranceDone++;
            else if (insurance.getInsuranceStatus().compareTo("None") == 0)
                insurancePending++;
            insuranceTotal++;
        }

        for (ClientInsuranceClaim insuranceClaim : ((Client)Main.AppUser).ClientInsuranceClaim)
        {
            if (insuranceClaim.getClaimStatus().compareTo("Approved") == 0)
            {
                claimApproved++;
            }
            else if (insuranceClaim.getClaimStatus().compareTo("Pending") == 0)
            {
                claimPending++;
            }
            claimTotal++;
        }

        lblTotalOrders.setText(String.valueOf(insuranceTotal));
        lblTotalDone.setText(String.valueOf(insuranceDone));
        lblPending.setText(String.valueOf(insurancePending));
        lblTotalClaims.setText(String.valueOf(claimTotal));
        lvlTotalApproved.setText(String.valueOf(claimApproved));
        lvlPedingClaims.setText(String.valueOf(claimPending));

        SetPieChartData();
    }

    @FXML
    void ShowPieChardPerst(MouseEvent event)
    {
        Label caption1 = new Label("");
        caption1.setTextFill(Color.DARKORANGE);
        //caption1.setStyle("-fx-font: 12 arial;");
        for (final PieChart.Data data : pieChartClaims.getData())
        {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    e -> {
                        caption1.setTranslateX(e.getSceneX());
                        caption1.setTranslateY(e.getSceneY());
                        caption1.setText(data.getPieValue() + "%");
                    });
        }

        Label caption2 = new Label("");
        caption2.setTextFill(Color.DARKORANGE);
        //caption2.setStyle("-fx-font: 12 arial;");

        for (final PieChart.Data data : pieChartSales.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    e -> {
                        caption2.setTranslateX(e.getSceneX());
                        caption2.setTranslateY(e.getSceneY());
                        caption2.setText((data.getPieValue()) + "%");
                    });
        }
    }

    private void SetPieChartData()
    {
        ObservableList<PieChart.Data> pieChartData1 =
                FXCollections.observableArrayList(
                        new PieChart.Data("Claims Total", claimTotal),
                        new PieChart.Data("Claims Approved", claimApproved),
                        new PieChart.Data("Claims Pending", claimPending));
        pieChartClaims.setData(pieChartData1);
        pieChartClaims.setTitle("Claims");

        ObservableList<PieChart.Data> pieChartData2 =
                FXCollections.observableArrayList(
                        new PieChart.Data("Insurance Total", insuranceTotal),
                        new PieChart.Data("Insurance Done", insuranceDone),
                        new PieChart.Data("Insurance Pending", insurancePending));
        pieChartSales.setData(pieChartData2);
        pieChartSales.setTitle("Insurances");
    }
}
