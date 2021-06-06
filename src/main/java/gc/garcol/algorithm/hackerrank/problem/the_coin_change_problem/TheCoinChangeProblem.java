package gc.garcol.algorithm.hackerrank.problem.the_coin_change_problem;

import java.util.Arrays;
import java.util.List;

/**
 * @author thai-van
 **/
public class TheCoinChangeProblem {

    public static void main(String... args) {
        List<Long> sample = Arrays.asList(2L, 5L, 3L, 6L);
        System.out.println(getWays(10, sample));
    }

    public static long getWays(int n, List<Long> c) {
        long[][] memoization = new long[c.size()][n + 1];
        return count(c.size() - 1, n, c, memoization);
    }

    // count(index, max) = count(index, max - c[index]) // keep coin, change max
    //                   + count(index - 1, max) // change coin, keep max
    public static long count(int index, int max, List<Long> c, long[][] memoization) {
        if (max == 0) return 1;

        if (index < 0) return 0;

        if (max < 0) return 0;

        if (memoization[index][max] > 0) return memoization[index][max];

        memoization[index][max] = count(index, (int) ((long) max - c.get(index)), c, memoization) + count(index - 1, max, c, memoization);
        return memoization[index][max];
    }
}
