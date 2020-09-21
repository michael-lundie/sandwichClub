package io.lundie.michael.sandwichclub.screens.sandwichlist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.lundie.michael.sandwichclub.R;
import io.lundie.michael.sandwichclub.screens.common.view.BaseObservableViewMvc;
import io.lundie.michael.sandwichclub.sandwiches.Sandwich;
import io.lundie.michael.sandwichclub.screens.common.ViewMvcFactory;

public class SandwichListViewMvcImpl extends BaseObservableViewMvc<SandwichListViewMvc.Listener>
        implements SandwichRecyclerAdapter.Listener, SandwichListViewMvc {

    private RecyclerView sandwichesRv;
    private SandwichRecyclerAdapter adapter;

    public SandwichListViewMvcImpl(LayoutInflater inflater, @Nullable ViewGroup parent, ViewMvcFactory viewMvcFactory) {
        setRootView(inflater.inflate(R.layout.layout_sandwich_list, parent, false));
        sandwichesRv = findViewById(R.id.sandwiches_listview);
        sandwichesRv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SandwichRecyclerAdapter(this, viewMvcFactory);
        sandwichesRv.setAdapter(adapter);
    }

    @Override
    public void onSandwichClicked(Sandwich sandwich) {
        for (Listener listener : getListeners()) {
            listener.onSandwichClicked(sandwich);
        }
    }

    @Override
    public Toolbar getToolbar() {
        return getRootView().findViewById(R.id.toolbar);
    }

    @Override
    public void bindSandwiches(List<Sandwich> sandwiches) {
        adapter.bindSandwiches(sandwiches);
    }
}
