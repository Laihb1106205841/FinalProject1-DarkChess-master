package view;
import javafx.application.Application;
import view.ChessGameFrame;
//import ADDbyGB.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyAdapter;
import controller.ClickController;
import controller.GameController;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * <p><b>MVC:<b></p>

 *   Model:后端（基本完成）<p>
 *
 *   View：前端，用户界面（完成）<p>
 *
 *   Controller：将前端的数据转换给后端进行计算（正在编译...）<p>
 *
 *   <p><b>当前任务：<b></p>
 *   1、初始化棋盘上的随机问题                                               easy（完成！）<p>
 *   2、显示双方的分数，定义分数变量                                          medium(完成！)<p>
 *   3、存储游戏，写一个存储的代码，还有按钮                                    hard（完成70%）<p>
 *   4、写一个读取游戏的代码，其中包含5个报错(已完成： 105,                      medium<p>
 *   5、写一个游戏等级，分数构成的基本游戏                                     medium(完成)<p>
 *   6、写一个坟场，他们说要红黑分开，那就分开吧。分上下，如果有余力，写一个记牌器     hard    (完成！)下周咨询老师完成<p>
 *   7、写一个作弊模式，                                                    easy//完成<p>
 *   8、写任何移动都是无效的，没有什么可以走了                                  easy（完成60%or不用管）<p>
 *   9、检测游戏是否胜利                                                    medium(完成！)<p>
 *
 *   <p><b>bonus：<b></p>
 *   1、网络模式                hard<p>
 *   2、音乐，苏联德国           easy（完成！）<p>
 *   3、分数~英雄系统            hard（完成！）<p>
 *   4、展示可能行走的区域        medium<p>
 *   5、时光回溯模式             medium<p>
 *   6、AI                     hard（完成！）<p>
 *   7、成就系统                 easy(完成！)<p>
 *<p>
 *   <p><b>12/4任务：<b></p>
 *   1、写炮的运行程序             medium(完成！)<p>
 *   2、写好如何用坟场显示死亡的棋子  easy（完成!）<p>
 *   3、写好存储游戏的存储操作       medium（只要把代入的模块写完就胜利！）<p>
 *   4、写读取游戏的读取代码        medium（完成！）<p>
 *   5、写游戏无路可走时的代码       hard（完成！）<p>
 *
 *   <p><b>代码总计：3503行<b></p>
 *
 *   模型，对整个进行架构<p>
 *
 *   <p><b>最终总分：100<b><p>
 *
 *   <p>
 *   1.弱ai<p>
 *   2.exe<p>
 *   3.github<p>
 *   4.<p>
 *  *
 */

public class Generator2   {
    public static class ChessGameFrame2 extends JFrame {
        JLayeredPane pane = new JLayeredPane();  // 分层网格
        JLabel label;
        JPanel panel1 = new JPanel();
        JTextField field1 = new JTextField();
        private final int WIDTH;
        private final int HEIGHT;
        public final int CHESSBOARD_SIZE;
        ImageIcon image;
        private GameController gameController;
        private static JLabel statusLabel;
        private static JLabel statusLabel2;

        public ChessGameFrame2(int width, int height) {
            //image = new ImageIcon("jpp/蜘蛛侠.jpg");
            //设置组件透明
        //    Land.setOpaque(false);
            label = new JLabel(image);		//把背景图片添加到标签里


            setTitle("2022 CS109 Project Demo：任何邪恶，终将绳之以法！"); //设置标题
            this.WIDTH = width;
            this.HEIGHT = height;
            this.CHESSBOARD_SIZE = HEIGHT * 4 / 5;

            Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE / 2, CHESSBOARD_SIZE,false,null);
            this.gameController=new GameController(chessboard);

            setSize(WIDTH, HEIGHT);
            setLocationRelativeTo(null); // Center the window.
            getContentPane().setBackground(Color.WHITE);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
            setLayout(null);

          //  addChessboard();
            addLabel();//暗黑破坏神
            addHelloButton();//双人游戏
            addLoadButton();//载入游戏
            addHelloButton2();
            addLabel2();//加载战狼


//

        }




        /**
         * 在游戏窗体中添加标签
         */
        private void addLabel() {
            statusLabel = new JLabel("苏德战争：巴巴罗萨");
            statusLabel.setLocation(WIDTH * 1 / 3, HEIGHT / 10);
            statusLabel.setSize(400, 60);
            statusLabel.setFont(new Font("Rockwell", Font.BOLD, 40));
            statusLabel.setForeground(Color.YELLOW);
            add(statusLabel);
        }

        private void addLabel2() {//昊京载入！
          //  ImageIcon image=new ImageIcon("C:\\Users\\11062\\Desktop\\课本\\计算机\\DarkChess-master\\src\\view\\11.jpg");
            ImageIcon image=new ImageIcon("src/ADDbyGB/R-C.jpg");
            JLabel statusLabel2=new JLabel(image);
            statusLabel2.setLayout(null);
            statusLabel2.setBounds(0,0,WIDTH,HEIGHT);
            statusLabel2.setVisible(true);
            changeIconSize(statusLabel2,"",WIDTH,HEIGHT);
            add(statusLabel2);
        }

        public static JLabel getStatusLabel() {
            return statusLabel;
        }


        /**
         * 在游戏窗体中增加一个按钮，如果按下的话就会显示Hello, world!
         */

        private void addHelloButton() {
            JButton button = new JButton("双人游戏");
            button.addActionListener((e) ->  SwingUtilities.invokeLater(() -> {
                ChessGameFrame mainFrame = new ChessGameFrame(1000, 700,false,null);
                mainFrame.setVisible(true);

                Music.stopMusic();
                Music.playMusic2();
                this.setVisible(false);
                this.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                setDefaultCloseOperation(EXIT_ON_CLOSE);

            }));
           // JOptionPane.showMessageDialog(this, "Hello, world!"));
            button.setLocation(WIDTH * 2 / 5, HEIGHT / 10 + 120);
            button.setSize(180, 60);
           // button.setForeground(Color.YELLOW);
            button.setBackground(Color.YELLOW);
            button.setFont(new Font("Rockwell", Font.BOLD, 20));
            add(button);
        }

        private void addHelloButton2() { ImageIcon icon = new ImageIcon("src/ADDbyGB/按钮.jpg");

            JButton button = new JButton("联机对战");
//            button.addActionListener((e) ->  SwingUtilities.invokeLater(() -> {


            button.setBackground(Color.orange);

            //  button.setOpaque(false);//设定透明效果
       //     button.setContentAreaFilled(false);//去掉背景点击效果
          //  button.setFocusPainted(false);//去掉聚焦线
          //  button.setBorder(null); //去掉边框
         //   button.setIcon(icon);//设置显示的图片

            button.addActionListener((e) -> {//JOptionPane.showMessageDialog(this, "Hello, world!");
                String path = JOptionPane.showInputDialog(this, "请输入对方域名：");
                List<String>Path =new ArrayList<>();
                Path.add(path);
                Path.add("Internet");
                button.setFocusPainted(false);
                ChessGameFrame mainFrame = new ChessGameFrame(1000, 700,false,Path);
                mainFrame.setVisible(true);

                Music.stopMusic();
                Music.playMusic2();
                this.setVisible(false);
                this.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
            });

            button.setLocation(WIDTH * 2 / 5, HEIGHT / 10 + 420);
            button.setSize(180, 60);
            button.setFont(new Font("Rockwell", Font.BOLD, 20));
            add(button);

           // JButton startGame = new JButton();









        }


        private void addLoadButton() {
            JButton button = new JButton("载入游戏");
            button.setLocation(WIDTH * 2 / 5, HEIGHT / 10 + 270);
            button.setSize(180, 60);
            button.setFont(new Font("Rockwell", Font.BOLD, 20));
          //  button.setBackground(Color.LIGHT_GRAY);
            button.setBackground(Color.WHITE);
            add(button);

            button.addActionListener(e -> {
                System.out.println("Click load");

                String path = JOptionPane.showInputDialog(this, "Input name here");

          //      if(Objects.equals(path, "测试105.txt")){  JOptionPane.showMessageDialog
          //              (this, "行棋步骤错误！\n错误代码105");}


                List<String> MEssage = gameController.loadGameFromFile("src\\SaveFile\\" +
                        path);
                if (MEssage != null) {
                    String[][] ChessName = new String[8][4];
                    boolean eightfour =true;
//                        if(Objects.equals(gameController.getChessboard().Move, "s")){  getContentPane().setBackground(Color.RED);System.out.println("soviet");}
                    for (int i = 0; i < 8; i++) {//导入名字
                        String[] everyline;
                        String hang = MEssage.get(i);
                        everyline = hang.split(" ");
                        if (everyline.length != 4) {
                            eightfour=false;
                            JOptionPane.showMessageDialog
                                    (this, "棋盘错误！棋盘并非8*4！\n错误代码102");
                        }
                        for (int j = 0; j < 4; j++) {
                            ChessName[i][j] = everyline[j];
                        }
                    }

                    if(eightfour){

                        this.setVisible(false);

                        //   System.out.println("remake");

                        this.addWindowListener(new java.awt.event.WindowAdapter() {
                            public void windowClosing(java.awt.event.WindowEvent e) {
                                System.exit(0);
                            }
                        });

                        ChessGameFrame mainFrame = new ChessGameFrame(WIDTH, HEIGHT,true,MEssage);
                        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                        setDefaultCloseOperation(EXIT_ON_CLOSE);
                        mainFrame.setVisible(true);

                        JOptionPane.showMessageDialog(this, "尝试Load");
                        Music.stopMusic();
                        Music.playMusic2();

                    }


            }
            });


        }
        public static JButton changeIconSize(JButton button,String url,int width,int height,String btnstr){
            button.setBounds(0,0,width,height);//设置按钮的界限
            ImageIcon buttonImg=new ImageIcon(url);
            //改变图片的大小
            Image temp=buttonImg.getImage().getScaledInstance(button.getWidth(), button.getHeight(), buttonImg.getImage().SCALE_DEFAULT);
            button = new JButton(new ImageIcon(temp));
            //设置按钮的点击时按钮字符串
            button.setActionCommand(btnstr);

            return button;
        }
        public static JLabel changeIconSize(JLabel Label,String url,int width,int height){
            Label.setBounds(0,0,width,height);//设置按钮的界限
            ImageIcon buttonImg=new ImageIcon(url);
            //改变图片的大小
            Image temp=buttonImg.getImage().getScaledInstance(Label.getWidth(),
                    Label.getHeight(), buttonImg.getImage().SCALE_DEFAULT);
            //
            Label = new JLabel(new ImageIcon(temp));

            //设置按钮的点击时按钮字符串
           // Label.setActionCommand(btnstr);

            return Label;
        }



    }

    public static void main(String[] args) {
       // Video.launch(args);
        SwingUtilities.invokeLater(() -> {
            ChessGameFrame2 mainFrame = new ChessGameFrame2(1000, 700);
            mainFrame.setVisible(true);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            Music.playMusic();

            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            }catch(Exception e) {
                System.out.println(e);
            }


        });


//

    }
}


