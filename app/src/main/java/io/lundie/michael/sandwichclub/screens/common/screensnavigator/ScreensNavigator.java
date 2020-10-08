package io.lundie.michael.sandwichclub.screens.common.screensnavigator;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.navigation.fragment.FragmentNavigator;

import java.util.Map;

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
    public void toScreenDetails(Sandwich sandwich, Map<View, String> sharedElements) {

        //TODO: Complete shared elements integration

        Log.e(getClass().getSimpleName(), "Element values" + sharedElements.values().toString());

        FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                .addSharedElements(sharedElements)
                .build();
        navHelper.getNavController().navigate(SandwichListFragmentDirections.relayListDestToDetailDest(sandwich), extras);
    }

    public void toScreenList() {
        navHelper.getNavController().navigate(SandwichDetailFragmentDirections.detailDestToListDestPop());
    }
}