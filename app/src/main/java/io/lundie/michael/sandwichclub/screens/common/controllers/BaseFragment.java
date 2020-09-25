package io.lundie.michael.sandwichclub.screens.common.controllers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import io.lundie.michael.sandwichclub.common.BaseObservable;
import io.lundie.michael.sandwichclub.di.ControllerCompositionRoot;
import io.lundie.michael.sandwichclub.screens.common.main.MainActivity;

public class BaseFragment extends Fragment {

    private ControllerCompositionRoot controllerCompositionRoot;

    public interface Listener {
        void onAccessibleNavController(NavController navController);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getCompositionRoot();
        controllerCompositionRoot.getNavHelper().setNavController(Navigation.findNavController(container));
        return null;
    }

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
