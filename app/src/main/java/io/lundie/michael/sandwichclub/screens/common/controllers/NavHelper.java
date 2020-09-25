package io.lundie.michael.sandwichclub.screens.common.controllers;

import androidx.annotation.Nullable;
import androidx.navigation.NavController;

public class NavHelper {

    private NavController navController;

    public void setNavController(NavController navController) {
        this.navController = navController;
    }

    public NavController getNavController() {
        if(navController == null) {
            throw new RuntimeException("Nav Controller must be set. Call to super must be made in fragment onCreate.");
        }
        return navController;
    }
}
