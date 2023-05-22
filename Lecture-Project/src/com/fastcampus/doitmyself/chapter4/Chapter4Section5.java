package com.fastcampus.doitmyself.chapter4;

import com.fastcampus.doitmyself.chapter4.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Chapter4Section5 {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User(3, "Charlie"));
        users.add(new User(1, "Alice"));
        users.add(new User(5, "Bob"));
        System.out.println(users);

        // user를 id 순으로 정렬하고 싶다.
        Comparator<User> idComparator = (u1, u2) -> u1.getId() - u2.getId();
        Collections.sort(users, idComparator);
        System.out.println(users);

        // user를 name 순으로 정렬하고 싶다.
        Collections.sort(users, (u1, u2) -> u1.getName().compareTo(u2.getName()));
        System.out.println(users);
    }
}
