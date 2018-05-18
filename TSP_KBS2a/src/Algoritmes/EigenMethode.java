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
public class EigenMethode {

    //Neemt oorspronkelijke hoeveelheid producten op in ArrayList
    public Route FindShortestRoute(ArrayList<Product> producten) {
        ArrayList<Product> shortestRouteProducts = new ArrayList<Product>(producten.size());

        //Print de oorsprongelijke route van de pakketjes met oorspronkelijke afstand
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("Initial Route           ==>" + Arrays.toString(producten.toArray()));
        System.out.println("Total distance          ==> " + new Route(producten).calculateTotalDistance());

        //pakt een Random product om bij te beginnen uit de order
        Product product = producten.get((int) (producten.size() * Math.random()));

        //update de route
        updateRoute(shortestRouteProducts, producten, product);

        //update de route continu tot er geen nieuwe steden zijn
        while (producten.size() >= 1) {
            product = getNextProduct(producten, product);
            updateRoute(shortestRouteProducts, producten, product);
        }
        // Returned nieuwste kortste route doormiddel van nearestneighbor
        return new Route(shortestRouteProducts);
    }

    //----------------------------------------------------------------------------------------------------
    //verwijderd het laatst toegevoegde product uit de lijst van afstanden calculeren en voegt aan shortestRoute toe
    public void updateRoute(ArrayList<Product> shortestRouteProducts, ArrayList<Product> producten, Product product) {
        shortestRouteProducts.add(product);
        producten.remove(product);

        System.out.println("Products in shortest route ==> " + Arrays.toString(shortestRouteProducts.toArray()));
        System.out.println("Remaining products         ==> " + Arrays.toString(producten.toArray()) + "\n");
    }

    //----------------------------------------------------------------------------------------------------\
    //berekent de afstand van het eerst volgende product van alle producten
    private Product getNextProduct(ArrayList<Product> producten, Product product) {
        return producten.stream().min((product1, product2) -> {
            int flag = 0;
            if (product1.measureDistance(product) < product2.measureDistance(product)) {
                flag = -1;
            } else if (product1.measureDistance(product) > product2.measureDistance(product)) {
                flag = 1;
            }
            return flag;
        }).get();
    }
}
