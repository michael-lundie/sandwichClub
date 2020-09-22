package io.lundie.michael.sandwichclub.screens.common.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import io.lundie.michael.sandwichclub.di.ControllerCompositionRoot;
import io.lundie.michael.sandwichclub.screens.common.main.MainActivity;

public class BaseFragment extends Fragment {

    private ControllerCompositionRoot controllerCompositionRoot;

    protected ControllerCompositionRoot getCompositionRoot() {
        if(controllerCompositionRoot == null) {
            controllerCompositionRoot = new ControllerCompositionRoot(
                    ((MainActivity) requireActivity()).getActivityCompositionRoot());
        }
        return controllerCompositionRoot;
    }

    protected void setToolbarWithTitle(Toolbar toolbar, boolean displayUp) {
        AppCompatActivity appCompatActivity = (AppCompatActivity)getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayShowTitleEnabled(true);
        if(displayUp) appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
