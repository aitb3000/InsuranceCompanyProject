package app.Controllers.Salesman;

import app.Main;
import app.Models.ClientInsurance;
import app.Models.Salesman;
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

    @FXML
    private Pane pnlOverview;

    @FXML
    private Label lblTotalOrders;

    @FXML
    private Label lblTotalDone;

    @FXML
    private Label lblPending;

    @FXML
    private PieChart pieChartSales;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Get all insurances of a client.
        if (((Salesman) Main.AppUser).ClientsInsurances.isEmpty()) {
            ((Salesman) Main.AppUser).ClientsInsurances = sqlConnection.getInstance().GetSalesmanClientInsurances("SELECT * FROM insurances WHERE insurances.salesmanId ='" + Main.AppUser.getId() + "'");
        }

        for (ClientInsurance clientInsurance : ((Salesman) Main.AppUser).ClientsInsurances) {
            if (clientInsurance.getInsuranceStatus().compareTo("Done") == 0)
                insuranceDone++;
            else if (clientInsurance.getInsuranceStatus().compareTo("Pending") == 0)
                insurancePending++;
            insuranceTotal++;
        }

        lblTotalOrders.setText(String.valueOf(insuranceTotal));
        lblTotalDone.setText(String.valueOf(insuranceDone));
        lblPending.setText(String.valueOf(insurancePending));

        SetPieChartData();

    }


    private void SetPieChartData() {
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Insurance Total", insuranceTotal),
                        new PieChart.Data("Insurance Done", insuranceDone),
                        new PieChart.Data("Insurance Pending", insurancePending));
        pieChartSales.setData(pieChartData);
        pieChartSales.setTitle("Salesman Insurances Sales");
    }


    @FXML
    private void ShowPieChardPerst(MouseEvent event) {
        Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");

        for (final PieChart.Data data : pieChartSales.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    e -> {
                        caption.setTranslateX(e.getSceneX());
                        caption.setTranslateY(e.getSceneY());
                        caption.setText(String.valueOf(data.getPieValue()) + "%");
                    });
        }
    }

}