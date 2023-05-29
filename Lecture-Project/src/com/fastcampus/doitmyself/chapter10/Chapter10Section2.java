package com.fastcampus.doitmyself.chapter10;

import com.fastcampus.doitmyself.chapter10.model.User;

public class Chapter10Section2 {
    /**
     * Builder Pattern(빌더 패턴)
     * - 대표적인 생성 패턴
     * - 객체의 생성에 대한 로직과 표현에 대한 로직을 분리해준다.
     * - 객체의 생성 과정을 유연하게 해준다.
     * - 객체의 생성 과정을 정의하고 싶거나 필드가 많아 constructor가 복잡해질 때 유용.
     */
    public static void main(String[] args) {
//        User user = User.builder(1, "Alice")
//                .withEmailAddress("alice@fastcampus.co.kr")
//                .withVerified(true)
//                .build();
//
//        System.out.println(user);

        User user = User.builder(1, "Alice")
                .with(builder -> { // 이렇게 해야되니까 변수를 public 으로
                    builder.emailAddress = "alice@fastcampus.co.kr";
                    builder.isVerified = true;
                }). build();

        System.out.println(user);
    }
}
