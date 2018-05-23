package BPPAlgorithms;

/**
 *
 * @author Christiaan
 */
import Core.Box;
import Core.Product;
import java.util.*;

public class Sort {

    // Function to sort by column
    public static void sortbyColumn(int arr[][], int col, boolean desc) {
        // Using built-in sort function Arrays.sort
        Arrays.sort(arr, new Comparator<int[]>() {

            @Override
            // Compare values according to columns
            public int compare(final int[] entry1,
                    final int[] entry2) {

                // To sort in descending order revert 
                // the '>' Operator
                if (desc) {
                    if (entry1[col] < entry2[col]) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else {
                    if (entry1[col] > entry2[col]) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        });  // End of function call sort().
    }

    public static ArrayList<Product> sortProductsInOrderBySize(ArrayList<Product> productArray, boolean desc) {
        int OrderSize = productArray.size();

        ArrayList<Product> newArray = new ArrayList<>();

        int[][] product2dArray = new int[OrderSize][2];

        for (int n = 0; n < OrderSize; n++) {
            product2dArray[n][0] = n + 1;
            product2dArray[n][1] = productArray.get(n).getSize();
        }

        sortbyColumn(product2dArray, 1, desc);

        for (int i = 0; i < product2dArray.length; i++) {
            for (int j = 0; j < product2dArray[i].length; j++) {
            }
            newArray.add(productArray.get(product2dArray[i][0] - 1));
        }
        return newArray;
    }
    
        public static ArrayList<Box> sortBoxesInOrderByFreeSpace(ArrayList<Box> boxArray, boolean desc) {
        int boxArraySize = boxArray.size();

        ArrayList<Box> newBoxArray = new ArrayList<>();

        int[][] box2dArray = new int[boxArraySize][2];
        

        for (int n = 0; n < boxArraySize; n++) {
            box2dArray[n][0] = n + 1;
            box2dArray[n][1] = boxArray.get(n).getFreeSpace();
        }

        sortbyColumn(box2dArray, 1, desc);

        for (int i = 0; i < box2dArray.length; i++) {
            for (int j = 0; j < box2dArray[i].length; j++) {
            }
            newBoxArray.add(boxArray.get(box2dArray[i][0] - 1));
        }
        return newBoxArray;
    }
        
                public static Box getFullesBox(ArrayList<Box> boxArray) {
        int boxArraySize = boxArray.size();

        ArrayList<Box> newBoxArray = new ArrayList<>();

        int[][] box2dArray = new int[boxArraySize][2];
        

        for (int n = 0; n < boxArraySize; n++) {
            box2dArray[n][0] = n + 1;
            box2dArray[n][1] = boxArray.get(n).getFreeSpace();
        }

        sortbyColumn(box2dArray, 1, true);

        for (int i = 0; i < box2dArray.length; i++) {
            for (int j = 0; j < box2dArray[i].length; j++) {
            }
            newBoxArray.add(boxArray.get(box2dArray[i][0] - 1));
        }
        return newBoxArray.get(0);
    }
}
