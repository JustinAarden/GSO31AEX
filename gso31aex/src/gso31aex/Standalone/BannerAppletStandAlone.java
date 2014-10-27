package gso31aex.Standalone;

import gso31aex.Standalone.BannerAppletStandAlone;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * <applet code="TextBanner" width=600 height=50> </applet>
 */
public class BannerAppletStandAlone extends JFrame {

    MockEffectenBeursStandAlone mock = new MockEffectenBeursStandAlone();

    // Set colors and initialize thread.

    
    

    public BannerAppletStandAlone() {
        mock.aexNumber();

        setBackground(Color.BLACK);
        setResizable(false);
        setSize(400, 100);
        setLayout(new BorderLayout());
        add(new TextPane());
        repaint();

    
    }
        public static void main(String[] argv) {
        java.awt.EventQueue.invokeLater(() -> {
            new BannerAppletStandAlone().setVisible(true);
        });
    }

    public class TextPane extends JPanel {

        int xPos = 420;

        int msgWidth;

        private int direction = -1;

        public TextPane() {
            setOpaque(true);
            setBackground(Color.BLACK);
            setForeground(Color.RED);

            setFont(new Font("Arial", Font.PLAIN, 30));

            Timer timer = new Timer(6, (ActionEvent e) -> {
                xPos += direction;
                FontMetrics fm = getFontMetrics(getFont());

                if ((xPos < -fm.stringWidth(mock.aex()))) {
                    xPos = 420; //waneer eind van string uit beeld gaat wordt deze direct weer getekend
                }

                repaint();
            });
            timer.setRepeats(true);
            timer.setCoalesce(true);
            timer.start();

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            System.out.println(xPos);
            g.drawString(mock.aex(), xPos, 40);  // hier bouwen we de string
        }
    }
}
