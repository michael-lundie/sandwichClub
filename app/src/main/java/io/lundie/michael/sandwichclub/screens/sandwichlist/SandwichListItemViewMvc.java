package io.lundie.michael.sandwichclub.screens.sandwichlist;

import io.lundie.michael.sandwichclub.common.ObservableViewMvc;
import io.lundie.michael.sandwichclub.model.Sandwich;

public interface SandwichListItemViewMvc extends ObservableViewMvc<SandwichListItemViewMvc.Listener> {

    interface Listener {
        void onSandwichClicked(Sandwich sandwich);
    }

    void registerListener(Listener listener);
    void unregisterListener(Listener listener);
    void bindSandwich(Sandwich sandwich);
}
