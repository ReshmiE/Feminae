package com.example.feminae_project;

public class periodtracker {
    private String last,llong,cycle,next;

    public periodtracker(){

    }
    public periodtracker(String last,String llong,String cycle,String next){

        this.last=last;
        this.llong=llong;
        this.cycle=cycle;
        this.next=next;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getLlong() {
        return llong;
    }

    public void setLlong(String llong) {
        this.llong = llong;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
