package gso31aex;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/*
 * <applet code="TextBanner" width=600 height=50> </applet>
 */
public class BannerApplet extends JApplet {

    // Set colors and initialize thread.
    @Override
    public void init() {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            }
            
            setBackground(Color.BLACK);
            setForeground(Color.RED);
            setSize(400,100);
            setLayout(new BorderLayout());
            add(new TextPane());
        });
    }

    // Start thread
    public void start() {
    }

    // Pause the banner.
    public void stop() {
    }
    
    public class TextPane extends JPanel {
        Random rand = new Random();
        int currRand1;
        int currRand2;
        int currRand3;
        int aexPhilips = 5;
        int xPos = 420;
        String masterMsg ;
        String str1 = " Philips: ";
        String str2 = " Rabobank: ";
        String str3 = " NSA: ";
        
        int msgWidth;
        

        private int direction = -10;

        public TextPane() {
            setOpaque(false);
            setBackground(Color.BLACK);
            setForeground(Color.RED);

            setFont(new Font("Arial", Font.PLAIN, 30));


            Timer timer = new Timer(60, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    xPos += direction;
                    FontMetrics fm = getFontMetrics(getFont());

                    if ((xPos < -fm.stringWidth(str1+str2+str3)))
                    {
                        
                         xPos = 420; //waneer eind van string uit beeld gaat wordt deze direct weer getekend
                    }
                    
                    repaint();
                }
            });
            timer.setRepeats(true);
            timer.setCoalesce(true);
            timer.start();
            
            
            Timer timer2 = new Timer(600, new ActionListener() 
            {
            @Override
                public void actionPerformed(ActionEvent e) 
                {
                  currRand1 = rand.nextInt(99 -1);
                  currRand2 = rand.nextInt(99 -3);  
                  currRand3 = rand.nextInt(99 -4);  
                }
                
              
            });
            timer2.setRepeats(true);
            timer2.setCoalesce(true);
            timer2.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            System.out.println(xPos);
            FontMetrics fm = g.getFontMetrics();
            msgWidth = -fm.stringWidth(str1 + str2 + str3);
            String msg = str1 + currRand1 + str2 + currRand2 + str3 + currRand3;
            g.drawString(msg, xPos, 40);  // hier bouwen we de string

            
        }
    }
}