package view;

import chessComponent.EmptySlotComponent;
import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *  * GB：                                    这个包比较重要！
 * 这个类表示游戏窗体，窗体上包含：
 * 1 Chessboard: 棋盘
 * 2 JLabel:  标签
 * 3 JButton： 按钮
 */
public class ChessGameFrameLoad extends JFrame {
    private final int WIDTH;
    private final int HEIGHT;
    public final int CHESSBOARD_SIZE;
    private GameController gameController;
    private static JLabel statusLabel;
    private static JLabel statusLabel2;
    private static JLabel statusLabel2a;
    private static JLabel statusLabelsu;
    private static JLabel statusLabelsuj2;
    private static JLabel statusLabelde;
    private static JLabel statusLabeldej2;
    private static JLabel HappyBug;

    public static JLabel getStatussLabelde() {return statusLabelde;}

    private static JLabel statusLabel3;

    private JButton saveB=new JButton("保存");
    private JButton renewB=new JButton("重新开始");
    public List<String> isload;
    //private ChessGameFrame a;

    public static JLabel getStatussLabeldej2() {
        return statusLabeldej2;
    }

    public ChessGameFrameLoad(int width, int height,List<String> isload) {
        setTitle("2022 CS109 Project Demo：任何邪恶，终将绳之以法！"); //设置标题
        this.WIDTH = width;
        this.HEIGHT = height;
        this.CHESSBOARD_SIZE = HEIGHT * 4 / 5;
        //设置窗口背景颜色

        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); // Center the window.
        getContentPane().setBackground(Color.LIGHT_GRAY);
       this.isload=isload;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        addChessboard();
        addLabel();
        //addDeadRedLabel();

        addBattleLabel();
        addRestart();
        addLoadButton();
        addHelloButton2();
        addHintButton3();
        addPointsBlackLabel();
        addPointsRedLabel();
        addSave();
        addDeadRedLabel();
        addDeadRedLabelonly();
        addDeadBlackLabel();
        addDeadBlackLabelonly();

        addDeadRedjiangLabel();
        addDeadRedjiangxianLabel();
        addDeadBlackjiangLabel();
        addDeadBlackjiangxianLabel();
        addHappy();
        addSovietButton();
        addGermanyButton();
        addAI();



        addLabel2();
     //   addDeadRedLabel();

    }


    /**
     * 在游戏窗体中添加棋盘
     */
    public void addChessboard() {
        Chessboard chessboard1 = new Chessboard(CHESSBOARD_SIZE / 2, CHESSBOARD_SIZE,true,isload);
        gameController = new GameController(chessboard1);
        chessboard1.setLocation(HEIGHT / 10, HEIGHT / 10);
        add(chessboard1);

        if(Objects.equals(gameController.getChessboard().Move, "d")){  getContentPane().setBackground(Color.GRAY);}
        if(Objects.equals(gameController.getChessboard().Move, "s")){  getContentPane().setBackground(Color.RED);System.out.println("soviet");}

    }
    private void addLabel2() {//昊京载入！
        //  ImageIcon image=new ImageIcon("C:\\Users\\11062\\Desktop\\课本\\计算机\\DarkChess-master\\src\\view\\11.jpg");
        ImageIcon image=new ImageIcon("C:\\Users\\11062\\Desktop\\课本\\计算机\\DarkChess-master\\src\\ADDbyGB\\QQ6.png");

        JLabel statusLabel2=new JLabel(image);
        statusLabel2.setLayout(null);
        statusLabel2.setBounds(0,0,WIDTH,HEIGHT);
        statusLabel2.setVisible(true);
        add(statusLabel2);


    }
