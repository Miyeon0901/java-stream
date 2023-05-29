package com.fastcampus.doitmyself.chapter9;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Chapter9Section1 {
    /**
     * - Scope(스코프/유효범위) - 변수에 접근할 수 있는 범위
     * - 함수 안에 함수가 있을 때 내부 함수에서 외부 함수에 있는 변수에 접근이 가능하다(lexical scope).
     *   그 반대는 불가능하다.
     * - Closure - 내부 함수가 존재하는 한 내부 함수가 사용한 외부 함수의 변수들 역시 계속 존재한다.
     *   이렇게 lexical scope를 포함하는 함수를 closure라 한다.
     * - 이 때 내부 함수가 사용한 외부 함수의 변수들은 내부 함수 선언 당시로부터 변할 수 없기 때문에 final로 선언되지 않더라도
     *   암묵적으로 final로 취급된다.
     */
    public static Supplier<String> getStringSupplier() { // String을 return하는 supplier를 return
        String hello = "Hello"; // lexical scope, final 취급, 변수 값 변경 시도시 compile error
        // 여기서는 world 변수에 접근 불가.
        Supplier<String> supplier = () -> {
            String world = "World";
            return hello + world;
        };

        return supplier;
    }

    /**
     * Curry
     * - 여러 개의 매개변수를 받는 함수를 중첩된 여러 개의 함수로 쪼개어 매개 변수를 한 번에 받지 않고 여러 단계에 걸쳐
     *   나눠 받을 수 있게 하는 기술
     * - Closure의 응용
     *   BiFunction<Integer, Integer, Integer> add = (x, y) -> x + y;
     *   => Function<Integer, Function<Integer, Integer>> add = x -> y -> x + y;
     */

    public static void main(String[] args) {
        Supplier<String> supplier = getStringSupplier();
        System.out.println(supplier.get());

        BiFunction<Integer, Integer, Integer> add = (x, y) -> x + y;
        Function<Integer, Function<Integer, Integer>> curriedAdd = x -> (y -> x + y);

        Function<Integer, Integer> addThree = curriedAdd.apply(3);
        int result = addThree.apply(10);
        System.out.println(result); // 10 -> (3 -> 10 + 3)
    }
}
