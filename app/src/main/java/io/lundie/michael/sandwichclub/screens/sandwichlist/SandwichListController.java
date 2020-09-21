package io.lundie.michael.sandwichclub.screens.sandwichlist;

import android.util.Log;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.lundie.michael.sandwichclub.sandwiches.FetchSandwichesUseCase;
import io.lundie.michael.sandwichclub.sandwiches.Sandwich;
import io.lundie.michael.sandwichclub.screens.common.screensnavigator.ScreensNavigator;

public class SandwichListController implements SandwichListViewMvcImpl.Listener, FetchSandwichesUseCase.Listener {

    private final FetchSandwichesUseCase fetchSandwichesUseCase;
    private final ScreensNavigator screensNavigator;
    private SandwichListViewMvc sandwichListViewMvc;
    private long lastSandwichFetch = 0L;
    private long refreshTimeNanoseconds = 60000000000L;

    public SandwichListController(FetchSandwichesUseCase fetchSandwichesUseCase,
                                  ScreensNavigator screensNavigator) {
        this.fetchSandwichesUseCase = fetchSandwichesUseCase;
        this.screensNavigator = screensNavigator;
    }

    //MVC factory requires view as parent, so we must inject viewMvc after the fact (activity
    // construction), so that we can acquire parent view for our view mvc.
    public void bindView(SandwichListViewMvc viewMvc) {
        sandwichListViewMvc = viewMvc;
    }

    public void onStart() {
        fetchSandwichesUseCase.registerListener(this);
        sandwichListViewMvc.registerListener(this);

        if(lastSandwichFetch != 0L) {
            long seconds;
            seconds = System.nanoTime() - lastSandwichFetch;
            seconds = TimeUnit.NANOSECONDS.toSeconds(seconds);
            Log.i(getClass().getSimpleName(), "Last fetch was " + seconds + " seconds ago");
        }

        if(lastSandwichFetch == 0L || ((System.nanoTime() - lastSandwichFetch) > refreshTimeNanoseconds)) {
            lastSandwichFetch = System.nanoTime();
            fetchSandwichesUseCase.fetchSandwichesAndNotify();
        }
    }

    public void onStop() {
        fetchSandwichesUseCase.unregisterListener(this);
    }

    @Override
    public void onSandwichesFetched(List<Sandwich> sandwiches) {
        sandwichListViewMvc.bindSandwiches(sandwiches);
        Log.i(getClass().getSimpleName(), "SANDWICHES FETCHED");
    }

    @Override
    public void onSandwichesFetchFailed() {

    }

    @Override
    public void onSandwichClicked(Sandwich sandwich) {
        Log.e(getClass().getSimpleName(), "Controller: Sandwich " + sandwich.getMainName() + " CLICKED.");
        screensNavigator.toScreenDetails(sandwich);
    }
}
