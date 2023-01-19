package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import javax.naming.Name;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Objects;

import static model.ChessColor.RED;

/**
 * 这个类是一个抽象类，主要表示8*4棋盘上每个格子的棋子情况。
 * 有两个子类：
 * 1. EmptySlotComponent: 空棋子
 * 2. ChessComponent: 表示非空棋子
 */
public abstract class SquareComponent extends JComponent {
//继承了JComponent
    private static final Color squareColor = new Color(250, 220, 190);
    protected static int spacingLength;
    protected static final Font CHESS_FONT = new Font("Rockwell", Font.BOLD, 36);
    protected int Points;
    protected String General;
    public String getGeneral() {return General;}

    /**
     * chessboardPoint: 表示8*4棋盘中，当前棋子在棋格对应的位置，如(0, 0), (1, 0)等等
     * chessColor: 表示这个棋子的颜色，有红色，黑色，无色三种
     * isReversal: 表示是否翻转
     * selected: 表示这个棋子是否被选中
     */
    private ChessboardPoint chessboardPoint;
    protected final ChessColor chessColor;
    protected boolean isReversal;
    private boolean selected;

    public void setPoints(int points) {
        Points = points;
    }

    public void setGeneral(String general) {
        General = general;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    protected int level;

    public int getLevel() {
        return level;
    }

    /**
     * handle click event
     */
    private final ClickController clickController;

    protected SquareComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        setLocation(location);
        setSize(size, size);
        this.chessboardPoint = chessboardPoint;
        this.chessColor = chessColor;
        this.selected = false;
        this.clickController = clickController;
        this.isReversal = false;






    }
    public int getPoints(){return Points;}

    public boolean isReversal() {
        return isReversal;
    }

    public void setReversal(boolean reversal) {
        isReversal = reversal;
    }

    public static void setSpacingLength(int spacingLength) {
        SquareComponent.spacingLength = spacingLength;
    }

    public ChessboardPoint getChessboardPoint() {
        return chessboardPoint;
    }

    public void setChessboardPoint(ChessboardPoint chessboardPoint) {
        this.chessboardPoint = chessboardPoint;
    }

    public ChessColor getChessColor() {
        return chessColor;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * @param another 主要用于和另外一个棋子交换位置
     *                <br>
     *                调用时机是在移动棋子的时候，将操控的棋子和对应的空位置棋子(EmptySlotComponent)做交换
     */
    public void swapLocation(SquareComponent another) {
        ChessboardPoint chessboardPoint1 = getChessboardPoint(), chessboardPoint2 = another.getChessboardPoint();
        Point point1 = getLocation(), point2 = another.getLocation();
        setChessboardPoint(chessboardPoint2);
        setLocation(point2);
        another.setChessboardPoint(chessboardPoint1);
        another.setLocation(point1);
    }

    /**
     * @param e 响应鼠标监听事件
     *          <br>
     *          当接收到鼠标动作的时候，这个方法就会自动被调用，调用监听者的onClick方法，处理棋子的选中，移动等等行为。
     */
    @Override
    protected void processMouseEvent(MouseEvent e) {
        super.processMouseEvent(e);
        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            System.out.printf("Click [%d,%d]\n", chessboardPoint.getX(), chessboardPoint.getY());
            clickController.onClick(this);
        }
    }

    /**
     * @param chessboard  棋盘
     * @param destination 目标位置，如(0, 0), (0, 1)等等
     * @return this棋子对象的移动规则和当前位置(chessboardPoint)能否到达目标位置
     * <br>
     * 这个方法主要是检查移动的合法性，如果合法就返回true，反之是false。
     */
    //todo: Override this method for Cannon
    public boolean canMoveTo(SquareComponent[][] chessboard, ChessboardPoint destination) {



         if(destination.getX()==chessboardPoint.getX()+1&&destination.getY()==chessboardPoint.getY()){
        SquareComponent destinationChess = chessboard[destination.getX()][destination.getY()];
        return destinationChess.isReversal|| destinationChess instanceof EmptySlotComponent;}
        else if(destination.getX()==chessboardPoint.getX()&&destination.getY()==chessboardPoint.getY()+1) {
           SquareComponent destinationChess = chessboard[destination.getX()][destination.getY()];
           return destinationChess.isReversal || destinationChess instanceof EmptySlotComponent;
       }
       else if(destination.getX()==chessboardPoint.getX()&&destination.getY()==chessboardPoint.getY()-1) {
           SquareComponent destinationChess = chessboard[destination.getX()][destination.getY()];
           return destinationChess.isReversal || destinationChess instanceof EmptySlotComponent;
       }
       else if(destination.getX()==chessboardPoint.getX()-1&&destination.getY()==chessboardPoint.getY()) {
           SquareComponent destinationChess = chessboard[destination.getX()][destination.getY()];
           return destinationChess.isReversal || destinationChess instanceof EmptySlotComponent;
       }//横竖左右移动



        //todo: complete this method

    //   else {return false;}
       return false;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        System.out.printf("repaint chess [%d,%d]\n", chessboardPoint.getX(), chessboardPoint.getY());
        g.setColor(squareColor);
        g.fillRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
    }

    public Boolean PaoMoveTo(SquareComponent[][] chessboard, ChessboardPoint destination){

        if(destination.getX()==chessboardPoint.getX()){
            if(destination.getY()>chessboardPoint.getY()){
                int distance =-1;
                for(int i=chessboardPoint.getY();i<destination.getY();i++){
                    if(chessboard[destination.getX()][i].level!=0){distance+=1;}}
                if(distance ==1){System.out.println(distance);

                    return true;}
            }

              else   if(destination.getY()<chessboardPoint.getY()){
                  int distance =-1;
                    for(int i=destination.getY();i<chessboardPoint.getY();i++){
                        if(chessboard[destination.getX()][i].level!=0){distance+=1;}}
                    if(distance ==1){System.out.println(distance);
                        SquareComponent destinationChess = chessboard[destination.getX()][destination.getY()];
                        return true;}}

            }

        if(destination.getY()==chessboardPoint.getY()){
            if(destination.getX()>chessboardPoint.getX()){
                int distance =-1;
                for(int i=chessboardPoint.getX();i<destination.getX();i++){
                    if(chessboard[i][destination.getY()].level!=0){distance+=1;}}
                if(distance ==1){System.out.println(distance);
                    return true;}
            }
            else if(destination.getX()<chessboardPoint.getX()){
                int distance =-1;
                for(int i=destination.getX();i<chessboardPoint.getX();i++){
                    if(chessboard[i][destination.getY()].level!=0){distance+=1;}}
                if(distance ==1){System.out.println(distance);
                    return true;}
            }
        }


       return false;
    }

}
