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
        SandwichDetailActivity.start(context, sandwich);
        Intent intent = new Intent(context, SandwichDetailActivity.class);
        intent.putExtra(SandwichDetailActivity.PARCELABLE_EXTRA, sandwich);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.getApplicationContext().startActivity(intent);
        //TODO: complete implementation
    }
}
