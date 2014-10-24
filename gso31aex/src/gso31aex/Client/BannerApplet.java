package gso31aex.Client;

import gso31aex.Shared.IEffectenbeurs;
import gso31aex.Shared.MockEffectenBeurs;
import java.awt.*;
import java.awt.event.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/*
 * <applet code="TextBanner" width=600 height=50> </applet>
 */
public class BannerApplet extends JApplet {

    String ipAddress = "127.0.0.1";
    int portNumber = 1098;

    private static final String bindingName = "AEX";

    // References to registry and student administration
    private Registry registry = null;
    private IEffectenbeurs ieffect = null;
    //private MockEffectenBeurs mock;

    @Override
    public void init() {

        try {
            registry = LocateRegistry.getRegistry(ipAddress, portNumber);
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: RemoteException: " + ex.getMessage());
            registry = null;
        }
        if (registry != null) {
            System.out.println("Client: Registry located");
        } else {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: Registry is null pointer");
        }

        if (registry != null) {

                try {
                    ieffect = (IEffectenbeurs) registry.lookup(bindingName);
                    System.out.println("Client: Still connected");
                } catch (RemoteException ex) {
                    System.out.println("Client: Cannot bind Effectenbeurs");
                    System.out.println("Client: RemoteException: " + ex.getMessage());
                    ieffect = null;
                } catch (NotBoundException ex) {
                    System.out.println("Client: Cannot bind Effectenbeur");
                    System.out.println("Client: NotBoundException: " + ex.getMessage());
                    ieffect = null;
                }


            if (ieffect != null) {
                System.out.println("Client: Effectenbeur bound");
            } else {
                System.out.println("Client: Effectenbeur is null pointer");
            }

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

                repaint();
                // Print contents of registry
            });
                    }
    }

    public class TextPane extends JPanel {

        int xPos = 420;
        int nr = 1;

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

                try {
                    if ((xPos < -fm.stringWidth(ieffect.getAex()))) {
                        xPos = 420; //waneer eind van string uit beeld gaat wordt deze direct weer getekend

                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(BannerApplet.class.getName()).log(Level.SEVERE, null, ex);
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
                g.drawString(ieffect.getAex(), xPos, 40);  // hier bouwen we de string
            } catch (RemoteException ex) {
                Logger.getLogger(BannerApplet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
