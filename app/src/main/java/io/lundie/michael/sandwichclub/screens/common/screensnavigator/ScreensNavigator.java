package io.lundie.michael.sandwichclub.screens.common.screensnavigator;

import android.content.Context;
import android.content.Intent;

import io.lundie.michael.sandwichclub.sandwiches.Sandwich;
import io.lundie.michael.sandwichclub.screens.sandwichdetail.SandwichDetailActivity;

public class ScreensNavigator {

    private Context context;

    public ScreensNavigator(Context context) {
        this.context = context;
    }

    public void toScreenDetails(Sandwich sandwich) {
        SandwichDetailActivity.startActivity(context, sandwich);
    }
}
