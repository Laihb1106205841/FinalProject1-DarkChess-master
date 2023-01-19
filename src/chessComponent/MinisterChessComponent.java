package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;



public class MinisterChessComponent extends ChessComponent {


        public MinisterChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
            super(chessboardPoint, location, chessColor, clickController, size,5,5);
            this.Points=5;
            if (this.getChessColor() == ChessColor.RED) {
                name = "相";
            } else {
                name = "象";
            }
        }


    }


