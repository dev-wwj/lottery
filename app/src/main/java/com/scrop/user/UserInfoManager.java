package com.scrop.user;

import com.scrop.mimpl.ConcreteMySubject;

/**
 * Created by Scrop on 2017/8/7.
 */
public class UserInfoManager {
    private static UserInfoManager ourInstance = new UserInfoManager();

    private static ConcreteMySubject subject;

    public static UserInfoManager getInstance() {
        return ourInstance;
    }

    private static UserInfo userInfo = new UserInfo();

    private UserInfoManager() {
        subject = new ConcreteMySubject();
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public static ConcreteMySubject getSubject() {
        return subject;
    }

    public void notifyUpdateUserInfo(){
        subject.notifyObservers();
    }



}
