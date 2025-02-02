package org.project.helper;

import com.github.javafaker.Faker;

public class Customer {

    private String customerName;
    private String customerLastName;
    private String customerEmail;
    private String customerPassword;
    private String[] customerBirthday;
    private String customerAddress;
    private String customerCity;
    private String customerPostalCode;
    private String customerMobilePhone;



    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
    public String getCustomerAddress() {
        return customerAddress;
    }
    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }
    public String getCustomerCity() {
        return customerCity;
    }
    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }
    public String getCustomerPostalCode() {
        return customerPostalCode;
    }
    public void setCustomerMobilePhone(String customerMobilePhone) {
        this.customerMobilePhone = customerMobilePhone;
    }
    public String getCustomerMobilePhone() {
        return customerMobilePhone;
    }


    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }
    public void setCustomerBirthday(String[] customerBirthday) {
        this.customerBirthday = customerBirthday;
    }
    public String[] getCustomerBirthday() {
        if(customerBirthday[2].charAt(0)=='0'){
            customerBirthday[2] = customerBirthday[2].substring(1);
        }
        return customerBirthday;
    }
    private String date() {
        return new Faker().date().birthday(18,90).toString();
    }

    public String [] tab() {
        return date().split(" ");
    }

}
