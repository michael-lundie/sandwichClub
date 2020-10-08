package io.lundie.michael.sandwichclub.screens.sandwichdetail;

import android.os.Bundle;
import android.telecom.Call;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.lundie.michael.sandwichclub.R;
import io.lundie.michael.sandwichclub.sandwiches.Sandwich;
import io.lundie.michael.sandwichclub.screens.common.controllers.BaseFragment;

public class SandwichDetailFragment extends BaseFragment implements SandwichDetailController.Listener {

    private SandwichDetailController sandwichDetailController;

    private SandwichDetailViewMvc viewMvc;

    public static final String PARCELABLE_EXTRA = "sandwich_parcelable";

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
        super.onCreateView(inflater, container, savedInstanceState);
        viewMvc = getCompositionRoot().getViewMvcFactory().getSandwichDetailViewMvc(container);

        Transition transition = new DetailsTransition();
        setSharedElementEnterTransition(transition);

        sandwichDetailController = getCompositionRoot().getSandwichDetailController();
        sandwichDetailController.bindView(viewMvc);
        sandwichDetailController.registerListener(this);
        setToolbarWithTitle(viewMvc.getToolbar(), true);

        if(getArguments() != null) {
            Log.i(getClass().getSimpleName(), "Arguments not null.");

            sandwichDetailController.setSandwichData((Sandwich) getArguments().getParcelable(PARCELABLE_EXTRA));
        }
        return viewMvc.getRootView();
    }

    private void scheduleStartPostponedTransition(final View sharedElement) {
        sharedElement.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        sharedElement.getViewTreeObserver().removeOnPreDrawListener(this);
                        startPostponedEnterTransition();
                        return true;
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        //TODO: might not need the listener - since image has already been in cache.
        sandwichDetailController.registerListener(this);
        sandwichDetailController.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        sandwichDetailController.unregisterListener(this);
        sandwichDetailController.onStop();
    }

    @Override
    public void onImagesFetched() {
        //TODO: continue setting up shared element listener - get element from mvc view for animation,
        // need to allow for multiple views - use View... views
        scheduleStartPostponedTransition(viewMvc.getSharedTransitionView());
    }
}
