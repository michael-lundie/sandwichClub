package io.lundie.michael.sandwichclub;

import android.app.Application;

import io.lundie.michael.sandwichclub.di.CompositionRoot;

public class App extends Application {

    private CompositionRoot mCompositionRoot;

    @Override
    public void onCreate() {
        super.onCreate();
        mCompositionRoot = new CompositionRoot();
    }

    public CompositionRoot getCompositionRoot() {
        return mCompositionRoot;
    }
}