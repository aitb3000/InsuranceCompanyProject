package app.Controllers.Client;

import app.Main;
import app.Models.Client;
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
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class Insurances implements Initializable {

    private ObservableList<Insurance> AllInsurances = FXCollections.observableArrayList();
    private FilteredList<Insurance> DataTable = new FilteredList<Insurance>(AllInsurances);

    @FXML
    private Pane pnlInsurences;

    @FXML
    private TextField txtSearchInsurance;

    @FXML
    private TableView<Insurance> tvInsurence;

    @FXML
    private TableColumn<Insurance, String> tcId;

    @FXML
    private TableColumn<Insurance, String> tcName;

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
            Insurance insurance = new Insurance();
            insurance.setInsuranceId((String) row[0]);
            insurance.setInsuranceName((String) row[1]);
            ((Client)Main.AppUser).ClientInsurances.add(insurance);
            AllInsurances.add(insurance);
        }


        tcId.setCellValueFactory(cellData -> cellData.getValue().insuranceIdProperty());
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
        Predicate<Insurance> containText = insu -> insu.getInsuranceName().contains(insurance);
        DataTable.setPredicate(containText);
    }

    @FXML
    void ShowAll(ActionEvent event)
    {
        DataTable.setPredicate(null);
    }
}
