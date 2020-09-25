package io.lundie.michael.sandwichclub.screens.sandwichlist.sandwichlistitem;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.navigation.Navigation;

import io.lundie.michael.sandwichclub.R;
import io.lundie.michael.sandwichclub.screens.common.view.BaseObservableViewMvc;
import io.lundie.michael.sandwichclub.sandwiches.Sandwich;

public class SandwichListItemViewMvcImpl extends BaseObservableViewMvc<SandwichListItemViewMvc.Listener>
        implements SandwichListItemViewMvc {

    private Sandwich sandwich;
    private final TextView title;

    public SandwichListItemViewMvcImpl(LayoutInflater inflater, ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_sandwich_list_item, parent, false));
        title = findViewById(R.id.sandwich_item_title);
        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(getClass().getSimpleName(), "ViewMvcImpl: Click registered");
                for (Listener listener : getListeners()) {
                    listener.onSandwichClicked(sandwich);
                }
            }
        });
    }

    @Override
    public void bindSandwich(Sandwich sandwich) {
        this.sandwich = sandwich;
        title.setText(sandwich.getMainName());
    }
}
