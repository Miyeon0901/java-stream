package com.fastcampus.doitmyself.chapter8;

import com.fastcampus.doitmyself.chapter6.model.User;
import com.fastcampus.doitmyself.chapter8.model.Order;
import com.fastcampus.doitmyself.chapter8.model.Order.OrderStatus;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter8Section6 {
    /**
     * public static <T, K, U> Collector<T, ?, Map<K,U>> toMap(
     *      Function< ? super T, ? extends K> keyMapper,
     *      Function< ? super T, ? extends U> valueMapper)
     *  - Stream 안의 데이터를 map 형태로 반환해주는 collector
     *  - keyMapper - 데이터를 map의 key로 변환하는 Function
     *  - valueMapper - 데이터를 map의 value로 변환하는 Function
     */

    public static void main(String[] args) {
        Map<Integer, String> numberMap = Stream.of(3, 5, -4, 2, 6)
                .collect(Collectors.toMap(Function.identity(), //x -> x, // keyMapper
                        x -> "Number is " + x));// valueMapper
        System.out.println(numberMap.get(3));

        User user1 = new User()
                .setId(101)
                .setName("Alice")
                .setVerified(true)
                .setEmailAddress("alice@fastcampus.co.kr");

        User user2 = new User()
                .setId(102)
                .setName("Bob")
                .setVerified(false)
                .setEmailAddress("bob@fastcampus.co.kr");

        User user3 = new User()
                .setId(103)
                .setName("Charlie")
                .setVerified(true)
                .setEmailAddress("charlie@fastcampus.co.kr");

        List<User> users = Arrays.asList(user1, user2, user3);

        Map<Integer, User> userIdToUserMap = users.stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));
        System.out.println(userIdToUserMap.get(103));

        Order order1 = new Order()
                .setId(1001)
                .setAmount(BigDecimal.valueOf(2000))
                .setStauts(Order.OrderStatus.CREATED);

        Order order2 = new Order()
                .setId(1002)
                .setAmount(BigDecimal.valueOf(4000))
                .setStauts(Order.OrderStatus.ERROR);

        Order order3 = new Order()
                .setId(1003)
                .setAmount(BigDecimal.valueOf(3000))
                .setStauts(Order.OrderStatus.CREATED);

        Order order4 = new Order()
                .setId(1004)
                .setAmount(BigDecimal.valueOf(7000))
                .setStauts(Order.OrderStatus.PROCESSED);

        List<Order> orders = Arrays.asList(order1, order2, order3, order4);

        // TODO: create a map from order id to order status
        Map<Long, OrderStatus> orderIdToOrderStatusMap = orders.stream()
                .collect(Collectors.toMap(Order::getId, Order::getStauts));
        System.out.println(orderIdToOrderStatusMap.get(1003L));

    }
}
