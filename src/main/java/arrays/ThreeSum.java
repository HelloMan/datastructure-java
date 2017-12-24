package arrays;

import org.jooq.lambda.Seq;
import org.jooq.lambda.tuple.Tuple;
import org.jooq.lambda.tuple.Tuple3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.jooq.lambda.tuple.Tuple.tuple;

/**
 * Given an array S of n integers, are there three elements in S that they add up to a specific target
 * Find all unique triplets in th array which gives the sum of target
 *
 * <p>
 * Time Complexity:O(N^2)
 * All elements in the list are processed for every index
 * </p>
 * Difficulty: Medium
 */
public class ThreeSum {

    public List<Tuple3<Integer, Integer, Integer>> threeSum(int[] values, int target) {
        Arrays.sort(values);
        List<Tuple3<Integer, Integer, Integer>> res = new ArrayList<>();
        for (int a=0;a<values.length-2;a++) {
            if (a == 0 || (a > 1 && values[a] != values[a - 1])) {
                int lo = a +1;
                int hi = values.length-1;
                int sum = target - values[a];
                while (lo < hi) {
                    int twoSum = values[lo] + values[hi];
                    if (twoSum == sum) {
                        res.add(Tuple.tuple(values[a], values[lo], values[hi]));
                        while (lo < hi && values[lo] == values[lo + 1]) {
                            lo++;
                        }
                        while (lo < hi && values[hi] == values[hi - 1]) {
                            hi--;
                        }
                        lo++;
                        hi--;
                    }else if (twoSum > sum){
                        hi--;

                    }else{
                        lo++;
                    }
                }
            }
        }

        return res;
    }
}
