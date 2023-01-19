package ADDbyGB;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class roll extends JFrame{
    JTextField f1,f2,f3,f4,f5,f6,f7,f8,f9;
    JButton b1_start,b1_stop,b2_start,b2_stop,b3_start,b3_stop;
    Label lb1,lb2,lb3,stg1,stg2,stg3;
    Panel p1,p2,p3;
    thread1 th1;
    thread2 th2;
    thread3 th3;
    int time1,time2,time3;

    String []str= {"Welcome","Hello","Rollby"};
    roll(){
        super("滚动字");
        setSize(500,500);
        setLocation(500,200);
        setBackground(Color.LIGHT_GRAY);
        setLayout(new GridLayout(6,1));
        p1=new Panel();p2=new Panel();p3=new Panel();
        f1=new JTextField(String.format("%115s",str[0]));//格式化字符串，调整字符串位置，使其在整个文本框中滚动
        f2=new JTextField(String.format("%115s",str[1]));//格式化字符串，调整字符串位置，使其在整个文本框中滚动
        f3=new JTextField(String.format("%115s",str[2]));//格式化字符串，调整字符串位置，使其在整个文本框中滚动
        f4=new JTextField(5);f5=new JTextField(5);f6=new JTextField(5);
        f7=new JTextField(8);f8=new JTextField(8);f9=new JTextField(8);
        f7.setEditable(false);f8.setEditable(false);f9.setEditable(false);
        //th1=new thread1();th2=new thread2();th3=new thread3();
        lb1=new Label("sleep");lb2=new Label("sleep");lb3=new Label("sleep");
        stg1=new Label("state");stg2=new Label("state");stg3=new Label("state");
        b1_start=new JButton("启动");b2_start=new JButton("启动");b3_start=new JButton("启动");
        b1_stop=new JButton("中断");b2_stop=new JButton("中断");b3_stop=new JButton("中断");
        time1=100;time2=100;time3=100;
        add(f1);add(p1);add(f2);add(p2);add(f3);add(p3);
        p1.setLayout(new FlowLayout());
        p2.setLayout(new FlowLayout());
        p3.setLayout(new FlowLayout());
        p1.add(lb1);p1.add(f4);p1.add(b1_start);p1.add(b1_stop);p1.add(stg1);p1.add(f7);
        p2.add(lb2);p2.add(f5);p2.add(b2_start);p2.add(b2_stop);p2.add(stg2);p2.add(f8);
        p3.add(lb3);p3.add(f6);p3.add(b3_start);p3.add(b3_stop);p3.add(stg3);p3.add(f9);
        b1_start.addActionListener(new DealEvent());
        b1_stop.addActionListener(new DealEvent());
        b2_start.addActionListener(new DealEvent());
        b2_stop.addActionListener(new DealEvent());
        b3_start.addActionListener(new DealEvent());
        b3_stop.addActionListener(new DealEvent());
        this.pack();//调整窗体大小
        setVisible(true);
    }
    class thread1 extends Thread{
        public void run() {
            while(true) {
                try {
                    String str=f1.getText();
                    f1.setText(str.substring(1)+str.substring(0, 1));
                    sleep(time1);
                }
                catch(Exception e) {
                    break;
                }
            }
        }
    }
    class thread2 extends Thread{
        public void run() {
            while(true) {
                try {
                    String str=f2.getText();
                    f2.setText(str.substring(1)+str.substring(0, 1));
                    sleep(time2);
                }
                catch(Exception e) {
                    break;
                }
            }
        }
    }
    class thread3 extends Thread{
        public void run() {
            while(true) {
                try {
                    String str=f3.getText();
                    f3.setText(str.substring(1)+str.substring(0, 1));
                    sleep(time3);
                }
                catch(Exception e) {
                    break;
                }
            }
        }
    }
    class DealEvent implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==b1_start) {
                f7.setText("RUNNABLE");
                time1=Integer.parseInt(f4.getText());
                th1=new thread1();
                b1_start.setEnabled(false);
                b1_stop.setEnabled(true);
                th1.start();
            }
            else if(e.getSource()==b1_stop) {
                f7.setText("STOP");
                b1_start.setEnabled(true);
                b1_stop.setEnabled(false);
                th1.interrupt();
            }
            else if(e.getSource()==b2_start) {
                f8.setText("RUNNABLE");
                time2=Integer.parseInt(f5.getText());
                th2=new thread2();
                b2_start.setEnabled(false);
                b2_stop.setEnabled(true);
                th2.start();
            }
            else if(e.getSource()==b2_stop) {
                f8.setText("STOP");
                b2_start.setEnabled(true);
                b2_stop.setEnabled(false);
                th2.interrupt();
            }
            else if(e.getSource()==b3_start) {
                f9.setText("RUNNABLE");
                time3=Integer.parseInt(f6.getText());
                th3=new thread3();
                b3_start.setEnabled(false);
                b3_stop.setEnabled(true);
                th3.start();
            }
            else if(e.getSource()==b3_stop) {
                f9.setText("STOP");
                b3_start.setEnabled(true);
                b3_stop.setEnabled(false);
                th3.interrupt();
            }
        }

    }
}

