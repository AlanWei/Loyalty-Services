/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import social.ISocialService;

/**
 *
 * @author alanwei
 */
public class SocialServerImpl extends UnicastRemoteObject implements ISocialService{
    
    public SocialServerImpl() throws RemoteException{
        
    }
    
    @Override
    public void postNotification(String message){
        System.out.println(message);
    }
    
}
