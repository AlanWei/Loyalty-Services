
import java.util.ArrayList;
import java.util.List;
import sale.Customer;
import sale.Sale;
import sale.SaleItem;
import server.ISaleService;
import server.SaleService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alanwei
 */
public class RPCClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        SaleService saleService = new SaleService();

        ISaleService iSaleService = saleService.getSalePort();
        
        // test sale
        Sale sale = new Sale();
        Customer customer = new Customer();
        SaleItem saleItem = new SaleItem();
        
        customer.setDateOfBirth("1993-03-07");
        customer.setGender("M");
        
        saleItem.setPrice(10.0);
        saleItem.setQuantity(20.0);
        saleItem.setProductID("1");
        
        sale.setCustomer(customer);
        sale.setSaleItem(saleItem);
        sale.setDate("2015-05-03");
        
        iSaleService.newSale(sale);
        
        List<Sale> ls = new ArrayList<>();
        ls = iSaleService.getSales();
        
        System.out.print(ls);
        System.out.print(ls.get(0).getCustomer().getId());
    }

}
