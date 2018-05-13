/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.util.ArrayList;

/**
 *
 * @author jelle
 */
public class Order {

    private ArrayList<String> order;

    public Order() {
        order = new ArrayList<>();
    }

    public void addToOrder(String id) {
        order.add(id);
    }

    public String toString() {
        String orderList = "De volgende ID's zijn opgevraagd: \n";
        for (String a : order) {
            orderList += "ID: " + a + "\n";
        }
        return orderList;
    }
}
