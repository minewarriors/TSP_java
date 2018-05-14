/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmes;

import Core.Product;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author jelle
 */
public class Route {

    private ArrayList<Product> products = new ArrayList<Product>();

    public Route(ArrayList<Product> initialRoute) {
        setProducts(initialRoute);
    }

    public Route(Route route) {
        route.products.stream().forEach(x -> products.add(x));
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return Arrays.toString(products.toArray());
    }

}
