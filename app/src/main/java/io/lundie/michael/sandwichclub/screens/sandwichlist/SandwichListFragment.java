package io.lundie.michael.sandwichclub.screens.sandwichlist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import io.lundie.michael.sandwichclub.screens.common.controllers.BaseFragment;

public class SandwichListFragment extends BaseFragment {

    private static final String SAVED_STATE_CONTROLLER = "SAVED_STATE_CONTROLLER";

    public static Fragment newInstance() {
        return new SandwichListFragment();
    };

    private SandwichListController sandwichListController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        SandwichListViewMvc viewMvc = getCompositionRoot().getViewMvcFactory().getSandwichListViewMvc(container);
        setToolbarWithTitle(viewMvc.getToolbar(), false);
        sandwichListController = getCompositionRoot().getSandwichListController();
        if (savedInstanceState != null) {
            Log.e(getClass().getSimpleName(), " List fragment --> Restoring State.");
            restoreControllerState(savedInstanceState);
        }
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

    private void restoreControllerState(Bundle savedInstanceState) {
        Log.e(getClass().getSimpleName(), " List fragment --> Restoring CONTROLLER.");
        sandwichListController.restoreSavedState(
                (SandwichListController.SavedState)
                        savedInstanceState.getParcelable(SAVED_STATE_CONTROLLER)
        );
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.e(getClass().getSimpleName(), "Fragment --> Writing outstate from controller");
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_STATE_CONTROLLER, sandwichListController.getSavedState());
    }
}