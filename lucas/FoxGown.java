package lucas;

import java.util.Arrays;
import java.util.Random;
/**
 * 
 * Ball Gown Fitting
 * The woodland creatures have created a beautiful ball gown for a
 *  local princess. Some of the foxes want to try on the ball gown 
 * to test it out before handing it over to the princess. 
 * The ball gown, which has a length S (1 <= S <= 1,000,000), is able 
 * to fit exactly two foxes. There are N foxes (2 <= N <= 100,000)
 * that are numbered from 1...N. 
 * Fox i has a length of L_i (1 <= L_i <= 1,000,000). Two foxes can
 * successfully try out the ball gown if their total length is not
 * longer than the gown’s length. Find out how many pairs of distinct 
 * foxes can fit into the ball gown. 
 * INPUT FORMAT
 * Line 1: Two integers separated by a space: N and S
 * Line 2..N+1: Line i+1 contains a single integer: L_i
 * OUTPUT FORMAT
 * Line 1: A single integer representing the number of pairs of foxes that
 * can fit into the ball gown. The order of the two foxes does not matter.
 */
public class FoxGown {
    public static void main(String[] args) {
        // N number of foxes
        int N = 10;
        // size of the gown
        int S = 100;
        int[] foxes = new int[N];
        Random rand = new Random();
        for (int i = 0; i < N; i++) {
            foxes[i] = rand.nextInt(S);
            System.out.print(foxes[i]);
            System.out.print("\t");
        }
        System.out.println();
        // step 1 filter out anything that is larger than the S
        // we will also do a quick sort of the array
        quickSort(foxes, 0, N - 1);
        int numofnewfoxes = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (foxes[i] <= S) {
                numofnewfoxes = i + 1;
                break;
            }
        }
        Arrays.stream(foxes).forEach(i -> {
            if (i <= S)
                System.out.print(String.valueOf(i) + "\t");
        });
        System.out.println("\nnumoffoxes = "+String.valueOf(numofnewfoxes));
        //now iterate through the sorted array, for each element, get the substration from N- foxies[i]
        // check howmany pairs are there for each element by traverse the array from end tail and find the 
        // first index where the fox[j] <= S and j> i
        int numofpairs = 0;
        for (int i = 0; i < numofnewfoxes; i++){
            int len_i = foxes[i];
            int max_len = S- len_i;
            for (int j = numofnewfoxes-1; j> i; j--){
                if (foxes[j]<=max_len){
                    //num of pairs should be incremtn by j-i
                    numofpairs = numofpairs + (j-1);
                    break;
                }
            }
        }
        System.out.println(numofpairs);
    }

   
   /**
    * Following section present a quick sort algorithm, you can find the 
    * the algorithm and visual from this website
    * https://www.programiz.com/dsa/quick-sort
    */
   
    // method to find the partition position
    static int partition(int array[], int low, int high) {

        // choose the rightmost element as pivot
        int pivot = array[high];

        // pointer for greater element
        int i = (low - 1);

        // traverse through all elements
        // compare each element with pivot
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {

                // if element smaller than pivot is found
                // swap it with the greatr element pointed by i
                i++;

                // swapping element at i with element at j
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }

        }

        // swapt the pivot element with the greater element specified by i
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        // return the position from where partition is done
        return (i + 1);
    }

    static void quickSort(int array[], int low, int high) {
        if (low < high) {

            // find pivot element such that
            // elements smaller than pivot are on the left
            // elements greater than pivot are on the right
            int pi = partition(array, low, high);

            // recursive call on the left of pivot
            quickSort(array, low, pi - 1);

            // recursive call on the right of pivot
            quickSort(array, pi + 1, high);
        }
    }
}