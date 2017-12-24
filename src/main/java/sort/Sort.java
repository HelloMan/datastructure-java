package sort;

/**
 * Created by chaojun on 17/12/24.
 */
public interface Sort<T extends Comparable<T>> {


    void sort();


    T[] getResults();

}
