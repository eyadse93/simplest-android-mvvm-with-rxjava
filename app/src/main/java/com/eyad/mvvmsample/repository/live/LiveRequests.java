package com.eyad.mvvmsample.repository.live;


import com.eyad.mvvmsample.models.UserModel;
import com.eyad.mvvmsample.repository.RepositoryManager;

/**
 * Created by eyadse93 on 9/14/17.
 */

public class LiveRequests {

    private RepositoryManager repositoryManager;

    public LiveRequests(RepositoryManager repositoryManager){
        this.repositoryManager = repositoryManager;
    }

    public void login(String username, String password){
        //fake request then..
        UserModel userModel = new UserModel();
        userModel.setId("12345");
        userModel.setUsername(username);
        userModel.setPassword(password);
        userModel.setName("ccc");
        userModel.setCredit(100);
        repositoryManager.loginResponse(userModel);
    }
}