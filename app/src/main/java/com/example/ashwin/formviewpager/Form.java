package com.example.ashwin.formviewpager;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ashwin on 25/5/17.
 */

public class Form implements Parcelable {
    private String name, email, username, street, city, country, education;
    private int houseNumber, zipCode;

    public Form() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.username);
        dest.writeString(this.street);
        dest.writeString(this.city);
        dest.writeString(this.country);
        dest.writeString(this.education);
        dest.writeInt(this.houseNumber);
        dest.writeInt(this.zipCode);
    }

    protected Form(Parcel in) {
        this.name = in.readString();
        this.email = in.readString();
        this.username = in.readString();
        this.street = in.readString();
        this.city = in.readString();
        this.country = in.readString();
        this.education = in.readString();
        this.houseNumber = in.readInt();
        this.zipCode = in.readInt();
    }

    public static final Parcelable.Creator<Form> CREATOR = new Parcelable.Creator<Form>() {
        @Override
        public Form createFromParcel(Parcel source) {
            return new Form(source);
        }

        @Override
        public Form[] newArray(int size) {
            return new Form[size];
        }
    };
}
