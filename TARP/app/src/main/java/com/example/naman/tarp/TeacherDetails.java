package com.example.naman.tarp;

/**
 * Created by naman on 09-Feb-18.
 */

public class TeacherDetails {
    public TeacherDetails() {
    }

    public TeacherDetails(String nameof, String email, String empid, String phno, String cab, String pass) {
        this.nameof = nameof;
        this.email = email;
        this.empid = empid;
        this.phno = phno;
        this.cab = cab;
        this.pass = pass;
    }

    public String nameof,email,empid,phno,cab,pass;
    public String getNameof() {
        return nameof;
    }

    public void setNameof(String nameof) {
        this.nameof = nameof;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getCab() {
        return cab;
    }

    public void setCab(String cab) {
        this.cab = cab;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
