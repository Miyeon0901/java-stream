package com.fastcampus.doitmyself.chapter6;

import com.fastcampus.doitmyself.chapter6.model.Order;
import com.fastcampus.doitmyself.chapter6.model.Order.OrderStatus;
import com.fastcampus.doitmyself.chapter6.model.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Chapter6Section4 {
    public static void main(String[] args) {
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

        // How to do(명령형 프로그래밍/Imperative Programming)
        List<String> emails = new ArrayList<>();
        for (User user: users) {
            if (!user.isVerified()) {
                String email = user.getEmailAddress();
                emails.add(email);
            }
        }
        System.out.println(emails);

        // What to do(선언형 프로그래밍/Declarativ Programming)
        List<String> unVerifiedUserEmailList = users.stream()
                .filter(user -> !user.isVerified())
                .map(User::getEmailAddress)
                .collect(Collectors.toList());

        System.out.println(unVerifiedUserEmailList);

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        Order order1 = new Order()
                .setId(1001)
                .setStauts(OrderStatus.CREATED)
                .setCreatedByUserId(101)
                .setCreateAt(now.minusHours(4));

        Order order2 = new Order()
                .setId(1002)
                .setStauts(OrderStatus.ERROR)
                .setCreatedByUserId(103)
                .setCreateAt(now.minusHours(1));

        Order order3 = new Order()
                .setId(1003)
                .setStauts(OrderStatus.PROCESSED)
                .setCreatedByUserId(102)
                .setCreateAt(now.minusHours(36));

        Order order4 = new Order()
                .setId(1004)
                .setStauts(OrderStatus.ERROR)
                .setCreatedByUserId(104)
                .setCreateAt(now.minusHours(25));

        Order order5 = new Order()
                .setId(1005)
                .setStauts(OrderStatus.IN_PROGRESS)
                .setCreatedByUserId(101)
                .setCreateAt(now.minusHours(10));

        List<Order> orderList = Arrays.asList(order1, order2, order3, order4, order5);

        // TODO: Find orders in ERROR status, and extract createdByUserIds as a list
        List<Long> errorOrderUserIds = orderList.stream()
                .filter(order -> order.getStauts() == OrderStatus.ERROR)
                .map(Order::getCreatedByUserId)
                .collect(Collectors.toList());
        System.out.println(errorOrderUserIds);

        // TODO: Find orders in ERROR status and created within 24 hours
        List<Order> ordersInErrorStatus24hrs = orderList.stream()
                .filter(order -> order.getStauts() == OrderStatus.ERROR)
                .filter(order -> order.getCreateAt().isAfter(now.minusDays(1)))
                .collect(Collectors.toList());
        for (Order order : ordersInErrorStatus24hrs) {
            System.out.println(order.toString());
        }

    }
}
