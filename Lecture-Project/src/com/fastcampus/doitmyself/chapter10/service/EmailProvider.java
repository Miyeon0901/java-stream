package com.fastcampus.doitmyself.chapter10.service;

import com.fastcampus.doitmyself.chapter10.model.User;

public interface EmailProvider {
    String getEmail(User user);
}
