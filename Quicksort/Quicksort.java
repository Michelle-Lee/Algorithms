/**
 * This method implements QuickSort with the option of partition using either the 
 * first element, last element, or the median of first, middle, last as the pivot.
 */

import java.util.Arrays;

public class QuickSort {
    public static int quickSort(long[] arr, int start, int end, String method){ // method: "start","end", or "median"
        if (end-start < 1){
            return 0;
        }
        int m = end - start;
        int right = 0;
        int left = 0;
        int partition = partition(arr, start, end, method);
        if(partition - 1 > start){    
            right = quickSort(arr, start, partition-1, method);
        }
        if(partition + 1 < end){
            left = quickSort(arr, partition+1, end, method);
        }
        m = m + right + left;
        return m;
    }
    
    // Partitions based on parition method: start, median, or end
    public static int partition(long[] arr, int start, int end, String method){
        if (method.equals("median")){
            findMed(arr, start, end);
        }
        if (method.equals("end")){
            swap(arr, start, end);
        }
        long pivot = arr[start];
        int i = start;
        for (int index = start; index <= end;index++){
            if (arr[index] < pivot){
                i++;
                swap(arr, i, index);
            }
        }
        swap(arr, start, i);
        return i;
    }
    
    // Find the median: first, middle, or last element
    public static void findMed(long[] arr, int start, int end){
        long[] pivs = {arr[start], arr[(end+start)/2], arr[end]};
        int minIdx = 0;
        long min = pivs[0];
        for (int i=0; i<3; i++){
            if (pivs[i] < min){
                min = pivs[i];
                minIdx = i;
            }
        }
        swap(pivs, 0, minIdx);
        long med = Math.min(pivs[1], pivs[2]);
        if (med == (arr[(end+start)/2])){
            swap(arr, start, (end+start)/2);
        }
        if (med == arr[end]){
            swap(arr, start, end);
        }
    }
    
    // Swap elements of two indices in an array
    public static long[] swap(long[] arr, int idx1, int idx2){
        long temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
        return arr;
    }
  
    // Sample test
    public static void main(String args[]) {
        long[] a = {10, 80, 30, 90, 40, 50, 70};
        quickSort(a, 0, a.length-1, "median");
        System.out.println("The sorted array is: " + Arrays.toString(a));
    }
}
