package io.lundie.michael.sandwichclub.screens.common.controllers;

public interface UpPressedDispatcher {
    void registerListener(UpPressedListener upPressedListener);
    void unregisterListener(UpPressedListener upPressedListener);
}
