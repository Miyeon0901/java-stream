package com.fastcampus.doitmyself.chapter8;

import com.fastcampus.doitmyself.chapter8.model.User;
import com.fastcampus.doitmyself.chapter8.service.EmailService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Chapter8Section9 {
    /**
     * void forEach(Consumer< ? super T> action);
     *      - 제공된 action을 Stream의 각 데이터에 적용해주는 종결 처리 메서드
     *      - Java의 iterable 인터페이스에도 forEach가 있기 때문에 Stream의 중간 처리가 필요 없다면
     *        iterable collection(Set, List 등)에서 바로 쓰는 것도 가능.
     */
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 5, 2, 1);
//        numbers.stream().forEach(number -> System.out.println("The number is " + number));
        numbers.forEach(number -> System.out.println("The number is " + number)); // stream으로 안 바꿔도 사용 가능.

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
                .setVerified(false)
                .setEmailAddress("charlie@fastcampus.co.kr");

        List<User> users = Arrays.asList(user1, user2, user3);

        EmailService emailService = new EmailService();
        users.stream()
                .filter(user -> !user.isVerified())
                .forEach(emailService::sendVerifyYourEmailEmail);
//                .forEach(user -> emailService.sendVerifyYourEmailEmail(user));

        for (User user: users) { // forEach로 쉽게 대체 가능
            System.out.println("Do an operation on user " + user.getName());
        }

        for (int i = 0 ; i < users.size(); i++) { // IntStream을 사용하면 forEach 사용 가능.
            User user = users.get(i);
            System.out.println("Do an operation on user " + user.getName() + " at index " + i);
        }

        // 시작점 '이상', 끝점 '미만'
        IntStream.range(0, users.size()).forEach(i -> {
            User user = users.get(i);
            System.out.println("Do an operation on user " + user.getName() + " at index " + i);
        });

    }
}
