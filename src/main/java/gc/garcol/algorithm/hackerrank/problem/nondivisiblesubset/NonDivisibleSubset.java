package gc.garcol.algorithm.hackerrank.problem.nondivisiblesubset;

import java.text.MessageFormat;
import java.util.*;

/**
 * @author thai-van
 **/
public class NonDivisibleSubset {

    public static void main(String... args) {
//        List<Integer> set = Arrays.asList(19, 10, 12, 10, 24, 25, 22);
        List<Integer> set = Arrays.asList(1, 7, 2, 4);
        int k = 4;
        System.out.println(nonDivisibleSubset(k, set));
    }

    public static int nonDivisibleSubset(int k, List<Integer> set) {
        Map<Remainder, Size> remainders = new HashMap<>();
        set.forEach(number -> {
            final Remainder remainder = new Remainder(number % k);
            if (!remainders.containsKey(remainder)) {
                remainders.put(remainder, new Size(0));
            }
            remainders.get(remainder).increase();
        });
        return findMax(remainders, k);
    }

    static int findMax(Map<Remainder, Size> remainders, int k) {
        Set<Remainder> traversedRemainder = new HashSet<>();
        final Result result = new Result();
        remainders.keySet().forEach(remainder -> {
//            System.out.println(remainder);
            if (traversedRemainder.contains(remainder)) return;
            if (remainder.remainder * 2 == k && remainders.get(remainder).size > 0) {
                traversedRemainder.add(remainder);
                result.add(1);
                return;
            }

            if (remainder.remainder % k == 0 && remainders.get(remainder).size > 0) {
                traversedRemainder.add(remainder);
                result.add(1);
                return;
            }

//            System.out.println(MessageFormat.format("pass {0}",remainder.remainder));

            final Remainder oppositeRemainder = new Remainder(k - remainder.remainder);
            Size remainderSize = remainders.get(remainder);
            Size oppositeSize = remainders.getOrDefault(oppositeRemainder, EMPTY_SIZE);
            result.add(Integer.max(remainderSize.size, oppositeSize.size));

            traversedRemainder.add(remainder);
            traversedRemainder.add(oppositeRemainder);
        });
        return result.result;
    }

    static class Result {
        int result;
        void add(int value) {
//            System.out.println(MessageFormat.format("add {0}", value));
            this.result += value;
        }
    }

    static class Size {
        int size;

        Size(int size) {
            this.size = size;
        }

        void increase() {
            this.size++;
        }

    }
    final static Size EMPTY_SIZE = new Size(0);

    static class Remainder {
        int remainder;

        Remainder(int remainder) {
            this.remainder = remainder;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Remainder)) return false;
            Remainder that = (Remainder) o;
            return that.remainder == this.remainder;
        }

        @Override
        public int hashCode() {
            return remainder;
        }
    }
}
