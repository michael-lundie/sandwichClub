package io.lundie.michael.sandwichclub.di;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import io.lundie.michael.sandwichclub.common.AppExecutors;
import io.lundie.michael.sandwichclub.screens.common.screensnavigator.ScreensNavigator;
import io.lundie.michael.sandwichclub.screens.common.ViewMvcFactory;
import io.lundie.michael.sandwichclub.sandwiches.FetchSandwichesUseCase;
import io.lundie.michael.sandwichclub.screens.sandwichdetail.FetchImageUseCase;
import io.lundie.michael.sandwichclub.screens.sandwichdetail.SandwichDetailController;
import io.lundie.michael.sandwichclub.screens.sandwichlist.SandwichListController;
import okhttp3.Call;

public class ControllerCompositionRoot {

    private final CompositionRoot compositionRoot;
    private Activity activity;

    private Picasso picasso;

    public ControllerCompositionRoot(CompositionRoot compositionRoot, Activity activity) {
        this.compositionRoot = compositionRoot;
        this.activity = activity;
    }

    public Picasso getPicasso() {
        if(picasso == null) {
            picasso = new Picasso.Builder(getContext()).build();
        }
        return picasso;
    }

    private Gson getGson() {
        return compositionRoot.getGson();
    }

    private Call getSandwichesDataDumpApi() {
        return compositionRoot.getSandwichesDataDumpApi();
    }

    private LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(activity);
    }

    public ViewMvcFactory getViewMvcFactory() {
        return new ViewMvcFactory(getLayoutInflater());
    }

    public FetchSandwichesUseCase getFetchSandwichesUseCase() {
        return new FetchSandwichesUseCase(getSandwichesDataDumpApi(), getGson(), getAppExecutors());
    }

    public FetchImageUseCase getFetchImageUseCase() {
        return new FetchImageUseCase(getPicasso());
    }

    private AppExecutors getAppExecutors() {
        return compositionRoot.getAppExecutors();
    }

    public SandwichListController getSandwichListController() {
        return  new SandwichListController(getFetchSandwichesUseCase(), getScreensNavigator());
    }

    private Context getContext() {
        return activity;
    }

    private ScreensNavigator getScreensNavigator() {
        return new ScreensNavigator(getContext());
    }

    public SandwichDetailController getSandwichDetailController() {
        return new SandwichDetailController(getFetchImageUseCase(), getScreensNavigator());
    }
}
