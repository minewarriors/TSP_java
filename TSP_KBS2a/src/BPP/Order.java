package BPP;

import java.util.ArrayList;

public class Order implements OrderInterface{

    private ArrayList<Product> orderPackages;

    public Order() {
        orderPackages = new ArrayList<>();
    }

    @Override
    public void addToOrder(Product p) {
        orderPackages.add(p);
    }

    @Override
    public ArrayList<Product> getOrderPackages() {
        return orderPackages;
    }

    @Override
    public String toString() {
        String orderList = "De volgende ID's zijn opgevraagd: \n";
        for (Product p : orderPackages) {
            orderList += p + "\n";
        }
        return orderList;
    }

     @Override
    public void print() { //print de gegevens in de array
        orderPackages.forEach((x) -> {
            System.out.println(x);
        });
    }
}
