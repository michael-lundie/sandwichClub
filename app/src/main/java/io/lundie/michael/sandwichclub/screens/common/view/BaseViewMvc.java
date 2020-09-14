package io.lundie.michael.sandwichclub.screens.common.view;

import android.content.Context;
import android.view.View;

public abstract class BaseViewMvc implements ViewMvc {
    private View rootView;

    @Override
    public View getRootView() {
        return rootView;
    }
    protected void setRootView(View rootView) {
        this.rootView = rootView;
    }


    protected Context getContext() {
        return getRootView().getContext();
    }

    protected  <T extends View> T findViewById(int id) {
        return getRootView().findViewById(id);
    }
}
