package io.lundie.michael.sandwichclub.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ResourceBundle;

public class JSONBuilder {

    private final JSONObject rootJsonObject;

    public JSONBuilder(String json) throws JSONException {
        rootJsonObject = new JSONObject(json);
    }

    public JSONObject getRootObject() {
        return rootJsonObject;
    }

    public JSONArray getRootJsonArray() throws JSONException {
        return getRootObject().getJSONArray("Sandwiches");
    }

    public JSONObject getJsonObject(String fieldName) throws JSONException {
        return rootJsonObject.getJSONObject(fieldName);
    }

    public JSONArray getJsonArray(String fieldName) throws JSONException {
        return rootJsonObject.getJSONArray(fieldName);
    }

    public JSONArray getNestedArray(JSONObject object, String fieldName) throws JSONException {
        return rootJsonObject.getJSONArray(fieldName);
    }
}
