package app.Controllers.CustomerService;

import app.Main;
import app.Models.Claim;
import app.Models.ClientInsurance;
import app.Models.ClientInsuranceClaim;
import app.connection.loggerAPI;
import app.connection.sqlConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import static app.Controllers.CustomerService.Claims.UpdateClaims;

public class Insurances implements Initializable {

    private ObservableList<ClientInsurance> AllInsurances = FXCollections.observableArrayList();
    private FilteredList<ClientInsurance> DataTable = new FilteredList<>(AllInsurances);

    @FXML
    private Button btnOpenClaim;

    @FXML
    private Pane pnlInsurences;

    @FXML
    private TextField txtSearchInsurance;

    @FXML
    private TableView<ClientInsurance> tvInsurence;

    @FXML
    private TableColumn<ClientInsurance, String> tciid;

    @FXML
    private TableColumn<ClientInsurance, String> tcType;

    @FXML
    private TableColumn<ClientInsurance, String> tcStatus;

    @FXML
    private TableColumn<ClientInsurance, String> tcClientId;

    @FXML
    private TableColumn<ClientInsurance, String> tcClientFname;

    @FXML
    private TableColumn<ClientInsurance, String> tcClientLname;

    @FXML
    private Button btnShowAll;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (!DataTable.isEmpty())
        {
            DataTable.clear();
        }

        if (!AllInsurances.isEmpty())
        {
            AllInsurances.clear();
        }

        //Get all insurances of a client.

        Main.AppUser.SetClientInsurances(sqlConnection.getInstance().GetAllInsurances());


        AllInsurances.addAll(Main.AppUser.GetClientInsurances());

        tciid.setCellValueFactory(cellData -> cellData.getValue().insuranceIdProperty());
        tcType.setCellValueFactory(cellData -> cellData.getValue().insuranceNameProperty());
        tcStatus.setCellValueFactory(cellData -> cellData.getValue().insuranceStatusProperty());
        tcClientId.setCellValueFactory(cellData -> cellData.getValue().clientIdProperty());
        tcClientFname.setCellValueFactory(cellData -> cellData.getValue().clientFirstNameProperty());
        tcClientLname.setCellValueFactory(cellData -> cellData.getValue().clientLastNameProperty());

        tvInsurence.setItems(DataTable);
    }

    @FXML
    void SearchInsurance(KeyEvent event)
    {
        if (event.getCode() == KeyCode.ENTER)
        {
            SearchAndShowInsurance(txtSearchInsurance.getText());
        }
    }

    @FXML
    void MouseSearchInsurance(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED)
        {
            SearchAndShowInsurance(txtSearchInsurance.getText());
        }
    }

    private void SearchAndShowInsurance(String clientId)
    {
        Predicate<ClientInsurance> containText = insu -> insu.getClientId().contains(clientId);
        DataTable.setPredicate(containText);
    }

    @FXML
    void ShowAll(ActionEvent event)
    {
        DataTable.setPredicate(null);
    }


    @FXML
    private void OpenClaim(ActionEvent event)
    {
        int index = tvInsurence.getSelectionModel().getSelectedIndex();

        if (index >= 0 )
        {
            ClientInsurance selectedInsurance = tvInsurence.getSelectionModel().getSelectedItem();
            if (CheckInsuranceClaim(selectedInsurance))
            {
                Main.ShowAlert(Alert.AlertType.ERROR,"You already open a claim foShowAllr this Insurance.");
                return;
            }
            boolean pass = sqlConnection.getInstance().SendQueryExecute(String.format("INSERT INTO claims VALUES " +
                            "('%s', '%s', '%s', '%s','%d', '%s' , '%s', '%s', '%s', '%s')",
                    Claim.getClaimStatus((byte)1),
                    selectedInsurance.getClientId(),
                    selectedInsurance.getClientFirstName(),
                    selectedInsurance.getClientLastName(),
                    Integer.valueOf(selectedInsurance.getInsuranceId()),
                    selectedInsurance.getInsuranceName(),
                    selectedInsurance.getInsuranceStatus(),
                    Main.AppUser.GetCurrentAppUser().getId(),
                    Main.AppUser.GetCurrentAppUser().getFirstName(),
                    Main.AppUser.GetCurrentAppUser().getLastName()));

            if (pass)
            {
                Main.ShowAlert(Alert.AlertType.CONFIRMATION, "Claim Opened.");
                loggerAPI.getInstance().WriteLog(this.getClass().getName(), "System", "New Claim added.");
                loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.GetCurrentAppUser().getUserName(), " Opened a new Claim.");
                UpdateClaims();
                tvInsurence.refresh();
            }
            else {
                Main.ShowAlert(Alert.AlertType.ERROR, "Cannot add a claim try again later.");
                loggerAPI.getInstance().WriteLog(this.getClass().getName(), "System", "Cannot add a claim try again later.");
            }
        }
    }

    private boolean CheckInsuranceClaim(ClientInsurance selectedInsurance)
    {

        ArrayList<ClientInsuranceClaim> temp = Main.AppUser.GetClientInsuranceClaim();
        for (ClientInsuranceClaim claim: temp )
        {
         if (claim.getInsuranceId().compareTo(selectedInsurance.getInsuranceId()) == 0)
         {
             return true;
         }
        }
        return false;
    }
}
