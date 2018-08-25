package io.lundie.michael.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import io.lundie.michael.sandwichclub.model.Sandwich;
import io.lundie.michael.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

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
            //TODO: Improve try/catch
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        //Looks like we have a sandwich, success! Let's start chewing our way through it. (Geddit ;] )
        populateUI(sandwich.getPlaceOfOrigin(), sandwich.getAlsoKnownAs(), sandwich.getIngredients(),
                sandwich.getDescription());

        //Set-up our image using Picasso.
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(String originText, List pseudonymsList, List ingredientsList,
                            String descriptionText) {

        // Get reference to our view objects.
        TextView originTv = (TextView) findViewById(R.id.origin_tv);
        TextView alsoKnownAsTv = (TextView)  findViewById(R.id.also_known_tv);
        TextView ingredientsTv = (TextView) findViewById(R.id.ingredients_tv);
        TextView descriptionTV = (TextView) findViewById(R.id.description_tv);

        originTv.setText(originText);
        alsoKnownAsTv.setText(listStringBuilder(pseudonymsList));
        ingredientsTv.setText(listStringBuilder(ingredientsList));
        descriptionTV.setText(descriptionText);
    }

    private String listStringBuilder(List inputList) {
        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < inputList.size(); i++) {
            outputString.append(inputList.get(i));
            if(i != inputList.size()-1) { outputString.append(", "); }
        }
        return outputString.toString();
    }
}
