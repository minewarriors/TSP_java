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
public class Order{
    private ArrayList<Product> orderPackages;
    private ArrayList<String> order;

    public Order() {
        order = new ArrayList<>();
        orderPackages = new ArrayList<>();
    }

    public void addToOrder(String id) {
        order.add(id);
    }
    public void addToOrder(Product p) {
        orderPackages.add(p);
    }

    public ArrayList<Product> getOrderPackages() {
        return orderPackages;
    }

    public ArrayList<String> getOrder() {
        return order;
    }
    
    

    @Override
    public String toString() {
        String orderList = "De volgende ID's zijn opgevraagd: \n";
        for (String a : order) {
            orderList += "ID: " + a + "\n";
        }
        return orderList;
    }

    public void print() { //print de gegevens in de array
        orderPackages.forEach((x) -> {
            System.out.println(x);
        });
    }

    public void addToOrder(BPP.Product p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
