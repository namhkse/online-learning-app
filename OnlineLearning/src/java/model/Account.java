/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *
 * @author midni
 */
public class Account {

    private int accountId;
    private String firstName;
    private String lastName;
    private String profilePictureUrl;
    private String email;
    private String password;
    private boolean gender;
    private String address;
    private String phone;
    private BigDecimal balance;
    private Role role;
    private Timestamp createdTime;
    private Timestamp modifiedTime;

    public Account() {
    }

    public Account(int accountID, String firstName, String lastName, String profilePictureUrl, String email, String password, boolean gender, String address, String phone, BigDecimal balance, Role role, Timestamp createdTime, Timestamp modifiedTime) {
        this.accountId = accountID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePictureUrl = profilePictureUrl;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.balance = balance;
        this.role = role;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountID) {
        this.accountId = accountID;
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

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Timestamp modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

}
