package com.example.feminae_project;

public class User {
    public String fullname, date, emailid, phoneno, pass;
    public User(String fullname, String emailid, String phoneno){

    }

    public User(String fullname,String date,String emailid,String phoneno,String pass){
        this.fullname=fullname;
        this.date=date;
        this.emailid=emailid;
        this.phoneno=phoneno;
        this.pass=pass;
    }

    public User(){

    }

    public String getFullname(){
        return fullname;
    }

    public String getDate(){
        return date;
    }

    public String getEmailid(){
        return emailid;
    }

    public String getPhoneno(){
        return phoneno;
    }

    public String getPass(){
        return pass;
    }
}


