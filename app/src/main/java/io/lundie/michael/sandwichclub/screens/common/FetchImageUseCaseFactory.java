package io.lundie.michael.sandwichclub.screens.common;

import com.squareup.picasso.Picasso;

import io.lundie.michael.sandwichclub.screens.sandwichdetail.FetchImageUseCase;

public class FetchImageUseCaseFactory {

    final Picasso picasso;

    public FetchImageUseCaseFactory(Picasso picasso) {
        this.picasso = picasso;
    }

    public FetchImageUseCase newFetchImageUseCase() {
        return new FetchImageUseCase(picasso);
    }
}
