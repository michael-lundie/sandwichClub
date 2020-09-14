package io.lundie.michael.sandwichclub.screens.sandwichlist;

import android.os.Bundle;

import io.lundie.michael.sandwichclub.screens.common.controllers.BaseActivity;

public class SandwichListActivity extends BaseActivity{

    private SandwichListController sandwichListController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SandwichListViewMvc viewMvc = getCompositionRoot().getViewMvcFactory().getSandwichListViewMvc(null);
        setSupportActionBar(viewMvc.getToolbar());
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        sandwichListController = getCompositionRoot().getSandwichListController();
        sandwichListController.bindView(viewMvc);
        setContentView(viewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        sandwichListController.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        sandwichListController.onStop();
    }

}
