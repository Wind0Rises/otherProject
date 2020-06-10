package com.liu.theory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @desc
 * @author Liuweian
 * @version 1.0.0
 * @createTime 2020/6/5 13:59
 */
public class StreamTest {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        List<String> list = Arrays.asList("first", "second", "thirdly", "fourth");
        list.stream().filter(new CustomPredicate()).count();
        long count = list.stream().filter(item -> new CustomPredicate().test(item)).count();
        System.out.println(count);

        System.out.println("##################################################");
        List<String> functionTestList = Arrays.asList("1", "2", "3", "4", "0");
        long functionResult = functionTestList.stream().map(new CustomFunction<>()).filter(item -> {
            System.out.println("     ==> " + item);
            return true;
        }).count();
        System.out.println(" dd " + functionResult);

        System.out.println("##################################################");
        functionTestList.stream().count();

        System.out.println("##################################################");
        List<String> secondTest = Arrays.asList("1", "2", "3", "4", "0");
        long result = secondTest.stream().mapToInt(item -> {
            System.out.println(item.getClass());
            if (Integer.parseInt(item) % 2 == 0) {
                return 100;
            }
            return 1;
        }).sum();
        System.out.println(result);

        System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
        List<String> flatMap1 = Arrays.asList("1", "2", "3", "4", "0");
        List<String> flatMap2 = Arrays.asList("first", "second", "thirdly");
        List<List<String>> flatMapList = new ArrayList<>();
        flatMapList.add(flatMap1);
        flatMapList.add(flatMap2);

        flatMapList.stream().flatMap(item -> item.stream()).forEach(System.out::println);

        flatMapList.stream().flatMap(item -> {
            System.out.println("dddd +" + item);
            return item.stream();
        }).filter(item -> {
            System.out.println(" liuweina " + item);
            return true;
        });


        List<Integer> flatInt1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> flatInt2 = Arrays.asList(7, 8, 9, 10);
        List<List<Integer>> flatIntMap =  new ArrayList<>();
        flatIntMap.add(flatInt1);
        flatIntMap.add(flatInt2);
        System.out.println("LLLLLLLLLRRRRRLLLLLLLLL");
        flatIntMap.stream().flatMapToInt(item -> item.stream().mapToInt(li -> li)).
                forEach(System.out::println);
    }

    static class CustomPredicate implements Predicate {
        @Override
        public boolean test(Object o) {
            System.out.println(o + "----------");
            return false;
        }
    }

    static class CustomFunction<String> implements Function<String, Integer> {

        @Override
        public Integer apply(String string) {
            System.out.println(string);
            return 3;
        }
    }
}
