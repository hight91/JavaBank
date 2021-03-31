package org.academiadecodigo.javabank.converters;


import org.academiadecodigo.javabank.persistence.model.account.Account;

import java.util.ArrayList;
import java.util.List;

public class CustomerDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<AccountDTO> accounts = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
