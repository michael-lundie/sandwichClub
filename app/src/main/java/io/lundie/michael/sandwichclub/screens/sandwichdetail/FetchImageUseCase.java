package io.lundie.michael.sandwichclub.screens.sandwichdetail;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import io.lundie.michael.sandwichclub.common.BaseObservable;

public class FetchImageUseCase extends BaseObservable<FetchImageUseCase.Listener> {

    Picasso picasso;

    public FetchImageUseCase(Picasso picasso) {
        this.picasso = picasso;
    }

    public interface Listener {
        void onImageFetched(Bitmap bitmap);
        void onImagePreparing(Drawable placeHolderDrawable);
        void onImageFetchFailed(Drawable errorDrawable);
    }

    public void fetchImageAndNotify(String imageUrl) {
        String url = "http://quest.lundie.io/sandwichclub/" + imageUrl;
            Picasso.get()
                    .load(url)
                    .into(new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                            notifyImageLoaded(bitmap);
                        }

                        @Override
                        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                            notifyImageFailed(errorDrawable);
                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {
                            notifyImagePreparing(placeHolderDrawable);
                        }
                    });
    }

    private void notifyImageLoaded(Bitmap bitmap) {
        for(Listener listener : getListeners()) {
            listener.onImageFetched(bitmap);
        }
    }

    private void notifyImageFailed(Drawable errorDrawable) {
        for(Listener listener : getListeners()) {
            listener.onImageFetchFailed(errorDrawable);
        }
    }

    private void notifyImagePreparing(Drawable placeHolderDrawable) {
        for(Listener listener : getListeners()) {
            listener.onImagePreparing(placeHolderDrawable);
        }
    }
}
