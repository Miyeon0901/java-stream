package com.fastcampus.doitmyself.chapter3.util;

import java.util.function.Function;

public class Adder implements Function<Integer, Integer> {

    // Integet를 받아 Integer를 return 하는 Object 형태의 함수 Adder를 만듦.
     public Integer apply(Integer x) {
         return x + 10;
     }
}
