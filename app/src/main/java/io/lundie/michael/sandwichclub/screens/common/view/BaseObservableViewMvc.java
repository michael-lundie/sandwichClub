package io.lundie.michael.sandwichclub.screens.common.view;

import androidx.navigation.NavController;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import io.lundie.michael.sandwichclub.screens.common.controllers.NavControlWrapper;

public abstract class BaseObservableViewMvc <ListenerType> extends BaseViewMvc
        implements ObservableViewMvc<ListenerType>, NavControlWrapper {

    private Set<ListenerType> listeners = new HashSet<>();
    private NavController navController;

    public void setNavController(NavController navController) {
        this.navController = navController;
    };

    @Override
    public NavController getNavController() {
        if(navController == null) {
            throw new NullPointerException("Cannot get nav controller before it is set on fragment inflation.");
        }
        return navController;
    }

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
