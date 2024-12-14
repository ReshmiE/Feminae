package com.example.feminae_project;

public class pggest {
    private String gest_age,due_date;

    public pggest(){

    }
    public pggest(String gest_age,String due_date){

        this.gest_age=gest_age;
        this.due_date=due_date;
    }

    public String getGest_age() {
        return gest_age;
    }

    public void setGest_age(String gest_age) {
        this.gest_age = gest_age;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }
}
