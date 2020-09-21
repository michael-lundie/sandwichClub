package io.lundie.michael.sandwichclub.screens.sandwichdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;

import io.lundie.michael.sandwichclub.R;
import io.lundie.michael.sandwichclub.sandwiches.Sandwich;
import io.lundie.michael.sandwichclub.screens.common.controllers.BaseActivity;

public class SandwichDetailActivity extends BaseActivity {

    private SandwichDetailController sandwichDetailController;

    private SandwichDetailViewMvc viewMvc;

    public static final String PARCELABLE_EXTRA = "extra_parcel";


    public static void startActivity(Context context, Sandwich sandwich) {
        Intent intent = new Intent(context, SandwichDetailActivity.class);
        intent.putExtra(SandwichDetailActivity.PARCELABLE_EXTRA, sandwich);
        context.startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewMvc = getCompositionRoot().getViewMvcFactory().getSandwichDetailViewMvc(null);

        sandwichDetailController = getCompositionRoot().getSandwichDetailController();
        sandwichDetailController.bindView(viewMvc);

        sendSandwichToController(getIntent());

        setSupportActionBar(viewMvc.getToolbar());
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(viewMvc.getRootView());
    }

    private void sendSandwichToController(Intent intent) {
        if (intent == null || intent.getParcelableExtra(PARCELABLE_EXTRA) == null) {
            closeOnError();
        } else {
            Sandwich sandwich = intent.getParcelableExtra(PARCELABLE_EXTRA);
            sandwichDetailController.setSandwichData(sandwich);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        sandwichDetailController.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        sandwichDetailController.onStop();
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

}