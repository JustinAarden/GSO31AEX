/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gso31aex.Server;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.net.MalformedURLException;
import java.rmi.Naming;
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
    private static boolean createRegistry = true;
    
    // Set port number
    private static int portNumber = 1099;
    
    // Set binding name for student administration
    private static String bindingName = "AEX";
    
    // References to registry and student administration
    private Registry     registry = null;
    private MockEffectenBeurs mock = null;
    
    // Constructor
    public RMIServer() {
        
        // Print port number for registry
        System.out.println("Server: Port number " + portNumber);
        
        mock = new MockEffectenBeurs();
        System.out.println("Server: Student administration created");
        
        // Bind student administration
        if (createRegistry) {
            // Create registry at port number
            registry = createRegistry();
        
            // Bind student administration using registry
            if (registry != null && mock != null) {
                
                System.out.println("Server: Student administration bound to " + bindingName);
            }
            else {
                System.out.println("Server: Student administration not bound");
            }
        }
        else {
            // Bind student adiministration using Naming
            if (mock != null) {

                System.out.println("Server: Student administration bound to " + bindingName);
            }
            else {
                System.out.println("Server: Student administration not bound");
            }
        }
    }
  
    // Create registry
    private Registry createRegistry() {
        
        // Create registry at port number
        Registry registry = null;
        try {
            registry = LocateRegistry.createRegistry(portNumber);
            System.out.println("Server: Registry created on port number " + portNumber);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot create registry");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            registry = null;
        }
        return registry;
    }
    

        
    // Bind student administration using Naming
    
    // Print IP addresses and network interfaces
    private static void printIPAddresses() {
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            System.out.println("Server: IP Address: " + localhost.getHostAddress());
            // Just in case this host has multiple IP addresses....
            InetAddress[] allMyIps = InetAddress.getAllByName(localhost.getCanonicalHostName());
            if (allMyIps != null && allMyIps.length > 1) {
                System.out.println("Server: Full list of IP addresses:");
                for (int i = 0; i < allMyIps.length; i++) {
                    System.out.println("    " + allMyIps[i]);
                }
            }
        } catch (UnknownHostException ex) {
            System.out.println("Server: Cannot get IP address of local host");
            System.out.println("Server: UnknownHostException: " + ex.getMessage());
        }

        try {
            System.out.println("Server: Full list of network interfaces:");
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                System.out.println("    " + intf.getName() + " " + intf.getDisplayName());
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    System.out.println("        " + enumIpAddr.nextElement().toString());
                }
            }
        } catch (SocketException ex) {
            System.out.println("Server: Cannot retrieve network interface list");
            System.out.println("Server: UnknownHostException: " + ex.getMessage());
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Welcome message
        if (createRegistry) {
            System.out.println("SERVER USING CREATE REGISTRY");
        }
        else {
            System.out.println("SERVER USING NAMING");
        }
        
        // Print IP addresses and network interfaces
        printIPAddresses();
        
        // Create server
        RMIServer server = new RMIServer();
    }
}
