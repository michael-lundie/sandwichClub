package io.lundie.michael.sandwichclub.screens.sandwichlist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.lundie.michael.sandwichclub.DetailActivity;
import io.lundie.michael.sandwichclub.screens.common.BaseActivity;
import io.lundie.michael.sandwichclub.model.Sandwich;
import io.lundie.michael.sandwichclub.network.SandwichSchema;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class SandwichListActivity extends BaseActivity implements SandwichListViewMvcImpl.Listener {

    private SandwichListViewMvc viewMvc;

    private Gson gson;
    private Call apiCall;

    private List<SandwichSchema> sandwichSchemas = new ArrayList<>();
    private String sandwichesJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewMvc = getCompositionRoot().getViewMvcFactory().getSandwichListViewMvc(null);
        viewMvc.registerListener(this);
        setSupportActionBar(viewMvc.getToolbar());
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        setContentView(viewMvc.getRootView());

        gson = getCompositionRoot().getGson();
        apiCall = getCompositionRoot().getSandwichesApi();

        try {
            getJsonFromNetwork();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getJsonFromNetwork() throws IOException {

        apiCall.enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    sandwichesJson = responseBody.string();
                    getSandwiches(sandwichesJson);
                }
            }

            public void onFailure(Call call, IOException e) {
                Log.e(this.getClass().getSimpleName(), "Network Error Occurred", e.getCause());
            }
        });
    }

    private void getSandwiches(String jsonString) {
        sandwichSchemas = gson.fromJson(jsonString, new TypeToken<ArrayList<SandwichSchema>>() {}.getType());
        Log.i(getClass().getSimpleName(), "Index 1 name: " + sandwichSchemas.get(0).getName().getMainName());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                bindSandwiches(sandwichSchemas);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void bindSandwiches(List<SandwichSchema> schemas) {
        List<Sandwich> sandwichList = new ArrayList<>();
        for (SandwichSchema schema : schemas) {
            Sandwich sandwich = new Sandwich();
            sandwich.setMainName(schema.getName().getMainName());
            sandwich.setAlsoKnownAs(schema.getName().getAlsoKnownAs());
            sandwich.setDescription(schema.getDescription());
            sandwich.setImage(schema.getImage());
            sandwich.setIngredients(schema.getIngredients());
            sandwich.setPlaceOfOrigin(schema.getPlaceOfOrigin());
            sandwichList.add(sandwich);
            Log.i(this.getClass().getSimpleName(), "Creating Sandwich from schema: " + sandwich.getMainName());
        }
        viewMvc.bindSandwiches(sandwichList);
    }

    private void launchDetailActivity(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_POSITION, position);
        startActivity(intent);
    }

    @Override
    public void onSandwichClicked(Sandwich sandwich) {
        Toast.makeText(this, "Sandwich clicked", Toast.LENGTH_SHORT).show();
    }
}
