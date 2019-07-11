package app.Controllers.Salesman;

import app.Main;
import app.Models.Client;
import app.Models.Insurance;
import app.connection.loggerAPI;
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

import static app.Models.TypeUser.eClient;
import static app.Models.UserInterface.TypeUserToInteger;

public class NewInsurance implements Initializable {

    private ObservableList<String> Insurances;
    private ArrayList<String> AL_Insurances_string = new ArrayList<>();
    private ArrayList<Insurance> AL_Insurances = new ArrayList<>();
    private ArrayList<Client> AL_UserClients;
    private ObservableList<Client> UserClients;

    private final double MoveXLeftInsurance = -200;
    private final double MoveXRightInsurance = 50;
    private final double MoveXLeftNewClient = -400;
    private final double MoveXRightNewClient = 100;

    @FXML
    private Pane pnlNewInsurance;

    @FXML
    private AnchorPane anchorPaneRoot;

    @FXML
    private Button btnSendNew;

    @FXML
    private Button btnCancelNew;

    @FXML
    private AnchorPane anchorPaneNewInsurance;

    @FXML
    private Label lblClientPhone;

    @FXML
    private Label lblClientAddress;

    @FXML
    private Label lblClientLastName;

    @FXML
    private Label lblClientStatus;

    @FXML
    private Label lblClientFirstName;

    @FXML
    private ChoiceBox<String> cbInsurance;

    @FXML
    private CheckBox checkBoxNewClient;

    @FXML
    private Label lblNewInsuranceCheckLField;

