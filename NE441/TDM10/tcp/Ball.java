package tcp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

public class Ball extends JPanel implements Runnable {

    Color color;
    int diameter;
    long delay;
    private int vx;
    private int vy;

    public Ball(String ballcolor, int xvelocity, int yvelocity) {
        if (ballcolor == "red") {
            color = Color.red;
        } else if (ballcolor == "blue") {
            color = Color.blue;
        } else if (ballcolor == "black") {
            color = Color.black;
        } else if (ballcolor == "cyan") {
            color = Color.cyan;
        } else if (ballcolor == "darkGray") {
            color = Color.darkGray;
        } else if (ballcolor == "gray") {
            color = Color.gray;
        } else if (ballcolor == "green") {
            color = Color.green;
        } else if (ballcolor == "yellow") {
            color = Color.yellow;
        } else if (ballcolor == "lightGray") {
            color = Color.lightGray;
        } else if (ballcolor == "magenta") {
            color = Color.magenta;
        } else if (ballcolor == "orange") {
            color = Color.orange;
        } else if (ballcolor == "pink") {
            color = Color.pink;
        } else if (ballcolor == "white") {
            color = Color.white;
        }
        diameter = 30;
        delay = 100;

        vx = xvelocity;
        vy = yvelocity;

        new Thread(this).start();

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int x = getX();
        int y = getY();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(color);
        g.fillOval(0, 0, 30, 30); //adds color to circle
        g.setColor(Color.black);
        g2.drawOval(0, 0, 30, 30); //draws circle
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(30, 30);
    }

    public void run() {

        try {
            // Randamize the location...
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    int x = (int) (Math.round(Math.random() * getParent().getWidth()));
                    int y = (int) (Math.round(Math.random() * getParent().getHeight()));

                    setLocation(x, y);
                }
            });
        } catch (InterruptedException exp) {
            exp.printStackTrace();
        } catch (InvocationTargetException exp) {
            exp.printStackTrace();
        }

        while (isVisible()) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }

            try {
                SwingUtilities.invokeAndWait(new Runnable() {
                    @Override
                    public void run() {
                        move();
                        repaint();
                    }
                });
            } catch (InterruptedException exp) {
                exp.printStackTrace();
            } catch (InvocationTargetException exp) {
                exp.printStackTrace();
            }
        }
    }

    public void move() {

        int x = getX();
        int y = getY();

        if (x + vx < 0 || x + diameter + vx > getParent().getWidth()) {
            vx *= -1;
        }
        if (y + vy < 0 || y + diameter + vy > getParent().getHeight()) {
            vy *= -1;
        }
        x += vx;
        y += vy;

        // Update the size and location...
        setSize(getPreferredSize());
        setLocation(x, y);

    }
}