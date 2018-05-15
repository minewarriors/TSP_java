/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmes;

import Core.Product;
import java.awt.Color;

/**
 *
 * @author jelle
 */
public class TestHarness {

    public static void main(String[] args) {
        //producten aan maken
        Product product1 = new Product(1, 2, 5, Color.RED, 1);
        Product product2 = new Product(2, 10, 100, Color.RED, 5);
        Product product3 = new Product(3, 1, 1, Color.RED, 1);

        //afstanden bepalen
        double distance1to2 = product1.measureDistance(product2);
        double distance2to3 = product2.measureDistance(product3);
        double distance1to3 = product1.measureDistance(product3);

        //resultaten uitprinten
        System.out.println("Distance van " + product1 + " naar " + product2 + " = " + String.format("%.2f", distance1to2) + " <--");
        System.out.println("Distance van " + product2 + " naar " + product3 + " = " + String.format("%.2f", distance2to3) + " <--");
        System.out.println("Distance van " + product1 + " naar " + product3 + " = " + String.format("%.2f", distance1to3) + " <--");
    }
}
