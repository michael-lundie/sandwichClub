package io.lundie.michael.sandwichclub.di;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import io.lundie.michael.sandwichclub.common.AppExecutors;
import io.lundie.michael.sandwichclub.sandwiches.FetchSandwichesUseCase;
import io.lundie.michael.sandwichclub.screens.common.FetchImageUseCaseFactory;
import io.lundie.michael.sandwichclub.screens.common.ViewMvcFactory;
import io.lundie.michael.sandwichclub.screens.common.controllers.NavHelper;
import io.lundie.michael.sandwichclub.screens.common.controllers.UpPressedDispatcher;
import io.lundie.michael.sandwichclub.screens.common.screensnavigator.ScreensNavigator;
import io.lundie.michael.sandwichclub.screens.sandwichdetail.FetchImageUseCase;
import io.lundie.michael.sandwichclub.screens.sandwichdetail.SandwichDetailController;
import io.lundie.michael.sandwichclub.screens.sandwichlist.SandwichListController;
import okhttp3.Call;

public class ControllerCompositionRoot {

    private final ActivityCompositionRoot activityCompositionRoot;

    private Picasso picasso;
    private SandwichListController sandwichListController;
    private NavHelper navHelper;

    public ControllerCompositionRoot(ActivityCompositionRoot activityCompositionRoot) {
        this.activityCompositionRoot = activityCompositionRoot;
    }

    public Picasso getPicasso() {
        if(picasso == null) {
            picasso = new Picasso.Builder(getContext()).build();
        }
        return picasso;
    }

    public NavHelper getNavHelper() {
        if(navHelper == null) {
            navHelper = new NavHelper();
        }
        return navHelper;
    }

    private AppCompatActivity getActivity() {
        return activityCompositionRoot.getAppCompatActivity();
    }

    private Gson getGson() {
        return activityCompositionRoot.getGson();
    }

    private Call getSandwichesDataDumpApi() {
        return activityCompositionRoot.getSandwichesDataDumpApi();
    }

    private LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(activityCompositionRoot.getAppCompatActivity());
    }

    public ViewMvcFactory getViewMvcFactory() {
        return new ViewMvcFactory(getLayoutInflater(), getFetchImageUseCaseFactory());
    }

    // Factory is used for injection into adapters via Mvc implementation
    // TODO: fetchImageUseCase is logic that should be instantiated in a
    //  controller - try to find a better way to get this to the adapter?
    //  Lazy injection maybe?
    private FetchImageUseCaseFactory getFetchImageUseCaseFactory() {
        return new FetchImageUseCaseFactory(getPicasso());
    }

    public FetchSandwichesUseCase getFetchSandwichesUseCase() {
        return new FetchSandwichesUseCase(getSandwichesDataDumpApi(), getGson(), getAppExecutors());
    }

    public FetchImageUseCase getFetchImageUseCase() {
        return new FetchImageUseCase(getPicasso());
    }

    private AppExecutors getAppExecutors() {
        return activityCompositionRoot.getAppExecutors();
    }

    public SandwichListController getSandwichListController() {
        if(sandwichListController == null) {
            sandwichListController =  new SandwichListController(getFetchSandwichesUseCase(), getUpPressedDispatcher(),
                    getScreensNavigator());
        } return sandwichListController;
    }

    private Context getContext() {
        return activityCompositionRoot.getAppCompatActivity();
    }

    private ScreensNavigator getScreensNavigator() {
        return new ScreensNavigator(getNavHelper());
    }

    public SandwichDetailController getSandwichDetailController() {
        return new SandwichDetailController(getFetchImageUseCase(), getUpPressedDispatcher(),
                getScreensNavigator());
    }

    private UpPressedDispatcher getUpPressedDispatcher() {
        return (UpPressedDispatcher) getActivity();
    }
}
