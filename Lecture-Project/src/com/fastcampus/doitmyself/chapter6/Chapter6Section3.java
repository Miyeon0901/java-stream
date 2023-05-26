package com.fastcampus.doitmyself.chapter6;

import com.fastcampus.doitmyself.chapter6.model.Order;
import com.fastcampus.doitmyself.chapter6.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter6Section3 {
    public static void main(String[] args) {
        // Map: 스트림 안의 데이터에 어떠한 처리를 해서 다른 형태로 변형
        List<Integer> numberList = Arrays.asList(3, 6, -4);
//        Stream<Integer> numberStream = numberList.stream();
//        Stream<Integer> numberStreamX2 = numberStream.map(x -> x * 2);
//        List<Integer> numberListX2 = numberStreamX2.collect(Collectors.toList());
        List<Integer> numberListX2 = numberList.stream()
                .map(x -> x * 2)
                .collect(Collectors.toList());
        System.out.println(numberListX2);

        Stream<Integer> numberListStream = numberList.stream();
        Stream<String> strStream = numberListStream.map(x -> "Number is " + x); // input은 Integer, return은 String
        List<String> strList = strStream.collect(Collectors.toList());
        System.out.println(strList);

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
        List<String> emailList = users.stream()
                .map(User::getEmailAddress) // .map(user -> user.getEmailAddress())
                .collect(Collectors.toList());
        System.out.println(emailList);

        Order order1 = new Order()
                .setId(1001)
                .setStauts(Order.OrderStatus.CREATED)
                .setCreatedByUserId(101);

        Order order2 = new Order()
                .setId(1002)
                .setStauts(Order.OrderStatus.ERROR)
                .setCreatedByUserId(103);

        Order order3 = new Order()
                .setId(1003)
                .setStauts(Order.OrderStatus.PROCESSED)
                .setCreatedByUserId(102);

        Order order4 = new Order()
                .setId(1004)
                .setStauts(Order.OrderStatus.ERROR)
                .setCreatedByUserId(104);

        Order order5 = new Order()
                .setId(1005)
                .setStauts(Order.OrderStatus.IN_PROGRESS)
                .setCreatedByUserId(101);

        List<Order> orderList = Arrays.asList(order1, order2, order3, order4, order5);

        // TODO: createdByUserId List 만들기
        List<Long> createdByUserIdList = orderList.stream()
                .map(Order::getCreatedByUserId)
                .collect(Collectors.toList());
        System.out.println(createdByUserIdList);
    }
}
