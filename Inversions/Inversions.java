import java.util.Arrays;

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
    
    public static void main(String args[]){
        long[] a = {30,90,10,8,7};
        arrAttributes ans = mergeSort(a);
        System.out.println("Number of inversions: " + ans.nInv);
        System.out.println("Sorted Array: " + Arrays.toString(ans.arraySorted));
    }
}

class arrAttributes{
    public long nInv;
    public long[] arraySorted;
    public arrAttributes(long n, long[] a) {
        this.nInv = n;
        this.arraySorted = a;
    }
}
