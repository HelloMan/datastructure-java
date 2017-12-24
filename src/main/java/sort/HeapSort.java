package sort;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 堆排序
 * 1. 从后往前堆节点和其子节点进行比较，如果是最大堆，节点必须大于子节点，如果是最小堆，节点必须小于子节点。
 * 2. 对于被交换的节点进行递归按照堆的性质进行调整
 * 3. 最后一步，根节点和最后一个节点进行交换，交换后堆根节点按照堆的性质调整。
 * 4. 从前往后输出即为排序好的堆
 */
public class HeapSort<T extends Comparable<T>> implements Sort<T> {
    private final T[] values;

    private final boolean desc;

    public HeapSort(T[] values, boolean desc){
        this.values = values;
        this.desc = desc;


    }

    public void adjust(int index,int length) {
        int maxIndex = index;

        int leftChild = getLeftChild(index);

        int righChild = getRihtChild(index);


        if (leftChild< length && compare(maxIndex,leftChild)) {
            maxIndex = leftChild;
        }
        if (righChild< length && compare(maxIndex,righChild)) {
            maxIndex = righChild;
        }

        if (maxIndex != index) {
            swap(maxIndex, index);
            adjust(maxIndex, length);
        }
    }

    private boolean compare(int i, int j) {
        int result = values[i].compareTo(values[j]);
        return desc ? result > 0 : result < 0;
    }

    private void swap(int i, int j) {
        T tmp = values[i];
        values[i] = values[j];
        values[j] = tmp;

    }



    private int getLeftChild(int index) {
        return 2 * index + 1;
    }

    private int getRihtChild(int index) {
        return 2 * (index + 1);
    }

    public static void main(String[] args) {

        Integer[] integers = Stream.of(20, 10, 30, 40, 80, 70, 60, 50).toArray(Integer[]::new);
        HeapSort maxHeap = new HeapSort(integers,false);
        maxHeap.sort();
        Arrays.stream(maxHeap.getResults()).forEach(System.out::println);

    }


    @Override
    public void sort() {
        int length = values.length-1;
        for (int i=length/2-1;i>=0;i--) {
            adjust(i, length);
        }
        for (int i= length;i>=0;i--) {
            swap(0, i);
            adjust(0,i);
        }
    }

    @Override
    public T[] getResults() {
        return values;
    }
}
