/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gso31aex.Shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Justin
 */
public interface IEffectenbeurs extends Remote {
    //public String getAex() throws RemoteException;
    public String getAex() throws RemoteException;
    public void generate() throws RemoteException;
    
}
