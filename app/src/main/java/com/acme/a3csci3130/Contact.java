package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

public class Contact implements Serializable {

    public String businessNum;
    public String name;
    public String primaryBusiness;
    public String address;
    public String prov;


    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    /**
     * Description: Creates business contact
     * @param businessNum Business ID number
     * @param name  Name of the business
     * @param primaryBusiness   Type of business
     * @param address   address of business
     * @param prov  province or territory
     */
    public Contact(String businessNum, String name, String primaryBusiness, String address, String prov){
        this.businessNum = businessNum;
        this.name = name;
        this.primaryBusiness = primaryBusiness;
        this.address = address;
        this.prov = prov;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("businessNum", businessNum);
        result.put("name", name);
        result.put("primaryBusiness", primaryBusiness);
        result.put("address", address);
        result.put("prov", prov);

        return result;
    }
}
