/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import social.ISocialService;

/**
 *
 * @author alanwei
 */
public class RMIClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, NotBoundException {

        String remoteAddress = "127.0.0.1";
        Registry registry = LocateRegistry.getRegistry(remoteAddress);
        ISocialService server = (ISocialService) registry.lookup("social");
        registry = LocateRegistry.getRegistry(remoteAddress);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your notification: ");
            String message = scanner.nextLine();
            server.postNotification(message);
        }
    }
}