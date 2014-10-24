/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gso31aex.Standalone;

import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.Timer;

/**
 *
 * @author Justin
 */
public class MockEffectenBeursStandAlone {

    String name1 = "\t   Philips: ";
    String name2 = "\t   Rabobank: ";
    String name3 = "\t   LG Electronics: ";
    String name4 = "\t   Sony: ";
    String name5 = "\t   Asus: ";
    Random rand = new Random();
    int random1;
    int random2;
    int random3;
    int random4;
    int random5;
    public int timerinterval; //eerste keer direct genereren zonder pause anders staat er eerste secondes 0!!    

    public void aexNumber() {
        Timer ramdomTimer = new Timer(timerinterval, (ActionEvent e) -> {
            random1 = rand.nextInt(99 - 1);
            random2 = rand.nextInt(99 - 2);
            random3 = rand.nextInt(99 - 6);
            random4 = rand.nextInt(99 - 8);
            random5 = rand.nextInt(99 - 4);

        });

        ramdomTimer.setRepeats(true);
        ramdomTimer.setCoalesce(true);
        ramdomTimer.start();

    }

    public String aex() {
        String aexString = name1 + random1 + name2 + random2 + name3 + random3 + name4 + random4 + name5 + random5;
        return aexString;
    }

}
