package io.lundie.michael.sandwichclub.screens.sandwichlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

import io.lundie.michael.sandwichclub.R;
import io.lundie.michael.sandwichclub.sandwiches.Sandwich;
import io.lundie.michael.sandwichclub.screens.common.FetchImageUseCaseFactory;
import io.lundie.michael.sandwichclub.screens.common.ViewMvcFactory;
import io.lundie.michael.sandwichclub.screens.common.view.BaseObservableViewMvc;

public class SandwichListViewMvcImpl extends BaseObservableViewMvc<SandwichListViewMvc.Listener>
        implements SandwichRecyclerAdapter.Listener, SandwichListViewMvc {

    private RecyclerView sandwichesRv;
    private ContentLoadingProgressBar sandwichesListPb;
    private SandwichRecyclerAdapter adapter;

    public SandwichListViewMvcImpl(LayoutInflater inflater, @Nullable ViewGroup parent,
                                   ViewMvcFactory viewMvcFactory, FetchImageUseCaseFactory imageUseCaseFactory) {
        setRootView(inflater.inflate(R.layout.layout_sandwich_list, parent, false));
        sandwichesRv = findViewById(R.id.sandwiches_listview);
        sandwichesListPb = findViewById(R.id.sandwich_list_pb);
        sandwichesRv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SandwichRecyclerAdapter(this, viewMvcFactory, imageUseCaseFactory);
        sandwichesRv.setAdapter(adapter);
    }

    @Override
    public void onSandwichClicked(Sandwich sandwich, Map<View, String> sharedElementsForTransition) {
        for (Listener listener : getListeners()) {
            listener.onSandwichClicked(sandwich, sharedElementsForTransition);
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

    @Override
    public void showProgressIndicator() {
        sandwichesListPb.show();
    }

    @Override
    public void hideProgressIndicator() {
        sandwichesListPb.hide();
    }

}
