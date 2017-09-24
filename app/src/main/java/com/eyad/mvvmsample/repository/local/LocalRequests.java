package com.eyad.mvvmsample.repository.local;


import com.eyad.mvvmsample.models.UserModel;
import com.eyad.mvvmsample.repository.RepositoryManager;

/**
 * Created by eyadse93 on 9/14/17.
 */

public class LocalRequests {

    private RepositoryManager repositoryManager;

    public LocalRequests(RepositoryManager repositoryManager){
        this.repositoryManager = repositoryManager;
    }

    public void saveUser(UserModel userModel){
        //fake saving user to shared prefs or database
    }
}