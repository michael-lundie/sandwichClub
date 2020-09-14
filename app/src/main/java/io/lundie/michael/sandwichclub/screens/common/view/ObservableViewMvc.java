package io.lundie.michael.sandwichclub.screens.common.view;

public interface ObservableViewMvc<ListenerType> extends ViewMvc {

    void registerListener(ListenerType listener);
    void unregisterListener(ListenerType listener);

}
