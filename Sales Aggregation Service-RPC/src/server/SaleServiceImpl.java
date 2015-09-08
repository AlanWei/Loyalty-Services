/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import dao.SaleDAO;
import domain.Customer;
import domain.Sale;
import domain.SaleItem;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.jws.WebService;
import sale.ISaleService;

/**
 *
 * @author alanwei
 */
@WebService(endpointInterface = "sale.ISaleService",
        serviceName = "SaleService",
        portName = "SalePort")

public class SaleServiceImpl implements ISaleService {

    private final SaleDAO saleDAO;

    public SaleServiceImpl() {
        this.saleDAO = new SaleDAO();
    }

    @Override
    public void newSale(Sale sale) throws RemoteException {
        System.out.println("\n ** addSale called on SOAP service **");
        System.out.println(sale);
        saleDAO.newSale(sale);
    }

    @Override
    public List<Sale> getSales() throws RemoteException {
        System.out.println("\n ** getSale called on SOAP service **");
        return saleDAO.getSales();
    }

}
