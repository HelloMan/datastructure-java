package arrays;

import org.jooq.lambda.tuple.Tuple2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target .
 * <p></p>
 * <p>You may assume that each input would have exactly one solution, and you may not use the same element twice.</p>
 *
 * <p>
 *   Example:
 *   Given nums = [2,7,11,15], target = 9,
 *   Because nums[0]+ nums[1] = 2+7 + 9 , return [0,1]
 * </p>
 */
public class TwoSum {

    public static int[] twoSum(int[] values, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> targetMap = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            Integer foundValue = targetMap.get(target - values[i]);
            if (foundValue != null) {
                result[0] = foundValue;
                result[1] = i;
                break;
            }else {
                targetMap.put(values[i], i);
            }
        }
        return result;
    }

    public static List<Tuple2<Integer,Integer>> twoSum2(int[] values, int target) {
        List<Tuple2<Integer,Integer>> result = new ArrayList<>();
        Map<Integer, Integer> targetMap = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            Integer foundValue = targetMap.get(target - values[i]);
            if (foundValue != null) {
                Tuple2<Integer, Integer> tuple2 = new Tuple2<>(values[foundValue], values[i]);
                result.add(tuple2);
            } else {
                targetMap.put(values[i], i);
            }
        }
        return result;
    }

}
