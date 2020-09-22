package io.lundie.michael.sandwichclub.screens.common.main;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import java.util.HashSet;
import java.util.Set;

import io.lundie.michael.sandwichclub.R;
import io.lundie.michael.sandwichclub.screens.common.controllers.BaseActivity;
import io.lundie.michael.sandwichclub.screens.common.controllers.FragmentFrameWrapper;
import io.lundie.michael.sandwichclub.screens.common.controllers.UpPressedDispatcher;
import io.lundie.michael.sandwichclub.screens.common.controllers.UpPressedListener;
import io.lundie.michael.sandwichclub.screens.sandwichlist.SandwichListFragment;

public class MainActivity extends BaseActivity implements UpPressedDispatcher, FragmentFrameWrapper {

    private final Set<UpPressedListener> upPressedListeners = new HashSet<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_contents_frame);
        if(savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            SandwichListFragment listFragment = new SandwichListFragment();
            ft.add(R.id.contents_frame, listFragment).commit();
        }
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

    @Override
    public FrameLayout getFragmentFrame() {
        return findViewById(R.id.contents_frame);
    }
}