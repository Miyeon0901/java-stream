package com.fastcampus.doitmyself.chapter6;

import com.fastcampus.doitmyself.chapter6.model.Order;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Chapter6Section6 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, -5, 4, -5, 2, 3);
        List<Integer> distinctNumbers = numbers.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println(distinctNumbers);

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        Order order1 = new Order()
                .setId(1001)
                .setStauts(Order.OrderStatus.CREATED)
                .setCreatedByUserId(101)
                .setCreateAt(now.minusHours(4));

        Order order2 = new Order()
                .setId(1002)
                .setStauts(Order.OrderStatus.ERROR)
                .setCreatedByUserId(103)
                .setCreateAt(now.minusHours(1));

        Order order3 = new Order()
                .setId(1003)
                .setStauts(Order.OrderStatus.PROCESSED)
                .setCreatedByUserId(102)
                .setCreateAt(now.minusHours(36));

        Order order4 = new Order()
                .setId(1004)
                .setStauts(Order.OrderStatus.ERROR)
                .setCreatedByUserId(104)
                .setCreateAt(now.minusHours(25));

        Order order5 = new Order()
                .setId(1005)
                .setStauts(Order.OrderStatus.IN_PROGRESS)
                .setCreatedByUserId(101)
                .setCreateAt(now.minusHours(10));

        List<Order> orderList = Arrays.asList(order1, order2, order3, order4, order5);

        // TODO: create a sorted list of unique CreatedByUserIds from the orders
        List<Long> uniqueCreatedByUserIds = orderList.stream()
                .map(Order::getCreatedByUserId)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(uniqueCreatedByUserIds);

    }
}
