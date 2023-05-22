package com.fastcampus.doitmyself.chapter3;

import java.util.function.Function;

public class Chapter3Section2 {

    public static void main(String[] args) {
        Function<Integer, Integer> myAdder1 = (Integer x) -> { // BinaryOperator<Integer>, IntBinaryOperator
            return x + 10;
        }; // Lambda Expression 사용. anonymous function.

        Function<Integer, Integer> myAdder2 = x -> x + 10; // Lambda Expression 을 보다 간결하게.

        int result = myAdder2.apply(5);
        System.out.println(result);
    }
}
