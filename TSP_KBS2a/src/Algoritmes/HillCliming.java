package Algoritmes;

import Core.Product;
import java.util.ArrayList;

public class HillCliming {

    private ArrayList<Product> shortestRoute = new ArrayList<Product>();

    public static final int maxima = 100;

    public void findShortestRoute(Route currentRoute) {
        Route adjecentRoute;
        int counter = 0;
        String compareRoutes = null;
        System.out.println("HERE STARTS WHILE LOOP");
        while (counter < maxima) {
            adjecentRoute = obtainAdjecentRoute(new Route(currentRoute));
            if (adjecentRoute.calculateTotalDistance() < currentRoute.calculateTotalDistance()) {
                compareRoutes = "<= (proceed)";
                currentRoute = new Route(adjecentRoute);
                System.out.println(currentRoute + " |      " + currentRoute.calculateTotalDistance() + "    |    " + compareRoutes);
                counter = 0;
            } else {
                compareRoutes = "> (stay) iteration # " + counter++;
                System.out.println(adjecentRoute + " |      " + currentRoute.calculateTotalDistance() + "    |    " + compareRoutes);
            }
            if (counter == maxima) {
                System.out.println(currentRoute + "        | potential maximum");
                System.out.println(currentRoute + "        | " + compareRoutes);
            }
        }

        currentRoute.getProducts().stream().forEach(x -> {
            shortestRoute.add(x);
        });
    }

    public Route obtainAdjecentRoute(Route route) {
        int x1 = 0;
        int x2 = 0;
        while (x1 == x2) {
            x1 = (int) (route.getProducts().size() * Math.random());
            x2 = (int) (route.getProducts().size() * Math.random());
        }
        Product p1 = route.getProducts().get(x1);
        Product p2 = route.getProducts().get(x2);
        route.getProducts().set(x1, p2);
        route.getProducts().set(x2, p1);

        return route;
    }

    public ArrayList<Product> getShortestRoute() {
        return shortestRoute;
    }

}
