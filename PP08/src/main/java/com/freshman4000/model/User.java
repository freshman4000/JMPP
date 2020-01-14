package com.freshman4000.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class User implements UserDetails {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private String email;
    @Column
    private String birthdate;
    @Column
    private String phone;
    @Column
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles;
    @Column
    private String password;

    public User() {
    }

    public User(String firstname, String lastname, String email, String birthdate, String phone, Set<Role> roles, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.birthdate = birthdate;
        this.phone = phone;
        this.roles = roles;
        this.password = password;
    }

    public User(Long id, String firstname, String lastname, String email, String birthdate, String phone, Set<Role> roles, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.birthdate = birthdate;
        this.phone = phone;
        this.roles = roles;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}