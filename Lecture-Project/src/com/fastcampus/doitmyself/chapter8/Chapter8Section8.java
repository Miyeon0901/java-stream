package com.fastcampus.doitmyself.chapter8;

import com.fastcampus.doitmyself.chapter8.model.User;
import com.fastcampus.doitmyself.chapter8.service.EmailService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Chapter8Section8 {
    /**
     * 조건을 만족하는 그룹과 만족하지 않는 그룹, 2개로 나눔.
     * public static <T> Collector<T, ?, Map<Boolean, List<T>>> partitioningBy(
     *      Predicate< ? super T> predicate)
     *      - GroupingBy와 유사하지만 Funtion 대신 Predicate을 받아 true와 false
     *        두 key가 존재하는 map을 반환하는 collector
     * public static <T, D, A> Collector<T, ?, Map<Boolean, D>> partitioningBy(
     *      Predicate< ? super T> predicate,
     *      Collector< ? super T, A, D> downstream
     *      - 마찬가지로 downstream collector를 넘겨 List 이외의 형태로 map의 value를 만드는 것 역시 가능.
     */
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(13, 2, 101, 203, 304, 402, 305, 349, 2312, 203);
        Map<Boolean, List<Integer>> numberPartitions = numbers.stream()
                .collect(Collectors.partitioningBy(number -> number % 2 == 0)); // true: 짝수, false: 홀수
        System.out.println("Even number: " + numberPartitions.get(true));
        System.out.println("Odd number: " + numberPartitions.get(false));

        User user1 = new User()
                .setId(101)
                .setName("Alice")
                .setEmailAddress("alice@fastcampus.co.kr")
                .setFriendUserIds(Arrays.asList(201, 202, 203, 204, 211, 212, 213, 214));

        User user2 = new User()
                .setId(102)
                .setName("Bob")
                .setEmailAddress("bob@fastcampus.co.kr")
                .setFriendUserIds(Arrays.asList(204, 205, 206));

        User user3 = new User()
                .setId(103)
                .setName("Charlie")
                .setEmailAddress("charlie@fastcampus.co.kr")
                .setFriendUserIds(Arrays.asList(204, 205, 207, 218));

        List<User> users = Arrays.asList(user1, user2, user3);

        Map<Boolean, List<User>> userPartitions = users.stream()
                .collect(Collectors.partitioningBy(user -> user.getFriendUserIds().size() > 5));
        EmailService emailService = new EmailService();
        for (User user: userPartitions.get(true)) {
            emailService.sendPlayWithFriendsEmail(user);
        }
        for (User user: userPartitions.get(false)) {
            emailService.sendMakeMoreFriendsEmail(user);
        }
    }
}
