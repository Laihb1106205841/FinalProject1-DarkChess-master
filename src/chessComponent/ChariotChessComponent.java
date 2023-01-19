package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;
import java.net.IDN;

/**
 * 表示黑红车
 */
public class ChariotChessComponent extends ChessComponent {

    public ChariotChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size,4,5);
        this.Points=5;
        if (this.getChessColor() == ChessColor.RED) {
            name = "俥";
         //   General ="罗科索夫斯基";
        } else {
            name = "車";
        //    General ="隆美尔";
        }

    }

}
