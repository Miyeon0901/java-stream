package com.fastcampus.doitmyself.chapter9;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter9Section2 {
    /**
     * Lazy Evaluation
     * - Lambda의 계산은 그 결과값이 필요할 때가 되어서야 계산된다.
     * - 이를 이용하여 불필요한 계산을 줄이거나 해당 코드의 실행 순서를 의도적으로 미룰 수 있다.
     */
    public static void main(String[] args) {
//        if (returnTrue() || returnFalse()) { // 뒤 조건은 보지도 않음. "returning false" 찍히지 않음.
//            System.out.println("true");
//        }

        if (or(returnTrue(), returnFalse())) { // 최적화가 되지 않음. 메서드 호출 전에 값이 모두 계산되기 때문.
            System.out.println("true");
        }

        if (lazyOr(() -> returnTrue(), () -> returnFalse())) { // Supplier를 거치면 lazy evaluation 이 적용됨.
            System.out.println("true");
        }

        Stream<Integer> integerStream = Stream.of(3, -2, 5, 8, -3, 10)
                .filter(x -> x > 0)
                .peek(x -> System.out.println("peeking " + x)) // consumer를 받아서 실행. 잠깐 힐끗 보는 용도.
                .filter(x -> x % 2 == 0);
        System.out.println("Before collect");

        List<Integer> integers = integerStream.collect(Collectors.toList());
        System.out.println("After collect: " + integers);

        // Before collect -> peeking -> After collect 순으로 출력됨.
        // Stream은 종결처리(collect)가 이루어지기 전까지 모든 계산을 미루기 때문.
    }

    public static boolean or(boolean x, boolean y) {
        return x || y;
    }

    public static boolean lazyOr(Supplier<Boolean> x, Supplier<Boolean> y) {
        return x.get() || y.get(); // get을 할때 계산이 이루어짐.
    }

    public static boolean returnTrue() {
        System.out.println("returning true");
        return true;
    }

    public static boolean returnFalse() {
        System.out.println("returning false");
        return false;
    }
}
