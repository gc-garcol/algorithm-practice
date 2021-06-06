package gc.garcol.algorithm.hackerrank.problem.nondivisiblesubset;

import java.util.*;

/**
 * @author thai-van
 **/
public class SubmitNonDivisibleSubset {
    public static void main(String... args) {
//        List<Integer> set = Arrays.asList(19, 10, 12, 10, 24, 25, 22);
//        List<Integer> set = Arrays.asList(1, 7, 2, 4);
        List<Integer> set = Arrays.asList(278, 576, 496, 727, 410, 124, 338, 149, 209, 702, 282, 718, 771, 575, 436);
        int k = 7;
        System.out.println(nonDivisibleSubset(k, set));
    }

    public static int nonDivisibleSubset(int k, List<Integer> set) {
        Map<Integer, Integer> remainders = new HashMap<>();
        set.forEach(number -> {
            final Integer remainder = number % k;
            if (!remainders.containsKey(remainder)) {
                remainders.put(remainder, 0);
            }
            remainders.put(remainder, remainders.get(remainder) + 1);
        });
        return findMax(remainders, k);
    }

    static int findMax(Map<Integer, Integer> remainders, int k) {
        Set<Integer> traversedRemainder = new HashSet<>();
        final Map<Integer, Integer> result = new HashMap<>();
        result.put(0, 0);
        remainders.keySet().forEach(remainder -> {
            if (traversedRemainder.contains(remainder)) return;
            if (remainder * 2 == k && remainders.get(remainder) > 0) {
                traversedRemainder.add(remainder);
                result.put(0, result.get(0) + 1);
                return;
            }

            if (remainder % k == 0 && remainders.get(remainder) > 0) {
                traversedRemainder.add(remainder);
                result.put(0, result.get(0) + 1);
                return;
            }

//            System.out.println(MessageFormat.format("pass {0}",remainder.remainder));

            final Integer oppositeRemainder = k - remainder;
            Integer remainderSize = remainders.get(remainder);
            Integer oppositeSize = remainders.getOrDefault(oppositeRemainder, 0);
            result.put(0, result.get(0) + Integer.max(remainderSize, oppositeSize));

            traversedRemainder.add(remainder);
            traversedRemainder.add(oppositeRemainder);
        });
        return result.get(0);
    }
}
