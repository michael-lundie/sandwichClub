package io.lundie.michael.sandwichclub.screens.sandwichlist;

import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;

import java.util.List;
import java.util.Map;

import io.lundie.michael.sandwichclub.sandwiches.Sandwich;
import io.lundie.michael.sandwichclub.screens.common.view.ObservableViewMvc;

public interface SandwichListViewMvc extends ObservableViewMvc<SandwichListViewMvc.Listener> {
    interface Listener {
        void onSandwichClicked(Sandwich sandwich, Map<View, String> sharedElementsForTransition);
    }

    void registerListener(Listener listener);

    void unregisterListener(Listener listener);

    Toolbar getToolbar();

    void bindSandwiches(List<Sandwich> sandwichList);

    void showProgressIndicator();

    void hideProgressIndicator();
}
