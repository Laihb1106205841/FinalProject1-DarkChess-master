package view;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ChessGameWin extends JFrame{
    private final int WIDTH;
    private final int HEIGHT;
    private String Yanse;
    private static JLabel LabelWin;
    public ChessGameWin(int width, int height,String Yanse){
        this.WIDTH = width;
        this.HEIGHT = height;
        this.Yanse=Yanse;
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); // Center the window.
        getContentPane().setBackground(Color.LIGHT_GRAY);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);


        addLabel();

        addRestart();
        addLabel2();
    }
    private void addLabel() {
        LabelWin = new JLabel(Yanse);
        LabelWin.setLocation(WIDTH * 3 / 5, HEIGHT / 2);
        LabelWin.setSize(200, 60);

        LabelWin.setForeground(Color.YELLOW);
        LabelWin.setFont(new Font("Rockwell", Font.BOLD, 40));
        add(LabelWin);
    }
    private void addLabel2() {//昊京载入！
          ImageIcon image=new ImageIcon("C:\\Users\\11062\\Desktop\\课本\\计算机\\DarkChess-master\\src\\view\\11.jpg");
       if(Objects.equals(Yanse, "德军胜利！")) {
            image = new ImageIcon("C:\\Users\\11062\\Desktop\\课本\\计算机\\DarkChess-master\\src\\ADDbyGB\\德军.jpg");
       }
       else  {
          image = new ImageIcon("C:\\Users\\11062\\Desktop\\课本\\计算机\\DarkChess-master\\src\\ADDbyGB\\苏军.jpg");
        }
        JLabel statusLabel2=new JLabel(image);
        statusLabel2.setLayout(null);
        statusLabel2.setBounds(0,0,WIDTH,HEIGHT);
        statusLabel2.setVisible(true);
        add(statusLabel2);
    }
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
        button.setForeground(Color.YELLOW);
        button.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(button);
    }
}
