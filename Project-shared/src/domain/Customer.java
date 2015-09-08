/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alanwei
 */
@XmlRootElement
public class Customer implements Serializable{

    @SerializedName("id")
    private String id;
    @SerializedName("sex")
    private String gender;
    @SerializedName("date_of_birth")
    private String dateOfBirth;
    @SerializedName("points")
    private Integer points;
    @SerializedName("un_used_points")
    private Integer unUsedPoints;
    @XmlElement(name = "coupon")
    private Collection<Coupon> coupons = new HashSet<>();
    @XmlElement(name = "transaction")
    private Collection<Transaction> transactions = new HashSet<>();

    public Customer(){
        
    }
    
    public Customer(String gender, String dateOfBirth){
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }
    
    public Customer(String id, String gender, String dateOfBirth) {
        this.id = id;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.points = 0;
        this.unUsedPoints = 0;
    }
    
    public void addCoupon(Coupon c){
        this.coupons.add(c);
    }
    
    public void addTransaction(Transaction t){
        this.transactions.add(t);
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the dateOfBirth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", coupons=" + coupons + '}';
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
     * @return the unUsedPoints
     */
    public Integer getUnUsedPoints() {
        return unUsedPoints;
    }

    /**
     * @param unUsedPoints the unUsedPoints to set
     */
    public void setUnUsedPoints(Integer unUsedPoints) {
        this.unUsedPoints = unUsedPoints;
    }

    /**
     * @return the coupons
     */
    public Collection<Coupon> getCoupons() {
        return coupons;
    }

    /**
     * @param coupons the coupons to set
     */
    public void setCouponList(Collection<Coupon> coupons) {
        this.coupons = coupons;
    }

    /**
     * @return the transactions
     */
    public Collection<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * @param transactions the transactions to set
     */
    public void setTransactionList(Collection<Transaction> transactions) {
        this.transactions = transactions;
    }

}
