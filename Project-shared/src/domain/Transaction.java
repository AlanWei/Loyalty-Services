/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alanwei
 */
@XmlRootElement
public class Transaction implements Serializable {
    
    @SerializedName("id")
    private String id;
    @SerializedName("shop")
    private String shop = "";
    @SerializedName("points")
    private Double points;
    @SerializedName("customer_id")
    private String customerID;

    public Transaction() {

    }
    
    public Transaction(String id, Double points, String customerID){
        this.id = id;
        this.points = points;
        this.customerID = customerID;
    }

    public Transaction(String id, String shop, Double points) {
        this.id = id;
        this.shop = shop;
        this.points = points;
    }
    
    public Transaction createTransaction(String id, Double points, String customerID){
        Transaction t = new Transaction(id, points, customerID);
        return t;
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
     * @return the shop
     */
    public String getShop() {
        return shop;
    }

    /**
     * @param shop the shop to set
     */
    public void setShop(String shop) {
        this.shop = shop;
    }

    /**
     * @return the points
     */
    public Double getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(Double points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Transaction{" + "id=" + id + ", shop=" + shop + ", points=" + points + '}';
    }
}
