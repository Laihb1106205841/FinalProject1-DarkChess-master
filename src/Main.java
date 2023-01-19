//import ADDbyGB.Timer;
import view.ChessGameFrame;

import javax.swing.*;//您好，您退休了

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChessGameFrame mainFrame = new ChessGameFrame(1000, 700,false,null);
            mainFrame.setVisible(true);
        });



    }
}
