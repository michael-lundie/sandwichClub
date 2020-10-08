package io.lundie.michael.sandwichclub.screens.sandwichdetail;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerDrawable;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import io.lundie.michael.sandwichclub.R;
import io.lundie.michael.sandwichclub.common.BaseObservable;

public class FetchImageUseCase extends BaseObservable<FetchImageUseCase.Listener> {

    Picasso picasso;
    private final Target target;

    public interface Listener {
        void onImageFetched(Bitmap bitmap);
        void onImageFetchedFromCache(Bitmap bitmap);
        void onImagePreparing(Drawable placeHolderDrawable, Target target);
        void onImageFetchFailed(Drawable errorDrawable);
    }

    public FetchImageUseCase(Picasso picasso) {
        this.picasso = picasso;
        this.target = createPicassoTarget();
    }

    private Target createPicassoTarget() {
        return new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                switch(from) {
                    case DISK:
                    case MEMORY:
                        notifyImageLoadedFromCache(bitmap);
                        break;
                    case NETWORK:
                        notifyImageLoaded(bitmap);
                        break;
                    default:
                        throw new RuntimeException("Handling of picasso error: " + from
                                + "is not implemented. Check Use Case implementation.");
                }

            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                notifyImageFailed(errorDrawable);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                notifyImagePreparing(placeHolderDrawable);
            }
        };
    }

    public void fetchImageAndNotify(String imageUrl) {

        Shimmer shimmer = new Shimmer.ColorHighlightBuilder().build();
        ShimmerDrawable shimmerDrawable = new ShimmerDrawable();
        shimmerDrawable.setShimmer(shimmer);

        String url = "http://quest.lundie.io/sandwichclub/" + imageUrl;
            Picasso.get()
                    .load(url)
                    .error(R.drawable.picasso_error_drawable)
                    .placeholder(shimmerDrawable)
                    .into(target);
    }

    private void notifyImageLoadedFromCache(Bitmap bitmap) {
        for(Listener listener : getListeners()) {
            listener.onImageFetchedFromCache(bitmap);
        }
        if(getListeners().isEmpty()) {
            Log.e(getClass().getSimpleName(), "image load from cache: 2No Listener");
        }
    }

    private void notifyImageLoaded(Bitmap bitmap) {
        for(Listener listener : getListeners()) {
            listener.onImageFetched(bitmap);
        }
        if(getListeners().isEmpty()) {
            Log.e(getClass().getSimpleName(), "image loaded: No Listener");
        }
    }

    private void notifyImageFailed(Drawable errorDrawable) {
        for(Listener listener : getListeners()) {
            listener.onImageFetchFailed(errorDrawable);
        }
        if(getListeners().isEmpty()) {
            Log.e(getClass().getSimpleName(), "Image fail: No Listener");
        }
    }

    private void notifyImagePreparing(Drawable placeHolderDrawable) {
        for(Listener listener : getListeners()) {
            listener.onImagePreparing(placeHolderDrawable, target);
        }
        if(getListeners().isEmpty()) {
            Log.e(getClass().getSimpleName(), "Image prep: No Listener");
        }
    }
}
