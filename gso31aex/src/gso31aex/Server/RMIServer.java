/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gso31aex.Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Nico Kuijpers
 */
public class RMIServer {

    // Set flag createRegistry when binding using registry
    // Reset flag createRegistry when binding using Naming
    // Set port number
    private static int portNumber = 1098;

    // Set binding name for student administration
    private static String bindingName = "AEX";

    // References to registry and student administration
    private Registry registry = null;
    private EffectenBeursAdmin ebAdmin;

    // Constructor
    // Constructor
    public RMIServer() {

//        ebAdmin.generateNumbers();
        // Print port number for registry
        System.out.println("Server: Port number " + portNumber);

        try {

            ebAdmin = new EffectenBeursAdmin();
        } catch (RemoteException ex) {
            ebAdmin = null;
        }

        // Create registry at port number
        try {
            registry = LocateRegistry.createRegistry(portNumber);
            System.out.println("Server: Registry created on port number " + portNumber);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot create registry");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            registry = null;
        }

        try {
            registry.rebind(bindingName, ebAdmin);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot bind Effectenbeurs");
            System.out.println("Server: RemoteException: " + ex.getMessage());
        }

    }

    public static void main(String[] args) {

        RMIServer server = new RMIServer();

    }

}
