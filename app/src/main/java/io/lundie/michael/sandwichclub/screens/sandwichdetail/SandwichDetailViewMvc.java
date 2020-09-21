package io.lundie.michael.sandwichclub.screens.sandwichdetail;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import androidx.appcompat.widget.Toolbar;

import io.lundie.michael.sandwichclub.sandwiches.Sandwich;
import io.lundie.michael.sandwichclub.screens.common.view.ObservableViewMvc;

public interface SandwichDetailViewMvc extends ObservableViewMvc<SandwichDetailViewMvc.Listener> {

    interface Listener {

        void onBackPressed();
    }

    Toolbar getToolbar();

    void setCollapsingToolbar(String toolbarTitle);

    void bindSandwich(Sandwich sandwich);

    void bindHeaderImage(Bitmap bitmap);

    void bindHeaderImage(Drawable drawable);

    void showImageProgressBar();

    void showImageErrorText();

    void hideImageProgressBar();
}
