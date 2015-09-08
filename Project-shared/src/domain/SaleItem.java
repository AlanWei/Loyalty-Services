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
public class SaleItem implements Serializable{

    @SerializedName("product_id")
    private String productID;
    @SerializedName("quantity")
    private Double quantity;
    @SerializedName("price_total")
    private Double price;
    
    public SaleItem(){
        
    }

    public SaleItem(String productID, Double quantity, Double price) {
        this.productID = productID;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * @return the productID
     */
    public String getProductID() {
        return productID;
    }

    /**
     * @param productID the productID to set
     */
    public void setProductID(String productID) {
        this.productID = productID;
    }

    /**
     * @return the quantity
     */
    public Double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SaleItem{" + "productID=" + productID + ", quantity=" + quantity + ", price=" + price + '}';
    }

}
