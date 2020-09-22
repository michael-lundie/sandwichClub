package io.lundie.michael.sandwichclub.di;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import io.lundie.michael.sandwichclub.common.AppExecutors;
import okhttp3.Call;

public class ActivityCompositionRoot {

    private final CompositionRoot compositionRoot;
    private final AppCompatActivity appCompatActivity;

    public ActivityCompositionRoot(CompositionRoot compositionRoot, AppCompatActivity appCompatActivity) {
        this.compositionRoot = compositionRoot;
        this.appCompatActivity = appCompatActivity;
    }

    public AppCompatActivity getAppCompatActivity() {
        return appCompatActivity;
    }

    protected Call getSandwichesDataDumpApi() {
        return compositionRoot.getSandwichesDataDumpApi();
    }

    protected Gson getGson() {
        return compositionRoot.getGson();
    }

    protected AppExecutors getAppExecutors() {
        return compositionRoot.getAppExecutors();
    }
}
