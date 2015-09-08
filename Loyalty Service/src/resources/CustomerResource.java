/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package resources;

import dao.CustomerDAO;
import domain.Customer;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author alanwei
 */
@Path("customers/{customerID}")

public class CustomerResource {
    
    private CustomerDAO dao = new CustomerDAO();
    private String customerID;
    private Customer customer;
    
    public CustomerResource(@PathParam("customerID") String customerID){
        if (this.dao.customerExists(customerID) == false){
            NotFoundException ne = new NotFoundException("There is no customer that matches that ID");
            throw ne;
        }
        else{
            this.customerID = customerID;
            this.customer = this.dao.getCustomerByID(this.customerID);
        }
    }
    
    @GET
    public Customer getCustomer(){
        return this.customer;
    }
}
