package Controller;
import javax.swing.*;
import java.awt.*;

public class Timer {
    private static int seconds;
    private JLabel label = new JLabel();
    private static int minutesPart;
    private static int secondsPart;
    private String time;
    private boolean running = true;
    private Thread thread;

    public Timer() {
        this.seconds = 0;
        Font poppinsFontBold = new Font("Poppins", Font.BOLD, 18);
        this.label.setFont(poppinsFontBold);
        this.label.setForeground(Color.decode("#4467df"));
        updateTime();
        this.label.setText(time);
    }

    private void updateTime() {
        this.minutesPart = seconds / 60;
        this.secondsPart = seconds % 60;
        this.time = String.format("%02d:%02d", minutesPart, secondsPart);
    }

    public void start() {
        if (thread == null || !thread.isAlive()) {
            thread = new Thread(this::run);
            thread.start();
        }
    }
    
    public void reset() {
        this.seconds = 0;
        updateTime();
        SwingUtilities.invokeLater(() -> label.setText(time));
    }

    private void run() {
        while (running) {
            updateTime();
            SwingUtilities.invokeLater(() -> label.setText(time));
            seconds++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getMinutesPart() {
        return minutesPart;
    }
    public static int getSeconds() {
        return secondsPart;
    }

    public JLabel getLabel() {
        return label;
    }

    public void stopTimer() {
        this.running = false;
        if (thread != null) {
            thread.interrupt();
        }
    }
}