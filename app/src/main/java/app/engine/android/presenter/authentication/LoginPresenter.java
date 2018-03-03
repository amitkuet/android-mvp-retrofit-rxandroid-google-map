package app.engine.android.presenter.authentication;

import android.content.Context;

import app.engine.android.AppEngine;
import app.engine.android.model.authentication.LoginCredentials;
import app.engine.android.model.authentication.LoginResponse;
import app.engine.android.service.network.LoginService;
import app.engine.android.view.authentication.LoginIterface;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class LoginPresenter {
    private final Context context;
    private final LoginIterface loginIterface;

    public LoginPresenter(Context context, LoginIterface loginIterface) {
        this.context = context;
        this.loginIterface = loginIterface;
    }

    public void login(LoginCredentials loginCredentials) {
        AppEngine.getInstance().networkAdapter.subscriber(AppEngine.getInstance().networkAdapter.create(LoginService.class).login(loginCredentials), new Observer<LoginResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }
            @Override
            public void onError(@NonNull Throwable e) {
                loginIterface.error();
            }
            @Override
            public void onComplete() {

            }
            @Override
            public void onNext(@NonNull LoginResponse loginResponse) {
                loginIterface.loginAPI();
            }
        });
    }

    public int validation(String username, String password) {
        if(username.isEmpty() || password.isEmpty()) return AppEngine.getInstance().constants.LOGIN_EMPTY_FIELD;
        return 0;
    }
}
