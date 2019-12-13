package com.freshman4000.model;

import javax.persistence.*;

@Entity
@Table
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String username;
    @Column
    private String lastname;
    @Column
    private String email;
    @Column
    private String birthdate;
    @Column
    private String phone;
    @Column
    private String role;
    @Column
    private String password;

    public User() {
    }

    public User(String username, String lastname, String email, String birthdate, String phone, String role, String password) {
        this.username = username;
        this.lastname = lastname;
        this.email = email;
        this.birthdate = birthdate;
        this.phone = phone;
        this.role = role;
        this.password = password;
    }

    public User(Long id, String username, String lastname, String email, String birthdate, String phone, String role, String password) {
        this.id = id;
        this.username = username;
        this.lastname = lastname;
        this.email = email;
        this.birthdate = birthdate;
        this.phone = phone;
        this.role = role;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("User: " +
                "id<span id=\"ufi\">%s</span> name<span id=\"ufi\">%s</span> last name<span id=\"ufi\">%s</span> email<span id=\"ufi\">%s</span> birth date<span id=\"ufi\">%s</span> phoneNumber<span id=\"ufi\">%s</span> role<span id=\"ufi\">%s</span>", id, username, lastname, email, birthdate, phone, role);
    }
}