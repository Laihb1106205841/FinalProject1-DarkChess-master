package view;


import chessComponent.*;
import controller.GameController;
import model.*;
import controller.ClickController;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * 这个类表示棋盘组建，其包含：
 * SquareComponent[][]: 4*8个方块格子组件
 * GB：                                    这个包比较重要！
 *             控制棋盘上面的样子
 */
public class Chessboard extends JComponent {





    private static final int ROW_SIZE = 8;
    private static final int COL_SIZE = 4;
    public int Happy =0;

    public Boolean IsLoad=false;

    private Boolean IsFenChang;

    private Boolean Order227;
    private Boolean OrderPanzer;

    public List<String> chessData;

    public int RedScore;
    private Boolean stillmaintainRed = true;

    public String isWin;

    public int getRedScore() {
        return RedScore;
    }

    public int getBlackScore() {
        return BlackScore;
    }

    public int BlackScore;

    private final SquareComponent[][] squareComponents = new SquareComponent[ROW_SIZE][COL_SIZE];

    public SquareComponent[][] getSquareComponents() {
        return squareComponents;
    }

    //todo: you can change the initial player
    private ChessColor currentColor = ChessColor.BLACK;

    //all chessComponents in this chessboard are shared only one model controller
    public final ClickController clickController = new ClickController(this);
    private final int CHESS_SIZE;

    public String DeadName;
    protected ArrayList<String>DeadRedpeople;

    public ArrayList<String> getDeadRedpeople() {
        return DeadRedpeople;
    }

    public ArrayList<String> getDeadBlackpeople() {
        return DeadBlackpeople;
    }

    public ArrayList<String> getDeadRedGeneral() {
        return DeadRedGeneral;
    }

    public ArrayList<String> getDeadBlackGeneral() {
        return DeadBlackGeneral;
    }

    protected ArrayList<String>DeadBlackpeople;

    protected ArrayList<String>DeadRedGeneral;
    protected ArrayList<String>DeadBlackGeneral;

    public Boolean isWinning = false;
    public String Move;

    public String getMove() {
        return Move;
    }

    public String getDeadName() {
        return DeadName;
    }
    public boolean isload;

    public Chessboard(int width, int height,boolean isload,List<String> loadFile) {
        setLayout(null); // Use absolute layout.
        setSize(width + 2, height);
        CHESS_SIZE = (height - 6) / 8;
        SquareComponent.setSpacingLength(CHESS_SIZE / 12);
        System.out.printf("chessboard [%d * %d], chess size = %d\n", width, height, CHESS_SIZE);


        DeadRedpeople = new ArrayList<>();
        DeadBlackpeople =new ArrayList<>();
        DeadRedGeneral =new ArrayList<>();
        DeadBlackGeneral =new ArrayList<>();
        Happy =0;
        OrderPanzer =false;
        Order227 =false;
        this.isload =isload;
       // this.chessData=new ArrayList<>();
        this.chessData=loadFile;
      //  chessData =loadFile;

        initAllChessOnBoard();

    }
    public String showChessboard(){
       String[][] chessNameShow=new String[8][4];

        for (int i=0;i<8;i++){
            for (int j=0;j<4;j++){
               chessNameShow[i][j]= squareComponents[i][j].getName();
            }
        }
        StringBuilder chessnameshow = new StringBuilder();
        for (int i=0;i<8;i++){
            for (int j=0;j<4;j++){
                chessnameshow.append(chessNameShow[i][j]);
                chessnameshow.append(" ");
            }
            chessnameshow.append("\n");
        }

        //System.out.println(chessnameshow);
        return String.valueOf(chessnameshow);
    }


    public SquareComponent[][] getChessComponents() {
        return squareComponents;
    }

    public ChessColor getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(ChessColor currentColor) {
        this.currentColor = currentColor;
    }

    /**
     * 将SquareComponent 放置在 ChessBoard上。里面包含移除原有的component及放置新的component
     *
     * @param squareComponent
     */
    public void putChessOnBoard(SquareComponent squareComponent) {
        int row = squareComponent.getChessboardPoint().getX(), col = squareComponent.getChessboardPoint().getY();
        if (squareComponents[row][col] != null) {
            remove(squareComponents[row][col]);
        }
        add(squareComponents[row][col] = squareComponent);
    }

    public String getIsWin() {
        return isWin;
    }

    public  int getHappy() {return Happy;}

