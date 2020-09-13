package io.lundie.michael.sandwichclub.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.lundie.michael.sandwichclub.network.SandwichNameSchema;
import io.lundie.michael.sandwichclub.network.SandwichSchema;

public class SandwichSchemaJsonParser {

    private JSONBuilder jsonBuilder;
    private JSONObject sandwichObject;
    private JSONObject sandwichNameSchemaObject;

    public SandwichSchemaJsonParser(JSONBuilder jsonBuilder) {
        this.jsonBuilder = jsonBuilder;
    }

    public ArrayList<SandwichSchema> parseJsonSandwichesData() throws JSONException {
        JSONArray sandwichObjects = jsonBuilder.getRootJsonArray();

        ArrayList<SandwichSchema> sandwichSchemas = new ArrayList<>();

        for (int i = 0; i < sandwichObjects.length(); i++) {
            JSONObject sandwichObject = sandwichObjects.getJSONObject(i);
            SandwichSchema sandwichSchema = new SandwichSchema();
            sandwichSchema.setName(parseSandwichNameSchemaObject(sandwichObject.getJSONObject("name")));
            sandwichSchemas.add(sandwichSchema);
        }

        return sandwichSchemas;
    }

    private SandwichNameSchema parseSandwichNameSchemaObject(JSONObject sandwichNameSchemaObject) throws JSONException {
        SandwichNameSchema nameSchema = new SandwichNameSchema();
        String name = sandwichNameSchemaObject.getString("mainName");
        nameSchema.setMainName(name);

        List<String> alsoKnownAs =
                parseArrayField(sandwichNameSchemaObject.getJSONArray("alsoKnownAs"));
        nameSchema.setAlsoKnownAs(alsoKnownAs);
        return nameSchema;
    }

    private String parseStringField(JSONObject jsonObject, String fieldLabel) throws JSONException {
        String stringField = "";
        stringField = jsonObject.getString(fieldLabel);
        return stringField;
    }

    private ArrayList<String> parseArrayField(JSONArray jsonArray) {

        ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                System.out.println("known as array" + jsonArray.getString(i));
                arrayList.add(jsonArray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return arrayList;
    }
}
