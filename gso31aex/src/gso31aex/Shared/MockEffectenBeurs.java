/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gso31aex.Shared;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

/**
 *
 * @author Justin
 */
public class MockEffectenBeurs {

    String name1 = " Philips: ";
    String name2 = " Rabobank: ";
    String name3 = " LG Electronics: ";
    String name4 = " Sony: ";
    String name5 = " Asus: ";
    Random rand = new Random();
    int random1;
    int random2;
    int random3;
    int random4;
    int random5;
    public ArrayList<String> aexbeurs;


    public void aexNumber() {
        Timer ramdomTimer = new Timer(2000, (ActionEvent e) -> {
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

    public String createAexString(){
        
        String aexString = name1 + random1 + name2 + random2 + name3 + random3 + name4 + random4 + name5 + random5;
        return aexString;

    }

    public String toString() {
        return name1 + random1 + name2 + random2 + name3 + random3 + name4 + random4 + name5 + random5;
    }
}
