package datastructure.array;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class TwoSumTest {

    @Test
    public void testTwosum() {
        assertThat(TwoSum.twoSum(new int[]{1, 2, 3, 4}, 5).length).isEqualTo(2);
        assertThat(TwoSum.twoSum(new int[]{1, 2, 3, 4}, 7).length).isEqualTo(2);
    }


    @Test
    public void testTwosum2() {
        assertThat(TwoSum.twoSum2(new int[]{1, 2, 3, 4}, 5).size()).isEqualTo(2);
        assertThat(TwoSum.twoSum2(new int[]{1, 2, 3, 4, 5}, 6).size()).isEqualTo(2);
        System.out.println("values add up to 5 are found as below:");
        TwoSum.twoSum2(new int[]{1, 2, 3, 4}, 5).forEach(System.out::println);
        System.out.println("values add up to 10 are found as below:");
        TwoSum.twoSum2(new int[]{1, 2, 3, 4, 5, 6 ,7, 8, 9}, 10).forEach(System.out::println);
    }
}
