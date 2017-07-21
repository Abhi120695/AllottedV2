package com.au.allotedv6;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhishek-Pc on 11-03-2016.
 */
public class Donloadeddata {
    @SerializedName("campus")
    @Expose
    private String campus;

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    @SerializedName("courses")
    @Expose
    private List<courses> courses;
    @SerializedName("reg_no")
    @Expose
    private String registerNumber;
    @SerializedName("dob")
    @Expose
    private String dateOfBirth;
    @SerializedName("mobile")
    @Expose
    private String mobileNumber;

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public List<com.au.allotedv6.courses> getCourses() {
        if (this.courses != null) {
            return this.courses;
        }
        return new ArrayList();
    }

    public void setCourses(List<com.au.allotedv6.courses> courses) {

        this.courses = courses;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }
    public Donloadeddata(String registerNumber, String dateOfBirth, String mobileNumber, String campus,List<courses> courses){
        this.registerNumber = registerNumber;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
        this.campus = campus;
        this.courses = courses;
    }

    @Override
    public String toString() {
        return courses.toString().replaceAll("[\\[\\]\\s]","");
    }
}
