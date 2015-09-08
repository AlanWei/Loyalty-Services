/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import dao.CustomerDAO;
import domain.Coupon;
import domain.Customer;
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
@Path("customers/{customerID}/coupons")

public class CouponListResource {

    private CustomerDAO dao = new CustomerDAO();
    private String customerID;
    private Customer customer;

    public CouponListResource(@PathParam("customerID") String customerID) {
        if (this.dao.customerExists(customerID) == false) {
            NotFoundException ne = new NotFoundException("There is no customer that matches that ID");
            throw ne;
        } else {
            this.customerID = customerID;
            this.customer = this.dao.getCustomerByID(customerID);
        }
    }

    @GET
    public Collection<Coupon> getCoupons() {
        return this.dao.getCustomerCouponsByCustomerID(customerID);
    }

    @POST
    public Response addCoupon(Coupon c, @Context UriInfo uri) {
        URI newURI = uri.getAbsolutePathBuilder()
                .path(c.getId().toString())
                .build();

        this.dao.addCouponByCustomer(c, this.customer);
        
        
        Integer originalUnUsedPoint = this.customer.getUnUsedPoints();
        Integer points = c.getPoints();
        this.customer.setUnUsedPoints(originalUnUsedPoint - points);

        return Response.created(newURI).build();
    }
}
