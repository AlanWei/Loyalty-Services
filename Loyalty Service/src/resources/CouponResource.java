/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import dao.CustomerDAO;
import domain.Coupon;
import domain.Customer;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author alanwei
 */
@Path("customers/{customerID}/coupons/{couponID}")

public class CouponResource {

    private CustomerDAO dao = new CustomerDAO();
    private String customerID;
    private Customer customer;
    private Coupon coupon;

    public CouponResource(@PathParam("customerID") String customerID, @PathParam("couponID") String couponID) {
        if (this.dao.customerExists(customerID) == false) {
            NotFoundException ne = new NotFoundException("There is no customer that matches that ID");
            throw ne;
        } else {
            this.customerID = customerID;
            this.customer = this.dao.getCustomerByID(this.customerID);
            this.coupon = this.dao.getCouponByCustomerAndID(couponID, this.customer);
        }
    }

    @GET
    public Coupon getCoupon() {
        if ("-1".equals(this.coupon.getId())) {
            NotFoundException ne = new NotFoundException("There is no couple that matches that ID");
            throw ne;
        } else {
            return this.coupon;
        }
    }
    
    @PUT
    public Response useCouple(){
        if (this.coupon.getUsed() == false){
            this.coupon.setUsed(true);
            return Response.noContent().build();
        }
        else {
            return Response.status(Response.Status.CONFLICT)
                    .entity("This coupon has been used")
                    .build();
        }
    }
}