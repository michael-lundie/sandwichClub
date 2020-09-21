package io.lundie.michael.sandwichclub.screens.sandwichdetail;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.lundie.michael.sandwichclub.R;
import io.lundie.michael.sandwichclub.common.BaseObservable;
import io.lundie.michael.sandwichclub.sandwiches.Sandwich;
import io.lundie.michael.sandwichclub.screens.common.ViewMvcFactory;
import io.lundie.michael.sandwichclub.screens.common.view.BaseObservableViewMvc;
import io.lundie.michael.sandwichclub.screens.common.view.BaseViewMvc;

public class SandwichDetailViewMvcImpl extends BaseObservableViewMvc<SandwichDetailViewMvc.Listener>
        implements SandwichDetailViewMvc {

    private ProgressBar progressBar;
    private ImageView headerIv;
    private TextView imageErrorTv;

    public SandwichDetailViewMvcImpl(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_sandwich_detail, parent, false));
        progressBar = findViewById(R.id.progressbar);
        headerIv = findViewById(R.id.image_iv);
        imageErrorTv = findViewById(R.id.image_error);
    }

    @Override
    public Toolbar getToolbar() {
        return findViewById(R.id.toolbar);
    }

    @Override
    public void setCollapsingToolbar(String toolbarTitle) {
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        if(toolbarTitle.isEmpty()) {
            toolbarTitle = getContext().getString(R.string.string_untitled);
        }

        if(getContext().getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT && toolbarTitle.length() > 14 ){
            collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedToolBarSmall);
            collapsingToolbar.setExpandedTitleMargin(48,0,16,112);
        }
    }



    @Override
    public void bindSandwich(Sandwich sandwich) {
        TextView originTv = (TextView) findViewById(R.id.origin_tv);
        TextView alsoKnownAsTv = (TextView)  findViewById(R.id.also_known_tv);
        TextView ingredientsTv = (TextView) findViewById(R.id.ingredients_tv);
        TextView descriptionTV = (TextView) findViewById(R.id.description_tv);

        String originText = sandwich.getPlaceOfOrigin();
        String descriptionText = sandwich.getDescription();
        List<String> ingredientsList = sandwich.getIngredients();
        List<String> pseudonymsList = sandwich.getAlsoKnownAs();

        if(sandwich.getPlaceOfOrigin().length() == 0 ) {
            originText = getContext().getString(R.string.detail_no_origin);
        }

        originTv.setText(originText);
        descriptionTV.setText(descriptionText);
        ingredientsTv.setText(listStringBuilder(ingredientsList));

        // Check if pseudonyms exist and deal with each case appropriately.
        if(pseudonymsList.size() == 0 ) {
            alsoKnownAsTv.setText(getContext().getString(R.string.detail_no_pseudonym));
        } else {
            alsoKnownAsTv.setText(listStringBuilder(pseudonymsList));
        }
    }

    @Override
    public void bindHeaderImage(Bitmap bitmap) {
        headerIv.setImageBitmap(bitmap);
    }

    @Override
    public void bindHeaderImage(Drawable drawable) {
        headerIv.setImageDrawable(drawable);
    }

    @Override
    public void showImageErrorText() {
        imageErrorTv.setVisibility(View.VISIBLE);
    }

    @Override
    public void showImageProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideImageProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    /**
     * A simple method to convert a list into a comma seperated value list.
     * @param inputList The input List
     * @return List as comma seperated value string
     */
    private String listStringBuilder(List inputList) {
        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < inputList.size(); i++) {
            outputString.append(inputList.get(i));
            if(i != inputList.size()-1) { outputString.append(", "); }
        }
        return outputString.toString();
    }
}
