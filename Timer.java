package ADDbyGB;

import javax.swing.*;
import java.awt.HeadlessException;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 计时器
 */
public class Timer extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final String INITIAL_LABEL_TEXT = "00:00:00 000";

    // 计数线程
    private CountingThread thread = new CountingThread();

    // 记录程序开始时间
    private long programStart = System.currentTimeMillis();

    // 程序一开始就是暂停的
    private long pauseStart = programStart;

    // 程序暂停的总时间
    private long pauseCount = 0;

    private JLabel label = new JLabel(INITIAL_LABEL_TEXT);

    private JButton startPauseButton = new JButton("开始");

    private JButton resetButton = new JButton("清零");

    private ActionListener startPauseButtonListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (thread.stopped) {
                pauseCount += (System.currentTimeMillis() - pauseStart);
                thread.stopped = false;
                startPauseButton.setText("暂停");
            } else {
                pauseStart = System.currentTimeMillis();
                thread.stopped = true;
                startPauseButton.setText("继续");
            }
        }
    };

    private ActionListener resetButtonListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            pauseStart = programStart;
            pauseCount = 0;
            thread.stopped = true;
            label.setText(INITIAL_LABEL_TEXT);
            startPauseButton.setText("开始");
        }
    };

    public Timer(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(300, 600);
        setResizable(false);

        setupBorder();
        setupLabel();
        setupButtonsPanel();

        startPauseButton.addActionListener(startPauseButtonListener);
        resetButton.addActionListener(resetButtonListener);

        thread.start(); // 计数线程一直就运行着
    }

    // 为窗体面板添加边框
    public void setupBorder() {
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.setContentPane(contentPane);
    }

    // 配置按钮
    private void setupButtonsPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(startPauseButton);
        panel.add(resetButton);
        add(panel, BorderLayout.SOUTH);
    }

    // 配置标签
    private void setupLabel() {
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 40));
        this.add(label, BorderLayout.CENTER);
    }

    // 程序入口
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Timer frame = new Timer("计时器");
        frame.pack();
        frame.setVisible(true);
    }

    private class CountingThread extends Thread {

        public boolean stopped = true;

        private CountingThread() {
            setDaemon(true);
        }

        @Override
        public void run() {
            while (true) {
                if (!stopped) {
                    long elapsed = System.currentTimeMillis() - programStart - pauseCount;
                    label.setText(format(elapsed));
                }

                try {
                    sleep(1);  // 1毫秒更新一次显示
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }

        // 将毫秒数格式化
        private String format(long elapsed) {
            int hour, minute, second, milli;

            milli = (int) (elapsed % 1000);
            elapsed = elapsed / 1000;

            second = (int) (elapsed % 60);
            elapsed = elapsed / 60;

            minute = (int) (elapsed % 60);
            elapsed = elapsed / 60;

            hour = (int) (elapsed % 60);

            return String.format("%02d:%02d:%02d %03d", hour, minute, second, milli);
        }
    }
}