package app.engine.android.service.network;



import app.engine.android.model.authentication.LoginCredentials;
import app.engine.android.model.authentication.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("/oauth/token")
    Observable<LoginResponse> login(@Body LoginCredentials loginCredentials);
}