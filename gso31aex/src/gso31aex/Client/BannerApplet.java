package gso31aex.Client;

import gso31aex.Standalone.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/*
 * <applet code="TextBanner" width=600 height=50> </applet>
 */
public class BannerApplet extends JApplet {
    MockEffectenBeursStandAlone mock = new MockEffectenBeursStandAlone();
   
    // Set colors and initialize thread.
    @Override
    public void init() {
        mock.timerinterval = 1;
        mock.aexNumber();
        

        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            }

            setBackground(Color.BLACK);
            setForeground(Color.RED);
            setSize(400, 100);
            setLayout(new BorderLayout());
            add(new TextPane());

        });
    }

    public class TextPane extends JPanel {

        
        int xPos = 420;

        int msgWidth;

        private int direction = -1;

        public TextPane() {
            setOpaque(false);
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
