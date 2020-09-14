package io.lundie.michael.sandwichclub.screens.sandwichlist.sandwichlistitem;

import io.lundie.michael.sandwichclub.screens.common.view.ObservableViewMvc;
import io.lundie.michael.sandwichclub.sandwiches.Sandwich;

public interface SandwichListItemViewMvc extends ObservableViewMvc<SandwichListItemViewMvc.Listener> {

    interface Listener {
        void onSandwichClicked(Sandwich sandwich);
    }

    void registerListener(Listener listener);
    void unregisterListener(Listener listener);
    void bindSandwich(Sandwich sandwich);
}
