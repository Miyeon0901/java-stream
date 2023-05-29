package com.fastcampus.doitmyself.chapter8.service;

import com.fastcampus.doitmyself.chapter8.model.User;

public class EmailService {
    public void sendPlayWithFriendsEmail(User user) {
        user.getEmailAddress().ifPresent(email ->
                System.out.println("Sending 'Play with Friends' email to " + email));
    }

    public void sendMakeMoreFriendsEmail(User user) {
        user.getEmailAddress().ifPresent(email ->
                System.out.println("Sending 'Make More Friends' email to " + email));
    }

    public void sendVerifyYourEmailEmail(User user) {
        user.getEmailAddress().ifPresent(email ->
                System.out.println("Sending 'Verify Your Email' email to " + email));
    }
}
