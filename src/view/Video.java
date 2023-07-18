package view;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Screen;
import javafx.stage.Stage;


import java.awt.*;
import java.io.File;
//import java.net.URI.*;

public class Video extends Application {
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) {


//        String url1=this.getClass().getClassLoader().getResource("Begin.mp4").toExternalForm();
//        Media media =new Media(url1);
//
//        String s1 = new File("file:///d:/Java/Begin.mp4").toString();//D:\Java\Begin.mp4
//        Media media1 = new Media(s1);
//        MediaPlayer mp1 = new MediaPlayer(media1);
//        mp1.play();

        String videoPath = "file:///D:\\Java\\FinalProject1-DarkChess-master\\src\\ADDbyGB\\Begin.mp4";
        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        Group root = new Group(mediaView);
        Scene scene = new Scene(root, 852, 480);

        stage.setScene(scene);
        stage.show();

        mediaPlayer.play();













    }
    public void start1(Stage primaryStage) throws Exception {
        // Create a media object from the given file
        Media media = new Media("file:///d:/Java/Begin.mp4");

        // Create a player to play the media
        MediaPlayer player = new MediaPlayer(media);

        // Create a view to display the media
        MediaView mediaView = new MediaView(player);

        // Create a pane for the view
        StackPane pane = new StackPane();
        pane.getChildren().add(mediaView);



    }
}