package io.lundie.michael.sandwichclub.di;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import io.lundie.michael.sandwichclub.common.AppExecutors;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

public class CompositionRoot {

    private OkHttpClient okHttpClient;
    private Gson gson;
    private Picasso picasso;

    public Call getSandwichesDataDumpApi() {
        Request request = new Request.Builder()
                .url("http://quest.lundie.io/sandwiches.json")
                .build();

        return getOkHttpClient().newCall(request);
    }

    public Gson getGson() {
        if(gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    private OkHttpClient getOkHttpClient() {
        if(okHttpClient == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.level(HttpLoggingInterceptor.Level.BASIC);
            okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        }
        return okHttpClient;
    }

    public AppExecutors getAppExecutors() {
        return AppExecutors.getInstance();
    }
}
