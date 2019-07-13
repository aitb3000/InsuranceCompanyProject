package app.Controllers.CustomerService;


import app.Main;
import app.Models.Claim;
import app.Models.ClientInsuranceClaim;
import app.connection.loggerAPI;
import app.connection.sqlConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class Claims implements Initializable {

    private static ObservableList<ClientInsuranceClaim> AllClientInsuranceClaim = FXCollections.observableArrayList();
    private static FilteredList<ClientInsuranceClaim> DataTable = new FilteredList<>(AllClientInsuranceClaim);
    private final String Approved = "Approved";
    private final String Disapproved = "Disapproved";

    @FXML
    private Pane pnlInsurences;

    @FXML
    private TextField txtSearchInsurance;

    @FXML
    private Button btnShowAll;

    @FXML
    private Button btnApprove;

    @FXML
    private Button btnDisapprove;

    @FXML
    private TableView<ClientInsuranceClaim> tvClaims;

    @FXML
    private TableColumn<ClientInsuranceClaim, String> tcId;

    @FXML
    private TableColumn<ClientInsuranceClaim, String> tcFname;

    @FXML
    private TableColumn<ClientInsuranceClaim, String> tcLname;

    @FXML
    private TableColumn<ClientInsuranceClaim, String> tcInsuranceName;

    @FXML
    private TableColumn<ClientInsuranceClaim, String> tcInsuranceStatus;

    @FXML
    private TableColumn<ClientInsuranceClaim, String> tcClaimStatus;

    @FXML
    private Label lblId;

    @FXML
    private Label lblFname;

    @FXML
    private Label lblLname;

    @FXML
    private Label lblCstatus;

    @FXML
    private Label lblInsuranceType;

    @FXML
    private Label lblInsuranceName;

    @FXML
    private Label lblInsuranceStatus;

    @FXML
    private Label lblClaimType;

    @FXML
    private Label lblClaimStatus;

    @FXML
    void MouseSearchInsurance(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED)
        {
            SearchAndShowInsurance(txtSearchInsurance.getText());
        }
    }

    @FXML
    void SearchInsurance(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
        {
            SearchAndShowInsurance(txtSearchInsurance.getText());
        }
    }

    @FXML
    void ShowAll(ActionEvent event) {
        DataTable.setPredicate(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ResetDetails();

        if (!DataTable.isEmpty())
        {
            DataTable.clear();
        }

        if (!AllClientInsuranceClaim.isEmpty())
        {
            AllClientInsuranceClaim.clear();
        }

        Main.AppUser.SetClientInsuranceClaim(sqlConnection.getInstance().GetDataClientInsuranceClaim("SELECT * FROM claims"));

        AllClientInsuranceClaim.addAll(Main.AppUser.GetClientInsuranceClaim());

        tcId.setCellValueFactory(cellData -> cellData.getValue().clientIdProperty());
        tcFname.setCellValueFactory(cellData -> cellData.getValue().clientFirstNameProperty());
        tcLname.setCellValueFactory(cellData -> cellData.getValue().clientLastNameProperty());
        tcInsuranceName.setCellValueFactory(cellData -> cellData.getValue().insuranceNameProperty());
        tcInsuranceStatus.setCellValueFactory(cellData -> cellData.getValue().insuranceIdProperty());
        tcClaimStatus.setCellValueFactory(cellData -> cellData.getValue().claimStatusProperty());

        tvClaims.setItems(DataTable);
    }

    private void ResetDetails()
    {
        lblClaimStatus.setText("");
        lblClaimType.setText("");
        lblCstatus.setText("");
        lblFname.setText("");
        lblId.setText("");
        lblInsuranceName.setText("");
        lblInsuranceType.setText("");
        lblLname.setText("");
        lblInsuranceStatus.setText("");
    }

    private void SearchAndShowInsurance(String clientId)
    {
        Predicate<ClientInsuranceClaim> containText = insu -> insu.getClientId().contains(clientId);
        DataTable.setPredicate(containText);
    }


    @FXML
    void ApproveClaim(ActionEvent event)
    {
        SendNewClaimStatus(Approved);
        loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.GetCurrentAppUser().getUserName(), "Approved a Claim");
    }

    @FXML
    void DisapproveClaim(ActionEvent event)
    {
        SendNewClaimStatus(Disapproved);
        loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.GetCurrentAppUser().getUserName(), "Disapproved a Claim");
    }

    private void SendNewClaimStatus(String newStatus)
    {
        int index = AllClientInsuranceClaim.indexOf(tvClaims.getSelectionModel().getSelectedItem());
        if ((index >= 0) && (index < AllClientInsuranceClaim.size()))
        {
            ClientInsuranceClaim selectedClaim = tvClaims.getSelectionModel().getSelectedItem();

           boolean pass = sqlConnection.getInstance().SendQueryExecute("UPDATE claims SET " +
                    "claimStatus='"+newStatus+"'," +
                    "customerServiceId='"+ Main.AppUser.GetCurrentAppUser().getId()+"'," +
                    "customerServiceFname='"+Main.AppUser.GetCurrentAppUser().getFirstName()+"'," +
                    "customerServiceLname='"+Main.AppUser.GetCurrentAppUser().getLastName()+"'" +
                    "WHERE claimId='"+selectedClaim.getClaimId()+"'");

           if (pass)
           {
               if (newStatus.compareTo(Approved) == 0)
                    UpdateSelectedRow(true);
               else
                   UpdateSelectedRow(false);
               loggerAPI.getInstance().WriteLog(this.getClass().getName(), "System", "Data Saved.");
           }
        }
    }

    private void UpdateSelectedRow(boolean status) {
        int index = AllClientInsuranceClaim.indexOf(tvClaims.getSelectionModel().getSelectedItem());
        if ((index >= 0) && (index < AllClientInsuranceClaim.size()))
        {
            if (status)
            {
                AllClientInsuranceClaim.get(index).setClaimStatus("Approved");
            } else {
                AllClientInsuranceClaim.get(index).setClaimStatus("Disapproved");
            }
            tvClaims.refresh();
        }
    }



    @FXML
    void ShowClientInformationKeyboardHandler(KeyEvent event)
    {
        UpdateClientInformation();
    }

    private void UpdateClientInformation() {
        int index = AllClientInsuranceClaim.indexOf(tvClaims.getSelectionModel().getSelectedItem());
        if ((index >= 0) && (index < AllClientInsuranceClaim.size())) {
            ClientInsuranceClaim clientInsuranceClaim = AllClientInsuranceClaim.get(index);
            lblClaimStatus.setText(clientInsuranceClaim.getClaimStatus());
            lblClaimType.setText(clientInsuranceClaim.getClaimId());
            lblCstatus.setText(clientInsuranceClaim.getClientStatus());
            lblFname.setText(clientInsuranceClaim.getClientFirstName());
            lblId.setText(clientInsuranceClaim.getClientId());
            lblInsuranceName.setText(clientInsuranceClaim.getInsuranceName());
            lblInsuranceType.setText(clientInsuranceClaim.getInsuranceId());
            lblLname.setText(clientInsuranceClaim.getClientLastName());
            lblInsuranceStatus.setText(clientInsuranceClaim.getInsuranceStatus());
        }
    }


    @FXML
    void ShowClientInformationMouseHandler(MouseEvent event)
    {
        UpdateClientInformation();
    }


    public static void UpdateClaims()
    {
        Main.AppUser.SetClientInsuranceClaim(sqlConnection.getInstance().GetDataClientInsuranceClaim("SELECT * FROM claims"));
        AllClientInsuranceClaim.clear();
        AllClientInsuranceClaim.addAll(Main.AppUser.GetClientInsuranceClaim());
    }
}
