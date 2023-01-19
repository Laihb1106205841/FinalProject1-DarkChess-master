package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;

public class ImproveSoldierChess extends ChessComponent {

    public ImproveSoldierChess(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size,4,1);
        //level=1;
        this.Points =1;
        if (this.getChessColor() == ChessColor.RED) {
            name = "鋲";
        } else {
            name = "卒";
        }

    }


}

