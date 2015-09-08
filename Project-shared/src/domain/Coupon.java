/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alanwei
 */
@XmlRootElement
public class Coupon {

    private String id;
    private Integer points;
    private Boolean used = false;
    private String customerID = "";

    public Coupon() {

    }

    public Coupon(String id, Integer points) {
        this.id = id;
        this.points = points;
    }
    
    public Coupon(String id, Integer points, String customerID){
        this.id = id;
        this.points = points;
        this.customerID = customerID;
    }

    public Coupon createCoupon(String id, Integer points){
        Coupon c = new Coupon(id, points);
        return c;
    }
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the points
     */
    public Integer getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(Integer points) {
        this.points = points;
    }

    /**
     * @return the used
     */
    public Boolean getUsed() {
        return used;
    }

    /**
     * @param used the used to set
     */
    public void setUsed(Boolean used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return "Coupon{" + "id=" + id + ", points=" + points + ", used=" + used + ", customerId" + customerID + '}';
    }

    /**
     * @return the customerID
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID the customerID to set
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
}