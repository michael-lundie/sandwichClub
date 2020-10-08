package io.lundie.michael.sandwichclub.screens.sandwichdetail;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.squareup.picasso.Target;

import io.lundie.michael.sandwichclub.common.BaseObservable;
import io.lundie.michael.sandwichclub.sandwiches.Sandwich;
import io.lundie.michael.sandwichclub.screens.common.controllers.UpPressedDispatcher;
import io.lundie.michael.sandwichclub.screens.common.controllers.UpPressedListener;
import io.lundie.michael.sandwichclub.screens.common.screensnavigator.ScreensNavigator;

public class SandwichDetailController extends BaseObservable<SandwichDetailController.Listener>
        implements FetchImageUseCase.Listener, UpPressedListener {

    private FetchImageUseCase fetchImageUseCase;
    private Listener listener;
    SandwichDetailViewMvc sandwichDetailViewMvc;
    private final ScreensNavigator screensNavigator;
    private UpPressedDispatcher dispatcher;
    private Sandwich sandwich;

    public interface Listener {
        void onImagesFetched();
    }

    public SandwichDetailController(FetchImageUseCase fetchImageUseCase,
                                    UpPressedDispatcher dispatcher,
                                    ScreensNavigator screensNavigator) {
        this.fetchImageUseCase = fetchImageUseCase;
        this.dispatcher = dispatcher;
        this.screensNavigator = screensNavigator;
    }

    public void bindView(SandwichDetailViewMvc viewMvc) { sandwichDetailViewMvc = viewMvc; }

    public void onStart() {
        dispatcher.registerListener(this);
        fetchImageUseCase.registerListener(this);
        fetchImageUseCase.fetchImageAndNotify(sandwich.getImage());
    }

    public void onStop() {
        dispatcher.unregisterListener(this);
        fetchImageUseCase.unregisterListener(this);
    }

    @Override
    public void onImagePreparing(Drawable placeHolderDrawable, Target target) {
        sandwichDetailViewMvc.showImageProgressBar();
    }

    @Override
    public void onImageFetched(Bitmap bitmap) {
        sandwichDetailViewMvc.bindHeaderImage(bitmap);
        sandwichDetailViewMvc.hideImageProgressBar();
        for(Listener listener : getListeners()) {
            listener.onImagesFetched();
        }
    }

    @Override
    public void onImageFetchedFromCache(Bitmap bitmap) {
        sandwichDetailViewMvc.bindHeaderImage(bitmap);
        sandwichDetailViewMvc.hideImageProgressBar();
        for(Listener listener : getListeners()) {
            listener.onImagesFetched();
        }
    }

    @Override
    public void onImageFetchFailed(Drawable errorDrawable) {
        sandwichDetailViewMvc.hideImageProgressBar();
        sandwichDetailViewMvc.showImageErrorText();
    }

    public void setSandwichData(Sandwich sandwich) {
        this.sandwich = sandwich;
        onSandwichDataReceived(sandwich);
    }

    private void onSandwichDataReceived(Sandwich sandwich) {
        sandwichDetailViewMvc.setCollapsingToolbar(sandwich.getMainName());
        sandwichDetailViewMvc.bindSandwich(sandwich);
    }

    @Override
    public boolean onUpPressed() {
        screensNavigator.toScreenList();
        return true;
    }
}
