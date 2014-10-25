/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Justin
 */
public interface IEffectenbeurs extends Remote {
    public String getAex() throws RemoteException;
}
