package io.lundie.michael.sandwichclub.screens.common.controllers;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import io.lundie.michael.sandwichclub.App;
import io.lundie.michael.sandwichclub.R;
import io.lundie.michael.sandwichclub.di.ActivityCompositionRoot;
import io.lundie.michael.sandwichclub.di.ControllerCompositionRoot;

public class BaseActivity extends AppCompatActivity {

    private ControllerCompositionRoot controllerCompositionRoot;
    private ActivityCompositionRoot activityCompositionRoot;

    public ActivityCompositionRoot getActivityCompositionRoot() {
        if(activityCompositionRoot == null) {
            activityCompositionRoot = new ActivityCompositionRoot(
                    ((App) getApplication()).getCompositionRoot(), this
            );
        } return activityCompositionRoot;
    }

    protected ControllerCompositionRoot getCompositionRoot() {
        if(controllerCompositionRoot == null) {
            controllerCompositionRoot = new ControllerCompositionRoot(getActivityCompositionRoot());
        }
        return controllerCompositionRoot;
    }
}
