package view;

//出问题处！

import javafx.scene.Group;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;

public class Generator3  extends Application {//extend

    @Override
    public void start(Stage stage) {
        String videoPath = "src/ADDbyGB/Begin.mp4";
        Media media = new Media(new File(videoPath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        Group root = new Group(mediaView);
        Scene scene = new Scene(root, 852, 480);

        stage.setScene(scene);
        stage.show();

        mediaPlayer.play();
    }

    public static void main(String[] args) {

        DarkChessMaster.launch(args);
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


//‪
//
// C:\Users\11062\Desktop\课本\计算机\DarkChess-master\out\artifacts\DarkChess_master_jar\DarkChess-master.jar
// C:\\Users\\11062\\Desktop\\课本\\计算机\\DarkChess-master\\out\\artifacts\\DarkChess_master_jar\\DarkChess-master.jar
    }



    //java -jar --module-path "C:\Program Files\Java\javafx-sdk-19\lib" --add-modules javafx.controls,javafx.fxml C:\Users\11062\Desktop\课本\计算机\DarkChess-master\out\artifacts\DarkChess_jar2\DarkChess_jar2.jar

//--module-path "C:\Program Files\Java\javafx-sdk-19\lib" --add-modules javafx.controls,javafx.fxml
    //java --module-path "C:\Program Files\Java\javafx-sdk-19\lib" --add-modules javafx.controls,javafx.fxml,javafx.media -jar DarkChess-master.jar
    //java --module-path "C:\Program Files\Java\javafx-sdk-19\lib" --add-modules javafx.controls,javafx.fxml,javafx.media -jar D:\Java\DarkChess-master.jar



}
