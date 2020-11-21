package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PauseMenu  extends Application {
    @FXML
    private ImageView Background;
    @FXML
    private AnchorPane GamePlayRoot;
    @FXML
    private Button PalyButton;

    @FXML
    void play(ActionEvent event) throws IOException {
        Parent Enterplayername = FXMLLoader.load(getClass().getResource("Gameplay.fxml"));
        Scene playername = new Scene(Enterplayername);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(playername);
        window.show();
        //GamePlayController controller = fxmlLoader.<GamePlayController>getController();



    }
    @FXML
    void save(){
        System.out.println("Save clicked");
    }

    @FXML
    void exit(){
        System.out.println("Exit clicked");
    }

    @FXML
    public void Enterplayernamescreen(ActionEvent event) throws IOException {
        Parent Enterplayername = FXMLLoader.load(getClass().getResource("Gameplay.fxml"));
        Scene playername = new Scene(Enterplayername);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(playername);
        window.show();

    }

    @Override
    public void start(Stage PrimaryStage) throws IOException {
        Parent StartMenu = FXMLLoader.load(getClass().getResource("PauseMenu.fxml"));
        PrimaryStage.setTitle("Color Switch");
        final Scene scene = new Scene(StartMenu);

        PrimaryStage.setScene(scene);
        PrimaryStage.show();
    }

    public static void main (String[] args){
        launch(args);
    }
}