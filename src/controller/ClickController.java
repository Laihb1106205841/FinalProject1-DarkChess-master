package controller;
//                                    如果你想做将领系统的话，这个包就一定要好好搞一下
//                                        点击动作 会发生什么

import chessComponent.SquareComponent;
import chessComponent.EmptySlotComponent;
import model.ChessColor;
import view.ChessGameFrame;
import view.Chessboard;
import view.Generator2;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;


public class ClickController extends Component {
    private final Chessboard chessboard;
    private SquareComponent first;

    public String DeadName;

  //  public String getDeadName() {return DeadName;}

    public ClickController(Chessboard chessboard) {
        this.chessboard = chessboard;

    }

    public void onClick(SquareComponent squareComponent) {
        //判断第一次点击
        if (first == null) {
            if (handleFirst(squareComponent)) {//是友军
                squareComponent.setSelected(true);
                first = squareComponent;
                first.repaint();
            }
        } else {
            if (first == squareComponent) { // 再次点击取消选取
                squareComponent.setSelected(false);
                SquareComponent recordFirst = first;
                first = null;
                recordFirst.repaint();
            } else if (handleSecond(squareComponent)) {
                //repaint in swap chess method.
               if(first.getLevel()>=squareComponent.getLevel()||first.getLevel()==2){
                chessboard.swapChessComponents(first, squareComponent);


                chessboard.clickController.swapPlayer();

                String DeadName = squareComponent.getName();
                this.DeadName=DeadName;
                System.out.println(DeadName);


                first.setSelected(false);
                first = null;
               }
               else if(Objects.equals(first.getName(), "兵")||Objects.equals(first.getName(), "卒") &&squareComponent.getLevel()==7){
                    chessboard.swapChessComponents(first, squareComponent);


                    chessboard.clickController.swapPlayer();

                    String DeadName = squareComponent.getName();
                    this.DeadName=DeadName;
                    System.out.println(DeadName);


                    first.setSelected(false);
                    first = null;
                }

                else {squareComponent.setSelected(false);
                   JOptionPane.showMessageDialog(squareComponent, "不是有效行棋！错误代码105，李在干神魔");
                   first.repaint();
                    squareComponent.setSelected(false);
                   first.setSelected(false);
                   first = null;
                   onClick(squareComponent);
                }
            }

            else if (!handleSecond(squareComponent)) {
                System.out.println("不是有效行棋！错误代码105");

                JOptionPane.showMessageDialog(squareComponent, "不是有效行棋！错误代码105，李在干神魔");
              //  JOptionPane.showMessageDialog( "lbwNB");
            }
        }
    }


    /**
     * @param squareComponent 目标选取的棋子
     * @return 目标选取的棋子是否与棋盘记录的当前行棋方颜色相同
     */

    private boolean handleFirst(SquareComponent squareComponent) {

     //    if(!Objects.equals(squareComponent.getName(), "炮")){System.out.println("not Cannon");}//检测是否是炮


        if (!squareComponent.isReversal()) {
            squareComponent.setReversal(true);
            System.out.printf("onClick to reverse a chess [%d,%d]\n", squareComponent.getChessboardPoint().getX(), squareComponent.getChessboardPoint().getY());
            squareComponent.repaint();
            chessboard.clickController.swapPlayer();
            return false;
        }
        return squareComponent.getChessColor() == chessboard.getCurrentColor();
    }

    /**
     * @param squareComponent first棋子目标移动到的棋子second
     * @return first棋子是否能够移动到second棋子位置
     */

    private boolean handleSecond(SquareComponent squareComponent) {

     //   if(first.){} //判断是否是炮
        if(first.getLevel()==2){
            if ((squareComponent instanceof EmptySlotComponent)) {
                return false;
            }
           System.out.println(108);
            if(squareComponent.isReversal()){
            return squareComponent.getChessColor() != chessboard.getCurrentColor() &&
                    first.PaoMoveTo(chessboard.getChessComponents(), squareComponent.getChessboardPoint());}

            else if(!squareComponent.isReversal()){
                return
                        first.PaoMoveTo(chessboard.getChessComponents(), squareComponent.getChessboardPoint());
            }
        }


        //没翻开或空棋子，进入if
        if (!squareComponent.isReversal()) {

            //没翻开且非空棋子不能走
            if (!(squareComponent instanceof EmptySlotComponent)) {
                return false;
            }
        }

//炮的规则在这里写
//if 不是炮，下面的return；if是，写一个新方法

        return squareComponent.getChessColor() != chessboard.getCurrentColor() &&
                first.canMoveTo(chessboard.getChessComponents(), squareComponent.getChessboardPoint());}
                 //SqareC 149行


