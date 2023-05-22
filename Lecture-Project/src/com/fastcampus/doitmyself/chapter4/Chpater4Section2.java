package com.fastcampus.doitmyself.chapter4;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Chpater4Section2 {
    public static void main(String[] args) {
        Consumer<String> myStringConsumer = (String str) -> {
            System.out.println(str);
        };
//        myStringConsumer.accept("hello world!");
//        myStringConsumer.accept("miyeon");

        List<Integer> integerInputs = Arrays.asList(4, 2, 3); // Arrays.asList immutable.

        Consumer<Integer> myIntegerProcessor = x ->
                System.out.println("Processing Integer " + x);
//        process(integerInputs, myIntegerProcessor);

        Consumer<Integer> myDifferentIntegerProcessor = x ->
                System.out.println("Processing Integer in different way " + x);
//        process(integerInputs, myDifferentIntegerProcessor);
        processGeneric(integerInputs, myDifferentIntegerProcessor);

        List<Double> doubleInputs = Arrays.asList(1.1, 2.2, 3.3);
        Consumer<Double> myDoubleProcessor = x ->
                System.out.println("Processing Double " + x);
        processGeneric(doubleInputs, myDoubleProcessor);
    }

    public static void process(List<Integer> inputs, Consumer<Integer> processor) {
        for (Integer input : inputs) {
            processor.accept(input);
        }
    }

    public static <T> void processGeneric(List<T> inputs, Consumer<T> processor) {
        for (T input : inputs) {
            processor.accept(input);
        }
    } // Generic Type 까지 이용하면 정말 유연한 프로그래밍이 가능.
}
