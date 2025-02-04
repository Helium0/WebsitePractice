package org.project.helper;


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



    public Customer(CustomerBuilder builder) {
        this.customerName = builder.customerName;
        this.customerLastName = builder.customerLastName;
        this.customerEmail = builder.customerEmail;
        this.customerPassword = builder.customerPassword;
        this.customerBirthday = builder.customerBirthday;
        this.customerAddress = builder.customerAddress;
        this.customerCity = builder.customerCity;
        this.customerPostalCode = builder.customerPostalCode;
        this.customerMobilePhone = builder.customerMobilePhone;
    }


    public static class CustomerBuilder {
        private String customerName;
        private String customerLastName;
        private String customerEmail;
        private String customerPassword;
        private String[] customerBirthday;
        private String customerAddress;
        private String customerCity;
        private String customerPostalCode;
        private String customerMobilePhone;


        public CustomerBuilder setCustomerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public String getCustomerName() {
            return customerName;
        }

        public CustomerBuilder setCustomerLastName(String customerLastName) {
            this.customerLastName = customerLastName;
            return this;
        }

        public String getCustomerLastName() {
            return customerLastName;
        }

        public CustomerBuilder setCustomerEmail(String customerEmail) {
            this.customerEmail = customerEmail;
            return this;
        }

        public String getCustomerEmail() {
            return customerEmail;
        }

        public CustomerBuilder setCustomerPassword(String customerPassword) {
            this.customerPassword = customerPassword;
            return this;
        }

        public String getCustomerPassword() {
            return customerPassword;
        }

        public CustomerBuilder setCustomerBirthday(String[] customerBirthday) {
            this.customerBirthday = customerBirthday;
            return this;
        }

        public String[] getCustomerBirthday() {
            if(customerBirthday[2].charAt(0)=='0'){
                customerBirthday[2] = customerBirthday[2].substring(1);
            }
            return customerBirthday;
        }

        public CustomerBuilder setCustomerAddress(String customerAddress) {
            this.customerAddress = customerAddress;
            return this;
        }

        public String getCustomerAddress() {
            return customerAddress;
        }

        public CustomerBuilder setCustomerCity(String customerCity) {
            this.customerCity = customerCity;
            return this;
        }

        public String getCustomerCity() {
            return customerCity;
        }

        public CustomerBuilder setCustomerPostalCode(String customerPostalCode) {
            this.customerPostalCode = customerPostalCode;
            return this;
        }

        public String getCustomerPostalCode() {
            return customerPostalCode;
        }

        public CustomerBuilder setCustomerMobilePhone(String customerMobilePhone) {
            this.customerMobilePhone = customerMobilePhone;
            return this;
        }

        public String getCustomerMobilePhone() {
            return customerMobilePhone;
        }

        public Customer build() {
            return new Customer(this);
        }

    }

}
