package io.lundie.michael.sandwichclub.screens.sandwichlist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.lundie.michael.sandwichclub.R;
import io.lundie.michael.sandwichclub.model.Sandwich;

public class SandwichListAdapter extends ArrayAdapter<Sandwich> implements SandwichListItemViewMvc.Listener {
    private final OnSandwichClickedListener onSandwichClickedListener;

    public interface OnSandwichClickedListener {
        void onSandwichClicked(Sandwich sandwich);
    }

    public SandwichListAdapter(Context context,
                               OnSandwichClickedListener onSandwichClickedListener) {
        super(context, 0);
        this.onSandwichClickedListener = onSandwichClickedListener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Log.e(this.getClass().getSimpleName(), "Sandwich Adapter getView called.");

        if (convertView == null) {
            SandwichListItemViewMvc viewMvc = new SandwichListItemViewMvcImpl(
                    LayoutInflater.from(getContext()), parent);
            viewMvc.registerListener(this);
            convertView = viewMvc.getRootView();
            convertView.setTag(viewMvc);
        }

        final Sandwich sandwich = getItem(position);

        // binding data to the view
        SandwichListItemViewMvc viewMvc = (SandwichListItemViewMvc) convertView.getTag();
        viewMvc.bindSandwich(sandwich);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSandwichClicked(sandwich);
            }
        });

        return convertView;
    }

    @Override
    public void onSandwichClicked(Sandwich sandwich) {
        this.onSandwichClickedListener.onSandwichClicked(sandwich);
    }
}