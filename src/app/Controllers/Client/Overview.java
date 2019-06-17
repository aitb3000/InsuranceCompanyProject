package app.Controllers.Client;

import app.Main;
import app.Models.*;
import app.connection.sqlConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Get all insurances of a client.
        if (((Client)Main.AppUser).ClientInsurances.isEmpty())
        {
            ((Client)Main.AppUser).ClientInsurances = sqlConnection.getInstance().GetClientInsurances();
        }

        if (((Client)Main.AppUser).ClientInsuranceClaim.isEmpty())
        {
            ((Client)Main.AppUser).ClientInsuranceClaim = sqlConnection.getInstance().GetDataClientInsuranceClaim("SELECT * FROM dbo.users, dbo.claims WHERE users.userId = '" + Main.AppUser.getId() +"'");
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
    }
}