    public void swapPlayer() {
        boolean isRedleft =false;
        boolean isBlackleft =false;
        for(int i=0;i<8;i++){
            for(int j=0;j<4;j++){
                if(chessboard.getSquareComponents()[i][j].getChessColor()==ChessColor.RED){
                    isRedleft =true;
                    break;
                }
                if(chessboard.getSquareComponents()[i][j].getChessColor()==ChessColor.BLACK){
                    isBlackleft =true;
                    break;
                }
            }
        }
        if(!isRedleft||!isBlackleft){//ChessGameFrame.getStatusLabel().setText("游戏结束");
            JOptionPane.showMessageDialog(this,"游戏结束！行棋显示已经失效！");
            JOptionPane.showMessageDialog(this,"获得成就：一切都结束了");
            chessboard.Happy+=1;
            ChessGameFrame.getHappyBug().setText("彩蛋："+chessboard.Happy);
            ChessGameFrame.getStatusLabel3().setText("游戏结束！");
        }
        else {
            chessboard.setCurrentColor(chessboard.getCurrentColor() == ChessColor.BLACK ? ChessColor.RED : ChessColor.BLACK);
            ChessGameFrame.getStatusLabel().setText(String.format("%S's TURN", chessboard.getCurrentColor().getName()));
            if (chessboard.getCurrentColor() == ChessColor.BLACK) {
                ChessGameFrame.getStatusLabel().setForeground(Color.BLACK);
            }
            if (chessboard.getCurrentColor() == ChessColor.RED) {
                ChessGameFrame.getStatusLabel().setForeground(Color.RED);
            }
            // ChessGameFrame.getContentPane().setBackground(Color.GRAY);
            if (Objects.equals(chessboard.getCurrentColor().getName(), "Red")) {
                chessboard.Move = "d";
            }
            if (Objects.equals(chessboard.getCurrentColor().getName(), "Black")) {
                chessboard.Move = "s";
            }

            ChessGameFrame.getStatusLabel2().setText(String.format("德军：" + chessboard.getBlackScore()));
            ChessGameFrame.getStatusLabel2a().setText(String.format("苏军：" + chessboard.getRedScore()));
        }


    }

    public void onClickAI(SquareComponent squareComponent) {
        //判断第一次点击
        if (first == null) {
            if (handleFirst(squareComponent)) {//是友军
                squareComponent.setSelected(true);
                first = squareComponent;
                first.repaint();
            }
        } else {
            if (first == squareComponent) { // 再次点击取消选取
                squareComponent.setSelected(false);
                SquareComponent recordFirst = first;
                first = null;
                recordFirst.repaint();
            } else if (handleSecond(squareComponent)) {
                //repaint in swap chess method.
                if(first.getLevel()>=squareComponent.getLevel()||first.getLevel()==2){
                    chessboard.swapChessComponents(first, squareComponent);


                    chessboard.clickController.swapPlayer();

                    String DeadName = squareComponent.getName();
                    this.DeadName=DeadName;
                    System.out.println(DeadName);


                    first.setSelected(false);
                    first = null;
                }

                else {squareComponent.setSelected(false);
                   // JOptionPane.showMessageDialog(squareComponent, "不是有效行棋！错误代码105，李在干神魔");
                    first.repaint();
                    squareComponent.setSelected(false);
                    first.setSelected(false);
                    first = null;
                    onClick(squareComponent);
                }
            }
            else if (!handleSecond(squareComponent)) {
              //  System.out.println("不是有效行棋！错误代码105");

               // JOptionPane.showMessageDialog(squareComponent, "不是有效行棋！错误代码105，李在干神魔");
                //  JOptionPane.showMessageDialog( "lbwNB");
            }
        }
    }


}
