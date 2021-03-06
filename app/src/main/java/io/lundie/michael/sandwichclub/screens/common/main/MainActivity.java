package io.lundie.michael.sandwichclub.screens.common.main;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import io.lundie.michael.sandwichclub.R;
import io.lundie.michael.sandwichclub.screens.common.controllers.BaseActivity;
import io.lundie.michael.sandwichclub.screens.common.controllers.UpPressedDispatcher;
import io.lundie.michael.sandwichclub.screens.common.controllers.UpPressedListener;

public class MainActivity extends BaseActivity implements UpPressedDispatcher {

    private final List<UpPressedListener> upPressedListeners = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_host);
    }

    @Override
    public void registerListener(UpPressedListener listener) {
        upPressedListeners.add(listener);
    }

    @Override
    public void unregisterListener(UpPressedListener listener) {
        upPressedListeners.remove(listener);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            onBackPressed();
        } return true;
    }

    @Override
    public void onBackPressed() {
        boolean upPressConsumed = false;
        for(UpPressedListener listener : upPressedListeners) {
            if(listener.onUpPressed()) {
                upPressConsumed = true;
            }
        }
        if(!upPressConsumed) super.onBackPressed();
    }
}