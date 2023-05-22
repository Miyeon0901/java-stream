package com.fastcampus.doitmyself.chapter3;

import com.fastcampus.doitmyself.chapter3.util.TriFunction;

public class Chapter3Section4 {

    public static void main(String[] args) {
        TriFunction<Integer, Integer,Integer, Integer> addThreeNumbers1 =
                (Integer x, Integer y, Integer z) -> {
                    return x + y + z;
                };

        TriFunction<Integer, Integer, Integer, Integer> addThreeNumbers2 = (x, y, z) -> x + y + z;
        int result = addThreeNumbers2.apply(3, 2, 5);
        System.out.println(result);
    }
}
