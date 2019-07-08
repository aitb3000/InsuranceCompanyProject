package app.Controllers.Salesman;

import app.Main;
import app.Models.Insurance;
import app.connection.sqlConnection;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
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

    private final double MoveXLeftInsurance = -200;
    private final double MoveXRightInsurance = 100;
    private final double MoveXLeftNewClient = -400;
    private final double MoveXRightNewClient = 200;

    @FXML
    private Pane pnlNewInsurance;

    @FXML
    private Button btnSendNew;

    @FXML
    private Button btnCancelNew;

    @FXML
    private AnchorPane anchorPaneNewInsurance;

    @FXML
    private AnchorPane anchorPaneRoot;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private ChoiceBox<String> cbInsurance;

    @FXML
    private CheckBox checkBoxNewClient;

    @FXML
    private Label lblNewInsuranceCheckLField;

    @FXML
    private AnchorPane anchorPaneNewClient;

    @FXML
    private TextField txtPass;

    @FXML
    private TextField txtAddr;

    @FXML
    private TextField txtPhone;

    @FXML
    private Label lblNewInsuranceCheckLField1;

    @FXML
    private TextField txtStatus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //TODO: Hide the errors labels and create handles!!!
        GetInsuranceFromXML();
    }


    @FXML
    void CancelNewInsuranceForm(ActionEvent event)
    {
        txtFirstName.clear();
        txtLastName.clear();
        txtId.clear();
    }

    @FXML
    void SendNewInsuranceForm(ActionEvent event) {
        if (CheckFields()) {
            lblNewInsuranceCheckLField.setVisible(false);
            if (checkBoxNewClient.isSelected())
            {
                //TODO: Checking INSERT query for the new User
                sqlConnection.getInstance().SendQuery("INSERT INTO users VALUES '" + txtId.getText() + "' '" + txtFirstName.getText() + "' '" + txtLastName.getText());
            }
            //TODO: Create a INSERT query
            sqlConnection.getInstance().SendQuery("INSERT INTO insurances VALUES" +
                    " ('" + Insurance.getInsuranceStatus((byte) 0) +
                    "','" + cbInsurance.getValue() +
                    "','" + txtId.getText() +
                    "','" + Main.AppUser.getId() + "')");

        }
        else
        {
            lblNewInsuranceCheckLField.setVisible(true);
        }
    }

    private boolean CheckFields()
    {
        if (txtId.getText().isEmpty()) return false;
        if (txtFirstName.getText().isEmpty()) return false;
        if (txtLastName.getText().isEmpty()) return false;

        return true;
    }



    private void GetInsuranceFromXML()
    {
        if ((Insurances!=null) && (!Insurances.isEmpty()))
            Insurances.clear();
        if (!AL_Insurances.isEmpty())
            AL_Insurances.clear();
        if (!cbInsurance.getItems().isEmpty())
            cbInsurance.getItems().clear();

        try
        {
            System.out.println("Loading Insurances types from xml file.");
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputFile = classLoader.getResourceAsStream("data/config.xml");

            //File inputFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Insurance");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    Insurance insurance = new Insurance();
                    insurance.setInsuranceId(eElement.getAttribute("InsuranceType"));
                    insurance.setInsuranceName(eElement.getElementsByTagName("InsuranceName").item(0).getTextContent());
                    AL_Insurances_string.add(insurance.getInsuranceName());
                    AL_Insurances.add(insurance);
                }
            }

            Insurances = FXCollections.observableArrayList(AL_Insurances_string);
            cbInsurance.setItems(Insurances);
            inputFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void StartAnimationInsuranceNewClient(ActionEvent event)
    {
        Timeline timeline = new Timeline();
        KeyValue kv;
        KeyFrame kf;

        if (checkBoxNewClient.isSelected())
        {
            // First is Moving the new insurance to the left
            kv = new KeyValue(anchorPaneNewInsurance.translateXProperty(), MoveXLeftInsurance, Interpolator.EASE_IN);
            timeline.setOnFinished(timeEvent -> ShowAnchorPane(anchorPaneNewClient, MoveXLeftNewClient ));
        }
        else
        {
            // First is Moving the new client to the right
            kv = new KeyValue(anchorPaneNewClient.translateXProperty(), MoveXRightNewClient, Interpolator.EASE_IN);
            timeline.setOnFinished(timeEvent -> ShowAnchorPane(anchorPaneNewInsurance, MoveXRightInsurance));
        }

        kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();

    }

    private void ShowAnchorPane(AnchorPane pane , double newXProperty)
    {
        Timeline timeline = new Timeline();
        KeyValue  kv = new KeyValue(pane.translateXProperty(), newXProperty, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }
}
