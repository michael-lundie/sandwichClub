package io.lundie.michael.sandwichclub.screens.sandwichlist.sandwichlistitem;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.squareup.picasso.Target;

import io.lundie.michael.sandwichclub.screens.common.view.ObservableViewMvc;
import io.lundie.michael.sandwichclub.sandwiches.Sandwich;
import io.lundie.michael.sandwichclub.screens.sandwichdetail.FetchImageUseCase;

public interface SandwichListItemViewMvc extends ObservableViewMvc<SandwichListItemViewMvc.Listener>, FetchImageUseCase.Listener {

    @Override
    View getRootView();

    @Override
    void onImageFetched(Bitmap bitmap);

    @Override
    void onImagePreparing(Drawable placeHolderDrawable, Target target);

    @Override
    void onImageFetchFailed(Drawable errorDrawable);

    interface Listener {
        void onSandwichClicked(Sandwich sandwich);
    }

    void registerListener(Listener listener);
    void unregisterListener(Listener listener);
    void bindSandwich(Sandwich sandwich);
}
