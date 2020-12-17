package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.css.Rect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static javafx.application.Application.launch;
import static javafx.application.Platform.exit;

public class Obstacle extends Application {
    final int LimitX = 200;
    protected int speedX;
    protected int speedY;
    protected int rotation_speed;
    @FXML
    protected  AnchorPane root;
    @FXML
    protected Group finalObstacle;
    @FXML
    protected  Rectangle bar1;
    @FXML
    protected  Rectangle bar2;
    @FXML
    protected Rectangle bar3;
    @FXML
    protected  Rectangle bar4;
    @FXML
    protected Rectangle star_1;
    @FXML
    protected  Rectangle star_2;
    @FXML
    protected Rectangle star_3;
    @FXML
    protected Rectangle star_4;
    @FXML
    protected Circle colorChanger;

    protected Image image;

    protected  ArrayList<Paint> colors = new ArrayList<>();
    protected double dx = 0.1;
    public void setSpeedX(){
        speedX = 100;
    }
    public int getSpeedX(){
        return speedX;
    }
//    @Override
//    public void start(Stage stage) throws IOException {
//        Parent obstacle = FXMLLoader.load(getClass().getResource("Obstacle.fxml"));
//        setSpeedX();
//        Scene scene = new Scene(obstacle);
//
//        Timeline timeline = new Timeline();
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.setAutoReverse(true);
//        final KeyValue kv = new KeyValue(obstacle.layoutXProperty(), random());
//        final KeyFrame kf = new KeyFrame(Duration.millis(4000), kv);
//        if(outOfWindow(bar1)){
//            System.out.println("HELLL");
//        }
//        timeline.getKeyFrames().add(kf);
//        timeline.play();
//        stage.setScene(scene);
//        stage.show();
//    }

    //    public Obstacle() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Obstacle.fxml"));
//        Obstacle obstacle = loader.getController();
//        this.bar1 = obstacle.bar1;
//        this.bar2 = obstacle.bar2;
//        this.bar3 = obstacle.bar3;
//        this.bar4 = obstacle.bar4;
//        this.finalObstacle = obstacle.finalObstacle ;
//    }
    public Obstacle(){
        colors.add(Color.LIME);
        colors.add(Color.DODGERBLUE);
        colors.add(Color.RED);
        colors.add(Color.YELLOW );
    }
    public void rotateFunction(Parent parent, int stAngle, int endAngle){
        RotateTransition rotate = new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setFromAngle(stAngle);
        rotate.setToAngle(endAngle);
        rotate.setCycleCount(RotateTransition.INDEFINITE);
        rotate.setDuration(Duration.millis(3000));
        rotate.setNode(parent);
        rotate.play();
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Obstacle.fxml"));
        Scene scene = new Scene(loader.load());
        Obstacle controller = loader.getController();
        Translate translate = new Translate();
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        final KeyValue kv = new KeyValue(translate.xProperty(), random());
        final KeyFrame kf = new KeyFrame(Duration.millis(4000), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
        controller.finalObstacle.getTransforms().add(translate);
        stage.setScene(scene);
        stage.show();
    }
    private void setTranslate(Group finalObstacle){
        Translate translate = new Translate();
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        final KeyValue kv = new KeyValue(translate.xProperty(), random());
        final KeyFrame kf = new KeyFrame(Duration.millis(2500), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
        finalObstacle.getTransforms().add(translate);
    }
    protected void setImage(){
        image = new Image("/assets/star.png");
    }
    protected void setStar(Rectangle star){
        if(star == null){
            return;
        }
        star.setFill(new ImagePattern(image));
    }
    protected void setColorChange(Paint paint){
        if(colorChanger == null){
            return;
        }
        colors.remove(paint);
        Random random = new Random();
        colorChanger.setFill(colors.get(random.nextInt(colors.size())));
        colors.add(paint);
    }
    public Group getObstacle(Paint paint) throws IOException {
        setTranslate(finalObstacle);
        setImage();
        setStar(star_1);
        System.out.println ("star_1");
        setStar(star_2);
        System.out.println("star_2");
        setStar(star_3);
        System.out.println("star_3");
        setStar(star_4);
        System.out.println("star_4");
        setColorChange(paint);
        return finalObstacle;
    }
    private int random(){
        Random random = new Random();
        return random.nextInt(420) + 400;
    }
    private boolean outOfWindow(Node node) {
        if(node == null){
            return false;
        }
        if (node.getBoundsInParent().intersects(node.getBoundsInParent().getWidth(), node.getBoundsInParent().getHeight(),
                root.getPrefWidth() - node.getBoundsInParent().getWidth() * 2,
                root.getPrefHeight() - node.getBoundsInParent().getHeight() * 2)){
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        launch(args);
    }

    public boolean WrongIntersection(Circle ball) {
        if(ball == null){
            System.out.println("Ball null hai");
        }
        return checkIntersection(bar1, ball) || checkIntersection(bar2, ball) || checkIntersection(bar3, ball) || checkIntersection(bar4, ball);
    }

    protected boolean checkIntersection(Shape bar, Circle ball){
        if(bar == null){
            System.out.println("Bar is null");
            return false;
        }
        if(ball == null){
            System.out.println("Ball is null");
        }
        Shape s = Shape.intersect(bar, ball);
        boolean collisionHappens;
        boolean colorNotSame;
        if(s.getBoundsInParent().getWidth() != -1){
            //System.out.println("Collision detected");
            collisionHappens = true;
        }
        else{
            collisionHappens = false;
        }
        //System.out.println(bar.getFill() + " " + ball.getFill());
        if(!bar.getFill().equals(ball.getFill())){

            //System.out.println("color is not same");
            colorNotSame = true;
        }
        else{
            colorNotSame = false;
        }
        return collisionHappens && colorNotSame;
    }

    public int checkStars(Circle ball){
        return starIntersects(star_1, ball) + starIntersects(star_2, ball) + starIntersects(star_3, ball) + starIntersects(star_4, ball);
    }

    protected void removeImage(Rectangle image){
        finalObstacle.getChildren().remove(image);
    }
    protected int starIntersects(Rectangle image, Circle ball){
        if(image == null){
            System.out.println("The image is null");
            exit();
            return 0;
        }
        if(ball == null){
            System.out.println("The ball is null");
            return 0;
        }
        Shape s = Shape.intersect(image, ball);
        if(s.getBoundsInParent().getWidth() != -1){
            //System.out.println("star collected");
            removeImage(image);
            return 1;
        }
        return 0;
    }

    public void checkColorChanger(Circle ball){
        if(colorChanger == null){
            return;
        }
        if(ball == null){
            return;
        }
        Shape s = Shape.intersect(ball, colorChanger);
        if(s.getBoundsInParent().getWidth() != -1){
            //System.out.println("Change Color");
            ball.setFill(colorChanger.getFill());
            finalObstacle.getChildren().remove(colorChanger);
        }
    }


}
