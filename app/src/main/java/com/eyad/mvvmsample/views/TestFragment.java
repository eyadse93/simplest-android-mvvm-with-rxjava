package com.eyad.mvvmsample.views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.eyad.mvvmsample.R;
import com.eyad.mvvmsample.main.fragments.BaseFragment;
import com.eyad.mvvmsample.models.UserModel;
import com.eyad.mvvmsample.viewModels.TestViewModel;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by eyadse93 on 9/14/17.
 */

public class TestFragment extends BaseFragment {

    TestViewModel viewModel;
    private Subscription subscription;

    TextView textView;
    Button button;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new TestViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        textView = view.findViewById(R.id.textView);
        button = view.findViewById(R.id.button);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = ProgressDialog.show(getActivity(), "Login", "...", true);
                viewModel.login("password", "username");
            }
        });
    }

    @Override
    protected void subscribeForNetworkRequests() {
        subscription = viewModel.getSubject()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new LoginSubscriber());
    }

    @Override
    protected void unsubscribeFromNetworkRequests() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    @Override
    protected void reconnectWithNetworkRequests() {
        subscription = viewModel.createSubject()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new LoginSubscriber());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }

    private class LoginSubscriber extends Subscriber<UserModel> {
        @Override
        public void onCompleted() {
            hideProgressDialog();
        }

        @Override
        public void onError(Throwable e) {
            hideProgressDialog();
            reconnectWithNetworkRequests();
        }

        @Override
        public void onNext(UserModel userModel) {
            hideProgressDialog();
            if (userModel != null){
                textView.setText(viewModel.getText(userModel));
            }
        }
    }
}
