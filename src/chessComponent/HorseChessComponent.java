package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;



public class HorseChessComponent extends ChessComponent {

    public HorseChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController,int size) {
        super(chessboardPoint, location, chessColor, clickController, size,3,5);
        this.Points=5;
        if (this.getChessColor() == ChessColor.RED) {
            name = "傌";
        } else {
            name = "馬";
        }
    }

}
