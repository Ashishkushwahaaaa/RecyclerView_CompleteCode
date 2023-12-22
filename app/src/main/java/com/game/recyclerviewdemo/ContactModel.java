package com.game.recyclerviewdemo;

public class ContactModel{
    int img;
    String name, number;

    public ContactModel(int img,String name,String number){
        this.img = img;
        this.name = name;
        this.number = number;
    }
    public ContactModel(String name,String number){
        this.name = name;
        this.number = number;
        this.img = R.drawable.baseline_person_4_24;
    }
}
