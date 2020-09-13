package io.lundie.michael.sandwichclub.di;

import android.app.Activity;
import android.view.LayoutInflater;

import com.google.gson.Gson;

import io.lundie.michael.sandwichclub.screens.common.ViewMvcFactory;
import okhttp3.Call;

public class ControllerCompositionRoot {

    private final CompositionRoot compositionRoot;
    private Activity activity;

    public ControllerCompositionRoot(CompositionRoot compositionRoot, Activity activity) {
        this.compositionRoot = compositionRoot;
        this.activity = activity;
    }

    public Gson getGson() {
        return compositionRoot.getGson();
    }

    public Call getSandwichesApi() {
        return compositionRoot.getSandwichesApi();
    }

    private LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(activity);
    }

    public ViewMvcFactory getViewMvcFactory() {
        return new ViewMvcFactory(getLayoutInflater());
    }
}