//    private void addChessboardFenChang() {
//        Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE / 4, CHESSBOARD_SIZE / 2);
//        gameController = new GameController(chessboard);
//        chessboard.setLocation(HEIGHT / 10+400, HEIGHT / 10);
//        add(chessboard);
//    }

    /**
     * 在游戏窗体中添加标签
     */
    private void addLabel() {
        statusLabel = new JLabel("BLACK's TURN");
        statusLabel.setLocation(WIDTH * 3 / 5, HEIGHT / 10);
        statusLabel.setSize(200, 60);

        statusLabel.setForeground(Color.BLACK);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(statusLabel);
    }

    public static JLabel getHappyBug() {return HappyBug;}

    private void addHappy() {
        HappyBug = new JLabel("彩蛋："+gameController.getChessboard().getHappy());
        HappyBug.setLocation(WIDTH * 1 / 10, HEIGHT / 100);
       HappyBug.setSize(200, 60);

      HappyBug.setForeground(Color.BLACK);
        HappyBug.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(HappyBug);
    }

    public static JLabel getStatusLabel2() {return statusLabel2;}

    public static JLabel getStatusLabel2a() {return statusLabel2a;}

    private void addPointsBlackLabel() {
        statusLabel2 = new JLabel("德军："+gameController.getChessboard().getBlackScore());
        statusLabel2.setForeground(Color.YELLOW);
       // System.out.println(gameController.getChessboard().getBlackScore());
        statusLabel2.setLocation(WIDTH * 4 / 5, HEIGHT / 10+20);
        statusLabel2.setSize(200, 60);
        statusLabel2.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(statusLabel2);
    }
    private void addPointsRedLabel() {
        statusLabel2a = new JLabel("苏军："+gameController.getChessboard().getRedScore());
        statusLabel2a.setForeground(Color.YELLOW);
        statusLabel2a.setLocation(WIDTH * 4 / 5, HEIGHT / 10+45);
        statusLabel2a.setSize(200, 60);
        statusLabel2a.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(statusLabel2a);
    }

    public static JLabel getStatusLabel3() {return statusLabel3;}

    private void addBattleLabel() {

        statusLabel3 = new JLabel("游戏开始！");
        statusLabel3.setForeground(Color.YELLOW);
        statusLabel3.setLocation(WIDTH * 1 / 2, HEIGHT / 10+30);
        statusLabel3.setSize(200, 60);
        statusLabel3.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(statusLabel3);

    }

    public static JLabel getStatusLabelsu() {return statusLabelsu;}
    private void addDeadRedLabel() {

        statusLabelsu = new JLabel("[]");
        statusLabelsu.setForeground(Color.YELLOW);
        statusLabelsu.setLocation(WIDTH * 1 / 2, HEIGHT / 10+120);
        statusLabelsu.setSize(100, 90);
        statusLabelsu.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(statusLabelsu);

    }
    private void addDeadRedjiangLabel() {
        JLabel statusLabelsuj = new JLabel("苏军阵亡将领：");
        statusLabelsuj.setForeground(Color.YELLOW);
        statusLabelsuj.setLocation(WIDTH * 1 / 2, HEIGHT / 10+380);
        statusLabelsuj.setSize(120, 30);
        statusLabelsuj.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(statusLabelsuj);

    }

    public static JLabel getStatusLabelsuj2() {return statusLabelsuj2;}

    private void addDeadRedjiangxianLabel() {
        statusLabelsuj2 = new JLabel("[]");
        statusLabelsuj2.setForeground(Color.YELLOW);
        statusLabelsuj2.setLocation(WIDTH * 1 / 2, HEIGHT / 10+400);
        statusLabelsuj2.setSize(140, 200);
        statusLabelsuj2.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(statusLabelsuj2);

    }
    private void addDeadRedLabelonly() {
        // String DeadChess2 =

        JLabel statusLabelsu1 = new JLabel("苏军阵亡名单：");
        statusLabelsu1.setForeground(Color.YELLOW);
        statusLabelsu1.setLocation(WIDTH * 1 / 2, HEIGHT / 10+100);
        statusLabelsu1.setSize(120, 30);
        statusLabelsu1.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(statusLabelsu1);

    }
    private void addDeadBlackLabel() {
        // String DeadChess2 =

        statusLabelde = new JLabel("[]");
        statusLabelde.setForeground(Color.YELLOW);
        statusLabelde.setLocation(WIDTH * 1 / 2, HEIGHT / 10+250);
        statusLabelde.setSize(100, 90);
        statusLabelde.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(statusLabelde);

    }
    private void addDeadBlackLabelonly() {
        // String DeadChess2 =

        JLabel statusLabelsu2 = new JLabel("德军阵亡名单：");
        statusLabelsu2.setForeground(Color.YELLOW);
        statusLabelsu2.setLocation(WIDTH * 1 / 2, HEIGHT / 10+230);
        statusLabelsu2.setSize(120, 30);
        statusLabelsu2.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(statusLabelsu2);

    }
    private void addDeadBlackjiangLabel() {
        JLabel statusLabelsuj = new JLabel("德军阵亡将领：");
        statusLabelsuj.setForeground(Color.YELLOW);
        statusLabelsuj.setLocation(WIDTH * 2 / 3, HEIGHT / 10+380);
        statusLabelsuj.setSize(120, 30);
        statusLabelsuj.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(statusLabelsuj);

    }
    private void addDeadBlackjiangxianLabel() {
        statusLabeldej2 = new JLabel("[]");
        statusLabeldej2.setForeground(Color.YELLOW);
        statusLabeldej2.setLocation(WIDTH * 2 / 3, HEIGHT / 10+400);
        statusLabeldej2.setSize(140, 200);
        statusLabeldej2.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(statusLabeldej2);

    }
    public static JLabel getStatusLabel() {
        return statusLabel;
    }

    /**
     * 在游戏窗体中增加一个按钮，如果按下的话就会显示Hello, world!
     */

    public void addRestart() {
        JButton button = new JButton("重开");

        button.addActionListener((e) -> {
            this.setVisible(false);

            System.out.println("remake");

            this.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            ChessGameFrame mainFrame = new ChessGameFrame(WIDTH, HEIGHT,false,null);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            mainFrame.setVisible(true);
            JOptionPane.showMessageDialog(this, "尝试Remake");
        });

        button.setLocation(WIDTH * 4 / 5, HEIGHT / 10 + 120);
        button.setSize(90, 30);
        button.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(button);
    }
    public void addSave() {
        JButton button = new JButton("保存");
        button.setLocation(WIDTH * 4 / 5, HEIGHT / 10 + 160);
        button.setSize(90, 30);
        button.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(button);
        button.addActionListener(e -> {
          //  String path = JOptionPane.showInputDialog(this, "Input Path here");
            String name = JOptionPane.showInputDialog(this, "Input name here");

          //  gameController.saveDataToFile(name,path);
            gameController.SaveFile(name);
            System.out.println("save！");


         //gamecontroller 保存在最下方编写，然后可以通过重开一个窗口实现

            JOptionPane.showMessageDialog(this, "保存成功了");
        });


    }
    private void addHelloButton2() {
        JButton button = new JButton("全球卫星");
          //  Boolean iskaiguo = false;
        button.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "伞兵一号卢本伟准备就绪！");
            for (int i = 0; i < 8; i++) {
                for(int j=0;j< 4;j++){
                  //明天问老师
                    if (!(gameController.getChessboard().getSquareComponents()[i][j] instanceof EmptySlotComponent)) {
                    gameController.getChessboard().getSquareComponents()[i][j].setReversal(true);
                    gameController.getChessboard().getSquareComponents()[i][j].repaint();
                    }
                }
            }
        });
        button.setLocation(WIDTH * 4 / 5, HEIGHT / 10 + 420);
        button.setSize(120, 30);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
    private void addHintButton3() {
        JButton button = new JButton("提示");
        button.addActionListener((e) ->
                JOptionPane.showMessageDialog(this, "    本游戏除了传统的暗棋设置，还增加了将领系统，成就系统，技能模式" +
                        "\n227号命令作为苏军统帅部针对保卫莫斯科的一道死命令，要求苏军士兵严守防线"+
                        "\n二战开始，面对T-34与Kv2，德军统战部反应要求设计一种新型坦克。最终，由亨舍尔公司研制的虎式坦克正式列装部队"
                +"\n德军与苏军在广阔的平原上进行着惨烈的作战。铭记历史，珍惜和平")
        );
        button.setLocation(WIDTH * 4 / 5, HEIGHT / 10 + 340);
        button.setSize(120, 30);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
    private void addAI() {
        JButton button = new JButton("参谋部");
        button.addActionListener((e) ->
                {
                    gameController.AI();
                }

        );
        button.setLocation(WIDTH * 4 / 5, HEIGHT / 10 + 540);
        button.setSize(120, 30);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
    private void addSovietButton() {
        JButton button = new JButton("227号命令");
        button.addActionListener((e) ->
        {
            if(gameController.getChessboard().getBlackScore()>=30&&!gameController.getChessboard().getOrder227()){
            String path = JOptionPane.showInputDialog(this, "在此输入227开启命令！");
            if(Objects.equals(path, "227")){
                JOptionPane.showMessageDialog(this, "No step back!\n苏军的兵等级+3！");
                gameController.getChessboard().initiate227();
            }
            }else {
                JOptionPane.showMessageDialog(this, "仍需等待命令！");
            }
        }
        );
        button.setLocation(WIDTH * 4 / 5, HEIGHT / 10 + 460);
        button.setSize(160, 30);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
    private void addGermanyButton() {
        JButton button = new JButton("闪电战");
        button.addActionListener((e) ->
                {
                    if(gameController.getChessboard().getRedScore()>=30&&!gameController.getChessboard().getOrderPanzer()){
                        String path = JOptionPane.showInputDialog(this, "输入panzer开启闪电战！");
                        if(Objects.equals(path, "panzer")){
                            JOptionPane.showMessageDialog(this, "成功研发虎式坦克!\n德军的车马等级+2！");
                            gameController.getChessboard().initiatePanzer();
                        }
                    }else {
                        JOptionPane.showMessageDialog(this, "新式坦克研究中！");
                    }
                }
        );
        button.setLocation(WIDTH * 4 / 5, HEIGHT / 10 + 500);
        button.setSize(160, 30);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }





    private void addLoadButton() {
        JButton button = new JButton("载入游戏");
        button.setLocation(WIDTH * 4 / 5, HEIGHT / 10 + 240);
        button.setSize(90, 30);
        button.setFont(new Font("Rockwell", Font.BOLD, 15));
        button.setBackground(Color.LIGHT_GRAY);
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click load");
            String path = JOptionPane.showInputDialog(this, "Input name here");
            gameController.loadGameFromFile("C:\\Users\\11062\\Desktop\\课本\\计算机\\DarkChess-master\\src\\SaveFile\\"
            + path);

        });

    }


//    void JlabelSetText(JLabel jLabel, String longString)
//            throws InterruptedException {
//        StringBuilder builder = new StringBuilder("<html>");
//        char[] chars = longString.toCharArray();
//        FontMetrics fontMetrics = jLabel.getFontMetrics(jLabel.getFont());
//        int start = 0;
//        int len = 0;
//        while (start + len < longString.length()) {
//            while (true) {
//                len++;
//                if (start + len > longString.length())break;
//                if (fontMetrics.charsWidth(chars, start, len)
//                        > jLabel.getWidth()) {
//                    break;
//                }
//            }
//            builder.append(chars, start, len-1).append("<br/>");
//            start = start + len - 1;
//            len = 0;
//        }
//        builder.append(chars, start, longString.length()-start);
//        builder.append("</html>");
//        jLabel.setText(builder.toString());
//    }

//————————————————
//        版权声明：本文为CSDN博主「御风逍遥」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//        原文链接：https://blog.csdn.net/zhhtao89/article/details/50179695








}
