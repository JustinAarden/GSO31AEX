package gso31aex.Client;

import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/*
 * <applet code="TextBanner" width=600 height=50> </applet>
 */
public class AexBanner extends JFrame {

    //private MockEffectenBeurs mock;
    public BannerController bc = new BannerController();

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new AexBanner().setVisible(true);
        });
    }
    
    

    public AexBanner() {

        setBackground(Color.BLACK);
        setResizable(false);
        setSize(400, 100);
        setLayout(new BorderLayout());
        add(new TextPane());

        repaint();

    }


    /*
    
     START TEKENEN VAN APPLET en PANEL!!!
    
    
     */
    public class TextPane extends JPanel {

        int xPos = 420;
        int nr = 1;

        int msgWidth;

        private int direction = -1;

        public TextPane() {
            setOpaque(true);
            setBackground(Color.BLACK);
            setForeground(Color.RED);

            setFont(new Font("Arial", Font.PLAIN, 30));

            Timer timer;
            timer = new Timer(6, (ActionEvent e) -> {
                xPos += direction;
                FontMetrics fm = getFontMetrics(getFont());
                try {
                    if ((xPos < -fm.stringWidth(bc.aexString()))) {
                        xPos = 420; //waneer eind van string uit beeld gaat wordt deze direct weer getekend

                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(AexBanner.class.getName()).log(Level.SEVERE, null, ex);
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
            try {
                g.drawString(bc.aexString(), xPos, 40);
            } catch (RemoteException ex) {
                Logger.getLogger(AexBanner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
