/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import dao.CustomerDAO;
import domain.Customer;
import domain.Transaction;
import java.net.URI;
import java.util.Collection;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author alanwei
 */
@Path("customers/{customerID}/transactions")

public class TransactionListResource {

    private CustomerDAO dao = new CustomerDAO();
    private String customerID;
    private Customer customer;

    public TransactionListResource(@PathParam("customerID") String customerID) {
        if (this.dao.customerExists(customerID) == false) {
            NotFoundException ne = new NotFoundException("There is no customer that matches that ID");
            throw ne;
        } else {
            this.customerID = customerID;
            this.customer = this.dao.getCustomerByID(customerID);
        }
    }

    @GET
    public Collection<Transaction> getTransactions() {
        return this.dao.getCustomerTransactionsByCustomerID(customerID);
    }
    
    @POST
    public Response addTransaction(Transaction t, @Context UriInfo uri){
        URI newURI = uri.getAbsolutePathBuilder()
                .path(t.getId())
                .build();
        
        this.dao.addTransactionByCustomer(t, this.customer);
        
        Integer originalPoint = this.customer.getPoints();
        Integer originalUnUsedPoint = this.customer.getUnUsedPoints();
        
        Integer points = t.getPoints().intValue();
        
        this.customer.setPoints(originalPoint + points);
        this.customer.setUnUsedPoints(originalUnUsedPoint + points);
        
        return Response.created(newURI).build();
    }
}
