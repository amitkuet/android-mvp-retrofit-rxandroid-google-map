package app.engine.android.util;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import app.engine.android.AppEngine;
import app.engine.android.BuildConfig;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkAdapter {

    public NetworkAdapter() {

    }
    public <T> T create(Class<T> service) {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(AppEngine.getInstance().constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(service);
    }
    public <T> T create(Class<T> service, String BASE_URL) {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(service);
    }
    public <T> void subscriber(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public <T> T callAPI(Class<T> serviceClass){
        return create(serviceClass, "http://www.mocky.io/");
    }
}
