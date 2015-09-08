/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alanwei
 */
public class Sale implements Serializable {

    @SerializedName("sale_date")
    private String date;
    @SerializedName("customer")
    private Customer customer;
    @SerializedName("register_sale_products")
    private List<SaleItem> saleItems;

    public Sale() {

    }

    public Sale(String date, List<SaleItem> saleItems, Customer customer) {
        this.date = date;
        this.saleItems = saleItems;
        this.customer = customer;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Sale{" + "date=" + date + ", customer=" + customer + ", saleItems=" + saleItems + '}';
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the saleItems
     */
    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    /**
     * @param saleItems the saleItems to set
     */
    public void setSaleItems(List<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }
}
