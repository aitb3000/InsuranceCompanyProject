package app.Controllers.Client;

import app.Main;
import app.Models.ClientInsuranceClaim;
import app.connection.sqlConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Claims implements Initializable {

    private ObservableList<ClientInsuranceClaim> AllInsurances = FXCollections.observableArrayList();
    private FilteredList<ClientInsuranceClaim> DataTable = new FilteredList<>(AllInsurances);

    @FXML
    private Pane pnlInsurences;

    @FXML
    private TextField txtSearchInsurance;

    @FXML
    private Button btnShowAll;

    @FXML
    private TableView<ClientInsuranceClaim> tvInsurence;

    @FXML
    private TableColumn<ClientInsuranceClaim, String> tciid;

    @FXML
    private TableColumn<ClientInsuranceClaim, String> tcStatus;

    @FXML
    private TableColumn<ClientInsuranceClaim, String> tcClientId;

    @FXML
    private TableColumn<ClientInsuranceClaim, String> tcClientFname;

    @FXML
    private TableColumn<ClientInsuranceClaim, String> tcClientLname;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        if (!DataTable.isEmpty())
        {
            DataTable.clear();
        }

        if (!AllInsurances.isEmpty())
        {
            AllInsurances.clear();
        }

        //Get all insurances of a client.

        Main.AppUser.SetClientInsurances(sqlConnection.getInstance().GetClientInsurances());

        AllInsurances.addAll(Main.AppUser.GetClientInsuranceClaim());

        tciid.setCellValueFactory(cellData -> cellData.getValue().claimIdProperty());
        tcStatus.setCellValueFactory(cellData -> cellData.getValue().claimStatusProperty());
        tcClientId.setCellValueFactory(cellData -> cellData.getValue().clientIdProperty());
        tcClientFname.setCellValueFactory(cellData -> cellData.getValue().clientFirstNameProperty());
        tcClientLname.setCellValueFactory(cellData -> cellData.getValue().clientLastNameProperty());

        tvInsurence.setItems(DataTable);
    }


    @FXML
    void MouseSearchInsurance(MouseEvent event)
    {

    }

    @FXML
    void SearchInsurance(KeyEvent event)
    {

    }

    @FXML
    void ShowAll(ActionEvent event)
    {

    }


}
