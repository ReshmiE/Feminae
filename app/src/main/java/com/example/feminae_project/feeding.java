package com.example.feminae_project;

public class feeding {

    private String dof, bt, et;

    public feeding(){

    }
    public feeding(String dof,String bt,String et){
        this.dof=dof;
        this.bt=bt;
        this.et=et;
    }

    public String getDof() {
        return dof;
    }

    public void setDof(String dof) {
        this.dof = dof;
    }

    public String getBt() {
        return bt;
    }

    public void setBt(String bt) {
        this.bt = bt;
    }

    public String getEt() {
        return et;
    }

    public void setEt(String et) {
        this.et = et;
    }
}
