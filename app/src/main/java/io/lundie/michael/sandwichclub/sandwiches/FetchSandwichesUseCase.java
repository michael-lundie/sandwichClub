package io.lundie.michael.sandwichclub.sandwiches;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.lundie.michael.sandwichclub.common.AppExecutors;
import io.lundie.michael.sandwichclub.common.BaseObservable;
import io.lundie.michael.sandwichclub.network.SandwichSchema;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class FetchSandwichesUseCase extends BaseObservable<FetchSandwichesUseCase.Listener> {

    private final Call sandwichesDataDump;
    private final Gson gson;
    private AppExecutors appExecutors;
    List<Sandwich> sandwiches;

    public interface Listener {
        void onSandwichesFetched(List<Sandwich> sandwiches);
        void onSandwichesFetchFailed();
    }

    public FetchSandwichesUseCase(Call sandwichesDataDump, Gson gson, AppExecutors appExecutors) {
        this.sandwichesDataDump = sandwichesDataDump;
        this.gson = gson;
        this.appExecutors = appExecutors;
    }

    public void fetchSandwichesAndNotify() {
        appExecutors.networkIO().execute(new Runnable() {
            @Override
            public void run() {
                try (Response response = sandwichesDataDump.clone().execute()) {
                    //Success
                    try (ResponseBody responseBody = response.body()) {
                        String sandwichesJson = responseBody.string();
                        List<SandwichSchema> sandwichSchemas = gson.fromJson(
                                sandwichesJson, new TypeToken<ArrayList<SandwichSchema>>() {}.getType());
                        notifySuccess(sandwichSchemas);
                    }
                } catch (IOException e) {
                    notifyFailure();
                    e.printStackTrace();
                }
            }
        });
    }
    private void notifyFailure() {
        for (Listener listener : getListeners()) {
            listener.onSandwichesFetchFailed();
        }
    }

    private void notifySuccess(List<SandwichSchema> sandwichSchemas) {
        sandwiches = new ArrayList<>();
        for (int i = 0; i < sandwichSchemas.size(); i++) {
            SandwichSchema sandwichSchema = sandwichSchemas.get(i);
            Sandwich sandwich = new Sandwich();
            sandwich.setMainName(sandwichSchema.getName().getMainName());
            sandwich.setAlsoKnownAs(sandwichSchema.getName().getAlsoKnownAs());
            sandwich.setPlaceOfOrigin(sandwichSchema.getPlaceOfOrigin());
            sandwich.setIngredients(sandwichSchema.getIngredients());
            sandwich.setDescription(sandwichSchema.getDescription());
            sandwich.setImage(sandwichSchema.getImage());
            sandwich.setImageLarge(sandwichSchema.getImageLarge());
            sandwich.setImageLicense(sandwichSchema.getImageLicense());
            sandwich.setImageAuthor(sandwichSchema.getImageAuthor());
            sandwich.setImageLink(sandwichSchema.getImageAuthorLink());
            sandwiches.add(sandwich);
        }

        appExecutors.mainThread().execute(new Runnable() {
            @Override
            public void run() {
                for (Listener listener : getListeners()) {
                    listener.onSandwichesFetched(sandwiches);
                }
            }
        });
    }
}