/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Shared.IEffectenbeurs;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Justin
 */
public class BannerController {

    public String aexstring;
    public Registry registry = null;
    public String bindingName = "AEX";
    public int portNumber = 1098;
    public String ipAddress = "127.0.0.1";
    public IEffectenbeurs ieffect = null;

    public String aexString() throws RemoteException 
    {   connect();
        return ieffect.getAex();
    }

    public void connect() throws RemoteException {

        try {
            registry = LocateRegistry.getRegistry(ipAddress, portNumber);
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: RemoteException: " + ex.getMessage());
            registry = null;
        }
        if (registry != null) {
        } else {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: Registry is null pointer");
        }

        if (registry != null) {

            try {
                ieffect = (IEffectenbeurs) registry.lookup(bindingName);
            } catch (RemoteException | NotBoundException ex) {
                ieffect = null;
            }

            if (ieffect != null) {
            } else {
                System.out.println("Client: Effectenbeur is null pointer");
            }

        }
    }

}
