package com.example.naman.tarp;

/**
 * Created by naman on 09-Feb-18.
 */

public class TeacherDetails {
    public TeacherDetails() {
    }

    public TeacherDetails(String nameof, String email, Long empid, Long phno, String cab, String pass) {
        this.name = nameof;
        this.email = email;
        this.regno = empid;
        this.phone = phno;
        this.cabin = cab;
        this.pass = pass;
    }

    public String name,email,cabin,pass;
    public Long phone,regno;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getRegno() {
        return regno;
    }

    public void setRegno(Long regno) {
        this.regno = regno;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
