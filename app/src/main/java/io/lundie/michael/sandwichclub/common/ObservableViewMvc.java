package io.lundie.michael.sandwichclub.common;

public interface ObservableViewMvc<ListenerType> extends ViewMvc {

    void registerListener(ListenerType listener);
    void unregisterListener(ListenerType listener);

}
