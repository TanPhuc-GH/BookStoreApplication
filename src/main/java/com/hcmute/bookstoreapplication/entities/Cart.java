package com.hcmute.bookstoreapplication.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private ArrayList<Item> items;

    public void addItem(ArrayList<Item> items){

    }

    public  void removeItem(ArrayList<Item> items){

    }
}
