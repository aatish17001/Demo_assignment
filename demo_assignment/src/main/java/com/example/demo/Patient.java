package com.example.demo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name="Patient_records")
public class Patient {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @Column(name = "name")
    private String Name;

    @Column(name = "address")
    private  String Address;

    @Column(name = "email")
    private  String Email;

    @Column(name = "phone_number")
    private  String Phone_number;

    @Column(name = "password")
    private String Password;

    public Patient() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone_number() {
        return Phone_number;
    }

    public void setPhone_number(String phone_number) {
        Phone_number = phone_number;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    Patient(String n, String a, String e, String p, String ps){
        Name=n;
        Address=a;
        Email=e;
        Phone_number=p;
        Password=ps;
    }
    @Override
    public String toString(){
        return  (this.getId()+" "+this.getName()+" "+this.getAddress()+" "+this.getEmail()+" "+this.getPassword()) ;
    }

}
