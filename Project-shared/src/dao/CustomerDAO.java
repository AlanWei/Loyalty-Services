/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Coupon;
import domain.Customer;
import domain.Transaction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author alanwei
 */
public class CustomerDAO {

    private static Map<String, Customer> customers = null;

    public CustomerDAO() {
        if (customers == null) {
            customers = new HashMap<>();
            customers.put("47fe23b6-2876-11e4-f7b9-f6ef126d13ce", new Customer("47fe23b6-2876-11e4-f7b9-f6ef126d13ce", "M", "1993-03-07"));
        }
    }

    // get
    
    public Collection<Customer> getCustomers() {
        return customers.values();
    }

    public Customer getCustomerByID(String id) {
        return customers.get(id);
    }

    public Collection<Coupon> getCustomerCouponsByCustomerID(String id) {
        return customers.get(id).getCoupons();
    }
    
    public Collection<Transaction> getCustomerTransactionsByCustomerID(String id){
        return customers.get(id).getTransactions();
    }
    
    public Coupon getCouponByCustomerAndID(String id, Customer customer){
        for (Coupon c: customer.getCoupons()){
            if (c.getId() == null ? id == null : c.getId().equals(id)){
                return c;
            }
        }
        
        Coupon notFound = new Coupon("-1", 0);
        return notFound;
    }
    
    public Transaction getTransactionByCustomerAndID(String id, Customer customer){
        for (Transaction t: customer.getTransactions()){
            if (t.getId() == null ? id == null : t.getId().equals(id)){
                return t;
            }
        }
        
        Transaction notFound = new Transaction("-1", "", 0.0);
        return notFound;
    }
    
    // add

    public void addCustomer(Customer c) {
        customers.put(c.getId(), c);
    }
    
    public void addCouponByCustomer(Coupon coupon, Customer customer){
        customer.addCoupon(coupon);
    }
    
    public void addTransactionByCustomer(Transaction transaction, Customer customer){
        customer.addTransaction(transaction);
    }
    
    // validation

    public Boolean customerExists(String id) {
        return customers.containsKey(id);
    }
}
