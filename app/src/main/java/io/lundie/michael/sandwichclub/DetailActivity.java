package io.lundie.michael.sandwichclub;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import io.lundie.michael.sandwichclub.model.Sandwich;
import io.lundie.michael.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

import java.util.List;

/**
 * Detail Activity for sandwich club app.
 * Note: The UI makes use of modified code from Chris Banes:
 * https://plus.google.com/+ChrisBanes/posts/J9Fwbc15BHN
 */
public class DetailActivity extends AppCompatActivity {

    public static final String LOG_TAG = DetailActivity.class.getName();

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        // Fetch dummy JSON data.
        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        // Return sandwich object for the currently selected sandwich. Delicious!
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error parsing JSON", e);
        }
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        String detailTitle = sandwich.getMainName();

        // Check if phone is in portrait mode then adjust font sizes to fit any long titles.
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT &&
                detailTitle.length() > 14 ){
            collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedToolBarSmall);
            collapsingToolbar.setExpandedTitleMargin(48,0,16,112);
        }

        collapsingToolbar.setTitle(detailTitle);

        //Looks like we have a sandwich, success! Let's start chewing our way through it. (Geddit ;] )
        populateUI(sandwich.getPlaceOfOrigin(), sandwich.getAlsoKnownAs(), sandwich.getIngredients(),
                sandwich.getDescription());

        // Get reference to the progress bar view
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbar);
        final TextView imageErrorTv = (TextView) findViewById(R.id.image_error);

        //Set-up our image using Picasso.
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv, new Callback() {
                    @Override
                    public void onSuccess() {
                        // Hide our progress bar view on completion of image download.
                        progressBar.setVisibility(View.GONE);
                    }
                    @Override
                    public void onError() {
                        // On error, hide progress bar and show error text in UI.
                        progressBar.setVisibility(View.GONE);
                        imageErrorTv.setVisibility(View.VISIBLE);
                    }
                });

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    /**
     * A method to populate the user interface with our parsed dummy JSON data.
     * @param originText Place of origin text as String.
     * @param pseudonymsList List of pseudonyms as List
     * @param ingredientsList List of ingredients as List.
     * @param descriptionText Description text as String.
     */
    private void populateUI(String originText, List pseudonymsList, List ingredientsList,
                            String descriptionText) {

        // Get reference to our view objects.
        TextView originTv = (TextView) findViewById(R.id.origin_tv);
        TextView alsoKnownAsTv = (TextView)  findViewById(R.id.also_known_tv);
        TextView ingredientsTv = (TextView) findViewById(R.id.ingredients_tv);
        TextView descriptionTV = (TextView) findViewById(R.id.description_tv);

        // Check if origin text data exists. If not, set appropriate text.
        if(originText.length() == 0 ) {
            originText = this.getString(R.string.detail_no_origin);
        }

        originTv.setText(originText);
        descriptionTV.setText(descriptionText);
        ingredientsTv.setText(listStringBuilder(ingredientsList));

        // Check if pseudonyms exist and deal with each case appropriately.
        if(pseudonymsList.size() == 0 ) {
            alsoKnownAsTv.setText(this.getString(R.string.detail_no_pseudonym));
        } else {
            alsoKnownAsTv.setText(listStringBuilder(pseudonymsList));
        }
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