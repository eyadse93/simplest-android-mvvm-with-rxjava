package com.eyad.mvvmsample.viewModels;


import com.eyad.mvvmsample.models.UserModel;
import com.eyad.mvvmsample.repository.RepositoryManager;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subjects.PublishSubject;


/**
 * Created by eyadse93 on 9/14/17.
 */

public class TestViewModel {

    private RepositoryManager repositoryManager;
    private PublishSubject<UserModel> subject;

    private Subscription subscription;

    public TestViewModel(){
        subject = PublishSubject.create();
        repositoryManager = RepositoryManager.getInstance();
        //repositoryManager.setSubject(subject);

        subscription = getRepositorySubject()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RepositorySubscriber());

    }

    private Observable getRepositorySubject() {
        return repositoryManager.getSubject();
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

    public void onDestroy() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    private class RepositorySubscriber extends Subscriber<Object> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(Object object) {
            if (object != null){
                if (object instanceof UserModel) {
                    subject.onNext((UserModel) object);
                }
            }
        }
    }



}
