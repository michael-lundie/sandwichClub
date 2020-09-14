package io.lundie.michael.sandwichclub.screens.sandwichlist;

import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.lundie.michael.sandwichclub.sandwiches.Sandwich;
import io.lundie.michael.sandwichclub.screens.common.ViewMvcFactory;
import io.lundie.michael.sandwichclub.screens.sandwichlist.sandwichlistitem.SandwichListItemViewMvc;

public class SandwichRecyclerAdapter extends RecyclerView.Adapter<SandwichRecyclerAdapter.ViewHolder>
    implements  SandwichListItemViewMvc.Listener {

    public interface Listener {
        void onSandwichClicked(Sandwich sandwich);
    }

    private final Listener listener;
    private ViewMvcFactory viewMvcFactory;

    private List<Sandwich> sandwiches = new ArrayList<>();

    public SandwichRecyclerAdapter(Listener listener, ViewMvcFactory viewMvcFactory) {
        this.listener = listener;
        this.viewMvcFactory = viewMvcFactory;
    }

    @Override
    public void onSandwichClicked(Sandwich sandwich) {
        Log.i(getClass().getSimpleName(), "Recycler Adapter: Click registered");
        this.listener.onSandwichClicked(sandwich);
    }

    public void bindSandwiches(List<Sandwich> sandwiches) {
        this.sandwiches = new ArrayList<>(sandwiches);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SandwichListItemViewMvc viewMvc = viewMvcFactory.getSandwichListItemViewMvc(parent);
        viewMvc.registerListener(this);
        return new ViewHolder(viewMvc);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.viewMvc.bindSandwich(sandwiches.get(position));
    }

    @Override
    public int getItemCount() {
        return (sandwiches != null) ? sandwiches.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final SandwichListItemViewMvc viewMvc;

        public ViewHolder(SandwichListItemViewMvc viewMvc) {
            super(viewMvc.getRootView());
            this.viewMvc = viewMvc;
        }
    }
}
