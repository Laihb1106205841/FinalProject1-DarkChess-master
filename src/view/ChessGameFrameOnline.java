package view;

import chessComponent.EmptySlotComponent;
import chessComponent.SquareComponent;
import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

/**
 *  * GB：                                    这个包比较重要！
 * 这个类表示游戏窗体，窗体上包含：
 * 1 Chessboard: 棋盘
 * 2 JLabel:  标签
 * 3 JButton： 按钮
 */
public class ChessGameFrameOnline extends JFrame {
    private final int WIDTH;
    public SquareComponent chess[] = new SquareComponent[16];//创建了32个棋子对象
    private int BLACKCHESS = 1;
    private int REDCHESS = 0;//黑棋是1，红旗是0
    private InetAddress myID;//自己id地址
    private InetAddress youID;//目标ID地址
    public int player;//设置当前玩家对象
    private boolean isFirst = false;//判断是否是第一次点击的棋子，以此分开两次点击棋子的碰撞矛盾
    private int x1,y1,x2,y2;//用来保存第一次第二次选中的坐标的
    public int map[][] = new int [8][4];
    public int Row = 8;
    public int Col = 4;//11行10列
    private boolean flag = true;//用来控制线程的运行
    private boolean isPlay = false;
    private int sendport;//发送端口
    private int receiveport = 8888;//接收端口
    private final int HEIGHT;
    public final int CHESSBOARD_SIZE;  //private boolean isFirst = false;//判断是否是第一次点击的棋子，以此分开两次点击棋子的碰撞矛盾
    private GameController gameController;
    private static JLabel statusLabel;
    private static JLabel statusLabel2;
    private static JLabel statusLabel2a;
    private static JLabel statusLabelsu;
    private static JLabel statusLabelsuj2;
    private static JLabel statusLabelde;
    private static JLabel statusLabeldej2;
    private static JLabel HappyBug;
    public boolean isload ;
    private SquareComponent firstChess = null;
    private SquareComponent secondChess = null;//设置第一次点击的棋子和第二次选中的棋子对象
    public List<String> Mes;

    public static JLabel getStatusLabelde() {return statusLabelde;}

    private static JLabel statusLabel3;

    private JButton saveB=new JButton("保存");
    private JButton renewB=new JButton("重新开始");
    //private ChessGameFrame a;

    public static JLabel getStatusLabeldej2() {
        return statusLabeldej2;
    }



//    public Point ReSetID(int x, int y)//重置id,将id转化成可辨识的坐标信息
//    {
//        int posx = (y+height/2)/height;
//        int posy = (x+width/2)/width;
//        return new Point(posx,posy);
//    }

    public void init_map()//初始化棋盘
    {
        for(int i = 0; i < Row; i++)
        {
            for(int j = 0; j < Col; j++)
            {
                map[i][j] = -1;//将棋盘初始化为-1，表示没有棋子id
            }
        }
    }

//








    public ChessGameFrameOnline(int width, int height, boolean isload, List<String> Mes) {
        setTitle("2022 CS109 Project Demo：任何邪恶，终将绳之以法！"); //设置标题
        this.WIDTH = width;
        this.HEIGHT = height;
        this.CHESSBOARD_SIZE = HEIGHT * 4 / 5;
        this.isload=isload;
        this.Mes=Mes;
        //设置窗口背景颜色

        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); // Center the window.
        getContentPane().setBackground(Color.LIGHT_GRAY);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);


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
        addHappy();


        addDeadRedjiangLabel();
        addDeadRedjiangxianLabel();
        addDeadBlackjiangLabel();
        addDeadBlackjiangxianLabel();
        addChessboard();

