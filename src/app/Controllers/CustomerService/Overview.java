package app.Controllers.CustomerService;

import app.Main;
import app.Models.Claim;
import app.Models.CustomerService;
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

    private int claimsTotal = 0;
    private int claimsPending = 0;
    private int claimsDone = 0;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Label lblTotalOrders;

    @FXML
    private Label lblTotalDone;

    @FXML
    private Label lblPending;

    @FXML
    private PieChart pieChartClaims;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //Get all insurances of a client.
        if (((CustomerService) Main.AppUser).Claims.isEmpty()) {
            ((CustomerService) Main.AppUser).Claims = sqlConnection.getInstance().GetDataCustomerServicesClaims("SELECT * FROM claims WHERE claims.customerServiceId ='" + Main.AppUser.getId() + "'");
        }

        for (Claim claim : ((CustomerService) Main.AppUser).Claims) {
            if (claim.getClaimStatus().compareTo("Done") == 0)
                claimsDone++;
            else if (claim.getClaimStatus().compareTo("Pending") == 0)
                claimsPending++;
            claimsTotal++;
        }

        lblTotalOrders.setText(String.valueOf(claimsTotal));
        lblTotalDone.setText(String.valueOf(claimsDone));
        lblPending.setText(String.valueOf(claimsPending));

        SetPieChartData();
    }


    @FXML
    private void ShowPieChardPerst(MouseEvent event) {
        Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");

        for (final PieChart.Data data : pieChartClaims.getData())
        {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    e -> {
                        caption.setTranslateX(e.getSceneX());
                        caption.setTranslateY(e.getSceneY());
                        caption.setText(data.getPieValue() + "%");
                    });
        }
    }

    private void SetPieChartData() {
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Claims Total", claimsTotal),
                        new PieChart.Data("Claims Done", claimsDone),
                        new PieChart.Data("Claims Pending", claimsPending));
        pieChartClaims.setData(pieChartData);
        pieChartClaims.setTitle("Customer Service Claims");
    }
}
