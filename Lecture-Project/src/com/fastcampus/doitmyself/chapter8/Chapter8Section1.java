package com.fastcampus.doitmyself.chapter8;

import com.fastcampus.doitmyself.chapter8.model.Order;
import com.fastcampus.doitmyself.chapter8.model.Order.OrderStatus;
import com.fastcampus.doitmyself.chapter8.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Chapter8Section1 {
    /**
     * Source(소스): 컬렉션, 배열 등
     * Intermediate Operations(중간 처리): 0개 이상의 filter, map 등의 중간처리
     * Terminal Operation(종결 처리): Collect, reduce 등
     */

    /**
     * Optional<T> max(Comparator< ? Super T> comparator);
     *      - Stream 안의 데이터 중 최댓값을 반환. Stream이 비어있다면 빈 Optional을 반환.
     * Optional<T> min(Comparator< ? Super T> comparator);
     *      - Stream 안의 데이터 중 최솟값을 반환. Stream이 비어있다면 빈 Optional을 반환.
     * long count();
     *      - Stream 안의 데이터의 개수를 반환.
     */

    public static void main(String[] args) {
        Optional<Integer> max = Stream.of(5, 3, 6, 2, 1)
                .max(Integer::compareTo); // .max((x, y) -> x - y)
        System.out.println(max.get());

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

        Optional<User> firstUser = users.stream()
                .min((u1, u2) -> u1.getName().compareTo(u2.getName()));
        System.out.println(firstUser.get());

        long positiveIntegerCount = Stream.of(1, -4, 5, -3, 6)
                .filter(x -> x > 0)
                .count();
        System.out.println("Positive integers: " + positiveIntegerCount);

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        user1.setCreatedAt(now.minusDays(2));
        user2.setCreatedAt(now.minusHours(10));
        user3.setCreatedAt(now.minusHours(1));
        User user4 = new User()
                .setId(104)
                .setName("David")
                .setVerified(true)
                .setEmailAddress("david@fastcampus.co.kr")
                .setCreatedAt(now.minusHours(27));

        users = Arrays.asList(user1, user2, user3, user4);

        // TODO: 최근 24시간 내 가입, 이메일 검증 안됨
        long unverifiedUsersIn24Hrs = users.stream()
                .filter(u -> u.getCreatedAt().isAfter(now.minusDays(1)))
                .filter(u -> !u.isVerified())
                .count();
        System.out.println(unverifiedUsersIn24Hrs);


        Order order1 = new Order()
                .setId(1001)
                .setAmount(BigDecimal.valueOf(2000))
                .setStauts(OrderStatus.CREATED);

        Order order2 = new Order()
                .setId(1002)
                .setAmount(BigDecimal.valueOf(4000))
                .setStauts(OrderStatus.CREATED);

        Order order3 = new Order()
                .setId(1003)
                .setAmount(BigDecimal.valueOf(3000))
                .setStauts(OrderStatus.CREATED);

        Order order4 = new Order()
                .setId(1004)
                .setAmount(BigDecimal.valueOf(7000))
                .setStauts(OrderStatus.PROCESSED);

        List<Order> orders = Arrays.asList(order1, order2, order3, order4);

        // TODO: find order with highest amount in ERROR status
//        Order erroredOrderWithMaxAmount = orders.stream()
//                .filter(o -> o.getStauts() == OrderStatus.ERROR)
//                .max((o1, o2) -> o1.getAmount().compareTo(o2.getAmount()))
//                .get();
//        System.out.println(erroredOrderWithMaxAmount);

        BigDecimal maxErroredAmount = orders.stream()
                .filter(o -> o.getStauts() == OrderStatus.ERROR)
                .map(Order::getAmount)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO); // ERROR 상태인 order가 없어도 0 출력.
        System.out.println(maxErroredAmount);

    }
}

