package app;

import app.Controllers.Login;
import app.Models.UserFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

    private static double x, y;
    private static Stage PrimaryStage;
    private static Pane MainRoot;
    public static Login LoginController;

    public static UserFactory AppUser;

    public static Pane getMainLayout() {
        return MainRoot;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        PrimaryStage = primaryStage;
        PrimaryStage.initStyle(StageStyle.UNDECORATED);
        ShowLogin();
    }


    public static void main(String[] args) {
        launch(args);
    }


    public static void ShowLogin() throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        Pane loginItems = loader.load(Main.class.getResource("/app/View/Login.fxml").openStream());
        LoginController = (Login) loader.getController();
        PrimaryStage.setScene(new Scene(loginItems));
        loginItems = AttachEvent(loginItems);
        MainRoot = loginItems;
        PrimaryStage.show();
    }

    public static void showUserMenu()
    {
        LoginController.showUserHome();
    }


    public static void ShowSalesmanHome() throws Exception
    {
        Pane root = FXMLLoader.load(Main.class.getResource("/app/View/Salesman/Home.fxml"));
        PrimaryStage.setScene(new Scene(root));
        root = AttachEvent(root);
        MainRoot = root;
        PrimaryStage.show();
    }

    public static void ShowClientHome() throws Exception
    {
        Pane root = FXMLLoader.load(Main.class.getResource("/app/View/Client/Home.fxml"));
        PrimaryStage.setScene(new Scene(root));
        root = AttachEvent(root);
        MainRoot = root;
        PrimaryStage.show();
    }

    public static void ShowCustomerServieHome() throws Exception
    {
        Pane root = FXMLLoader.load(Main.class.getResource("/app/View/CustomerService/Home.fxml"));
        PrimaryStage.setScene(new Scene(root));
        root = AttachEvent(root);
        MainRoot = root;
        PrimaryStage.show();
    }


    private static Pane AttachEvent(Pane root)
    {
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {

            PrimaryStage.setX(event.getScreenX() - x);
            PrimaryStage.setY(event.getScreenY() - y);

        });

        return root;
    }



    public static void ShowAlert(Alert.AlertType alertType, String msg)
    {
        Alert alert = new Alert(alertType);
        alert.setTitle("Message from Application");
        alert.setHeaderText("Message is: " + msg);
        alert.show();
//        alert.showAndWait().ifPresent(rs -> {
//            if (rs == ButtonType.OK) {
//                System.out.println("Pressed OK.");
//            }
//        });
    }
}