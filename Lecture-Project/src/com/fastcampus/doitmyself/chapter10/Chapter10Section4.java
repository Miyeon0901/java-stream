package com.fastcampus.doitmyself.chapter10;

import com.fastcampus.doitmyself.chapter10.model.Price;
import com.fastcampus.doitmyself.chapter10.model.User;
import com.fastcampus.doitmyself.chapter10.service.EmailProvider;
import com.fastcampus.doitmyself.chapter10.service.EmailSender;
import com.fastcampus.doitmyself.chapter10.service.MakeMoreFriendsEmailProvider;
import com.fastcampus.doitmyself.chapter10.service.VerifyYourEmailAddressEmailProvider;

import java.util.Arrays;
import java.util.List;

public class Chapter10Section4 {
    /**
     * Strategy Pattern(전략 패턴)
     * - 대표적인 행동 패턴
     * - 런타임에 어떤 전략(알고리즘)을 사용할 지 선택할 수 있게 해준다.
     * - 전략들을 캡슐화 하여 간단하게 교체할 수 있게 해준다.
     */
    public static void main(String[] args) {
        User user1 = User.builder(1, "Alice")
                .with(builder -> { // 이렇게 해야되니까 변수를 public 으로
                    builder.emailAddress = "alice@fastcampus.co.kr";
                    builder.isVerified = false;
                    builder.friendUserIds = Arrays.asList(201, 202, 203, 204, 211, 212, 213, 214);
                }). build();

        User user2 = User.builder(2, "Bob")
                .with(builder -> {
                    builder.emailAddress = "bob@fastcampus.co.kr";
                    builder.isVerified = true;
                    builder.friendUserIds = Arrays.asList(212, 213, 214);
                }). build();

        User user3 = User.builder(3, "Charlie")
                .with(builder -> {
                    builder.emailAddress = "charlie@fastcampus.co.kr";
                    builder.isVerified = true;
                    builder.friendUserIds = Arrays.asList(201, 202, 203, 204, 211, 212);
                }). build();

        List<User> users = Arrays.asList(user1, user2, user3);

        EmailSender emailSender = new EmailSender();
        EmailProvider verifyYourEmailAddressEmailProvider = new VerifyYourEmailAddressEmailProvider();
        MakeMoreFriendsEmailProvider makeMoreFriendsEmailProvider = new MakeMoreFriendsEmailProvider();

        emailSender.setEmailProvider(verifyYourEmailAddressEmailProvider);
        users.stream()
                .filter(user -> !user.isVerified())
                .forEach(emailSender::sendEmail);

        emailSender.setEmailProvider(makeMoreFriendsEmailProvider);
        users.stream()
                .filter(User::isVerified)
                .filter(user -> user.getFriendUserIds().size() <= 5)
                .forEach(emailSender::sendEmail);

        emailSender.setEmailProvider(user -> "'Play With Friends' email for " + user.getName());
        users.stream()
                .filter(User::isVerified)
                .filter(user -> user.getFriendUserIds().size() > 5)
                .forEach(emailSender::sendEmail);

    }
}
