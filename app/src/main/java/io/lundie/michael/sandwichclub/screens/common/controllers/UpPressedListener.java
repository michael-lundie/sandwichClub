package io.lundie.michael.sandwichclub.screens.common.controllers;

/**
 * See excellent Activity/Fragment onBackPressed solution here: https://stackoverflow.com/a/46425415
 */
public interface UpPressedListener {
    /**
     *
     * @return True if the listener has handled the back press or false if not.
     */
    boolean onUpPressed();
}
