package app.Controllers.Salesman;

import app.Main;
import app.Models.Client;
import app.Models.ClientInsurance;
import app.Models.ClientInsuranceClaim;
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
    private TableColumn<ClientInsurance, String> tcClientStatus;

    @FXML
    private TableColumn<ClientInsurance, String> tcInsuranceName;

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
        ArrayList<Object[]> results = sqlConnection.getInstance().GetData("");

        if (!DataTable.isEmpty())
        {
            DataTable.clear();
        }

        if (!AllClientInsurance.isEmpty())
        {
            AllClientInsurance.clear();
        }

        if (!((Client) Main.AppUser).ClientInsurances.isEmpty()) {
            ((Client) Main.AppUser).ClientInsurances.clear();
        }

        for (Object[] row : results)
        {
            ClientInsurance clientInsurance = new ClientInsurance();
            clientInsurance.setId((String)row[0]);
            clientInsurance.setFirstName((String)row[1]);
            clientInsurance.setLastName((String)row[2]);
            clientInsurance.setInsuranceName((String)row[3]);
            clientInsurance.setInsuranceStatus((String)row[4]);
            ((Client) Main.AppUser).ClientInsurances.add(clientInsurance);
        }

        tcId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        tcFname.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        tcLname.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        tcClientStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        tcInsuranceName.setCellValueFactory(cellData -> cellData.getValue().insuranceNameProperty());
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
        Predicate<ClientInsurance> containText = insu -> insu.getId().contains(id);
        DataTable.setPredicate(containText);
    }

    private void UpdateClientInformation()
    {
        int index = AllClientInsurance.indexOf(tvInsurence.getSelectionModel().getSelectedItem());
        ClientInsurance clientInsurance = AllClientInsurance.get(index);
        lblCstatus.setText(clientInsurance.getStatus());
        lblFname.setText(clientInsurance.getFirstName());
        lblId.setText(clientInsurance.getId());
        lblInsuranceName.setText(clientInsurance.getInsuranceName());
        lblInsuranceType.setText(clientInsurance.getInsuranceType());
        lblLname.setText(clientInsurance.getLastName());
        lblInsuranceStatus.setText(clientInsurance.getInsuranceStatus());
    }
}
