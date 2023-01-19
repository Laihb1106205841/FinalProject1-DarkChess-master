package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class Videotry extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
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