    @FXML
    private ChoiceBox<Client> cbId;

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

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ClearFields();
        GetAllClients();
        GetInsuranceFromXML();
        HideErrors();
    }

    private void ClearFields()
    {
        ResetNewClientFields();
        ResetExistClientFields();
    }

    private void ResetExistClientFields()
    {
        lblClientStatus.setText("");
        lblClientPhone.setText("");
        lblClientAddress.setText("");
        lblClientLastName.setText("");
        lblClientFirstName.setText("");
    }

    private void GetAllClients()
    {
       AL_UserClients = sqlConnection.getInstance().GetAllUserClients();
       UserClients = FXCollections.observableArrayList(AL_UserClients);

       cbId.setItems(UserClients);

        // if the item of the list is changed
        cbId.getSelectionModel().selectedIndexProperty().addListener((ov, value, new_value) -> {
            Client selectedClient = AL_UserClients.get(new_value.intValue());
            lblClientFirstName.setText(selectedClient.getFirstName());
            lblClientLastName.setText(selectedClient.getLastName());
            lblClientAddress.setText(selectedClient.getAddress());
            lblClientPhone.setText(selectedClient.getPhone());
            lblClientStatus.setText(selectedClient.getStatus());
        });

    }

    private void HideErrors()
    {
        lblNewInsuranceCheckLField.setVisible(false);
        lblNewInsuranceCheckLField1.setVisible(false);
    }

    @FXML
    void CancelNewInsuranceForm(ActionEvent event)
    {
        if (checkBoxNewClient.isSelected())
        {
            ResetNewClientFields();
        }

        HideErrors();
    }

    @FXML
    void SendNewInsuranceForm(ActionEvent event) {
        boolean pass = false;
        CheckErrors();

        // if there are any errors we don't continue.
        if ((lblNewInsuranceCheckLField1.isVisible()) || (lblNewInsuranceCheckLField.isVisible()))
        {
            return;
        }

        // if new client is chosen.
        if (checkBoxNewClient.isSelected())
        {
            pass = sqlConnection.getInstance().SendQueryExecute(String.format("INSERT INTO users VALUES" +
                            "('%s','%s','%s','%d','%s','%s','%s','%s')",
                    txtId.getText(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    TypeUserToInteger(eClient),
                    txtPass.getText(),
                    txtAddr.getText(),
                    txtPhone.getText(),
                    txtStatus.getText()));

            // if new user selected and we successfully added the new user now we can
            // add the new insurance to the db.
            if (pass == true)
            {

                pass = sqlConnection.getInstance().SendQueryExecute(String.format("INSERT INTO insurances VALUES" +
                                "('%s','%d','%s','%s','%s','%s','%s','%s','%s')",
                        txtId.getText(),
                        cbInsurance.getSelectionModel().getSelectedIndex(),
                        Insurance.getInsuranceStatus("Pending"),
                        cbInsurance.getValue(),
                        txtFirstName.getText(),
                        txtLastName.getText(),
                        Main.AppUser.GetCurrentAppUser().getId(),
                        Main.AppUser.GetCurrentAppUser().getFirstName(),
                        Main.AppUser.GetCurrentAppUser().getLastName()));

            }
            if (pass)
            {
                ClearFields();
                Main.ShowAlert(Alert.AlertType.CONFIRMATION,"Client by created.");
                loggerAPI.getInstance().WriteLog(this.getClass().getName(), "System", "Added a New Client with an Insurance.");
                loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.GetCurrentAppUser().getUserName(), "New Client created with an Insurance.");
            }
            else
            {
                loggerAPI.getInstance().WriteLog(this.getClass().getName(), "System", "Cannot create new client already exist.");
                Main.ShowAlert(Alert.AlertType.ERROR,"Client by this Id already exist.");
            }
        }//if (checkBoxNewClient.isSelected() == true)

        //--------------------------------------------------------------------------------------------------

        // if (checkBoxNewClient.isSelected() == false)
        else {
            Client selectedClient = cbId.getSelectionModel().getSelectedItem();
            if (selectedClient != null)
            {
                pass = sqlConnection.getInstance().SendQueryExecute(String.format("INSERT INTO insurances VALUES ('%s','%d','%s','%s','%s','%s','%s','%s','%s')",
                        selectedClient.getId(),
                        (cbInsurance.getSelectionModel().getSelectedIndex() + 1),
                        Insurance.getInsuranceStatus("Pending"), // Default new insurance is Pending
                        cbInsurance.getValue(),
                        selectedClient.getFirstName(),
                        selectedClient.getLastName(),
                        Main.AppUser.GetCurrentAppUser().getId(),
                        Main.AppUser.GetCurrentAppUser().getFirstName(),
                        Main.AppUser.GetCurrentAppUser().getLastName()));

                if (pass)
                {
                    ClearFields();
                    Main.ShowAlert(Alert.AlertType.CONFIRMATION, "New Insurance added.");
                    loggerAPI.getInstance().WriteLog(this.getClass().getName(), "System", "New Insurance added.");
                    loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.GetCurrentAppUser().getUserName(), " Added a New Insurance.");
                }
                else
                {
                    Main.ShowAlert(Alert.AlertType.ERROR, "Cannot addInsurance try again later.");
                    loggerAPI.getInstance().WriteLog(this.getClass().getName(), "System", "Cannot addInsurance try again later.");
                }
            }
        }
    }


    private void CheckErrors()
    {
        if (checkBoxNewClient.isSelected())
        {
            if (!CheckNewClientFields())
            {
                lblNewInsuranceCheckLField1.setVisible(true);
            }
            else
            {
                lblNewInsuranceCheckLField1.setVisible(false);
            }
        }
        else
        {
            if (!CheckNewInsuranceFields())
            {
                lblNewInsuranceCheckLField.setVisible(true);
            }
            else
            {
                lblNewInsuranceCheckLField.setVisible(false);
            }
        }

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

    private boolean CheckNewInsuranceFields()
    {
        int index = cbId.getSelectionModel().getSelectedIndex();

        if (index < 0) return false;
        else return true;
    }

    private boolean CheckNewClientFields()
    {
        if (txtAddr.getText().isEmpty()) {return false;}
        if (txtPass.getText().isEmpty()) {return false;}
        if (txtPhone.getText().isEmpty()) {return false;}
        if (txtStatus.getText().isEmpty()) {return false;}

        return true;
    }

    private void ResetNewClientFields()
    {
        txtPass.clear();
        txtAddr.clear();
        txtPhone.clear();
        txtStatus.clear();
        txtLastName.clear();
        txtId.clear();
        txtFirstName.clear();
    }

}




