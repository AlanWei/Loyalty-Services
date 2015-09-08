/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import domain.Sale;
import domain.SaleItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 *
 * @author alanwei
 */
public class SaleDAO {
    
    private List<Sale> sales = new ArrayList<>();
    //private List<SaleItem> saleItems = new ArrayList<>();
    
    public void newSale(Sale sale){
        this.sales.add(sale);
    }
    
    public List<Sale> getSales(){
        return this.sales;
    }
}
