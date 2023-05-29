package com.fastcampus.doitmyself.chapter8;

import com.fastcampus.doitmyself.chapter8.model.User;
import com.fastcampus.doitmyself.chapter8.service.EmailService;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Chapter8Section10 {
    /**
     * List<Integer> numbers = Arrays.asList(1, 2, 3);
     * Stream<Integer> parallelStream = numbers.parallelStream();
     * Stream<Integer> parallelStream2 = number.stream().parallel();
     *      - Sequential vs. Parallel
     *      - 여러개의 스레드를 이용하여 stream의 처리 과정을 병렬화(parallelize)
     *      - 중간 과정은 병렬 처리 되지만 순서가 있는 Stream의 경우 종결 처리 했을 때의 결과물이 기존의 순차적 처리와
     *        일치하도록 종결 처리과정에서 조정된다. 즉 List로 collect한다면 순서가 항상 올바르게 나온다는 것.
     *      - 장점:
     *          굉장히 간단하게 병렬 처리를 사용할 수 있게 해준다.
     *          속도가 비약적으로 빨라질 수 있다.
     *      - 단점:
     *          항상 속도가 빨라지는 것은 아니다.
     *          공통으로 사용하는 리소스가 있을 경우 잘못된 결과가 나오거나 아예 오류가 날 수도 있다. (deadlock)
     *          이를 막기 위해 mutex, semaphore 등 병렬 처리 기술을 이용하면 순차 처리보다 느려질 수도 있다.
     */

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
                .setVerified(false)
                .setEmailAddress("charlie@fastcampus.co.kr");

        User user4 = new User()
                .setId(103)
                .setName("David")
                .setVerified(false)
                .setEmailAddress("david@fastcampus.co.kr");

        User user5 = new User()
                .setId(102)
                .setName("Eve")
                .setVerified(false)
                .setEmailAddress("eve@fastcampus.co.kr");

        User user6 = new User()
                .setId(103)
                .setName("Frank")
                .setVerified(false)
                .setEmailAddress("frank@fastcampus.co.kr");

        List<User> users = Arrays.asList(user1, user2, user3, user4, user5);

        // Sequential
        long startTime = System.currentTimeMillis();
        EmailService emailService = new EmailService();
        users.stream()
                .filter(user -> !user.isVerified())
                .forEach(emailService::sendVerifyYourEmailEmail);
        long endTime = System.currentTimeMillis();
        System.out.println("Sequential: " + (endTime - startTime) + "ms");

        // Parallel: 순서가 망가짐.
        startTime = System.currentTimeMillis();
        users.stream().parallel()
                .filter(user -> !user.isVerified())
                .forEach(emailService::sendVerifyYourEmailEmail);
        endTime = System.currentTimeMillis();
        System.out.println("Parallel: " + (endTime - startTime) + "ms");

        // 중간 과정 순서는 뒤죽박죽. 결과는 순서가 맞게 나옴.
        List<User> processUsers = users.parallelStream()
                .map(user -> {
                    System.out.println("Capitalize user name for user " + user.getId());
                    user.setName(user.getName().toUpperCase());
                    return user;
                })
                .map(user -> {
                    System.out.println("Set 'isVerified' to true for user " + user.getId());
                    user.setVerified(true);
                    return user;
                })
                .collect(Collectors.toList());
        System.out.println(processUsers);
        // 중간 과정의 순서가 중요하다면 parallel stream 을 쓰는 것이 어려움.
        // 꼭 써야한다면 mutex, semaphore 등을 사용. -> Sequential을 쓰는 것보다 느려질 가능성.
    }
}
