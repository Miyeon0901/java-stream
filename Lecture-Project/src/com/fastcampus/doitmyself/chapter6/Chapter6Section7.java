package com.fastcampus.doitmyself.chapter6;

import com.fastcampus.doitmyself.chapter6.model.Order;
import com.fastcampus.doitmyself.chapter6.model.OrderLine;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter6Section7 {

    public static void main(String[] args) {
        // FlatMap: Map처럼 데이터를 다른 형태로 변환해 줌과 동시에 중첩된 stream을 납작하게 만들어줌.
        String[][] cities = new String[][] {
                {"Seoul", "Busan"},
                {"San Francisco", "New York"},
                {"Madrid", "Barcelona"}
        };
        Stream<String[]> cityStream = Arrays.stream(cities);
        Stream<Stream<String>> cityStreamStream = cityStream.map(x -> Arrays.stream(x));
        List<Stream<String>> cityStreamList = cityStreamStream.collect(Collectors.toList());

        Stream<String[]> cityStream2 = Arrays.stream(cities); // cityStream은 이미 데이터를 다 흘려보내서 다시 쓸 수 없음.
        Stream<String> flattenedCityStream = cityStream2.flatMap(x -> Arrays.stream(x));
        List<String> flattenedCityList = flattenedCityStream.collect(Collectors.toList());
        System.out.println(flattenedCityList);

        Order order1 = new Order()
                .setId(1001)
                .setOrderLines(Arrays.asList(
                        new OrderLine()
                                .setId(10001)
                                .setType(OrderLine.OrderLineType.PURCHASE)
                                .setAmount(BigDecimal.valueOf(5000)),
                        new OrderLine()
                                .setId(10002)
                                .setType(OrderLine.OrderLineType.PURCHASE)
                                .setAmount(BigDecimal.valueOf(4000))
                ));

        Order order2 = new Order()
                .setId(1002)
                .setOrderLines(Arrays.asList(
                        new OrderLine()
                                .setId(10003)
                                .setType(OrderLine.OrderLineType.PURCHASE)
                                .setAmount(BigDecimal.valueOf(2000)),
                        new OrderLine()
                                .setId(10004)
                                .setType(OrderLine.OrderLineType.DISCOUNT)
                                .setAmount(BigDecimal.valueOf(-1000))
                ));

        Order order3 = new Order()
                .setId(1003)
                .setOrderLines(Arrays.asList(
                        new OrderLine()
                                .setId(10005)
                                .setType(OrderLine.OrderLineType.PURCHASE)
                                .setAmount(BigDecimal.valueOf(2000))
                ));

        List<Order> orders = Arrays.asList(order1, order2, order3);
        List<OrderLine> mergedOrderLines = orders.stream()      // Stream<Order>
                .map(Order::getOrderLines)                      // Stream<List<OrderLine>>
//                .map(List::stream)                              // Stream<Stream<OrderLine>>
                .flatMap(List::stream)                          // Stream<OrderLine>
                .collect(Collectors.toList());
        System.out.println(mergedOrderLines);

        // 내 코드
        Stream<Order> orderStream = orders.stream();
        Stream<OrderLine> orderLineStream = orderStream.flatMap(x -> x.getOrderLines().stream());
        System.out.println(orderLineStream.collect(Collectors.toList()));
    }
}

