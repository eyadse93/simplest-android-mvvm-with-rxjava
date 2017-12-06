package com.eyad.mvvmsample.repository;


import com.eyad.mvvmsample.models.UserModel;
import com.eyad.mvvmsample.repository.cloud.LiveRequests;
import com.eyad.mvvmsample.repository.local.LocalRequests;

import rx.subjects.PublishSubject;

/**
 * Created by eyadse93 on 9/14/17.
 */

public class RepositoryManager {

    private static RepositoryManager instance;

    private LiveRequests liveRequests;
    private LocalRequests localRequests;
    private PublishSubject subject;

    private RepositoryManager() {

    }

    public static RepositoryManager getInstance() {
        synchronized (RepositoryManager.class) {
            if (instance == null) {
                instance = new RepositoryManager();
                instance.liveRequests = new LiveRequests(instance);
                instance.localRequests = new LocalRequests(instance);
                instance.subject = PublishSubject.create();
            }
            return instance;
        }
    }

    public void setSubject(PublishSubject subject){
        instance.subject = subject;
    }

    public PublishSubject getSubject() {
        return subject;
    }

    public void loginRequest(String username, String password){
        liveRequests.login(username, password);
    }

    public void loginResponse(UserModel userModel){
        localRequests.saveUser(userModel);
        subject.onNext(userModel);
    }
}