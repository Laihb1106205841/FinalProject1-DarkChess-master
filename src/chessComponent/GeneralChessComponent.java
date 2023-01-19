package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;
import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;


public class GeneralChessComponent extends ChessComponent {


        public GeneralChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
            super(chessboardPoint, location, chessColor, clickController, size,7,30);
            this.Points=30;
           if (this.getChessColor() == ChessColor.RED) {
                name = "将";
            } else {
               name = "帅";
            }
        }

    }

