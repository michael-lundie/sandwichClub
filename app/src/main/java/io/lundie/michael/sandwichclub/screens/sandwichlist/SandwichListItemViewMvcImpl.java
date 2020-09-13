package io.lundie.michael.sandwichclub.screens.sandwichlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.lundie.michael.sandwichclub.R;
import io.lundie.michael.sandwichclub.common.BaseObservableViewMvc;
import io.lundie.michael.sandwichclub.common.BaseViewMvc;
import io.lundie.michael.sandwichclub.model.Sandwich;

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
