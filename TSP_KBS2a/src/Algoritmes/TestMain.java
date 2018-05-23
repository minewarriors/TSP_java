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
 * @author Bram ten Brinke
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Product p1 = new Product(1, 1, 2, Color.BLUE, 30);
        Product p2 = new Product(2, 5, 5, Color.BLUE, 30);
        
        System.out.println(p1.measureDistance(p2));
    }
    
}
