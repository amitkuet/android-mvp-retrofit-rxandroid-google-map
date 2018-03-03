package app.engine.android.view.authentication;

public interface LoginIterface {
    <T> void openActivity(Class<T> activityClass);
    void loginAPI();
    void error();
}
