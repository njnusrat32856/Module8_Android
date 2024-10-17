package com.nusrat.childcare.model;

public class User {

    private int id;
    private String firstname;
    private String lastname;
    private String fullname;
    private String email;
    private String password;
    private String type;

    public User() {
    }

    public User(int id, String firstname, String lastname, String fullname, String email, String password, String type) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public boolean isEmpty() {
        return this.firstname.isEmpty() ||
                this.lastname.isEmpty() ||
                this.email.isEmpty() ||
                this.password.isEmpty() ||
                this.type.isEmpty();
    }
}
