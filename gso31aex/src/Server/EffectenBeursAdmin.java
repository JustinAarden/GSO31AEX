/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Shared.IEffectenbeurs;
import Shared.MockEffectenBeurs;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.Timer;

/**
 *
 * @author Justin
 */
public class EffectenBeursAdmin extends UnicastRemoteObject implements IEffectenbeurs {

    MockEffectenBeurs mock1;
    public String aexKoersen;


    public EffectenBeursAdmin() throws RemoteException {

        MockEffectenBeurs mock = new MockEffectenBeurs();
        mock.aexNumber();
        Timer timer = new Timer(1000, (ActionEvent e) -> { //om de seconde update hij de koersen
            aexKoersen = mock.createAexString();

        });

        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
    }

    @Override
    public String getAex() throws RemoteException {

        return aexKoersen;
    }

}
