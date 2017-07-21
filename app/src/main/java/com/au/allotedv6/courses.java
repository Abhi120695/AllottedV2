package com.au.allotedv6;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Abhishek-Pc on 11-03-2016.
 */
public class courses {
    @SerializedName("slot")
    @Expose
    private String slot;

    public String getSlots() {
        return slot;
    }

    public void setSlots(String slots) {
        this.slot = slot;
    }
public courses(String slot){
    this.slot = slot;

}

    @Override
    public String toString() {
        return slot;
    }
}
