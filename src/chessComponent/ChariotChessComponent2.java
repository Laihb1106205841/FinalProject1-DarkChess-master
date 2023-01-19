package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;

public class ChariotChessComponent2 extends ChessComponent {

    public ChariotChessComponent2(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size, 3, 5);
        this.Points=5;
        if (this.getChessColor() == ChessColor.RED) {
            name = "俥";
       //     General = "华西列夫斯基";
        } else {
            name = "車";
        //    General = "古德里安";
        }

    }

}
