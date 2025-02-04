package org.project.helper;

import com.github.javafaker.Faker;

public class CustomerHelper {

    private Faker fakerMethod() {
        return new Faker();
    }

    private String date() {
        return fakerMethod().date().birthday(18,90).toString();
    }

    private String customerZipCode() {
        String zip = fakerMethod().address().zipCode();
        if(fakerMethod().address().zipCode().length() >= 5) {
            zip = fakerMethod().address().zipCode().substring(0,5);
        }
        return  zip;
    }

    public Customer.CustomerBuilder customerObject() {
        Customer.CustomerBuilder customerBuilder = new Customer.CustomerBuilder()
                .setCustomerName(fakerMethod().name().firstName());
        customerBuilder.setCustomerLastName(fakerMethod().name().lastName());
        customerBuilder.setCustomerEmail(fakerMethod().internet().emailAddress());
        customerBuilder.setCustomerPassword(fakerMethod().internet().password());
        customerBuilder.setCustomerBirthday(date().split(" "));
        customerBuilder.setCustomerAddress(fakerMethod().address().streetAddress());
        customerBuilder.setCustomerCity(fakerMethod().address().cityName());
        customerBuilder.setCustomerPostalCode(customerZipCode());
        customerBuilder.setCustomerMobilePhone(fakerMethod().phoneNumber().cellPhone());
        return customerBuilder;
    }
}
