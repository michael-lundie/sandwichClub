package io.lundie.michael.sandwichclub.di;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import io.lundie.michael.sandwichclub.common.AppExecutors;
import io.lundie.michael.sandwichclub.screens.common.controllers.FragmentFrameWrapper;
import io.lundie.michael.sandwichclub.screens.common.controllers.UpPressedDispatcher;
import io.lundie.michael.sandwichclub.screens.common.screensnavigator.ScreensNavigator;
import io.lundie.michael.sandwichclub.screens.common.ViewMvcFactory;
import io.lundie.michael.sandwichclub.sandwiches.FetchSandwichesUseCase;
import io.lundie.michael.sandwichclub.screens.sandwichdetail.FetchImageUseCase;
import io.lundie.michael.sandwichclub.screens.sandwichdetail.SandwichDetailController;
import io.lundie.michael.sandwichclub.screens.sandwichlist.SandwichListController;
import okhttp3.Call;

public class ControllerCompositionRoot {

    private final ActivityCompositionRoot activityCompositionRoot;

    private Picasso picasso;

    public ControllerCompositionRoot(ActivityCompositionRoot activityCompositionRoot) {
        this.activityCompositionRoot = activityCompositionRoot;
    }

    public Picasso getPicasso() {
        if(picasso == null) {
            picasso = new Picasso.Builder(getContext()).build();
        }
        return picasso;
    }

    private FragmentManager getFragmentManager() {
        return getActivity().getSupportFragmentManager();
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
        return new ViewMvcFactory(getLayoutInflater());
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
        return  new SandwichListController(getFetchSandwichesUseCase(), getUpPressedDispatcher(),
                getScreensNavigator());
    }

    private Context getContext() {
        return activityCompositionRoot.getAppCompatActivity();
    }

    private ScreensNavigator getScreensNavigator() {
        return new ScreensNavigator(getFragmentManager(), getFragmentFrameWrapper());
    }

    private FragmentFrameWrapper getFragmentFrameWrapper() {
        return (FragmentFrameWrapper) getActivity();
    }

    public SandwichDetailController getSandwichDetailController() {
        return new SandwichDetailController(getFetchImageUseCase(), getUpPressedDispatcher(),
                getScreensNavigator());
    }

    private UpPressedDispatcher getUpPressedDispatcher() {
        return (UpPressedDispatcher) getActivity();
    }
}
