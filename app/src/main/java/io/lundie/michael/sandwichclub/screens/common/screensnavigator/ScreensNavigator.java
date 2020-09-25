package io.lundie.michael.sandwichclub.screens.common.screensnavigator;

import io.lundie.michael.sandwichclub.sandwiches.Sandwich;
import io.lundie.michael.sandwichclub.screens.common.controllers.NavHelper;
import io.lundie.michael.sandwichclub.screens.sandwichdetail.SandwichDetailFragmentDirections;
import io.lundie.michael.sandwichclub.screens.sandwichlist.SandwichListFragmentDirections;

public class ScreensNavigator {

    private NavHelper navHelper;

    public ScreensNavigator(NavHelper navHelper) {
        this.navHelper = navHelper;
    }

    /*
      We will keep our frame layout out of the construction set here.
     */
    public void toScreenDetails(Sandwich sandwich) {
        navHelper.getNavController().navigate(SandwichListFragmentDirections.relayListDestToDetailDest(sandwich));
    }


    public void toScreenList() {
        navHelper.getNavController().navigate(SandwichDetailFragmentDirections.detailDestToListDestPop());
    }
}