//try {
//    run();
//} catch (Exception e) {
//    throw new RuntimeException(e);
//}


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
        Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE / 2, CHESSBOARD_SIZE,isload,Mes);
        gameController = new GameController(chessboard);
        chessboard.setLocation(HEIGHT / 10, HEIGHT / 10);
        add(chessboard);

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
        HappyBug = new JLabel("彩蛋："+"0");
        HappyBug.setLocation(WIDTH * 1 / 10, HEIGHT / 100);
       HappyBug.setSize(200, 60);

      HappyBug.setForeground(Color.BLACK);
        HappyBug.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(HappyBug);
    }

    public static JLabel getStatusLabel2() {return statusLabel2;}

    public static JLabel getStatusLabel2a() {return statusLabel2a;}

    private void addPointsBlackLabel() {
        statusLabel2 = new JLabel("德军："+0);
        statusLabel2.setForeground(Color.YELLOW);
       // System.out.println(gameController.getChessboard().getBlackScore());
        statusLabel2.setLocation(WIDTH * 4 / 5, HEIGHT / 10+20);
        statusLabel2.setSize(200, 60);
        statusLabel2.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(statusLabel2);
    }
    private void addPointsRedLabel() {
        statusLabel2a = new JLabel("苏军："+0);
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
            ChessGameFrameOnline mainFrame = new ChessGameFrameOnline(WIDTH, HEIGHT,false,null);
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
          List<String> message=  gameController.loadGameFromFile("C:\\Users\\11062\\Desktop\\课本\\计算机\\DarkChess-master\\src\\SaveFile\\"
            + path);

            String realPath ="C:\\Users\\11062\\Desktop\\课本\\计算机\\DarkChess-master\\src\\SaveFile\\"+path;
                    try {
                        List<String> chessData = null;
                        try {
                            chessData = Files.readAllLines(Path.of(realPath));
                            System.out.println(chessData);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

//                        Chessboard chessboard1 = new Chessboard(CHESSBOARD_SIZE / 2, CHESSBOARD_SIZE,true,chessData);
//                        chessboard1.setVisible(true);
//
//                        chessboard1.repaint();
//                        gameController = new GameController(chessboard1);
//                        chessboard1.setLocation(HEIGHT / 10, HEIGHT / 10);
//                        chessboard1.setVisible(true);
//                        gameController.setChessboard(chessboard1);
//                        add(chessboard1);
//                        if(Objects.equals(gameController.getChessboard().Move, "d")){  getContentPane().setBackground(Color.GRAY);}
//                        if(Objects.equals(gameController.getChessboard().Move, "s")){  getContentPane().setBackground(Color.RED);System.out.println("soviet");}

                        this.setVisible(false);

                     //   System.out.println("remake");

                        this.addWindowListener(new java.awt.event.WindowAdapter() {
                            public void windowClosing(java.awt.event.WindowEvent e) {
                                System.exit(0);
                            }
                        });
                        ChessGameFrameOnline mainFrame = new ChessGameFrameOnline(WIDTH, HEIGHT,true,message);
                        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                        setDefaultCloseOperation(EXIT_ON_CLOSE);
                        mainFrame.setVisible(true);

                        JOptionPane.showMessageDialog(this, "尝试Load");
                    } finally {

                    }
                }
        );
    }

    public void run()
    {
        DatagramSocket sock = null;
        try {
            sock = new DatagramSocket(receiveport);//打开监听窗口
            byte data[] = new byte[100];
            DatagramPacket pocket = new DatagramPacket(data,data.length);
            while(flag)
            {
                sock.receive(pocket);//接收数据
                //读取接收信息
                String str = new String(data);
                String s[] = new String[6];
                s = str.split("\\|");//将数据信息按照|进行分割
                if(s[0].equals("play"))//表示此时这个对象是一个被邀请的对象，将被邀请的对象设置为黑棋
                {
                    player = BLACKCHESS;//被邀请者设为黑棋
                    send("connect|",youID,sendport);
                    //开始画棋盘
                   // FirstPaintChess();
                    isPlay = false;//因为是红棋先走，所以黑棋此时不能下棋
                }
                else if(s[0].equals("connect"))//表示此时的对象是游戏发出者对象，并且已经和被邀请对象建立连接
                {
                    player = REDCHESS;//游戏发出者设为红棋对象
                   // FirstPaintChess();
                    isPlay = true;//因为此时是红棋，而红旗先走，所以红棋此时可以下棋
                }
                else if(s[0].equals("lose"))//对方认输
                {
                    JOptionPane.showConfirmDialog(null, "认输", "对方棋手认输！", JOptionPane.OK_OPTION);
                    isPlay = false;
                }
                else if(s[0].equals("success"))//对方赢了
                {
                    if(s[1].equals("黑棋赢"))
                    {
                        JOptionPane.showConfirmDialog(null, "输了", "黑棋赢了！您输了！", JOptionPane.OK_OPTION);
                    }
                    else if(s[1].equals("红棋赢"))
                    {
                        JOptionPane.showConfirmDialog(null, "输了", "红棋赢了！您输了！", JOptionPane.OK_OPTION);
                    }
                    isPlay = false;
                }
                else if(s[0].equals("move"))//对方走棋
                {
                    int indx = Integer.parseInt(s[1]);
                    System.out.println("indx->"+indx);
                    int posx = Integer.parseInt(s[2]);
                    System.out.println("posx->"+posx);
                    int posy = Integer.parseInt(s[3]);
                    System.out.println("posy->"+posy);
                    int x = chess[indx].getX();
                    int y = chess[indx].getY();
                    map[x][y] = -1;
                    chess[indx].setPoints(posx);
                    chess[indx].setPoints(posy);
                    if(map[posx][posy] != -1)
                    {
                        chess[map[posx][posy]] = null;
                    }
                    map[posx][posy] = indx;
                    repaint();
                    isPlay = true;
                }
                else if(s[0].equals("quit"))//对方退出
                {
                    JOptionPane.showConfirmDialog(null, "提示", "对方离开，游戏结束！", JOptionPane.OK_OPTION);
                    isPlay = false;
                    flag = false;//退出线程
                }

            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally
        {
            if(sock != null)
            {
                sock.close();
            }
        }

    }
    public void send(String str,InetAddress ip,int port) //发送数据报
    {
        DatagramSocket s = null;
        try{
            s = new DatagramSocket();//创建一个数据报套接字
            byte data[] = new byte[100];
            data = str.getBytes();
            DatagramPacket pocket = new DatagramPacket(data,data.length,ip,port);//将数据报的信息放入自寻址包中,自寻址信息包括数据，数据长度，目标ip地址，目标端口号
            s.send(pocket);//发送自寻址包
            System.out.println("发送信息："+str);
        }catch(IOException ex)
        {
            ex.printStackTrace();
        }finally
        {
            if(s != null)
                s.close();
        }
    }

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









