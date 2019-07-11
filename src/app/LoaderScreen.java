package app;

import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public final class LoaderScreen
{
    private static Group root;
    private static boolean flag = false;
    private static final double LayoutX = Main.getMainLayout().widthProperty().getValue() / 2;
    private static final double LayoutY = Main.getMainLayout().widthProperty().getValue() / 4;
    private static boolean active = true;

    /**
     * Shows the loading screen.
     */
    public static void showLoadingScreen(){
        if(Platform.isSupported(ConditionalFeature.GRAPHICS)) {
            try {

                ArcTo arc = new ArcTo();
                arc.setRadiusX(50.0);
                arc.setRadiusY(50.0);
                arc.setX(10);
                arc.setY(0);
                arc.setAbsolute(false);
                arc.setSweepFlag(false);
                arc.setLargeArcFlag(true);

                Circle c1 = new Circle(6.0, Color.MEDIUMSPRINGGREEN);
                c1.setLayoutX(LayoutX);
                c1.setLayoutY(LayoutY);

                Circle c2 = new Circle(6.0, Color.MEDIUMSPRINGGREEN);
                c2.setLayoutX(LayoutX);
                c2.setLayoutY(LayoutY);

                Circle c3 = new Circle(6.0, Color.MEDIUMSPRINGGREEN);
                c3.setLayoutX(LayoutX);
                c3.setLayoutY(LayoutY);

                Circle c4 = new Circle(6.0, Color.MEDIUMSPRINGGREEN);
                c4.setLayoutX(LayoutX);
                c4.setLayoutY(LayoutY);

                Circle c6 = new Circle(6.0, Color.MEDIUMSPRINGGREEN);
                c6.setLayoutX(LayoutX);
                c6.setLayoutY(LayoutY);

                Path p1 = new Path();
                p1.getElements().add(new MoveTo(0, 0));
                p1.getElements().add(arc);
                p1.getElements().add(new ClosePath());

                Path p2 = new Path();
                p2.getElements().add(new MoveTo(0, 0));
                p2.getElements().add(arc);
                p2.getElements().add(new ClosePath());

                Path p3 = new Path();
                p3.getElements().add(new MoveTo(0, 0));
                p3.getElements().add(arc);
                p3.getElements().add(new ClosePath());

                Path p4 = new Path();
                p4.getElements().add(new MoveTo(0, 0));
                p4.getElements().add(arc);
                p4.getElements().add(new ClosePath());

                Path p6 = new Path();
                p6.getElements().add(new MoveTo(0, 0));
                p6.getElements().add(arc);
                p6.getElements().add(new ClosePath());

                PathTransition pathTransition1 = new PathTransition();
                pathTransition1.setDuration(Duration.millis(2000));
                pathTransition1.setNode(c1);
                pathTransition1.setPath(p1);
                pathTransition1.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
                pathTransition1.setCycleCount(400);
                pathTransition1.setAutoReverse(false);
                pathTransition1.play();

                PathTransition pathTransition2 = new PathTransition();
                pathTransition2.setDuration(Duration.millis(2000));
                pathTransition2.setDelay(Duration.millis(150));
                pathTransition2.setNode(c2);
                pathTransition2.setPath(p2);
                pathTransition2.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
                pathTransition2.setCycleCount(400);
                pathTransition2.setAutoReverse(false);
                pathTransition2.play();

                PathTransition pathTransition3 = new PathTransition();
                pathTransition3.setDuration(Duration.millis(2000));
                pathTransition3.setDelay(Duration.millis(300));
                pathTransition3.setNode(c3);
                pathTransition3.setPath(p3);
                pathTransition3.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
                pathTransition3.setCycleCount(400);
                pathTransition3.setAutoReverse(false);
                pathTransition3.play();

                PathTransition pathTransition4 = new PathTransition();
                pathTransition4.setDuration(Duration.millis(2000));
                pathTransition4.setDelay(Duration.millis(450));
                pathTransition4.setNode(c4);
                pathTransition4.setPath(p4);
                pathTransition4.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
                pathTransition4.setCycleCount(400);
                pathTransition4.setAutoReverse(false);
                pathTransition4.play();

                PathTransition pathTransition6 = new PathTransition();
                pathTransition6.setDuration(Duration.millis(2000));
                pathTransition6.setDelay(Duration.millis(550));
                pathTransition6.setNode(c6);
                pathTransition6.setPath(p6);
                pathTransition6.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
                pathTransition6.setCycleCount(400);
                pathTransition6.setAutoReverse(false);
                pathTransition6.play();

                root = new Group(c1, c2, c3, c4, c6);

//                root.layoutXProperty().bind(Main.getMainLayout().widthProperty().subtract(root.prefWidth(0)).divide(1));
//                root.layoutYProperty().bind(Main.getMainLayout().heightProperty().subtract(root.prefHeight(0)).divide(1));
//
//
//                Main.getMainLayout().heightProperty().addListener((observable, oldValue, newValue) -> {
//                    root.layoutXProperty().bind(Main.getMainLayout().widthProperty().subtract(root.prefWidth(0)).divide(1));
//                    root.layoutYProperty().bind(Main.getMainLayout().heightProperty().subtract(root.prefHeight(0)).divide(1));
//                });
//
//
//                Main.getMainLayout().widthProperty().addListener((observable, oldValue, newValue) -> {
//                    root.layoutXProperty().bind(Main.getMainLayout().widthProperty().subtract(root.prefWidth(0)).divide(1));
//                    root.layoutYProperty().bind(Main.getMainLayout().heightProperty().subtract(root.prefHeight(0)).divide(1));
//                });


                flag = true;

                Main.getMainLayout().getChildren().add(root);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("The System doesn't support");
        }
    }

    /**
     * Hides the loading screen.
     */
    public static void hideLoadingScreen(){
        if(flag)
            Main.getMainLayout().getChildren().remove(root);
        flag = false;
    }


    public static void ShowLoadingScreen()
    {
        active = true;
        final Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                Platform.runLater(()-> LoaderScreen.showLoadingScreen());
                while(active){}
                return null;
            }
        };

        task.setOnSucceeded(event -> {
            try
            {
                Platform.runLater(()->LoaderScreen.hideLoadingScreen());
            } catch (Exception e){}

        });

        Thread t = new Thread(task);
        t.start();
    }

    public static void HideLoadingScreen()
    {
        active = false;
    }
}
