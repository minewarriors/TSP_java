package Core;

import java.awt.Color;

/**
 *
 * @author Christiaan & Jelle
 */

public class Product {

    private int productId;
    private int x;
    private int y;
    private Color color;
    private int size;

    public Product(Color color, int size) {
        this(0, 0, 0, color, size);
    }

    public Product(int productId, int x, int y, Color color, int size) {
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

    public Color getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "product" + productId;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
