/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gso31aex.Server;

import gso31aex.Shared.IEffectenbeurs;
import gso31aex.Shared.MockEffectenBeurs;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author Justin
 */
public class EffectenBeursAdmin extends UnicastRemoteObject implements IEffectenbeurs {

    MockEffectenBeurs mock1;
    public String aexKoersen;
    public String aexKoersen1;
    //String test = mock.createAexString();

    public EffectenBeursAdmin() throws RemoteException {

        MockEffectenBeurs mock = new MockEffectenBeurs();
        mock.aexNumber();
        Timer timer = new Timer(1, (ActionEvent e) -> {
            aexKoersen = mock.createAexString();

        });

        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
    }

    @Override
    public void generate() {

        mock1.aexNumber();
    }

    public void refresher() {

    }

    @Override
    public String getAex() throws RemoteException {

        return aexKoersen;
    }

}
