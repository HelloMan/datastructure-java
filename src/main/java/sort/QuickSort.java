package sort;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 快速排序实现
 */
public class QuickSort<T extends Comparable<T>> implements Sort<T> {


    private final T[] values;

    private final boolean desc;


    public QuickSort(T[] values, boolean desc) {
        this.values = values;
        this.desc = desc;


    }

    private void quicksort(int low, int high){

        if (low < high) {
            int pivot = partition(low, high);
            quicksort(low, pivot-1);
            quicksort(pivot + 1, high);
        }


    }

    private boolean compare(int i, int j) {
        int result = values[i].compareTo(values[j]);
        return desc ? result >= 0 : result < 0;
    }


    private int partition(int low, int high) {
        int pivot = low;
        while (low < high) {
            //将比枢轴小的元素移到低端，此时right位相当于空，等待低位比pivotkey大的数补上
            while (low < high && values[high].compareTo(values[pivot])>=0) {
                --high;

            }
            values[low] = values[high];
            //将比枢轴大的元素移到高端，此时left位相当于空，等待高位比pivotkey小的数补上
            while (low < high && values[low].compareTo(values[pivot])<=0) {
                ++low;
            }
            values[high] = values[low];
        }
        //当left == right，完成一趟快速排序，此时left位相当于空，等待pivotkey补上
        values[low] = values[pivot];
        return low;
    }

    public static void main(String[] args) {
        Integer[] integers = Stream.of(20, 10, 30, 40, 80, 70, 60, 50).toArray(Integer[]::new);
        QuickSort quickSort = new QuickSort(integers,true);

        quickSort.sort();

        quickSort.getResults().forEach(System.out::println);
    }

    @Override
    public void sort() {
        quicksort(0, values.length - 1);

    }

    @Override
    public Stream<T> getResults() {
        return Arrays.stream(values);
    }
}
