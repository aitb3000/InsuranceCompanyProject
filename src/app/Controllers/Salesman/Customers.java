package app.Controllers.Salesman;

import app.Main;
import app.Models.Client;
import app.Models.ClientInsurance;
import app.Models.Salesman;
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

public class Customers implements Initializable {

    private ObservableList<ClientInsurance> AllClientInsurance = FXCollections.observableArrayList();
    private FilteredList<ClientInsurance> DataTable = new FilteredList<ClientInsurance>(AllClientInsurance);

    @FXML
    private Pane pnlInsurences;

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
    private TableColumn<ClientInsurance, String> tcInsurnceId;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResetDetails();
        ArrayList<ClientInsurance> results = sqlConnection.getInstance().GetSalesmanClientInsurances("SELECT * FROM insurances WHERE insurances.salesmanId='" + Main.AppUser.getId() + "'");

        if (!DataTable.isEmpty())
        {
            DataTable.clear();
        }

        if (!AllClientInsurance.isEmpty())
        {
            AllClientInsurance.clear();
        }


        tcId.setCellValueFactory(cellData -> cellData.getValue().insuranceIdProperty());
        tcFname.setCellValueFactory(cellData -> cellData.getValue().ucLnameProperty());
        tcLname.setCellValueFactory(cellData -> cellData.getValue().ucFnameProperty());
        tcInsurnceId.setCellValueFactory(cellData -> cellData.getValue().insuranceIdProperty());
        tcInsuranceType.setCellValueFactory(cellData -> cellData.getValue().insuranceNameProperty());
        tcInsuranceStatus.setCellValueFactory(cellData -> cellData.getValue().insuranceStatusProperty());

        tvInsurence.setItems(DataTable);
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
            clientInsurance.setInsuranceStatus("Approved");
            tvInsurence.refresh();

            sqlConnection.getInstance().SendQuery("UPDATE [dbo].insurances SET istatus = '1' WHERE ucid= '" + clientInsurance.getUcId() + "'");
        }
    }

    @FXML
    void DisapproveInsurance(ActionEvent event) {
        int index = AllClientInsurance.indexOf(tvInsurence.getSelectionModel().getSelectedItem());
        if ((index >= 0) && (index < AllClientInsurance.size())) {
            ClientInsurance clientInsurance = AllClientInsurance.get(index);
            clientInsurance.setInsuranceStatus("Disapprove");
            tvInsurence.refresh();

            sqlConnection.getInstance().SendQuery("UPDATE [dbo].insurances SET istatus = '2' WHERE ucid= '" + clientInsurance.getUcId() + "'");
        }
    }
}
