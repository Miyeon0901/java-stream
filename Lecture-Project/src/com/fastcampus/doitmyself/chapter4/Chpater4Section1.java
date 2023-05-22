package com.fastcampus.doitmyself.chapter4;

import java.util.function.Supplier;

public class Chpater4Section1 {
    public static void main(String[] args) {
        Supplier<String> myStringSupplier = () -> "hello world!";
//        System.out.println(myStringSupplier.get());

        Supplier<Double> myRandomDoubleSupplier = () -> Math.random();
        printRandomDoubles(myRandomDoubleSupplier, 5);

    }

    // random number를 만드는 방식에 전혀 관여하지 않음.
    public static void printRandomDoubles(Supplier<Double> randomSupplier, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(randomSupplier.get());
        }
    }
}
