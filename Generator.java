package view;
import view.ChessGameFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.InetAddress;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyAdapter;
import controller.ClickController;
import controller.GameController;



import javax.swing.*;
import java.awt.event.KeyEvent;


public class Generator {
    public static void main(String[] args) {
        GamePanel gp = new GamePanel();
        gp.launch();
    }

//    public static class ChessFrame extends JFrame {
//
//        private JButton start = new JButton("开始");
//        private JButton end = new JButton("结束");
//        private JButton lose = new JButton("认输");
//        private JPanel paneUp = new JPanel();
//        private ChessGameFrame chesspanel = new ChessGameFrame(1000,700);
//        private JPanel paneDown = new JPanel();
//
//        public ChessFrame()//构造函数
//        {
//            paneDown.add(start);
//            paneDown.add(lose);
//            paneDown.add(end);
//            lose.setEnabled(false);
//
//
//
//            Container con = this.getContentPane();
//            con.add(paneUp, BorderLayout.NORTH);
//            con.add(paneDown, BorderLayout.SOUTH);
//
//            this.setTitle("黑暗破坏棋");
//            this.setSize(new Dimension(600, 700));
//            this.setVisible(true);
//            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        }



    public static class GamePanel extends JFrame {
        int width = 800;
        int height = 610;//长宽

        //准备启动
        public void launch() {
            setTitle("暗黑破坏神");//标题
            setSize(width,height);//窗口初始大小
            setLocationRelativeTo(null);//屏幕居中
            setDefaultCloseOperation(3);//关闭事件
            setResizable(false);//用户不能调整大小
            setVisible(true);//窗口可见
         //   this.addKeyListener(new GamePanel.KeyMonitor());
        }

        @Override
        public void paint(Graphics g){
         //   g.setColor(Color.gray);//画笔颜色
         //   g.fillRect(0,0,width,height);//实心矩形
            g.setColor(Color.blue);//改变画笔颜色
            g.setFont(new Font("仿宋",Font.BOLD,50));//改变文字大小和样式
            g.drawString("选择游戏模式",220,100);
            g.drawString("双人游戏",220,200);
            g.drawString("联网模式",220,300);
            addHelloButton2();
        }


        private void addHelloButton2() {
            JButton button = new JButton("Hello");
       button.addActionListener((e) -> JOptionPane.showMessageDialog(this ,"Hello, world!"));
            button.setLocation(500, 30);
            button.setSize(100, 200);
            button.setFont(new Font("Rockwell", Font.BOLD, 20));
            add(button);


    }
    }
}
