/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sale;

import domain.Sale;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author alanwei
 */
@WebService
public interface ISaleService extends Remote {

    void newSale(@WebParam(name = "sale") Sale sale) throws RemoteException;

    List<Sale> getSales() throws RemoteException;

}
