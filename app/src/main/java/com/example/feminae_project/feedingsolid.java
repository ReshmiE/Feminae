package com.example.feminae_project;

public class feedingsolid {
    private String dof, tof, solid;

    public feedingsolid(){

    }
    public feedingsolid(String dof,String tof,String solid){
        this.dof=dof;
        this.tof=tof;
        this.solid=solid;
    }

    public String getDof() {
        return dof;
    }

    public void setDof(String dof) {
        this.dof = dof;
    }

    public String getTof() {
        return tof;
    }

    public void setTof(String tof) {
        this.tof = tof;
    }

    public String getSolid() {
        return solid;
    }

    public void setSolid(String solid) {
        this.solid = solid;
    }
}
