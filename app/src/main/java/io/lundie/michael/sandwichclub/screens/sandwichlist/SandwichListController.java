package io.lundie.michael.sandwichclub.screens.sandwichlist;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.lundie.michael.sandwichclub.sandwiches.FetchSandwichesUseCase;
import io.lundie.michael.sandwichclub.sandwiches.Sandwich;
import io.lundie.michael.sandwichclub.screens.common.controllers.UpPressedDispatcher;
import io.lundie.michael.sandwichclub.screens.common.controllers.UpPressedListener;
import io.lundie.michael.sandwichclub.screens.common.screensnavigator.ScreensNavigator;

public class SandwichListController implements SandwichListViewMvcImpl.Listener, FetchSandwichesUseCase.Listener, UpPressedListener {

    private final FetchSandwichesUseCase fetchSandwichesUseCase;
    private UpPressedDispatcher dispatcher;
    private final ScreensNavigator screensNavigator;
    private SandwichListViewMvc sandwichListViewMvc;
    private long lastSandwichFetch = 0L;
    private long refreshTimeNanoseconds = 60000000000L;

    private List<Sandwich> sandwiches;

    public SandwichListController(FetchSandwichesUseCase fetchSandwichesUseCase,
                                  UpPressedDispatcher dispatcher,
                                  ScreensNavigator screensNavigator) {
        this.fetchSandwichesUseCase = fetchSandwichesUseCase;
        this.dispatcher = dispatcher;
        this.screensNavigator = screensNavigator;
    }

    //MVC factory requires view as parent, so we must inject viewMvc after the fact (activity
    // construction), so that we can acquire parent view for our view mvc.
    public void bindView(SandwichListViewMvc viewMvc) {
        sandwichListViewMvc = viewMvc;
    }

    public void onStart() {
        if(sandwiches != null) {
            Log.e(getClass().getSimpleName(), "Sandwiches --> Restoring list from restored data");
            sandwichListViewMvc.bindSandwiches(sandwiches);
        }
        fetchSandwichesUseCase.registerListener(this);
        dispatcher.registerListener(this);
        sandwichListViewMvc.registerListener(this);

        //TODO: If case for logging purposes only - remove
        if(lastSandwichFetch != 0L) {
            long seconds;
            seconds = System.nanoTime() - lastSandwichFetch;
            seconds = TimeUnit.NANOSECONDS.toSeconds(seconds);
            Log.i(getClass().getSimpleName(), "Last fetch was " + seconds + " seconds ago");
        }

        if(lastSandwichFetch == 0L || ((System.nanoTime() - lastSandwichFetch) > refreshTimeNanoseconds)) {
            lastSandwichFetch = System.nanoTime();
            sandwichListViewMvc.showProgressIndicator();
            fetchSandwichesUseCase.fetchSandwichesAndNotify();
        } else {
            sandwichListViewMvc.hideProgressIndicator();
        }
    }

    public void onStop() {
        dispatcher.unregisterListener(this);
        fetchSandwichesUseCase.unregisterListener(this);
    }

    @Override
    public void onSandwichesFetched(List<Sandwich> sandwiches) {
        sandwichListViewMvc.hideProgressIndicator();
        this.sandwiches = sandwiches;
        sandwichListViewMvc.bindSandwiches(this.sandwiches);
        Log.e(getClass().getSimpleName(), "SANDWICHES FETCHED");
    }

    @Override
    public void onSandwichesFetchFailed() {
        sandwichListViewMvc.hideProgressIndicator();
    }

    @Override
    public void onSandwichClicked(Sandwich sandwich) {
        Log.e(getClass().getSimpleName(), "Controller: Sandwich " + sandwich.getMainName() + " CLICKED.");
        screensNavigator.toScreenDetails(sandwich);
    }

    @Override
    public boolean onUpPressed() {
        return false;
    }

    public SavedState getSavedState() {
        return new SavedState(sandwiches, lastSandwichFetch);
    }

    public void restoreSavedState(SavedState savedState) {
        Log.i(getClass().getSimpleName(), "Restoring state.");
        sandwiches = savedState.sandwiches;
        lastSandwichFetch = savedState.lastFetched;
    }

    public static class SavedState implements Parcelable {
        private List<Sandwich> sandwiches;
        private Long lastFetched;

        public SavedState(List<Sandwich> sandwiches, Long lastFetched) {
            this.sandwiches = sandwiches;
            this.lastFetched = lastFetched;
        }

        protected SavedState(Parcel in) {
            sandwiches = in.createTypedArrayList(Sandwich.CREATOR);
            lastFetched = in.readLong();
        }

        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeTypedList(sandwiches);
            dest.writeLong(lastFetched);
        }
    }
}
