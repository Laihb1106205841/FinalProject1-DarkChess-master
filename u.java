package ADDbyGB;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Random;

public class u{
    public static void main(String[] args)
    {
        JFrame frame=new JFrame();
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(3);

        frame.setBackground(Color.BLACK);
        mypanel p=new mypanel();
        frame.add(p);
        frame.setVisible(true);
        Thread t=new Thread(p);
        t.start();
    }
}
class mypanel extends JPanel implements Runnable{
    private static final long serialVersionUID=1L;
    Font font=new Font("font",Font.BOLD,50);
    String colors[]={"red","yellow","blue"};
    Random random=new Random();
    int[] snowx=new int [600];
    int[] snowy=new int[600];
    int[] snowy1=new int[600];

    public mypanel()
    {

        for(int i=0;i<600;i++)
        {
            snowx[i]=this.random(800);
            snowy[i]=this.random(600);
        }
    }

    public void snowspaint(Graphics g)
    {
        g.setColor(Color.WHITE);
        for(int i=0;i<600;i++)
        {
            g.drawString("*",snowx[i],snowy[i]);
            for(int j=-1;j<snowy1[i];j++)
            {
                g.drawString("*",snowx[i],600-j*3);
            }
        }
    }
    public void heardpaint(Graphics g)
    {
        double x, y, r;
        Image OffScreen = createImage(500, 500);
        Graphics drawOffScreen = OffScreen.getGraphics();

        for (int i = 0; i < 90; i++) {
            for (int j = 0; j < 90; j++) {
                r = Math.PI / 45 * i * (1 - Math.sin(Math.PI / 45 * j)) * 18;
                x = r * Math.cos(Math.PI / 45 * j) * Math.sin(Math.PI / 45 * i)+ 500 / 2-100;
                y = -r * Math.sin(Math.PI / 45 * j) + 500 / 4-50;

                drawOffScreen.setColor(Color.PINK);
                drawOffScreen.fillOval((int) x, (int) y, 2, 2);
            }
            // 生成图片
            g.drawImage(OffScreen, 0, 0, this);
        }
    }
    public void moonpaint(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillOval(-170, -170, 1100, 1100);
        g.setColor(Color.YELLOW);
        g.fillOval(600, 70, 130, 130);
        g.setColor(Color.BLACK);
        g.fillOval(550, 50, 130, 130);
    }
    public int[] getColor()
    {
        int k=random.nextInt(5);
        int a[][]={{250,0,0},{0,0,250},{250,0,250},{47,56,92},{0,250,0}};
        return a[k];
    }
    public void fontpaint(Graphics g)
    {
        int a[]=getColor();
        g.setFont(font);
        g.setColor(new Color(a[0],a[1],a[2]));
        g.drawString("再",350,100);
        a=getColor();
        g.setColor(new Color(a[0],a[1],a[2]));
        g.drawString("颖",125,185);
        a=getColor();
        g.setColor(new Color(a[0],a[1],a[2]));
        g.drawString("大",450,100);
        a=getColor();
        g.setColor(new Color(a[0],a[1],a[2]));
        g.drawString("的",350,250);
        a=getColor();
        g.setColor(new Color(a[0],a[1],a[2]));
        g.drawString("雪",450,250);
        a=getColor();
        g.setColor(new Color(a[0],a[1],a[2]));
        g.drawString("也",100,370);
        a=getColor();
        g.setColor(new Color(a[0],a[1],a[2]));
        g.drawString("掩",250,370);
        a=getColor();
        g.setColor(new Color(a[0],a[1],a[2]));
        g.drawString("盖",400,370);
        a=getColor();
        g.setColor(new Color(a[0],a[1],a[2]));
        g.drawString("不",550,370);
        a=getColor();
        g.setColor(new Color(a[0],a[1],a[2]));
        g.drawString("了",700,370);
        a=getColor();
        g.setColor(new Color(a[0],a[1],a[2]));
        g.drawString("我",200,500);
        a=getColor();
        g.setColor(new Color(a[0],a[1],a[2]));
        g.drawString("的",400,500);
        a=getColor();
        g.setColor(new Color(a[0],a[1],a[2]));
        g.drawString("心",600,500);
    }
    public void paint(Graphics g)
    {

        moonpaint(g);
        heardpaint(g);
        snowspaint(g);
        fontpaint(g);
    }
    public void run()
    {
        while(true){
            for(int i=0;i<snowy.length;i++)
            {
                if(snowy[i]<=600)
                {
                    snowy[i]++;
                }
                else {
                    snowy1[i]++;
                    snowy[i]=0;
                }
            }
            repaint();
            try{
                Thread.sleep(10);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    public int random(int num)
    {
        return (int)(Math.random()*num);
    }
}
