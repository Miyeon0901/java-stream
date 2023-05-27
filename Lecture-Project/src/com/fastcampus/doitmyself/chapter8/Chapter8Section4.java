package com.fastcampus.doitmyself.chapter8;

import com.fastcampus.doitmyself.chapter6.model.Order;
import com.fastcampus.doitmyself.chapter6.model.OrderLine;
import com.fastcampus.doitmyself.chapter8.model.User;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Chapter8Section4 {
    /**
     * 병렬 컴퓨팅에서 이야기하는 map reduce가 바로 이 reduce.
     * Max/ Min/ Count 도 사실 reduce의 일종.
     *
     * Optional<T> reduce(BinaryOperator<T> accumulator);
     *      - 주어진 accumulator를 이용해 데이터를 합침. Stream이 비어있을 경우 빈 Optional을 반환.
     * T reduce(T identity, BinaryOperator<T> accumulator);
     *      - 주어진 초기값과 accumulator를 이용. 초기값이 있기 때문에 항상 반환값이 존재.
     * <U> U reduce(U identify,
     *              BiFunction<U, ? super T, U> accumulator,
     *              BinaryOprator<U> combiner;
     *      - 합치는 과정에서 타입이 바뀔 경우 사용. Map + reduce로 대체 가능.
     *        combiner는 병렬 작업 시 사용.
     */

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 4, -2, -5, 3);
        int sum = numbers.stream()
                .reduce((x, y) -> x + y)
                .get();
        System.out.println(sum);

        int min = numbers.stream()
                .reduce((x, y) -> x < y ? x : y)
//                .reduce((x, y) -> {
//                    if (x < y) {
//                        return x;
//                    }
//                    return y;
//                })
                .get();
        System.out.println(min);

        int product = numbers.stream()
                .reduce(1, (x, y) -> x * y); // 초기값을 제공했기 때문에 return 타입이 Optional이 아니므로 get()할 필요 없음.
        System.out.println(product);

        List<String> numberStrList = Arrays.asList("3", "2", "5", "-4");
        int sumOfNumberStrList = numberStrList.stream()
                .map(Integer::parseInt)
                .reduce(0, (x, y) -> x + y);
        System.out.println(sumOfNumberStrList);

        // reduce3, 잘 쓰지 않는 방법, T는 String U는 Intger
        int sumOfNumberStrList2 = numberStrList.stream()
                .reduce(0,
                        (number, str) -> number + Integer.parseInt(str),
                        (num1, num2) -> num1 + num2);
        System.out.println(sumOfNumberStrList2);

        User user1 = new User()
                .setId(101)
                .setName("Alice")
                .setFriendUserIds(Arrays.asList(201, 202, 203, 204));

        User user2 = new User()
                .setId(102)
                .setName("Bob")
                .setFriendUserIds(Arrays.asList(204, 205, 206));

        User user3 = new User()
                .setId(103)
                .setName("Charlie")
                .setFriendUserIds(Arrays.asList(204, 205, 207));

        List<User> users = Arrays.asList(user1, user2, user3);

        int sumOfNumberOfFriends = users.stream()
//                .map(user -> user.getFriendUserIds().size())
                .map(User::getFriendUserIds)
                .map(List::size)
                .reduce(0, (x, y) -> x + y);
        System.out.println(sumOfNumberOfFriends);

        // TODO: find the sum of amounts
        Order order1 = new Order()
                .setId(1001)
                .setOrderLines(Arrays.asList(
                        new OrderLine()
                                .setAmount(BigDecimal.valueOf(1000)),
                        new OrderLine()
                                .setAmount(BigDecimal.valueOf(2000))
                ));

        Order order2 = new Order()
                .setId(1002)
                .setOrderLines(Arrays.asList(
                        new OrderLine()
                                .setAmount(BigDecimal.valueOf(2000)),
                        new OrderLine()
                                .setAmount(BigDecimal.valueOf(3000))
                ));

        Order order3 = new Order()
                .setId(1003)
                .setOrderLines(Arrays.asList(
                        new OrderLine()
                                .setAmount(BigDecimal.valueOf(1000)),
                        new OrderLine()
                                .setAmount(BigDecimal.valueOf(2000))
                ));

        List<Order> orders = Arrays.asList(order1, order2, order3);

        BigDecimal sumOfAmounts = orders.stream()
                .map(Order::getOrderLines) // Stream<List<OrderLine>>
                .flatMap(List::stream) // Stream<OrderLine>
                .map(OrderLine::getAmount)
                .reduce(BigDecimal.ZERO, (x, y) -> x.add(y));
        System.out.println(sumOfAmounts);


    }
}
