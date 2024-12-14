package com.example.feminae_project;

public class babyinfo {

    private String babyname, sex, birthday;

    public babyinfo(){

    }
    public babyinfo(String babyname,String sex,String birthday){
        this.babyname=babyname;
        this.sex=sex;
        this.birthday=birthday;
    }

    public String getBabyname() {
        return babyname;
    }

    public void setBabyname(String babyname) {
        this.babyname = babyname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
