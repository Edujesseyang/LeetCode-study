package src.Sorting;

import java.util.ArrayList;
import java.util.List;

public class WidestVerticalAreaBetweenTwoPointsContainingNoPoints_1637 {
    /*Given n points on a 2D plane where points[i] = [xi, yi], Return the widest vertical area between two points such that no points are inside the area.

A vertical area is an area of fixed-width extending infinitely along the y-axis (i.e., infinite height). The widest vertical area is the one with the maximum width.

Note that points on the edge of a vertical area are not considered included in the area.

 */

    public static void main(String[] args) {

    }

    public int maxWidthOfVerticalArea(int[][] points) {
        int size = points.length;
        mergeSort(points, 0, size - 1);
        for(int[] p : points){
            System.out.print(p[0] + " ");
        }

        int max = 0;
        for(int i = 0; i < size - 1; i++){
            int dist = points[i + 1][0] - points[i][0];
            max = Math.max(max, dist);
        }
        return max;
    }

    public void mergeSort(int[][] array, int left, int right){
        int mid = left + (right - left) / 2;
        if(left >= right) return;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    public void merge(int[][] array, int left, int mid, int right){
        List<int[]> copy = new ArrayList<>();
        int l = left, r = mid + 1, end = right;

        while(l <= mid && r <= end){
            if(array[l][0] < array[r][0]){
                copy.add(array[l++]);
            } else {
                copy.add(array[r++]);
            }
        }

        while(l <= mid) copy.add(array[l++]);
        while(r <= end) copy.add(array[r++]);

        for(int[] i : copy){
            array[left++] = i;
        }
    }
}
