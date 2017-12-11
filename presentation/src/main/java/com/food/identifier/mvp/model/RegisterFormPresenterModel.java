package com.food.identifier.mvp.model;

/**
 * Created by taras on 12/11/2017.
 */

public class RegisterFormPresenterModel {
    private String login;
    private String firstName;
    private String lastName;
    private String password;
    private int type;
    private int registrationKey;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRegistrationKey() {
        return registrationKey;
    }

    public void setRegistrationKey(int registrationKey) {
        this.registrationKey = registrationKey;
    }
}
