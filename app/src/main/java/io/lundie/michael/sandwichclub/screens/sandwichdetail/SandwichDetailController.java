package io.lundie.michael.sandwichclub.screens.sandwichdetail;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import io.lundie.michael.sandwichclub.sandwiches.Sandwich;
import io.lundie.michael.sandwichclub.screens.common.screensnavigator.ScreensNavigator;

public class SandwichDetailController implements FetchImageUseCase.Listener {

    private FetchImageUseCase fetchImageUseCase;
    SandwichDetailViewMvc sandwichDetailViewMvc;
    private final ScreensNavigator screensNavigator;
    private Sandwich sandwich;

    public SandwichDetailController(FetchImageUseCase fetchImageUseCase,
                                    ScreensNavigator screensNavigator) {
        this.fetchImageUseCase = fetchImageUseCase;
        this.screensNavigator = screensNavigator;
    }

    public void bindView(SandwichDetailViewMvc viewMvc) { sandwichDetailViewMvc = viewMvc; }

    public void setCollapsingToolbar() {
        sandwichDetailViewMvc.setCollapsingToolbar(sandwich.getMainName());
    }

    public void onStart() {
        fetchImageUseCase.registerListener(this);
        fetchImageUseCase.fetchImageAndNotify(sandwich.getImage());
    }

    public void onStop() {
        fetchImageUseCase.unregisterListener(this);
    }

    @Override
    public void onImageFetched(Bitmap bitmap) {
        sandwichDetailViewMvc.bindHeaderImage(bitmap);
        sandwichDetailViewMvc.hideImageProgressBar();
    }

    @Override
    public void onImagePreparing(Drawable placeHolderDrawable) {
        sandwichDetailViewMvc.showImageProgressBar();
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
        sandwichDetailViewMvc.bindSandwich(sandwich);
        sandwichDetailViewMvc.setCollapsingToolbar(sandwich.getMainName());
    }


}