    /**
     * 交换chess1 chess2的位置
     *
     * @param chess1
     * @param chess2
     */
    public void swapChessComponents(SquareComponent chess1, SquareComponent chess2) {
        // Note that chess1 has higher priority, 'destroys' chess2 if exists.
        if (!(chess2 instanceof EmptySlotComponent)) {
//            if(Objects.equals(chess1.getName(), "兵") && Order227 && Objects.equals(chess2.getName(), "帅")){
//                int Points = chess2.getPoints();
//                RedScore += Points;
//                this.DeadName = chess2.getName();
//                DeadBlackpeople.add(DeadName);
//                DeadBlackGeneral.add(chess2.getGeneral());
//                String foreGen =DeadBlackGeneral.toString();
//                String formertext = DeadBlackpeople.toString();
//                //      ChessGameFrame.getStatusLabel3().setText(formertext+ "\n"+ DeadName);//问老师！！！！！
//                String strMsg = formertext ;
//                try {
//                    JlabelSetText(ChessGameFrame.getStatusLabelde(),strMsg);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                try {
//                    JlabelSetText(ChessGameFrame.getStatusLabeldej2(),foreGen);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                remove(chess2);
//                JOptionPane.showMessageDialog(this, "227号命令成功起到了作用！\n歼灭了德军集团军群！");
//            }
//            if( OrderPanzer && Objects.equals(chess2.getName(), "将")){
//                if(Objects.equals(chess1.getName(), "車")&&Objects.equals(chess1.getName(), "馬")){
//                int Points = chess2.getPoints();
//                RedScore += Points;
//                this.DeadName = chess2.getName();
//                DeadBlackpeople.add(DeadName);
//                DeadBlackGeneral.add(chess2.getGeneral());
//                String foreGen =DeadBlackGeneral.toString();
//                String formertext = DeadBlackpeople.toString();
//                //      ChessGameFrame.getStatusLabel3().setText(formertext+ "\n"+ DeadName);//问老师！！！！！
//                String strMsg = formertext ;
//                try {
//                    JlabelSetText(ChessGameFrame.getStatusLabelde(),strMsg);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                try {
//                    JlabelSetText(ChessGameFrame.getStatusLabeldej2(),foreGen);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                remove(chess2);
//                JOptionPane.showMessageDialog(this, "闪电战命令成功起到了作用！\n合围了苏军集团军群！");
//            }}
            if (true) {
                int Points = chess2.getPoints();
                //  System.out.println(Points);
                if (chess2.getChessColor() == ChessColor.BLACK) {
                    RedScore += Points;
                }
                System.out.println(RedScore);
                if (chess2.getChessColor() == ChessColor.RED) {
                    BlackScore += Points;
                }
                this.DeadName = chess2.getName();
 //                 这里是写分数的！！！！！！！！！！！！！！！！！！！
                if (chess2.getChessColor() == ChessColor.RED) {
                DeadRedpeople.add(DeadName);
                DeadRedGeneral.add(chess2.getGeneral());
                    String formertext = DeadRedpeople.toString();
                    //      ChessGameFrame.getStatusLabel3().setText(formertext+ "\n"+ DeadName);//问老师！！！！！
                    String strMsg = formertext ;
                    try {
                        JlabelSetText(ChessGameFrame.getStatusLabelsu(),strMsg);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    String formeGen =DeadRedGeneral.toString();
                    try {
                        JlabelSetText(ChessGameFrame.getStatusLabelsuj2(),formeGen);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

            }//IBM SPSS Statistics
                if (chess2.getChessColor() == ChessColor.BLACK) {
                    DeadBlackpeople.add(DeadName);
                    DeadBlackGeneral.add(chess2.getGeneral());
                    String foreGen =DeadBlackGeneral.toString();
                    String formertext = DeadBlackpeople.toString();
                    //      ChessGameFrame.getStatusLabel3().setText(formertext+ "\n"+ DeadName);//问老师！！！！！
                    String strMsg = formertext ;
                    try {
                        JlabelSetText(ChessGameFrame.getStatusLabelde(),strMsg);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        JlabelSetText(ChessGameFrame.getStatusLabeldej2(),foreGen);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                //这里是写坟场的！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！


                remove(chess2);

                //这里是写游戏效果的！！！！！！！！！！！！！！！！！！！！！
                if (Objects.equals(chess2.getGeneral(), "古德里安")) {
                    JOptionPane.showMessageDialog(this, "古德里安的第四装甲师被歼灭！");}
                if (Objects.equals(chess2.getGeneral(), "朱可夫")) {
                    JOptionPane.showMessageDialog(this, "苏军统帅朱可夫被歼灭！");}
                if (Objects.equals(chess2.getGeneral(), "保卢斯")) {
                    JOptionPane.showMessageDialog(this, "保卢斯元帅向苏军投降！");}
                if (Objects.equals(chess2.getGeneral(), "科涅夫")) {
                    JOptionPane.showMessageDialog(this, "科涅夫的第3集团军被歼灭！");}
                if (Objects.equals(chess2.getGeneral(), "里布")) {
                    JOptionPane.showMessageDialog(this, "里布的第6集团军被歼灭！");}
                if (Objects.equals(chess2.getGeneral(), "罗科索夫斯基")) {
                    JOptionPane.showMessageDialog(this, "罗科索夫斯基的乌克兰方面军第3装甲师被歼灭！");}
                if (Objects.equals(chess2.getGeneral(), "赫鲁晓夫")) {
                    JOptionPane.showMessageDialog(this,
                            "赫鲁晓夫在高加索阻击战中被歼灭！");}
                if (Objects.equals(chess2.getGeneral(), "曼施坦因")) {
                    JOptionPane.showMessageDialog(this,
                            "曼施坦因B集团军群没能突破苏军的防线！");}
                if (Objects.equals(chess2.getGeneral(), "布琼尼")) {
                    JOptionPane.showMessageDialog(this,
                            "布琼尼的骑兵军长眠于敌人的钢铁履带下！");}
                if (Objects.equals(chess2.getGeneral(), "克莱斯特")) {
                    JOptionPane.showMessageDialog(this,
                            "克莱斯特装甲第一集团军群被围歼！");}

                //成就模式：
                if(Objects.equals(chess2.getGeneral(), "保卢斯")&&
                        Objects.equals(chess1.getGeneral(), "朱可夫")){
                    JOptionPane.showMessageDialog(this, "达成成就！“元帅的投降”");
                    Happy +=1;
                    ChessGameFrame.getHappyBug().setText("彩蛋："+Happy);
                }
                if(Objects.equals(chess1.getChessColor(), ChessColor.RED)&&
                        Objects.equals(chess2.getGeneral(), "朱可夫")){
                    JOptionPane.showMessageDialog(this, "达成成就！“杀死你的上司”");
                    Happy +=1;
                    ChessGameFrame.getHappyBug().setText("彩蛋："+Happy);
                }
                if(Objects.equals(chess1.getChessColor(),ChessColor.RED)&&
                        Objects.equals(chess2.getGeneral(), "赫鲁晓夫")){
                    JOptionPane.showMessageDialog(this, "达成成就！“贝利亚的逆袭”");
                    Happy +=1;
                    ChessGameFrame.getHappyBug().setText("彩蛋："+Happy);
                }


                add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation(), clickController, CHESS_SIZE));

                chess1.swapLocation(chess2);
                int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
                squareComponents[row1][col1] = chess1;
                int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
                squareComponents[row2][col2] = chess2;

                //只重新绘制chess1 chess2，其他不变
                chess1.repaint();
                chess2.repaint();



            }
            //  else {}


        } else {
            chess1.swapLocation(chess2);//如果是空气盘
            int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
            squareComponents[row1][col1] = chess1;
            int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
            squareComponents[row2][col2] = chess2;


            //只重新绘制chess1 chess2，其他不变
            chess1.repaint();
            chess2.repaint();
        }


        if (BlackScore >= 60) {
            if (!isWinning) {
                System.out.println("Black win!");
                isWin = "德军胜利！";
                JOptionPane.showMessageDialog(this, isWin);
                JOptionPane.showMessageDialog(this, "您可以继续游戏，或者重新开始");
                isWinning = true;
                chess1.repaint();
                chess2.repaint();
                ChessGameFrame.getStatusLabel3().setText("德军胜利！");
                ChessGameWin Win=new ChessGameWin(1000,700,"德军胜利！");
                Win.setVisible(true);

            }
        }
        if (RedScore >= 60) {
            if (!isWinning) {
                System.out.println("苏军胜利!");
                isWin = "苏军胜利!";
                JOptionPane.showMessageDialog(this, isWin);
                JOptionPane.showMessageDialog(this, "您可以继续游戏，或者重新开始");
                isWinning = true;
                chess1.repaint();
                chess2.repaint();
                ChessGameFrame.getStatusLabel3().setText("苏军胜利！");
                ChessGameWin Win=new ChessGameWin(1000,700,"苏军胜利！");
                Win.setVisible(true);
            }
        }


    }

    static int getRanInArr(int[] array) {
        int length = array.length;
        /*Random random = new Random();
        int index=random.nextInt(length)+1;*/
        int index = (int) (Math.random() * length);
        return array[index];
    }


    //FIXME:   Initialize chessboard for testing only.(done!!!)
    private void initAllChessOnBoard() {
        Random random = new Random();

        if (!isload) {

//
        Boolean[] zhanwei = new Boolean[32];
        for (int i = 0; i < 32; i++) {
            zhanwei[i] = true;
        }

        for (int i = 0; i < squareComponents.length; i++) {
            for (int j = 0; j < squareComponents[i].length; j++) {
                ChessComponent squareComponent;

                int nextID = random.nextInt(32);
                if (!zhanwei[nextID])//==true
                {
                    Boolean initiated = true;
                    while (initiated) {
                        nextID = random.nextInt(32);
                        if (zhanwei[nextID]) {
                            initiated = false;
                            break;
                        }
                    }
                }

                if (nextID < 16) {
                    ChessColor color = ChessColor.BLACK;
                    if (nextID == 0) {
                        squareComponent = new GeneralChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("曼施坦因");
                        squareComponent.setID(0);
                        //   Qizi.remove(0);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 1) {
                        squareComponent = new CannonChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("里布");
                        squareComponent.setID(1);
                        //    Qizi.remove(1);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 2) {
                        squareComponent = new CannonChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("李斯特");
                        squareComponent.setID(2);
                        //               Qizi.remove(2);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 3) {
                        squareComponent = new AdvisorChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("龙德施泰德");
                        squareComponent.setID(3);
                        //           Qizi.remove(3);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 4) {
                        squareComponent = new AdvisorChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("莫德尔");
                        squareComponent.setID(4);
                        //          Qizi.remove(4);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 5) {
                        squareComponent = new MinisterChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("布劳希奇");
                        squareComponent.setID(5);
                        //         Qizi.remove(5);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 6) {
                        squareComponent = new MinisterChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("舍尔纳");
                        squareComponent.setID(6);
                        //       Qizi.remove(6);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 7) {
                        squareComponent = new ChariotChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("古德里安");
                        squareComponent.setID(7);
                        //         Qizi.remove(7);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 8) {
                        squareComponent = new ChariotChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("隆美尔");
                        squareComponent.setID(8);
                        //         Qizi.remove(8);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 9) {
                        squareComponent = new HorseChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("博克");
                        squareComponent.setID(9);
                        //         Qizi.remove(9);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 10) {
                        squareComponent = new HorseChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("克鲁格");
                        squareComponent.setID(10);
                        //         Qizi.remove(10);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 11) {
                        squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("保卢斯");
                        squareComponent.setID(11);
                        //          Qizi.remove(11);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 12) {
                        squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("维茨勒本");
                        squareComponent.setID(12);
                        //            Qizi.remove(12);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 13) {
                        squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("魏克斯");
                        squareComponent.setID(13);
                        //         Qizi.remove(13);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 14) {
                        squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("屈希勒尔");
                        squareComponent.setID(14);
                        //           Qizi.remove(14);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 15) {
                        squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("克莱斯特");
                        squareComponent.setID(15);
                        //         Qizi.remove(15);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    }

                } else if (nextID >= 16) {
                    ChessColor color = ChessColor.RED;
                    if (nextID == 16) {
                        squareComponent = new GeneralChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("朱可夫");
                        squareComponent.setID(16);
                        //           Qizi.remove(16);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 17) {
                        squareComponent = new CannonChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("科涅夫");
                        squareComponent.setID(17);
                        //          Qizi.remove(17);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 18) {
                        squareComponent = new CannonChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("戈沃罗夫");
                        squareComponent.setID(18);
                        //           Qizi.remove(18);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 19) {
                        squareComponent = new AdvisorChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("马利诺夫斯基");
                        squareComponent.setID(19);
                        //             Qizi.remove(19);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 20) {
                        squareComponent = new AdvisorChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("华西列夫斯基");
                        squareComponent.setID(20);
                        //            Qizi.remove(20);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 21) {
                        squareComponent = new MinisterChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("库兹涅佐夫");
                        squareComponent.setID(21);
                        //             Qizi.remove(21);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 22) {
                        squareComponent = new MinisterChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("沙波什尼科夫");
                        squareComponent.setID(22);
                        //             Qizi.remove(22);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 23) {
                        squareComponent = new ChariotChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("罗科索夫斯基");
                        squareComponent.setID(23);
                        //           Qizi.remove(23);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 24) {
                        squareComponent = new ChariotChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("瓦图京");
                        squareComponent.setID(24);
                        //           Qizi.remove(24);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 25) {
                        squareComponent = new HorseChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("梅列茨科夫");
                        squareComponent.setID(25);
                        //        Qizi.remove(25);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 26) {
                        squareComponent = new HorseChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("巴格拉米扬");
                        squareComponent.setID(26);
                        //           Qizi.remove(26);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 27) {
                        squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("巴季茨基");
                        squareComponent.setID(27);
                        //            Qizi.remove(27);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 28) {
                        squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("赫鲁晓夫");
                        squareComponent.setID(28);
                        //           Qizi.remove(28);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 29) {
                        squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("崔可夫");
                        squareComponent.setID(29);
                        //            Qizi.remove(29);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 30) {
                        squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("布琼尼");
                        squareComponent.setID(30);
                        //          Qizi.remove(30);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    } else if (nextID == 31) {
                        squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);
                        squareComponent.setGeneral("铁木辛哥");
                        squareComponent.setID(31);
                        //           Qizi.remove(31);
                        squareComponent.setVisible(true);
                        putChessOnBoard(squareComponent);
                        zhanwei[nextID] = false;
                    }

                } else
                    squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);


//


            }
        }
    }
        if(isload){
            loadGame(chessData);

        }
//
    }

    /**
     * 绘制棋盘格子
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


    }

    public Boolean getOrder227() {
        return Order227;
    }

    public String GetOrder227() {
        String order ="";
        if(Order227){
            order ="T";
        }
        else {
            order="F";
        }

        return order;
    }

    public Boolean getOrderPanzer() {
        return OrderPanzer;
    }
    public String GetOrderPanzer(){
        String order ="";
        if(OrderPanzer){
            order="T";
        }
        else {
            order="F";
        }
        return order;
    }

    /**
     * 将棋盘上行列坐标映射成Swing组件的Point
     *
     * @param row 棋盘上的行
     * @param col 棋盘上的列
     * @return
     */
    private Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE + 3, row * CHESS_SIZE + 3);
    }

    public void setHappy(int happy) {
        Happy = happy;
    }

    public void setFenChang(Boolean fenChang) {
        IsFenChang = fenChang;
    }

    public void setOrder227(Boolean order227) {
        Order227 = order227;
    }

    public void setOrderPanzer(Boolean orderPanzer) {
        OrderPanzer = orderPanzer;
    }

    public void setRedScore(int redScore) {
        RedScore = redScore;
    }

    public void setStillmaintainRed(Boolean stillmaintainRed) {
        this.stillmaintainRed = stillmaintainRed;
    }

    public void setIsWin(String isWin) {
        this.isWin = isWin;
    }

    public void setBlackScore(int blackScore) {
        BlackScore = blackScore;
    }

    public void setDeadName(String deadName) {
        DeadName = deadName;
    }

    public void setDeadRedpeople(ArrayList<String> deadRedpeople) {
        DeadRedpeople = deadRedpeople;
    }

    public void setDeadBlackpeople(ArrayList<String> deadBlackpeople) {
        DeadBlackpeople = deadBlackpeople;
    }

    public void setDeadRedGeneral(ArrayList<String> deadRedGeneral) {
        DeadRedGeneral = deadRedGeneral;
    }

    public void setDeadBlackGeneral(ArrayList<String> deadBlackGeneral) {
        DeadBlackGeneral = deadBlackGeneral;
    }

    public void setWinning(Boolean winning) {
        isWinning = winning;
    }

    public void setMove(String move) {
        Move = move;
    }

    /**
     * 通过GameController调用该方法
     *
     * @param chessData
     */
    public void loadGame(List<String> chessData) {
        if (chessData != null) {
            chessData.forEach(System.out::println);


        String[][] ChessName = new String[8][4];
        String ChessShouldHaveName = "车車 俥兵卒 将帅 相象 砲炮 null 兵 \n 卒 傌 null 馬 仕士象炮 ";

        for (int i = 0; i < 8; i++) {//导入名字
            String[] everyline;
            String hang = chessData.get(i);
            everyline = hang.split(" ");
            if (everyline.length != 4) {
                JOptionPane.showMessageDialog
                        (this, "棋盘错误！棋盘并非8*4！\n错误代码102");
            }
            for (int j = 0; j < 4; j++) {
                ChessName[i][j] = everyline[j];
            }
        }
        for (int i = 0; i < 8; i++) {//103
            for (int j = 0; j < 4; j++) {
                if (ChessShouldHaveName.indexOf(ChessName[i][j]) == -1) {//相信我，这个没问题
                    JOptionPane.showMessageDialog
                            (this, "棋子错误！棋子并非红黑7种棋子之一！\n错误代码103");


                    ChessGameFrame mainFrame = new ChessGameFrame(WIDTH, HEIGHT,false,null);

                    mainFrame.setVisible(true);
                    JOptionPane.showMessageDialog
                            (this, "点击重开按钮继续游戏");

                    return;
                }
            }
        }


        String[][] ChessGeneral = new String[8][4];
        for (int i = 0; i < 8; i++) {//导入将领
            String[] everyline;
            String hang = chessData.get(i + 9);
            everyline = hang.split(" ");
            if (everyline.length != 4) {
                JOptionPane.showMessageDialog
                        (this, "有未任命的指挥官！");
            }
            for (int j = 0; j < 4; j++) {
                ChessGeneral[i][j] = everyline[j];
            }
        }
        String[][] ChessLevel = new String[8][4];
        for (int i = 0; i < 8; i++) {//导入等级
            String[] everyline;
            String hang = chessData.get(i + 18);
            everyline = hang.split("");
            if (everyline.length != 4) {
                JOptionPane.showMessageDialog
                        (this, "有分数丢失！");
            }
            for (int j = 0; j < 4; j++) {
                ChessLevel[i][j] = everyline[j];
            }
        }
        Boolean[][] ChessIsReveal = new Boolean[8][4];
        for (int i = 0; i < 8; i++) {//导入翻棋
            String[] everyline;
            String hang = chessData.get(i + 27);
            everyline = hang.split("");
            if (everyline.length != 4) {
                JOptionPane.showMessageDialog
                        (this, "有棋子是否翻起状态丢失！");
            }

            for (int j = 0; j < 4; j++) {
                Boolean isreveal = false;
                if (Objects.equals(everyline[j], "T")) {
                    isreveal = true;
                }
                if (Objects.equals(everyline[j], "F")) {
                    isreveal = false;
                }
                ChessIsReveal[i][j] = isreveal;
            }
        }
        String iswin = chessData.get(36);
        if (Objects.equals(iswin, "null")) {
            isWinning = false;
            isWin = iswin;
        }
        if (Objects.equals(iswin, "德军胜利！")) {
            isWinning = true;
            isWin = iswin;
            ChessGameFrame.getStatusLabel3().setText("德军胜利！");
        }
        if (Objects.equals(iswin, "苏军胜利！")) {
            isWinning = true;
            isWin = iswin;
            ChessGameFrame.getStatusLabel3().setText("苏军胜利！");

        }
        String DeadRedren = chessData.get(37);
        String fixDRR = DeadRedren.substring(1, DeadRedren.length() - 1);
        String[] DeadRen = fixDRR.split(", ");
        DeadRedpeople.addAll(Arrays.asList(DeadRen));

        System.out.println(fixDRR);
        String formertext1 = DeadRedpeople.toString();
        String strMsg1 = formertext1;
        try {
            JlabelSetText(ChessGameFrame.getStatusLabelsu(), strMsg1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // ChessGameFrame.getStatusLabelsu().setText(DeadRedpeople.toString());
        String DeadBlackren = chessData.get(38);
        ChessGameFrame.getStatusLabelde().setText(DeadBlackren);
        String DeadRedGeneral = chessData.get(39);
        ChessGameFrame.getStatusLabelsuj2().setText(DeadRedGeneral);
        String DeadBlackGeneral = chessData.get(40);
        ChessGameFrame.getStatusLabeldej2().setText(DeadBlackGeneral);

        String xingqifang = chessData.get(41);
        if (!Objects.equals(xingqifang, "BLACK") && !Objects.equals(xingqifang, "RED")
                && !xingqifang.equals("游戏结束")) {
            JOptionPane.showMessageDialog
                    (this, """
                            缺少行棋方！
                            导入数据只有棋盘，没有下一步行棋的方的提示
                            错误编码：104""");

            JOptionPane.showMessageDialog
                    (this, "点击重开按钮继续游戏");

            return;
        }
        if (Objects.equals(xingqifang, "RED")) {
            currentColor = ChessColor.RED;
            ChessGameFrame.getStatusLabel().setText("RED's TURN");
            ChessGameFrame.getStatusLabel().setForeground(Color.RED);
        }
        if (Objects.equals(xingqifang, "BLACK")) {
            currentColor = ChessColor.BLACK;
            ChessGameFrame.getStatusLabel().setText("BLACK's TURN");
            ChessGameFrame.getStatusLabel().setForeground(Color.BLACK);
        }


        String SovietFen = chessData.get(43);
        RedScore = Integer.parseInt(SovietFen);
        ChessGameFrame.getStatusLabel2a().setText("苏军：" + RedScore);
        String GermanFen = chessData.get(44);
        BlackScore = Integer.parseInt(GermanFen);
        ChessGameFrame.getStatusLabel2().setText("德军：" + BlackScore);

        String happy = chessData.get(45);
        Happy = Integer.parseInt(happy);
        ChessGameFrame.getHappyBug().setText("彩蛋：" + Happy);

        String oRder227 = chessData.get(46);
        String oRderPanzer = chessData.get(47);
        if (Objects.equals(oRder227, "F")) {
            Order227 = false;
        }
        if (Objects.equals(oRder227, "T")) {
            Order227 = true;
        }
        if (Objects.equals(oRderPanzer, "F")) {
            OrderPanzer = false;
        }
        if (Objects.equals(oRderPanzer, "T")) {
            OrderPanzer = true;
        }

        ChessColor[][] Chesscolor = new ChessColor[8][4];
        for (int i = 0; i < 8; i++) {//导入颜色
            String[] everyline;
            String hang = chessData.get(i + 48);
            everyline = hang.split(" ");
            if (everyline.length != 4) {
                JOptionPane.showMessageDialog
                        (this, "有未确定的军队！");
            }
            for (int j = 0; j < 4; j++) {
                if (Objects.equals(everyline[j], "RED")) {
                    Chesscolor[i][j] = ChessColor.RED;
                }
                if (Objects.equals(everyline[j], "BLACK")) {
                    Chesscolor[i][j] = ChessColor.BLACK;
                }
            }
        }


//        ChessGameFrameLoad mainFrame= new ChessGameFrameLoad(1000,700);
//
//        mainFrame.setVisible(true);
//       mainFrame.LoadMessage =chessData;


//(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController, CHESS_SIZE);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {

                ChessboardPoint CPoint = new ChessboardPoint(i, j);
                //remove(squareComponents[i][j]);

                if (Objects.equals(ChessName[i][j], "兵") || Objects.equals(ChessName[i][j], "卒")) {
                    squareComponents[i][j] = new SoldierChessComponent(CPoint, calculatePoint(i, j), Chesscolor[i][j], clickController, CHESS_SIZE);
                    //      squareComponents[i][j].repaint();

                }
                if (Objects.equals(ChessName[i][j], "炮") || Objects.equals(ChessName[i][j], "砲")) {
                    squareComponents[i][j] = new CannonChessComponent(CPoint, calculatePoint(i, j), Chesscolor[i][j], clickController, CHESS_SIZE);
                    //    squareComponents[i][j].repaint();
                }
                if (Objects.equals(ChessName[i][j], "馬") || Objects.equals(ChessName[i][j], "傌")) {
                    squareComponents[i][j] = new HorseChessComponent(CPoint, calculatePoint(i, j), Chesscolor[i][j], clickController, CHESS_SIZE);
                    //   squareComponents[i][j].repaint();
                }
                if (Objects.equals(ChessName[i][j], "俥") || Objects.equals(ChessName[i][j], "車")) {
                    squareComponents[i][j] = new ChariotChessComponent(CPoint, calculatePoint(i, j), Chesscolor[i][j], clickController, CHESS_SIZE);
                    //    squareComponents[i][j].repaint();
                }
                if (Objects.equals(ChessName[i][j], "相") || Objects.equals(ChessName[i][j], "象")) {
                    squareComponents[i][j] = new MinisterChessComponent(CPoint, calculatePoint(i, j), Chesscolor[i][j], clickController, CHESS_SIZE);
                    //   squareComponents[i][j].repaint();
                }
                if (Objects.equals(ChessName[i][j], "仕") || Objects.equals(ChessName[i][j], "士")) {
                    squareComponents[i][j] = new AdvisorChessComponent(CPoint, calculatePoint(i, j), Chesscolor[i][j], clickController, CHESS_SIZE);
                    //    squareComponents[i][j].repaint();
                }
                if (Objects.equals(ChessName[i][j], "将") || Objects.equals(ChessName[i][j], "帅")) {
                    squareComponents[i][j] = new GeneralChessComponent(CPoint, calculatePoint(i, j), Chesscolor[i][j], clickController, CHESS_SIZE);
                    //squareComponents[i][j].repaint();
                }
                if (Objects.equals(ChessName[i][j], "null")) {
                    squareComponents[i][j] = new EmptySlotComponent(CPoint, calculatePoint(i, j), clickController, CHESS_SIZE);

                }
                //     squareComponents[i][j] =new ChessComponent(CPoint,calculatePoint(i,j),Chesscolor[i][j],clickController,CHESS_SIZE);
                squareComponents[i][j].setGeneral(ChessGeneral[i][j]);
                squareComponents[i][j].setLevel(Integer.parseInt(ChessLevel[i][j]));
                squareComponents[i][j].setReversal(ChessIsReveal[i][j]);

                putChessOnBoard(squareComponents[i][j]);
                //squareComponents[i][j].repaint();

                squareComponents[i][j].setVisible(true);

                //  squareComponents[i][j].


            }
        }


    }
    }//保存处

    protected void initiate227(){
        for(int i=0;i<8;i++){
            for (int j=0;j<4;j++){
                if(Objects.equals(squareComponents[i][j].getName(), "兵")){
                    squareComponents[i][j].setLevel(4);
                    squareComponents[i][j].repaint();
                }
            }
        }
          Order227 =true;
    }
    protected void initiatePanzer(){
        for(int i=0;i<8;i++){
            for (int j=0;j<4;j++){
                if(Objects.equals(squareComponents[i][j].getName(), "馬")){
                    squareComponents[i][j].setLevel(5);
                    squareComponents[i][j].repaint();
                }
                if(Objects.equals(squareComponents[i][j].getName(), "車")){
                    squareComponents[i][j].setLevel(6);
                    squareComponents[i][j].repaint();
                }
            }
        }
        OrderPanzer=true;
    }







    void JlabelSetText(JLabel jLabel, String longString)
            throws InterruptedException {
        StringBuilder builder = new StringBuilder("<html><body>");
        char[] chars = longString.toCharArray();
        FontMetrics fontMetrics = jLabel.getFontMetrics(jLabel.getFont());
        int start = 0;
        int len = 0;
        while (start + len < longString.length()) {
            while (true) {
                len++;
                if (start + len > longString.length()) break;
                if (fontMetrics.charsWidth(chars, start, len)
                        > jLabel.getWidth()) {
                    break;
                }
            }
            builder.append(chars, start, len - 1).append("<br/>");
            start = start + len - 1;
            len = 0;
        }
        builder.append(chars, start, longString.length() - start);
        builder.append("<body></html>");
        jLabel.setText(builder.toString());
    }

    public String ShowGeneral(){
        String[][] chessGeneralShow=new String[8][4];

        for (int i=0;i<8;i++){
            for (int j=0;j<4;j++){
                chessGeneralShow[i][j]= squareComponents[i][j].getGeneral();
            }
        }
        StringBuilder chessGeneralshow = new StringBuilder();
        for (int i=0;i<8;i++){
            for (int j=0;j<4;j++){
                chessGeneralshow.append(chessGeneralShow[i][j]);
                chessGeneralshow.append(" ");
            }
            chessGeneralshow.append("\n");
        }

        return String.valueOf(chessGeneralshow);
    }
    public String Showlevel(){
        String[][] chesslevelShow=new String[8][4];

        for (int i=0;i<8;i++){
            for (int j=0;j<4;j++){
                chesslevelShow[i][j]= String.valueOf(squareComponents[i][j].getLevel());
            }
        }
        StringBuilder chesslevelshow = new StringBuilder();
        for (int i=0;i<8;i++){
            for (int j=0;j<4;j++){
                chesslevelshow.append(chesslevelShow[i][j]);
              //  chesslshow.append(" ");
            }
            chesslevelshow.append("\n");
        }

        return String.valueOf(chesslevelshow);
    }

    public String ShowIsReveal(){
        String[][] chessRevelShow=new String[8][4];

        for (int i=0;i<8;i++){
            for (int j=0;j<4;j++){
                if(squareComponents[i][j].isReversal()){
                    chessRevelShow[i][j]="T";
                }
                else {
                    chessRevelShow[i][j]="F";
                }
            }
        }
        StringBuilder chessRevealshow = new StringBuilder();
        for (int i=0;i<8;i++){
            for (int j=0;j<4;j++){
                chessRevealshow.append(chessRevelShow[i][j]);
                //  chesslshow.append(" ");
            }
            chessRevealshow.append("\n");
        }

        return String.valueOf(chessRevealshow);
    }
    public String SHowColor(){
        String[][] chessColorShow=new String[8][4];

        for (int i=0;i<8;i++){
            for (int j=0;j<4;j++){
               chessColorShow[i][j]= String.valueOf(squareComponents[i][j].getChessColor());
            }
        }
        StringBuilder chesscolorshow = new StringBuilder();
        for (int i=0;i<8;i++){
            for (int j=0;j<4;j++){
                chesscolorshow.append(chessColorShow[i][j]);
                chesscolorshow.append(" ");
                //  chesslshow.append(" ");
            }
            chesscolorshow.append("\n");
        }

        return String.valueOf(chesscolorshow);
    }

    public static void ObjectPrint(Object[] array) {
        for (Object element : array) System.out.printf("%s ", element);
        System.out.println();
    }












}