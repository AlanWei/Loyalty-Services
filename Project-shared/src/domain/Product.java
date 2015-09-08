/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 *
 * @author alanwei
 */
public class Product implements Serializable{
    
    @SerializedName("source_id")
    private String sourceID;
    private String handle;
    private String type;
    private String tags;
    private String name;
    private String description;
    private String sku;
    @SerializedName("retail_price")
    private String retailPrice;

    
    public Product(String sourceID, String handle, String type, String tags, String name, String description, String sku,
            String retailPrice){
        this.sourceID = sourceID;
        this.handle = handle;
        this.type = type;
        this.tags = tags;
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.retailPrice = retailPrice;
    }
    
    /**
     * @return the sourceID
     */
    public String getSourceID() {
        return sourceID;
    }

    /**
     * @param sourceID the sourceID to set
     */
    public void setSourceID(String sourceID) {
        this.sourceID = sourceID;
    }

    /**
     * @return the handle
     */
    public String getHandle() {
        return handle;
    }

    /**
     * @param handle the handle to set
     */
    public void setHandle(String handle) {
        this.handle = handle;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the tags
     */
    public String getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the sku
     */
    public String getSku() {
        return sku;
    }

    /**
     * @param sku the sku to set
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * @return the retailPrice
     */
    public String getRetailPrice() {
        return retailPrice;
    }

    /**
     * @param retailPrice the retailPrice to set
     */
    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }
    
    
    @Override
    public String toString() {
        return "Product{" + "sourceID=" + sourceID + ", handle=" + handle + ", type=" + type + ", tags=" + tags
                + ", name" + name + ", description" + description + ", sku" + sku + "retailPrice" + retailPrice + '}';
    }
}
