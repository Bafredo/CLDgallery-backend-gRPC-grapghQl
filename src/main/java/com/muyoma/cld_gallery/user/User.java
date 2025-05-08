package com.muyoma.cld_gallery.user;


import com.muyoma.cld_gallery.grouping.membership.Membership;
import com.muyoma.cld_gallery.media.collections.Collection;
import com.muyoma.cld_gallery.media.transactions.Uploads;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String firstName;
    String lastName;
    String phoneNumber;
    String email;
    String password;

    int validationCode;
    boolean verificationStatus;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Collection> collections = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Uploads> uploads = new ArrayList<>();

    @OneToMany(mappedBy = "member" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Membership> memberships = new ArrayList<>();

    public List<Membership> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<Membership> memberships) {
        this.memberships = memberships;
    }

    public List<Uploads> getUploads() {
        return uploads;
    }

    public void setUploads(List<Uploads> uploads) {
        this.uploads = uploads;
    }

    public boolean isVerificationStatus() {
        return verificationStatus;
    }

    public List<Collection> getCollections() {
        return collections;
    }

    public void setCollections(List<Collection> collections) {
        this.collections = collections;
    }


    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public int getValidationCode() {
        return validationCode;
    }
    public boolean getVerificationStatus() {
        return verificationStatus;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setValidationCode(int validationCode) {
        this.validationCode = validationCode;
    }
    public void setVerificationStatus(boolean verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public User() {

    }

    public User( String firstName, String lastName, String phoneNumber, String email, String password,int validationCode, boolean verificationStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.validationCode = validationCode;
        this.verificationStatus = verificationStatus;
    }
}
