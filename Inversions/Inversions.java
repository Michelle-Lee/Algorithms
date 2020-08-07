import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Inversions {
    public static arrAttributes mergeSort(long[] arr) {
       long numInv = 0;
       int n = arr.length;
       if (n == 1) {
           numInv = 0;
           return new arrAttributes(numInv,arr);
       }
       
       // split the array
       int midL = (int)n/2;
       int midR = (int) Math.ceil((double)n/2);
       long[] left = new long[midL];
       long[] right = new long[midR];
       System.arraycopy(arr, 0, left, 0, midL);
       System.arraycopy(arr, midL, right, 0, midR);
       
       // Recursion of left and right
       arrAttributes x = mergeSort(left);
       arrAttributes y = mergeSort(right);
       
       left = x.arraySorted;
       right = y.arraySorted;
       
       // Merge, sort, and count
       long[] sortedArr = new long[n];
       int i = 0;
       int j = 0;
       for (int k = 0; i < midL && j < midR; k++) {
           if (left[i] < right[j]) {
               sortedArr[k] = left[i];
               i++;
           } else {
               sortedArr[k] = right[j];
               j++;
               numInv = numInv + (midL - i);
           }
       }
       if (i == midL) {
           System.arraycopy(right, j, sortedArr, i+j, midR-j);
       } 
       if (j == midR) {
           System.arraycopy(left, i, sortedArr, i+j, midL-i);
       }
       numInv = numInv + x.nInv + y.nInv;
       return new arrAttributes(numInv,sortedArr);
    }
    
    // import file as long[] array
    public static long[] fileToArray(File file) throws FileNotFoundException {
        
        Scanner scn = new Scanner(file);
        List <Long> arrL = new ArrayList<>();
        while(scn.hasNextLong()) {
            Long line = scn.nextLong();
            arrL.add(line);
        }
        scn.close();
        Long[] arr = new Long[arrL.size()];
        arr = arrL.toArray(arr);
        
        long[] arrlong = toPrimitives(arr);
        return arrlong;
     }
    
    // helper method to convert to primitive 
    public static long[] toPrimitives(Long... objects) {
        long[] primitives = new long[objects.length];
        for (int i = 0; i < objects.length; i++){
             primitives[i] = objects[i];
            }
        return primitives;
    }
    
    public static void main(String args[]) throws FileNotFoundException{
    	File f = new File("IntegerArray.txt");
    	long[] a = fileToArray(f);
        arrAttributes ans = mergeSort(a);
        System.out.println("Sorted Array: " + Arrays.toString(ans.arraySorted));
        System.out.println("Number of inversions: " + ans.nInv);
    	
    }
}

// helper class to store array and number of inversions
class arrAttributes{
    public long nInv;
    public long[] arraySorted;
    public arrAttributes(long n, long[] a) {
        this.nInv = n;
        this.arraySorted = a;
    }
}
