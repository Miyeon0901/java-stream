package com.fastcampus.doitmyself.chapter9;

import com.fastcampus.doitmyself.chapter9.model.Order;
import com.fastcampus.doitmyself.chapter9.model.OrderLine;
import com.fastcampus.doitmyself.chapter9.priceprocessor.OrderLineAggregationPriceProcessor;
import com.fastcampus.doitmyself.chapter9.priceprocessor.TaxPriceProcessor;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Chapter9Section3 {
    /**
     * Funtion Composition(합성 함수)
     * - 여러 개의 함수를 함쳐 하나의 함수로 만드는 것
     * - f(x) = 2 * x, g(x) = x + 10 => g(f(x)) = 2 * x + 10
     *
     * java.util.funtion.Funtion
     * <V> Funtion<V, R> compose(Function< ? super V, ? extends T> before)
     *      - 파라미터로 들어온 함수를 먼저 실행 후 자기 자신을 실행, 써있는 순서와 반대로 실행
     * <V> Funtion<T, V> andThen(Funtion< ? super R, ? extends V> after)
     *      - 자신을 먼저 실행 후 파라미터로 들어온 함수를 실행. 써있는 순서대로 실행. 더 선호함.
     */
    public static void main(String[] args) {
        Function<Integer, Integer> multiplyByTwo = x -> 2 * x;
        Function<Integer, Integer> addTen = x -> x + 10;

        Function<Integer, Integer> composedFunction = multiplyByTwo.andThen(addTen); // x -> 2 * x + 10
        System.out.println(composedFunction.apply(3));

        Order unprocessedOrder = new Order()
                .setId(1001L)
                .setOrderLines(Arrays.asList(
                        new OrderLine().setAmount(BigDecimal.valueOf(1000)),
                        new OrderLine().setAmount(BigDecimal.valueOf(2000))));

        List<Function<Order, Order>> priceProcessors = getPriceProcessors(unprocessedOrder);

        Function<Order, Order> mergedPriceProcessor = priceProcessors.stream()
                .reduce(Function.identity(), Function::andThen);
//                .reduce(Function.identity(), (priceProcessor1, priceProcessor2) -> priceProcessor1.andThen(priceProcessor2));

        Order processedOrder = mergedPriceProcessor.apply(unprocessedOrder);
        System.out.println(processedOrder);

    }

    public static List<Function<Order, Order>> getPriceProcessors(Order order) {
        return Arrays.asList(new OrderLineAggregationPriceProcessor(),
                new TaxPriceProcessor(new BigDecimal("9.375")));
    }
}
