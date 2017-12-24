package sort;

import java.util.stream.Stream;

/**
 * 排序算法
 */
public interface Sort<T extends Comparable<T>> {

    /**
     * 排序
     */
    void sort();


    /**
     * 排序结果流
     * @return
     */
    Stream<T> getResults();

}
