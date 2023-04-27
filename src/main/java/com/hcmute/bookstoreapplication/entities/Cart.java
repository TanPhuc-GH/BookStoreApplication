package com.hcmute.bookstoreapplication.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Cart {
    private ArrayList<Item> items;

    public void addItem(ArrayList<Item> items){

    }

    public  void removeItem(ArrayList<Item> items){

    }
}
