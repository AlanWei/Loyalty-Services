/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author alanwei
 */
public class Server {

    public static void main(String args[]) throws RemoteException {
        SocialServerImpl socialServerImpl = new SocialServerImpl();

        Registry registry = LocateRegistry.createRegistry(1099);
        
        registry.rebind("social", socialServerImpl);

        System.out.println("RMI Service Ready.");

    }

}
