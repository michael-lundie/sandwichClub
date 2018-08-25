package io.lundie.michael.sandwichclub.utils;

import android.text.TextUtils;
import android.util.Log;

import io.lundie.michael.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String sandwichJson) throws JSONException {

        Sandwich mySandwich = new Sandwich();

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(sandwichJson)) {
            return null;
        }

        // Let's create our JSON object from the provided string input to our method.
        JSONObject jsonSandwichObject = new JSONObject(sandwichJson);

        // Grabbing our name object so we can parse that data.
        JSONObject sandwichNameJso = jsonSandwichObject.getJSONObject("name");
        Log.i("TEST", "JSON data: " + sandwichNameJso);

        // Parse the mainName string

        mySandwich.setMainName(sandwichNameJso.getString("mainName"));
        Log.i("TEST", "JSON data: " + mySandwich.getMainName());

        // Parse our alsoKnownAs array (list of pseudonyms)

        JSONArray alsoKnownAsJsa = sandwichNameJso.getJSONArray("alsoKnownAs");
        ArrayList<String> alsoKnownAs = new ArrayList<>();
        if (alsoKnownAsJsa != null) {
            // Add each pseudonym to our array list
            for (int pseudonym = 0; pseudonym < alsoKnownAsJsa.length(); pseudonym++) {
                alsoKnownAs.add(alsoKnownAsJsa.getString(pseudonym));
            }
        }

        // Add the array to our sandwich object
        mySandwich.setAlsoKnownAs(alsoKnownAs);
        Log.i("TEST", "JSON data: " + mySandwich.getAlsoKnownAs());

        // Let's parse our placeOfOrigin data next.
        mySandwich.setPlaceOfOrigin(jsonSandwichObject.getString("placeOfOrigin"));
        Log.i("TEST", "JSON data: " + mySandwich.getPlaceOfOrigin());

        // Next up: grab our image url
        // TODO: test and validate correct URL
        mySandwich.setImage(jsonSandwichObject.getString("image"));

        // Last, but not least... The ingredients! Omnomnom.
        JSONArray ingredientsJsa = jsonSandwichObject.getJSONArray("ingredients");
        ArrayList<String> ingredients = new ArrayList<>();
        if (ingredientsJsa != null) {
            for (int ingredient = 0; ingredient < ingredientsJsa.length(); ingredient++) {
                ingredients.add(ingredientsJsa.getString(ingredient));
            }
        }

        // Add the ingredients array list to our sandwich object.
        mySandwich.setIngredients(ingredients);
        Log.i("TEST", "JSON data: " + mySandwich.getIngredients());

        return mySandwich;

    }
}
