package Core;

import java.util.ArrayList;

/**
 *
 * @author Christiaan
 */
public class Box {

    private int freeSpace;

    private int boxSize;

    private ArrayList<Product> productBoxArray = new ArrayList<>();

    public Box(int boxSize) {
        this.boxSize = boxSize;
        this.freeSpace = this.boxSize;
    }

    public boolean AddProduct(Product product, boolean add) {
        if (product.getSize() <= freeSpace) {
            this.freeSpace = freeSpace - product.getSize();
            if(add){
            productBoxArray.add(product);
            }
            return true;
        } else {
            return false;
        }
    }

    public int getFreeSpace() {
        return freeSpace;
    }

    public ArrayList<Product> getProductBoxArray() {
        return productBoxArray;
    }

    public void print() { //print de gegevens in de array
        productBoxArray.forEach((x) -> {
            System.out.println(x);
        });
    }

    public void clearBox() {
        this.productBoxArray.clear();
        this.freeSpace = this.boxSize;
    }
}
