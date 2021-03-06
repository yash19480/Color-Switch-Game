package sample;

import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Gameplay {
    @FXML
    Scene scene;
    @FXML
    private AnchorPane Pane
            ;
    @FXML
    private ImageView Star;
    @FXML
    private ImageView PauseButton_img;
    @FXML
    private Arc arc1;
    @FXML
    private Arc arc2;
    @FXML
    private Arc arc3;
    @FXML
    private Arc arc4;
    @FXML
    private Circle Incircle;
    @FXML
    private Circle ball;
    private double balljump;
    private double dx;

    @FXML
    public void keyPressed(KeyEvent event){
        System.out.println("Some key pressed");
    }
    //@FXML
    //public void KeyboardSet()
    @FXML
    public void initData(ActionEvent event){
        System.out.println("Working??");
        scene = new Scene(Pane, 1024, 800, Color.TRANSPARENT);
        //scene.
       final Group outerCircle = new Group(arc1, arc2, arc3, arc4,Incircle);
       Group star = new Group(Star);
        RotateTransition rotate = new RotateTransition();
        RotateTransition rotate_anti = new RotateTransition();
        rotate_anti.setAxis(Rotate.Z_AXIS);
        rotate_anti.setFromAngle(360);
        rotate_anti.setToAngle(0);
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setFromAngle(0);
        rotate.setToAngle(360);
        rotate_anti.setCycleCount(RotateTransition.INDEFINITE);
        rotate.setCycleCount(RotateTransition.INDEFINITE);
        rotate.setDuration(Duration.millis(1000));
        rotate_anti.setDuration(Duration.millis(5000));
        rotate.setNode(outerCircle);
        rotate_anti.setNode(star);
        rotate_anti.play();
        rotate.play();
        balljump = ball.getLayoutX();


        dx = 3;

       // Pane.

        Pane.getChildren().add(outerCircle);
        Pane.getChildren().add(star);
        ball.toFront();
        Pane.onKeyPressedProperty( );
        ((Node) event.getSource()).getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent event) {
                //System.out.println("Chara "+event.getCharacter());
//                System.out.println("Code"+event.getCode());
//                System.out.println("Text"+event.getText());
                // System.out.println(event.);
                switch (event.getCode()) {
                    case W:
                        balljump = ball.getLayoutY()-100;
                        if(dx>0)
                        dx = -5;
                        else
                            dx -=3;
                        System.out.println("UP"); break;
                    case U:
                        System.out.println("UP Pressed"); break;
                    case D:
                        System.out.println("D Pressed"); break;
                    case R: System.out.println("Right"); break;
                    case O:  System.out.println("DOWN"); break;
                    case L:  System.out.println("LEFT"); break;
                    default:
                        System.out.println("Horha h");
                }
            }
        });

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
           // double dx = 1; //Step on x or velocity


            @Override
            public void handle(ActionEvent t) {
                //move the ball

                ball.setLayoutY(ball.getLayoutY() + dx);
                //ball.setLayoutY(ball.getLayoutY() + dy);

                Bounds bounds = Pane.getBoundsInLocal();

                //If the ball reaches the left or right border make the step negative
                if(Math.abs(dx)<1){

                    dx = 1;

                }
                if(dx>0){
                    dx += 0.03*dx;
                }
                else{
                    dx -= (0.03)*dx;
                }

                //If the ball reaches the bottom or top border make the step negative


            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

   }
   @FXML
   public void flippause(MouseEvent event) throws  IOException {
        flipIcon(PauseButton_img);
   }
   private void flipIcon(ImageView Icon){
       RotateTransition rotate = new RotateTransition();
       rotate.setAxis(Rotate.Z_AXIS);
       rotate.setFromAngle(0);
       rotate.setToAngle(360);
       rotate.setCycleCount(1);
       rotate.setDuration(Duration.millis(1000));
       rotate.setNode((Node)Icon);
       rotate.play();
   }

    @FXML
    void jump(){
        System.out.println("Jump");
    }

    @FXML
    void openPauseMenu(MouseEvent event) throws IOException {
        Parent Pause = FXMLLoader.load(getClass().getResource("pauseMenu.fxml"));
        Scene player = new Scene(Pause);


        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(player);
        window.show();
    }
//    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//        @Override
//        public void handle(KeyEvent event) {
//            switch (event.getCode()) {
//                case UP:    goNorth = true; break;
//                case DOWN:  goSouth = true; break;
//                case LEFT:  goWest  = true; break;
//                case RIGHT: goEast  = true; break;
//                case SHIFT: running = true; break;
//            }
//        }
//    });

}
