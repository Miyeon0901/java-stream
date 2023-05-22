package com.fastcampus.doitmyself.chapter3;

import java.util.function.BiFunction;

public class Chapter3Section3 {

    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> add1 = (Integer x, Integer y) -> {
            return x + y;
        };

        BiFunction<Integer, Integer, Integer> add2 = (x, y) -> x + y;
        int result = add2.apply(3, 5);
        System.out.println(result );
    }

}
