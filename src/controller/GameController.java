package controller;

import chessComponent.ChessComponent;
import chessComponent.SquareComponent;
import model.ChessColor;
import model.ChessboardPoint;
import view.ChessGameFrame;
import view.ChessGameFrameLoad;
import view.Chessboard;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * 这个类主要完成由窗体上组件触发的动作。
 * 例如点击button等
 * ChessGameFrame中组件调用本类的对象，在本类中的方法里完成逻辑运算，将运算的结果传递至chessboard中绘制
 */
public class GameController {
    protected Chessboard chessboard;
    public Boolean ASifload;

    public void setChessboard(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public Chessboard getChessboard() {return chessboard;}

    public GameController(Chessboard chessboard) {
        this.chessboard = chessboard;
        ASifload=false;

    }

    public List<String> loadGameFromFile(String path) {
        ArrayList<String> FileInString =new ArrayList<>();

//更多请阅读：https://www.yiibai.com/java/java-read-text-file.html目前已经删除，但是很有借鉴意义

        try {
            int baohan =path.indexOf("txt");
            if(baohan ==-1){
                JOptionPane.showMessageDialog(this.getChessboard(), "文件格式错误！\n不是txt文件或文件后缀名未加txt！\n错误代码101");
                return null;
            }
            else {
            List<String> chessData = Files.readAllLines(Path.of(path));

          //  ChessGameFrame.
            ASifload=true;
//
           // ChessGameFrameLoad Load =new ChessGameFrameLoad(1000,700,chessData);
//
           // Load.setVisible(true);

           // chessboard.loadGame(chessData);
         //   chessboard.remove(getChessboard());
          //  chessboard.setVisible(false);
           // chessboard =null;

            return chessData;}
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }










//

    public void SaveFile(String name){
        String  chessboardName   = chessboard.showChessboard();//成功！棋子们的名字
        String  chessboardGeneral =chessboard.ShowGeneral();//将军
        String  chessboardlevel =chessboard.Showlevel();//等级
        String  chessboardisReveal =chessboard.ShowIsReveal();//揭开

        String isWin =chessboard.isWin;


        String RedDeadPeople =chessboard.getDeadRedpeople().toString();//死人
        String BlackDeadPeople =chessboard.getDeadBlackpeople().toString();
        String RedDeadGeneral =chessboard.getDeadRedGeneral().toString();
        String BlackDeadGeneral =chessboard.getDeadBlackGeneral().toString();

        String currentColor =chessboard.getCurrentColor().toString();//当前行棋方

        String RedScore = String.valueOf(chessboard.getRedScore());//分数
        String BlackScore = String.valueOf(chessboard.getBlackScore());

        String Happy = String.valueOf(chessboard.getHappy());//彩蛋数
        String Order227 =chessboard.GetOrder227();
        String Orderpanzer =chessboard.GetOrderPanzer();
        String chessColor =chessboard.SHowColor();


        String Final =chessboardName+"\n"+chessboardGeneral+"\n"+chessboardlevel
                +"\n"+chessboardisReveal+"\n"+isWin+"\n"+RedDeadPeople+"\n"+
                BlackDeadPeople+"\n"+RedDeadGeneral+"\n"+BlackDeadGeneral+"\n"
                +currentColor+"\n"+currentColor+"\n"+RedScore+"\n"+BlackScore+
                "\n"+Happy+"\n"+Order227+"\n"+Orderpanzer+"\n"+chessColor;


        File f=new File("C:\\Users\\11062\\Desktop\\课本\\计算机\\DarkChess-master\\src\\SaveFile\\"+name+".txt");//指定文件
        FileOutputStream fos= null;//创建输出流fos并以f为参数
        try {
            fos = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        OutputStreamWriter osw=new OutputStreamWriter(fos);//创建字符输出流对象osw并以fos为参数
        BufferedWriter bw=new BufferedWriter(osw);//创建一个带缓冲的输出流对象bw，并以osw为参数
        try {
            bw.write(Final);//使用bw写入一行文字，为字符串形式String
            //这里就是关键要保存的地方啦！
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            bw.newLine();//换行
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            bw.close();//关闭并保存
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    public void AI(){
        Boolean isgo =false;
        if(chessboard.getCurrentColor()== ChessColor.BLACK){

            for (int i=0;i<8;i++){
                for (int j=0;j<4;j++){
                    SquareComponent dangqian =chessboard.getSquareComponents()[i][j];
                    ChessboardPoint zuobiao =  dangqian.getChessboardPoint();
                    if(dangqian.isReversal()&&dangqian.getChessColor()==ChessColor.BLACK){
                        for (int i1=0;i1<8;i1++){
                            for (int j1=0;j1<4;j1++){
                                if(!isgo){
                                ChessboardPoint destination =new ChessboardPoint(i1,j1);
                                SquareComponent destinationchess =chessboard.getSquareComponents()[i1][j1];

                                if(dangqian.getLevel()!=2&&destinationchess.getChessColor()==ChessColor.RED){
                        if(dangqian.canMoveTo(chessboard.getSquareComponents(),destination)&&dangqian.getLevel()>=destinationchess.getLevel()){
                            chessboard.swapChessComponents(dangqian,destinationchess);

                           ChessGameFrame.getStatusLabel2().setText("德军："+getChessboard().getBlackScore());

                            chessboard.setCurrentColor(ChessColor.RED);
                            ChessGameFrame.getStatusLabel().setText("RED's TURN");
                            ChessGameFrame.getStatusLabel().setForeground(Color.RED);

                         isgo =true;}}
                                if(dangqian.getLevel()==2&&destinationchess.getChessColor()==ChessColor.RED){
                                    if(dangqian.PaoMoveTo(chessboard.getSquareComponents(),destination)) {
                                        chessboard.swapChessComponents(dangqian, destinationchess);
                                        chessboard.setCurrentColor(ChessColor.RED);
                                        ChessGameFrame.getStatusLabel().setText("RED's TURN");
                                        ChessGameFrame.getStatusLabel().setForeground(Color.RED);
                                        ChessGameFrame.getStatusLabel2().setText("德军："+getChessboard().getBlackScore());

                                        isgo = true;
                                        }}
                                        }
                            }}}
                }
            }
            while (!isgo) {
                Random k = new Random();
                int next = k.nextInt(0, 100);
                if (next <= 80) {
                    int X = k.nextInt(0, 8);
                    int Y = k.nextInt(0, 4);
                    SquareComponent dangqian = chessboard.getSquareComponents()[X][Y];

                    if (!isgo && !dangqian.isReversal()) {
                        dangqian.setReversal(true);
                        dangqian.repaint();
                        isgo = true;
                        chessboard.setCurrentColor(ChessColor.RED);
                        ChessGameFrame.getStatusLabel().setText("RED's TURN");
                        ChessGameFrame.getStatusLabel().setForeground(Color.RED);
                    }
                }
                if(next>80){
                    for (int i=0;i<8;i++){
                        for (int j=0;j<4;j++){
                            SquareComponent dangqian =chessboard.getSquareComponents()[i][j];
                            ChessboardPoint zuobiao =  dangqian.getChessboardPoint();
                            if(dangqian.isReversal()&&dangqian.getChessColor()==ChessColor.BLACK){
                                for (int i1=0;i1<8;i1++){
                                    for (int j1=0;j1<4;j1++){
                                        if(!isgo){
                                            ChessboardPoint destination =new ChessboardPoint(i1,j1);
                                            SquareComponent destinationchess =chessboard.getSquareComponents()[i1][j1];
                                            if(dangqian.getLevel()!=2&&destinationchess.getLevel()==0){
                                                if(dangqian.canMoveTo(chessboard.getSquareComponents(),destination)&&dangqian.getLevel()>=destinationchess.getLevel()){
                                                    chessboard.swapChessComponents(dangqian,destinationchess);
                                                    chessboard.setCurrentColor(ChessColor.RED);

                                                    ChessGameFrame.getStatusLabel2a().setText("德军："+getChessboard().getRedScore());
                                                    ChessGameFrame.getStatusLabel().setText("RED's TURN");
                                                    ChessGameFrame.getStatusLabel().setForeground(Color.RED);
                                                    isgo =true;}}

                                        }
                                    }
                                }
                            }
                        }
                    }


                }
            }
        }
        if(chessboard.getCurrentColor()== ChessColor.RED){

            for (int i=0;i<8;i++){
                for (int j=0;j<4;j++){
                    SquareComponent dangqian =chessboard.getSquareComponents()[i][j];
                    ChessboardPoint zuobiao =  dangqian.getChessboardPoint();
                    if(dangqian.isReversal()&&dangqian.getChessColor()==ChessColor.RED){
                        for (int i1=0;i1<8;i1++){
                            for (int j1=0;j1<4;j1++){
                                if(!isgo){
                                    ChessboardPoint destination =new ChessboardPoint(i1,j1);
                                    SquareComponent destinationchess =chessboard.getSquareComponents()[i1][j1];
                                    if(dangqian.getLevel()!=2&&destinationchess.getChessColor()==ChessColor.BLACK){
                                        if(dangqian.canMoveTo(chessboard.getSquareComponents(),destination)&&dangqian.getLevel()>=destinationchess.getLevel()){
                                            chessboard.swapChessComponents(dangqian,destinationchess);
                                            chessboard.setCurrentColor(ChessColor.BLACK);
                                            ChessGameFrame.getStatusLabel2a().setText("苏军："+getChessboard().getRedScore());
                                            ChessGameFrame.getStatusLabel().setText("BLACK's TURN");
                                            ChessGameFrame.getStatusLabel().setForeground(Color.BLACK);
                                            isgo =true;}}
                                    if(dangqian.getLevel()==2&&destinationchess.getChessColor()==ChessColor.BLACK){
                                        if(dangqian.PaoMoveTo(chessboard.getSquareComponents(),destination)) {
                                            chessboard.swapChessComponents(dangqian, destinationchess);
                                            chessboard.setCurrentColor(ChessColor.BLACK);
                                            ChessGameFrame.getStatusLabel2a().setText("苏军："+getChessboard().getRedScore());
                                            ChessGameFrame.getStatusLabel().setText("BLACK's TURN");
                                            ChessGameFrame.getStatusLabel().setForeground(Color.BLACK);
                                            isgo = true;
                                        }}
                                }
                            }}}
                }
            }
            while (!isgo) {
                Random k = new Random();
                int next = k.nextInt(0, 100);
                if (next <= 80) {
                int X = k.nextInt(0, 8);
                int Y = k.nextInt(0, 4);
                SquareComponent dangqian = chessboard.getSquareComponents()[X][Y];

                if (!isgo && !dangqian.isReversal()) {
                    dangqian.setReversal(true);
                    dangqian.repaint();
                    isgo = true;
                    chessboard.setCurrentColor(ChessColor.BLACK);
                    ChessGameFrame.getStatusLabel().setText("BLACK's TURN");
                    ChessGameFrame.getStatusLabel().setForeground(Color.BLACK);
                }
            }
                if(next>80){
                    for (int i=0;i<8;i++){
                        for (int j=0;j<4;j++){
                            SquareComponent dangqian =chessboard.getSquareComponents()[i][j];
                            ChessboardPoint zuobiao =  dangqian.getChessboardPoint();
                            if(dangqian.isReversal()&&dangqian.getChessColor()==ChessColor.RED){
                                for (int i1=0;i1<8;i1++){
                                    for (int j1=0;j1<4;j1++){
                                        if(!isgo){
                                            ChessboardPoint destination =new ChessboardPoint(i1,j1);
                                            SquareComponent destinationchess =chessboard.getSquareComponents()[i1][j1];
                                            if(dangqian.getLevel()!=2&&destinationchess.getLevel()==0){
                                                if(dangqian.canMoveTo(chessboard.getSquareComponents(),destination)&&dangqian.getLevel()>=destinationchess.getLevel()){
                                                    chessboard.swapChessComponents(dangqian,destinationchess);
                                                    chessboard.setCurrentColor(ChessColor.BLACK);

                                                    ChessGameFrame.getStatusLabel2a().setText("苏军："+getChessboard().getRedScore());
                                                    ChessGameFrame.getStatusLabel().setText("BLACK's TURN");
                                                    ChessGameFrame.getStatusLabel().setForeground(Color.BLACK);
                                                    isgo =true;}}
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }


    }











    public ChessboardPoint randomPoint(){
        Random i =new Random();
        int X = i.nextInt(0,8);
        int Y =i.nextInt(0,4);
        ChessboardPoint random =new ChessboardPoint(X,Y);
        return random;
    }




  }

