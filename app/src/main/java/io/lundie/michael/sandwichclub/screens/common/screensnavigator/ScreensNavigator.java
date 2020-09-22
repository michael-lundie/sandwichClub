package io.lundie.michael.sandwichclub.screens.common.screensnavigator;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import io.lundie.michael.sandwichclub.R;
import io.lundie.michael.sandwichclub.sandwiches.Sandwich;
import io.lundie.michael.sandwichclub.screens.common.controllers.FragmentFrameWrapper;
import io.lundie.michael.sandwichclub.screens.sandwichdetail.SandwichDetailFragment;

public class ScreensNavigator {

    private final FragmentManager fragmentManager;
    private final FragmentFrameWrapper fragmentFrameWrapper;

    public ScreensNavigator(FragmentManager fragmentManager, FragmentFrameWrapper fragmentFrameWrapper) {
        this.fragmentManager = fragmentManager;
        this.fragmentFrameWrapper = fragmentFrameWrapper;
    }

    /*
      We will keep our frame layout out of the construction set here.
     */
    public void toScreenDetails(Sandwich sandwich) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        SandwichDetailFragment detailFragment = SandwichDetailFragment.newInstance(sandwich);
        ft.replace(fragmentFrameWrapper.getFragmentFrame().getId(), detailFragment).addToBackStack("detailsFrag").commit();
    }

    public void toScreenList() {
        fragmentManager.popBackStack();
    }
}
