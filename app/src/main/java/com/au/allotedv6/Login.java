package com.au.allotedv6;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Abhishek-Pc on 11-03-2016.
 */
public class Login {
    @SerializedName("campus")
    @Expose
    private String campus;
    @SerializedName("dob")
    @Expose
    private String dateOfBirth;
    @SerializedName("mobile")
    @Expose
    private String mobileNumber;
    @SerializedName("name")

    private String name1;
    @SerializedName("reg_no")
    @Expose
    private String registerNumber;
    @SerializedName("status")
    @Expose
    private status mstatus;

    public Login(String registerNumber, String dateOfBirth, String mobileNumber, String campus, String name, status cstatus) {
        this.registerNumber = registerNumber;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
        this.campus = campus;
        this.name1 = name;
        this.mstatus = cstatus;
    }

    public String getRegisterNumber() {
        return this.registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCampus() {
        return this.campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getName() {
        return this.name1;
    }

    public void setName(String name) {
        this.name1 = name;
    }

    @Override
    public String toString() {

        return name1;
    }
}

