package io.lundie.michael.sandwichclub.screens.sandwichdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import io.lundie.michael.sandwichclub.R;
import io.lundie.michael.sandwichclub.sandwiches.Sandwich;
import io.lundie.michael.sandwichclub.screens.common.controllers.BaseActivity;

/**
 * Detail Activity for sandwich club app.
 * Note: The UI makes use of modified code from Chris Banes:
 * https://plus.google.com/+ChrisBanes/posts/J9Fwbc15BHN
 */
public class SandwichDetailActivity extends BaseActivity {

    public static final String LOG_TAG = SandwichDetailActivity.class.getName();

    public static final String PARCELABLE_EXTRA = "extra_parcel";

    public static void start(Context context, Sandwich sandwich) {
        Intent intent = new Intent(context, SandwichDetailActivity.class);
        intent.putExtra(SandwichDetailActivity.PARCELABLE_EXTRA, sandwich);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SandwichDetailViewMvc viewMvc = getCompositionRoot().getViewMvcFactory().getSandwichDetailViewMvc(null);

        setSupportActionBar(viewMvc.getToolbar());
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        Sandwich sandwich = intent.getParcelableExtra(PARCELABLE_EXTRA);
        if (sandwich == null) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        viewMvc.setCollapsingToolbar(sandwich.getMainName());

        viewMvc.loadImages(sandwich.getImage());

        setTitle(sandwich.getMainName());

        viewMvc.bindSandwich(sandwich);
        setContentView(viewMvc.getRootView());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }
}