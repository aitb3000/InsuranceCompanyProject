package app.Controllers.Client;

import app.Main;
import app.Models.Insurance;
import app.connection.sqlConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewInsurance implements Initializable {

    private ObservableList<String> Insurances;
    private ArrayList<String> AL_Insurances_string = new ArrayList<>();
    private ArrayList<Insurance> AL_Insurances = new ArrayList<>();

    @FXML
    private Pane pnlNewInsurance;

    @FXML
    private Label lblClientName;

    @FXML
    private Label lblNewInsuranceCheckLField;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private ChoiceBox<String> cbInsurance;

    @FXML
    private Button btnSendNew;

    @FXML
    private Button btnCancelNew;


    @FXML
    void CancelNewInsuranceForm(ActionEvent event)
    {
        txtFirstName.setText(Main.AppUser.getFirstName());
        txtLastName.setText(Main.AppUser.getLastName());
        txtId.setText(Main.AppUser.getId());
        lblNewInsuranceCheckLField.setVisible(false);
    }

    @FXML
    void SendNewInsuranceForm(ActionEvent event)
    {
        if (CheckFields())
        {
            lblNewInsuranceCheckLField.setVisible(false);
            lblNewInsuranceCheckLField.setVisible(false);

            sqlConnection.getInstance().SendQuery(String.format("INSERT INTO insurances VALUES ('%s','%d','%s','%s','%s','%s','%s','%s','%s')",
                    txtId.getText(),
                    cbInsurance.getSelectionModel().getSelectedIndex(),
                    Insurance.getInsuranceStatus((byte) 0),
                    cbInsurance.getValue(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    Main.AppUser.getId(),
                    Main.AppUser.getFirstName(),
                    Main.AppUser.getLastName()));
        }
        else
        {
            lblNewInsuranceCheckLField.setVisible(true);
        }

    }

    private boolean CheckFields()
    {
        if ((txtId.getText().isEmpty()) || (txtId.getText().length() !=9))
            return false;
        if ((txtLastName.getText().isEmpty()) || (txtFirstName.getText().isEmpty()))
            return false;
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblClientName.setText(Main.AppUser.getUserName());
        lblNewInsuranceCheckLField.setVisible(false);
        GetInsuranceFromXML();
        txtId.setText(Main.AppUser.getId());
        txtFirstName.setText(Main.AppUser.getFirstName());
        txtLastName.setText(Main.AppUser.getLastName());
    }

    private void GetInsuranceFromXML()
    {
        if ((Insurances!=null) && (!Insurances.isEmpty()))
            Insurances.clear();
        if (!AL_Insurances.isEmpty())
            AL_Insurances.clear();
        if (!cbInsurance.getItems().isEmpty())
            cbInsurance.getItems().clear();

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputFile = classLoader.getResourceAsStream("data/config.xml");

            //File inputFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("Insurance");
            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {


                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    Insurance insurance = new Insurance();
                    insurance.setInsuranceId(eElement.getAttribute("InsuranceType"));
                    insurance.setInsuranceName(eElement.getElementsByTagName("InsuranceName").item(0).getTextContent());
                    AL_Insurances.add(insurance);
                    AL_Insurances_string.add(insurance.getInsuranceName());
                    System.out.println("Insurance Type Number : "
                            + eElement.getAttribute("InsuranceType"));
                    System.out.println("Insurance Type Name : "
                            + eElement
                            .getElementsByTagName("InsuranceName")
                            .item(0)
                            .getTextContent());
                }
            }
            Insurances = FXCollections.observableArrayList(AL_Insurances_string);
            cbInsurance.setItems(Insurances);
            inputFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
