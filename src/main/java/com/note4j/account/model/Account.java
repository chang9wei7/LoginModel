package com.note4j.account.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 character long.")
    @NotNull(message = "username can't be null.")
    private String user_name;

    @Size(min = 7, max = 11, message = "Phone number must be 7 and 11 digitial long")
    @Pattern(regexp = "^[0-9]+$", message = "Phone number must be digital.")
    private String phone;

    @Size(min = 3, max = 30, message = "The address must be at least 10 words.")
    private String address;
    @Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,4}", message = "Inviald email address.")
    private String mail;
    @NotNull(message = "Please input password.")
    @Size(min = 6, max = 20, message = "The password mst be at least 6 and at most character long.")
    private String password;

    public Account() {

    }


    public Account(String user_name, String address, String phone,
                   String password) {
        super();
//        this.id = id;
        this.user_name = user_name;
        this.address = address;
        this.phone = phone;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) {
        Account account = new Account();
        account.setUser_name("cs");
        System.out.println(account.getUser_name());
    }
}
