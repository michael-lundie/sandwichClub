package io.lundie.michael.sandwichclub.screens.common;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import io.lundie.michael.sandwichclub.screens.sandwichdetail.SandwichDetailViewMvc;
import io.lundie.michael.sandwichclub.screens.sandwichdetail.SandwichDetailViewMvcImpl;
import io.lundie.michael.sandwichclub.screens.sandwichlist.sandwichlistitem.SandwichListItemViewMvc;
import io.lundie.michael.sandwichclub.screens.sandwichlist.sandwichlistitem.SandwichListItemViewMvcImpl;
import io.lundie.michael.sandwichclub.screens.sandwichlist.SandwichListViewMvc;
import io.lundie.michael.sandwichclub.screens.sandwichlist.SandwichListViewMvcImpl;

public class ViewMvcFactory {

    private final LayoutInflater layoutInflater;
    private FetchImageUseCaseFactory imageUseCaseFactory;

    public ViewMvcFactory(LayoutInflater layoutInflater, FetchImageUseCaseFactory imageUseCaseFactory) {
        this.layoutInflater = layoutInflater;
        this.imageUseCaseFactory = imageUseCaseFactory;
    }

    public SandwichListViewMvc getSandwichListViewMvc(@Nullable ViewGroup parent) {
        return new SandwichListViewMvcImpl(layoutInflater, parent, this, imageUseCaseFactory);
    }

    public SandwichDetailViewMvc getSandwichDetailViewMvc(@Nullable ViewGroup parent) {
        return new SandwichDetailViewMvcImpl(layoutInflater, parent);
    }

    public SandwichListItemViewMvc getSandwichListItemViewMvc(@Nullable ViewGroup parent) {
        return new SandwichListItemViewMvcImpl(layoutInflater, parent);
    }
}
