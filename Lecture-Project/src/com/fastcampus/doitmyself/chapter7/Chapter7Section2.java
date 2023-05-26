package com.fastcampus.doitmyself.chapter7;

import com.fastcampus.doitmyself.chapter7.model.User;

import java.util.Optional;

public class Chapter7Section2 {
    /**
     Optional이 비어있는지 체크할 필요 없이 여러 작업을 가능케 함.
     public void ifPresent(Consumer< ? super T> action) - Optional이 null이 아니라면 action을 수행
     public <U> optional<U> map(Function< ? super T, ? extends U> mapper)
        - Optional이 null이면 mapper를 적용. 함수의 return type에 따라서 Optional 안에 있는 값의 type도 변함.
     public <U> optional<U> flatMap(Function< ? super T, ? extends Optional< ? extends U>> mapper)
        - mapper의 리턴 값이 또 다른 Optional이라면 한 단계의 Optional이 되도록 납작하게 해줌
     */

    public static void main(String[] args) {
        Optional<User> maybeUser = Optional.ofNullable(maybeGetUser(true));
        maybeUser.ifPresent(user -> System.out.println(user));

        Optional<Integer> maybeId = Optional.ofNullable(maybeGetUser(true))
                .map(User::getId);
        maybeId.ifPresent(System.out::println);

        String userName = Optional.ofNullable(maybeGetUser(false))
                .map(User::getName)
                .map(name -> "The name is " + name)
                .orElse("Name is empty");
        System.out.println(userName);

//        Optional<Optional<String>> maybeEmail = Optional.ofNullable(maybeGetUser(true))
//                .map(User::getEmailAddress);
        Optional<String> maybeEmail = Optional.ofNullable(maybeGetUser(true))
                .flatMap(User::getEmailAddress);
        maybeEmail.ifPresent(System.out::println);
    }

    public static User maybeGetUser(boolean returnUser) {
        if (returnUser) {
            return new User()
                    .setId(1001)
                    .setName("Alice")
//                    .setEmailAddress("alice@fastcampus.co.kr")
                    .setVerified(false);
        }
        return null;
    }
}
