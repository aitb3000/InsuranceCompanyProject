package app.Controllers.Salesman;

import app.Main;
import app.Models.Client;
import app.Models.ClientInsurance;
import app.Models.Insurance;
import app.Models.Salesman;
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

public class Customers implements Initializable {

    private ObservableList<ClientInsurance> AllClientInsurance;
    private FilteredList<ClientInsurance> DataTable;
    private ArrayList<ClientInsurance> AL_ClientInsurance;


    @FXML
    private TextField txtSearchInsurance;

    @FXML
    private Button btnShowAll;

    @FXML
    private TableView<ClientInsurance> tvInsurence;

    @FXML
    private TableColumn<ClientInsurance, String> tcId;

    @FXML
    private TableColumn<ClientInsurance, String> tcFname;

    @FXML
    private TableColumn<ClientInsurance, String> tcLname;

    @FXML
    private TableColumn<ClientInsurance, String> tcInsuranceType;

    @FXML
    private TableColumn<ClientInsurance, String> tcInsuranceStatus;

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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResetDetails();

        AL_ClientInsurance = sqlConnection.getInstance().GetSalesmanClientInsurances(
                "SELECT * FROM insurances WHERE insurances.salesmanId='" + Main.AppUser.GetCurrentAppUser().getId() + "'");

        AllClientInsurance = FXCollections.observableArrayList(AL_ClientInsurance);
        DataTable = new FilteredList<>(AllClientInsurance);

        tcId.setCellValueFactory(cellData -> cellData.getValue().insuranceIdProperty());
        tcFname.setCellValueFactory(cellData -> cellData.getValue().ucLnameProperty());
        tcLname.setCellValueFactory(cellData -> cellData.getValue().ucFnameProperty());
        tcInsuranceType.setCellValueFactory(cellData -> cellData.getValue().insuranceNameProperty());
        tcInsuranceStatus.setCellValueFactory(cellData -> cellData.getValue().insuranceStatusProperty());

        tvInsurence.setItems(DataTable);
    }




    @FXML
    void MouseSearchInsurance(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED)
        {
            SearchAndShowInsurance(txtSearchInsurance.getText());
        }
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
    void ShowAll(ActionEvent event) {
        DataTable.setPredicate(null);
    }

    @FXML
    void ShowClientInformationKeyboardHandler(KeyEvent event)
    {
        UpdateClientInformation();
    }

    @FXML
    void ShowClientInformationMouseHandler(MouseEvent event)
    {
        UpdateClientInformation();
    }


    private void ResetDetails()
    {
        lblCstatus.setText("");
        lblFname.setText("");
        lblId.setText("");
        lblInsuranceName.setText("");
        lblInsuranceType.setText("");
        lblLname.setText("");
        lblInsuranceStatus.setText("");
    }

    private void SearchAndShowInsurance(String id)
    {
        Predicate<ClientInsurance> containText = insu -> insu.getUcId().contains(id);
        DataTable.setPredicate(containText);
    }

    private void UpdateClientInformation()
    {
        int index = AllClientInsurance.indexOf(tvInsurence.getSelectionModel().getSelectedItem());
        if ((index < AllClientInsurance.size()) && (index >= 0))
        {
            ClientInsurance clientInsurance = AllClientInsurance.get(index);
            lblCstatus.setText(clientInsurance.getUcStatus());
            lblFname.setText(clientInsurance.getUcFname());
            lblId.setText(clientInsurance.getUcId());
            lblInsuranceName.setText(clientInsurance.getInsuranceName());
            lblInsuranceType.setText(clientInsurance.getInsuranceType());
            lblLname.setText(clientInsurance.getUcLname());
            lblInsuranceStatus.setText(clientInsurance.getInsuranceStatus());
        }
    }


    @FXML
    void ApproveInsurance(ActionEvent event) {
        int index = AllClientInsurance.indexOf(tvInsurence.getSelectionModel().getSelectedItem());
        if ((index >= 0) && (index < AllClientInsurance.size()))
        {
            ClientInsurance clientInsurance = AllClientInsurance.get(index);

            boolean pass = sqlConnection.getInstance().SendQueryExecute(String.format("UPDATE insurances SET " +
                            "insuranceStatus='%s' WHERE insuranceId='%s'",
                    Insurance.getInsuranceStatus("Approved"),
                    clientInsurance.getInsuranceId()));

            if (pass)
            {
                loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.GetCurrentAppUser().getUserName(), "Insurance Approved");
                clientInsurance.setInsuranceStatus("Approved");
                tvInsurence.refresh();
            }
            else
            {
                Main.ShowAlert(Alert.AlertType.ERROR,"Cannot update insurance status, please try again later.");
            }
        }

    }

    @FXML
    void DisapproveInsurance(ActionEvent event) {
        int index = AllClientInsurance.indexOf(tvInsurence.getSelectionModel().getSelectedItem());
        if ((index >= 0) && (index < AllClientInsurance.size())) {
            ClientInsurance clientInsurance = AllClientInsurance.get(index);

            boolean pass = sqlConnection.getInstance().SendQueryExecute(String.format("UPDATE insurances SET " +
                            "insuranceStatus='%s' WHERE insuranceId='%s'",
                    Insurance.getInsuranceStatus("Disapproved"),
                    clientInsurance.getInsuranceId()));

            if (pass == true)
            {
                loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.GetCurrentAppUser().getUserName(), "Insurance Disapproved");
                clientInsurance.setInsuranceStatus("Disapprove");
                tvInsurence.refresh();
            }
            else
            {
                Main.ShowAlert(Alert.AlertType.ERROR,"Cannot update insurance status, please try again later.");
            }
        }
    }
}
