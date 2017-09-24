package com.eyad.mvvmsample.viewModels;


import com.eyad.mvvmsample.models.UserModel;
import com.eyad.mvvmsample.repository.RepositoryManager;

import rx.subjects.PublishSubject;

/**
 * Created by eyadse93 on 9/14/17.
 */

public class TestViewModel {

    private RepositoryManager repositoryManager;
    private PublishSubject<UserModel> subject;

    public TestViewModel(){
        subject = PublishSubject.create();
        repositoryManager = RepositoryManager.getInstance();
        repositoryManager.setSubject(subject);
    }

    public PublishSubject<UserModel> createSubject() {
        subject = PublishSubject.create();
        return subject;
    }

    public PublishSubject<UserModel> getSubject() {
        return subject;
    }

    public void login(String username, String password) {
        repositoryManager.loginRequest(username, password);
    }

    public String getText(UserModel userModel){
        String text = userModel.getName() + " , " + userModel.getCredit();
        return text;
    }
}
