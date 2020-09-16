package io.lundie.michael.sandwichclub.screens.sandwichdetail;

import androidx.appcompat.widget.Toolbar;

import io.lundie.michael.sandwichclub.sandwiches.Sandwich;
import io.lundie.michael.sandwichclub.screens.common.view.ViewMvc;

public interface SandwichDetailViewMvc extends ViewMvc {

    Toolbar getToolbar();

    void setCollapsingToolbar(String toolbarTitle);

    void bindSandwich(Sandwich sandwich);

    void loadImages(String imageUrl);

}
