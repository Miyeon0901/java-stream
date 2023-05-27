package com.fastcampus.doitmyself.chapter8;

import com.fastcampus.doitmyself.chapter8.model.Order;
import com.fastcampus.doitmyself.chapter8.model.User;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Chapter8Section2 {

    /**
     * boolean allMatch(Predicate< ? super T> predicate;
     *      - Stream 안의 모든 데이터가 predicate을 만족하면 true(and)
     * boolean anyMatch(Predicate< ? super T> predicate;
     *      - Stream 안의 데이터 중 하나라도 predicate을 만족하면 true(or)
     */

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, -4, 2, 7, 9);

        boolean allPositive = numbers.stream()
                .allMatch(number -> number > 0);
        System.out.println("Are all numbers positive: " + allPositive);

        boolean anyNegative = numbers.stream()
                .anyMatch(number -> number < 0);
        System.out.println("Is any number negative: " + anyNegative);

        User user1 = new User()
                .setId(101)
                .setName("Alice")
                .setVerified(true)
                .setEmailAddress("alice@fastcampus.co.kr");

        User user2 = new User()
                .setId(102)
                .setName("Bob")
                .setVerified(true)
                .setEmailAddress("bob@fastcampus.co.kr");

        User user3 = new User()
                .setId(103)
                .setName("Charlie")
                .setVerified(true)
                .setEmailAddress("charlie@fastcampus.co.kr");

        List<User> users = Arrays.asList(user1, user2, user3);

        boolean areAllUsersVerified = users.stream()
                .allMatch(User::isVerified);
        System.out.println(areAllUsersVerified);

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

        // TODO: check if any of orders in ERROR status
        boolean isAnyOrderInErrorStatus = orders.stream()
                .anyMatch(order -> order.getStauts().equals(Order.OrderStatus.ERROR));
        System.out.println(isAnyOrderInErrorStatus);
    }
}
