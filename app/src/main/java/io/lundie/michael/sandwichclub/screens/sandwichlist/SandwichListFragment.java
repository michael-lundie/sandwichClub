package io.lundie.michael.sandwichclub.screens.sandwichlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import io.lundie.michael.sandwichclub.screens.common.controllers.BaseFragment;

public class SandwichListFragment extends BaseFragment {

    public static Fragment newInstance() {
        return new SandwichListFragment();
    };

    private SandwichListController sandwichListController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SandwichListViewMvc viewMvc = getCompositionRoot().getViewMvcFactory().getSandwichListViewMvc(container);
        setToolbarWithTitle(viewMvc.getToolbar(), false);
        sandwichListController = getCompositionRoot().getSandwichListController();
        sandwichListController.bindView(viewMvc);
        return viewMvc.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        sandwichListController.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        sandwichListController.onStop();
    }

}
