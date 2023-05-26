package com.fastcampus.doitmyself.chapter7;

import com.fastcampus.doitmyself.chapter7.model.User;

import java.util.Optional;

public class Chapter7Section1 {
    public static void main(String[] args) {
        User user1 = new User()
                .setId(1001)
                .setName("Alice")
                .setVerified(false);

        User user2 = new User()
                .setId(1001)
                .setName("Alice")
                .setEmailAddress("alice@fastcampus.co.kr")
                .setVerified(false);

        System.out.println("Same: " + userEquals(user2, user1));
//        System.out.println("Same: " + userEquals(user1, user2)); // Null.equals

        /**
         static method(Optional.으로 사용)
         public static <T> Optional<T> of(T value) - Null이 아닌 오브젝트를 이용해 Optional을 만들 때
         public static <T> Optional<T> Empty() - 빈 Optional을 만들 때
         public static <T> Optional<T> ofNullable(T value) - Null인지 아닌지 알 지 못하는 오브젝트로 Optional을 만들 때

         instance method(Optional object.으로 사용)
         public boolean isPresent() - 안의 오브젝트가 null인지 아닌지 체크, Null이 아닐 시 true
         public T get() - Optional 안의 값을 추출. Null이라면 에러
         public T orElse(T other) - Optional이 null이 아니라면 Optional 안의 값을, null이라면 other로 공급된 값을 리턴
         public T orElseGet(Supplier< ? extends T> supplier - Optional이 null이 아니라면 Optional 안의 값을, null이라면 supplier로 공급되는 값을 리턴
         public <X extends Throwable> T orElseThrow(Supplier< ? extends X> exceptionSupplier) throws X
            - Optional이 null이 아니라면 Optional의 값을, null이라면 exceptionSupplier로 공급되는 exception을 던짐
         */

        String someEmail = "some@email.com";
        String nullEmail = null;

        Optional<String> maybeEmail = Optional.of(someEmail);
        Optional<String> maybeEmail2 = Optional.empty();
        Optional<String> maybeEmail3 = Optional.ofNullable(someEmail);
        Optional<String> maybeEmail4 = Optional.ofNullable(nullEmail);

        String email = maybeEmail.get();
        System.out.println(email);

//        String email2 = maybeEmail2.get(); // NoSuchElementException
        if (maybeEmail2.isPresent()) {
            System.out.println(maybeEmail2.get());
        }

        String defaultEmail = "default@email.com";
        String email3 = maybeEmail4.orElse(defaultEmail); // maybeEmail3가 비어있으면 defaultEmail을 리턴
        System.out.println(email3);

        String email4 = maybeEmail4.orElseGet(() -> defaultEmail);
        System.out.println(email4);

        String email5 = maybeEmail2.orElseThrow(() -> new RuntimeException("email not present"));

    }

    public static boolean userEquals(User u1, User u2) {
        return u1.getId() == u2.getId()
                && u1.getName().equals(u2.getName())
                && u1.getEmailAddress().equals(u2.getEmailAddress())
                && u1.isVerified() == u2.isVerified();
    }
}
