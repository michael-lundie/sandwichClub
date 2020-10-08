package io.lundie.michael.sandwichclub.screens.sandwichlist.sandwichlistitem;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Target;

import java.util.HashMap;
import java.util.Map;

import io.lundie.michael.sandwichclub.R;
import io.lundie.michael.sandwichclub.screens.common.view.BaseObservableViewMvc;
import io.lundie.michael.sandwichclub.sandwiches.Sandwich;

public class SandwichListItemViewMvcImpl extends BaseObservableViewMvc<SandwichListItemViewMvc.Listener>
        implements SandwichListItemViewMvc {

    private final String LOG_TAG = this.getClass().getSimpleName();

    private final ImageView imageView;
    //private final ShimmerFrameLayout shimmerView;
    private Sandwich sandwich;
    private final TextView titleView;

    public SandwichListItemViewMvcImpl(LayoutInflater inflater, ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_sandwich_list_item, parent, false));
        titleView = findViewById(R.id.sandwich_item_title);
        imageView = findViewById(R.id.sandwich_item_image);
        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTransitionNames();
                Log.i(getClass().getSimpleName(), "ViewMvcImpl: Click registered");
                for (Listener listener : getListeners()) {
                    listener.onSandwichClicked(sandwich, getSharedElementMap());
                }
            }
        });
    }

    private void setTransitionNames() {
        titleView.setTransitionName(sandwich.getMainName() + "_title");
        imageView.setTransitionName(sandwich.getMainName() + "_img");
    }

    private Map<View, String> getSharedElementMap() {
        final Map<View, String> sharedElementMap = new HashMap<>();
        sharedElementMap.put(titleView, sandwich.getMainName() + "_title");
        sharedElementMap.put(imageView, sandwich.getMainName() + "_img");
        return sharedElementMap;
    }

    @Override
    public void bindSandwich(Sandwich sandwich) {
        this.sandwich = sandwich;
        titleView.setText(sandwich.getMainName());
    }


    //TODO: Complete image list item implementation.
    // see if anyone has any better ideas on how to do this.
    @Override
    public void onImageFetched(Bitmap bitmap) {
        Log.e(LOG_TAG, "Image fetched from network for " + sandwich.getMainName() );
        imageView.setImageBitmap(bitmap);
        //shimmerView.stopShimmer();
    }

    @Override
    public void onImageFetchedFromCache(Bitmap bitmap) {
        Log.e(LOG_TAG, "Image fetched from cache for " + sandwich.getMainName() );
        imageView.setImageBitmap(bitmap);
        //shimmerView.stopShimmer();
    }

    @Override
    public void onImagePreparing(Drawable placeHolderDrawable, Target target) {
        Log.e(LOG_TAG, "Image preparing for " + sandwich.getMainName() );
        imageView.setTag(target);
        imageView.setImageDrawable(placeHolderDrawable);
    }

    @Override
    public void onImageFetchFailed(Drawable errorDrawable) {

        Log.e(LOG_TAG, "Image failed for " + sandwich.getMainName() );
        //shimmerView.stopShimmer();
        imageView.setImageDrawable(errorDrawable);
    }
}
