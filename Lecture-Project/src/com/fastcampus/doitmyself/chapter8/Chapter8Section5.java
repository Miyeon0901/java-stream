package com.fastcampus.doitmyself.chapter8;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter8Section5 {
    public static void main(String[] args) {
        List<Integer> numberList = Stream.of(3, 5, -3, 3, 4, 5)
                .collect(Collectors.toList());
        System.out.println(numberList);

        Set<Integer> numberSet = Stream.of(3, 5, -3, 3, 4, 5)
                .collect(Collectors.toSet());
        System.out.println(numberSet); // set은 순서가 없고, 중복이 제거됨

        List<Integer> numberList2 = Stream.of(3, 5, -3, 3, 4, 5)
                .collect(Collectors.mapping(x -> Math.abs(x), Collectors.toList())); // map + collect
        System.out.println(numberList2);

        Set<Integer> numberSet2 = Stream.of(3, 5, -3, 3, 4, 5)
                .map(x -> Math.abs(x))
                .collect(Collectors.toSet());
//                .collect(Collectors.mapping(x -> Math.abs(x), Collectors.toSet())); // map + collect
        System.out.println(numberSet2);

        int sum = Stream.of(3, 5, -3, 3, 4, 5)
                .collect(Collectors.reducing(0, (x, y) -> x + y));
        System.out.println(sum);
    }
}
