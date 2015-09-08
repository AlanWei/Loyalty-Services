/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package social;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author alanwei
 */
public interface ISocialService extends Remote{
    
    void postNotification(String message) throws RemoteException;
}
