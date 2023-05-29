package com.fastcampus.doitmyself.chapter10.service;

import com.fastcampus.doitmyself.chapter10.model.User;

public abstract class AbstractUserService {
    protected abstract boolean validateUser(User user);

    protected abstract void writeToDB(User user);

    // 알고리즘의 뼈대를 생성.
    public void createUser(User user) {
        if (validateUser(user)) {
            writeToDB(user);
        } else {
            System.out.println("Cannot create user");
        }
    }
}
