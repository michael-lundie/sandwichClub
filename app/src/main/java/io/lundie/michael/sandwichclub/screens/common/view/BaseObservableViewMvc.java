package io.lundie.michael.sandwichclub.screens.common.view;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class BaseObservableViewMvc <ListenerType> extends BaseViewMvc
        implements ObservableViewMvc<ListenerType> {

    private Set<ListenerType> listeners = new HashSet<>();

    @Override
    public void registerListener(ListenerType listener) {
        listeners.add(listener);
    }

    @Override
    public void unregisterListener(ListenerType listener) {
        listeners.remove(listener);
    }

    /**
     * Gets set of listeners
     * @return unmodifiable set if listeners to prevent changes to this set by implementations
     * of MVC views.
     */
    protected Set<ListenerType> getListeners() {
        return Collections.unmodifiableSet(listeners);
    }
}
