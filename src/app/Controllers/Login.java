package app.Controllers;

import app.LoaderScreen;
import app.Main;
import app.Models.Client;
import app.Models.CustomerService;
import app.Models.Salesman;
import app.Models.User;
import app.connection.loggerAPI;
import app.connection.sqlConnection;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {

    @FXML
    private Label lblWrongUser;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnExit;

    public void LoginHandler(ActionEvent actionEvent)
    {
        Login();
    }

    public void ExitApp(ActionEvent actionEvent)
    {
        loggerAPI.getInstance().CloseLogger();
        Platform.exit();
    }

    private void Login()
    {
        try {

            if (CheckLoginFields())
            {
                sqlConnection.getInstance().connectToServer(txtUsername.getText(), txtPassword.getText());

            } else {
                lblWrongUser.setVisible(true);
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void showUserHome()
    {
        try {
            if (Main.AppUser != null) {
                lblWrongUser.setVisible(false);
                if (Main.AppUser instanceof Client) {
                    Main.ShowClientHome();
                } else if (Main.AppUser instanceof Salesman) {
                    Main.ShowSalesmanHome();
                } else if (Main.AppUser instanceof CustomerService) {
                    Main.ShowCustomerServieHome();
                }
                loggerAPI.getInstance().WriteLog(this.getClass().getName(), Main.AppUser.getUserName(), "Sign in.");
            } else {
                lblWrongUser.setVisible(true);
            }
        }catch (Exception ex) {
        }
    }


    private boolean CheckLoginFields()
    {
        if ((!txtUsername.getText().isEmpty()) && (!txtPassword.getText().isEmpty()))
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    @FXML
    void LoginAction(KeyEvent event)
    {
        if (event.getCode() == KeyCode.ENTER)
        {
            Login();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        lblWrongUser.setVisible(false);
    }
}


