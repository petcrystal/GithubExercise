package com.zlm.project.connect;

import com.zlm.project.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseClient {

    // -------------------------------------------
    private final int DEFAULT_TIME_OUT;

    private static Retrofit retrofit;

    // -------------------------------------------
    protected BaseClient() {
        DEFAULT_TIME_OUT = 60;
        init();
    }

    // -------------------------------------------
    private void init() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //.baseUrl(BuildConfig.SERVER_URL)
                .baseUrl("https://api.github.com/")
                .client(builder.build())
                .build();
    }

    // -------------------------------------------

    /**
     * create api service.
     *
     * @param service api service
     * @return T
     */
    protected static <T> T createService(Class<T> service) {
        return retrofit.create(service);
    }

    // -------------------------------------------

}
