package view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;


public class DarkChessMaster extends Application {

    public static void main(String[] args) {
        launch(args);
        SwingUtilities.invokeLater(() -> {
            Generator2.ChessGameFrame2 mainFrame = new Generator2.ChessGameFrame2(1000, 700);
            mainFrame.setVisible(true);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            Music.playMusic();

            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            }catch(Exception e) {
                System.out.println(e);
            }

        });
    }

    @Override
    public void start(Stage primaryStage) {
        // 创建一个MediaPlayer对象
        String videoFile = "src/ADDbyGB/Begin.mp4"; // 替换为您的视频文件路径
        Media media = new Media(new File(videoFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        // 创建一个MediaView对象，用于显示视频
        MediaView mediaView = new MediaView(mediaPlayer);

        // 创建一个Group对象，并将MediaView添加到其中
        Group root = new Group();
        root.getChildren().add(mediaView);

        // 创建一个Scene对象，并将Group添加到其中
        Scene scene = new Scene(root, 800, 480);

        // 设置舞台的Scene，并显示舞台
        primaryStage.setScene(scene);
        primaryStage.show();

        // 播放视频
        mediaPlayer.play();
    }
}

