package app;

import app.Models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

    private static double x, y;
    private static Stage PrimaryStage;

    public static User AppUser;

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
        Parent root = FXMLLoader.load(Main.class.getResource("/app/View/Login.fxml"));
        PrimaryStage.setScene(new Scene(root));

        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {

            PrimaryStage.setX(event.getScreenX() - x);
            PrimaryStage.setY(event.getScreenY() - y);

        });
        PrimaryStage.show();
    }


    public static void ShowHome() throws Exception
    {
        Parent root = FXMLLoader.load(Main.class.getResource("/app/View/Salesman/Home.fxml"));
        PrimaryStage.setScene(new Scene(root));

        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {

            PrimaryStage.setX(event.getScreenX() - x);
            PrimaryStage.setY(event.getScreenY() - y);

        });
        PrimaryStage.show();
    }

    public static void ShowClientHome() throws Exception
    {
        Parent root = FXMLLoader.load(Main.class.getResource("/app/View/Client/Home.fxml"));
        PrimaryStage.setScene(new Scene(root));

        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {

            PrimaryStage.setX(event.getScreenX() - x);
            PrimaryStage.setY(event.getScreenY() - y);

        });
        PrimaryStage.show();
    }
}