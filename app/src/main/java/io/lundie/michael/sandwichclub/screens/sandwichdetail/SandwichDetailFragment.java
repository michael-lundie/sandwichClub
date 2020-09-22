package io.lundie.michael.sandwichclub.screens.sandwichdetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.lundie.michael.sandwichclub.sandwiches.Sandwich;
import io.lundie.michael.sandwichclub.screens.common.controllers.BaseFragment;

public class SandwichDetailFragment extends BaseFragment {

    private SandwichDetailController sandwichDetailController;

    private SandwichDetailViewMvc viewMvc;

    public static final String PARCELABLE_EXTRA = "extra_parcel";

    public final String LOG_TAG = getClass().getSimpleName();

    public static SandwichDetailFragment newInstance(Sandwich sandwich) {
        SandwichDetailFragment detailFragment = new SandwichDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(PARCELABLE_EXTRA, sandwich);
        detailFragment.setArguments(args);
        return detailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewMvc = getCompositionRoot().getViewMvcFactory().getSandwichDetailViewMvc(container);

        sandwichDetailController = getCompositionRoot().getSandwichDetailController();
        sandwichDetailController.bindView(viewMvc);
        setToolbarWithTitle(viewMvc.getToolbar(), true);

        if(getArguments() != null) {
            sandwichDetailController.setSandwichData((Sandwich) getArguments().getParcelable(PARCELABLE_EXTRA));
        }
        return viewMvc.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        sandwichDetailController.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        sandwichDetailController.onStop();
    }
}
