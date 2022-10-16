package model;

import exception.CustomException;

import java.util.regex.Pattern;

public abstract class User {
    private String firstName;
    private String lastName;
    private String nationalCode;
    private Account account;

    public User(String firstName, String lastName, String nationalCode, String password) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setNationalCode(nationalCode);
        this.account = new Account(nationalCode, password);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.length() > 2)
            this.firstName = firstName;
        else
            throw new CustomException(firstName + " is not a valid name.");
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.length() > 3)
            this.lastName = lastName;
        else
            throw new CustomException(lastName + " is invalid.");
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        if (Pattern.matches("^[0-9]{10}$", nationalCode))
            this.nationalCode = nationalCode;
        else
            throw new CustomException("invalid national code!");
    }

    public Account getAccount() {
        return account;
    }

    public boolean validation(String password) {
        return account.validatePassword(password);
    }

}
