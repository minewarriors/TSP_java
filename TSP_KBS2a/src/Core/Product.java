/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.awt.Color;

/**
 *
 * @author jelle
 */
public class Product {

    private int productId;
    private int x;
    private int y;
    private String color;
    private int size;

    public Product(String color, int size) {
        this(0, 0, 0, color, size);
    }

    public Product(int productId, int x, int y, String color, int size) {
        this.productId = productId;
        this.x = x;
        this.y = y;
        this.color = color;
        this.size = size;
    }

    public int getProductId() {
        return productId;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", x=" + x + ", y=" + y + ", color=" + color + ", size=" + size + '}';
    }

    //afstand berekenen tussen producten
    public double measureDistance(Product product) {
        //delta coördinaten berekenen
        double deltaX = (product.getX() - this.getX());
        double deltaY = (product.getY() - this.getY());

        //tot de macht
        double powerOfX = Math.pow(deltaX, 2);
        double powerOfY = Math.pow(deltaY, 2);

        //wortel trekken voor afstand tussen coördinaten
        return Math.sqrt((powerOfX + powerOfY));
    }
}
