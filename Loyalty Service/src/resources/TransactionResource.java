/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import dao.CustomerDAO;
import domain.Customer;
import domain.Transaction;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author alanwei
 */
@Path("customers/{customerID}/transactions/{transactionID}")

public class TransactionResource {

    private CustomerDAO dao = new CustomerDAO();
    private String customerID;
    private Customer customer;
    private Transaction transaction;

    public TransactionResource(@PathParam("customerID") String customerID, @PathParam("transactionID") String transactionID) {
        if (this.dao.customerExists(customerID) == false) {
            NotFoundException ne = new NotFoundException("There is no customer that matches that ID");
            throw ne;
        } else {
            this.customerID = customerID;
            this.customer = this.dao.getCustomerByID(this.customerID);
            this.transaction = this.dao.getTransactionByCustomerAndID(transactionID, customer);
        }
    }

    @GET
    public Transaction getTransaction() {
        //System.out.print(this.transaction.getId());
        if ("-1".equals(this.transaction.getId())) {
            NotFoundException ne = new NotFoundException("There is no transaction that matches that ID");
            throw ne;
        } else {
            return this.transaction;
        }
    }
}
