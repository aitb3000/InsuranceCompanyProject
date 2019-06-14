package app.Controllers.Client;

import app.Main;
import app.Models.Client;
import app.Models.ClientInsurance;
import app.Models.Insurance;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class Insurances implements Initializable {

    private ObservableList<ClientInsurance> AllInsurances = FXCollections.observableArrayList();
    private FilteredList<ClientInsurance> DataTable = new FilteredList<ClientInsurance>(AllInsurances);

    @FXML
    private Pane pnlInsurences;

    @FXML
    private TextField txtSearchInsurance;

    @FXML
    private TableView<ClientInsurance> tvInsurence;

    @FXML
    private TableColumn<ClientInsurance, String> tcId;

    @FXML
    private TableColumn<ClientInsurance, String> tcName;

    @FXML
    private Button btnShowAll;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Object[]> results = sqlConnection.getInstance().GetData("");

        if (!DataTable.isEmpty())
        {
            DataTable.clear();
        }

        if (!AllInsurances.isEmpty())
        {
            AllInsurances.clear();
        }

        if (!((Client) Main.AppUser).ClientInsurances.isEmpty())
        {
            ((Client)Main.AppUser).ClientInsurances.clear();
        }


        for (Object[] row : results)
        {
            ClientInsurance insurance = new ClientInsurance();
            insurance.setInsuranceType((String) row[0]);
            insurance.setInsuranceName((String) row[1]);
            insurance.setInsuranceStatus((String) row[2]);
            ((Client)Main.AppUser).ClientInsurances.add(insurance);

        }

        AllInsurances.addAll(((Client) Main.AppUser).ClientInsurances);

        tcId.setCellValueFactory(cellData -> cellData.getValue().insuranceTypeProperty());
        tcName.setCellValueFactory(cellData -> cellData.getValue().insuranceNameProperty());

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

    private void SearchAndShowInsurance(String insurance)
    {
        Predicate<ClientInsurance> containText = insu -> insu.getInsuranceName().contains(insurance);
        DataTable.setPredicate(containText);
    }

    @FXML
    void ShowAll(ActionEvent event)
    {
        DataTable.setPredicate(null);
    }
}
