package io.lundie.michael.sandwichclub.screens.common;

import androidx.appcompat.app.AppCompatActivity;

import io.lundie.michael.sandwichclub.App;
import io.lundie.michael.sandwichclub.di.ControllerCompositionRoot;

public class BaseActivity extends AppCompatActivity {

    private ControllerCompositionRoot controllerCompositionRoot;

    protected ControllerCompositionRoot getCompositionRoot() {
        if(controllerCompositionRoot == null) {
            controllerCompositionRoot = new ControllerCompositionRoot(
                    ((App) getApplication()).getCompositionRoot(),
                    this
            );
        }
        return controllerCompositionRoot;
    }
}
