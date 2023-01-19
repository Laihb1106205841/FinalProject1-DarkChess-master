package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;

/**
 * 表示棋盘上非空棋子的格子，是所有非空棋子的父类
 */
public class ChessComponent extends SquareComponent{
    @Override
    public String getName() {return name;}

    protected String name;// 棋子名字：例如 兵，卒，士等
    protected String General;

    @Override
    public void setName(String name) {this.name = name;}

    public void setGeneral(String general) {General = general;}

    public void setLevel(int level) {this.level = level;}

    public void setPoints(int points) {Points = points;}

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getGeneral() {return General;}

    public int getLevel() {return level;}

    public int getPoints() {return Points;}

    @Override
    public String toString() {
        return "ChessComponent{" +
                "General='" + General + '\'' +
                '}';
    }

   // protected int level;
    protected int Points;

    public int getID() {return ID;}

    protected int ID;

    public ChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController,
                          int size, int level, int Points) {
        super(chessboardPoint, location, chessColor, clickController, size);
        this.level=level;
        this.Points=Points;

        //    this.ID=ID;
   //     this.General=General;


    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //绘制棋子填充色
        g.setColor(Color.ORANGE);
        g.fillOval(spacingLength, spacingLength, this.getWidth() - 2 * spacingLength, this.getHeight() - 2 * spacingLength);
       //绘制棋子边框
        g.setColor(Color.DARK_GRAY);
        g.drawOval(spacingLength, spacingLength, getWidth() - 2 * spacingLength, getHeight() - 2 * spacingLength);

        if (isReversal) {
            //绘制棋子文字
            g.setColor(this.getChessColor().getColor());
            g.setFont(CHESS_FONT);
            g.drawString(this.name, this.getWidth() / 4, this.getHeight() * 2 / 3);

            //绘制棋子被选中时状态
            if (isSelected()) {
                g.setColor(Color.RED);
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(4f));
                g2.drawOval(spacingLength, spacingLength, getWidth() - 2 * spacingLength, getHeight() - 2 * spacingLength);



            }
        }
    }
}
