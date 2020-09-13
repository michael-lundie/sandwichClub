package io.lundie.michael.sandwichclub.network;

import java.util.List;

public class SandwichSchema {
    private SandwichNameSchema name;
    private String placeOfOrigin;
    private String description;
    private String image;
    private String imageLarge;
    private String imageLicense;
    private String imageAuthor;
    private String imageAuthorLink;
    private List<String> ingredients = null;

    public SandwichNameSchema getName() {
        return name;
    }

    public void setName(SandwichNameSchema name) {
        this.name = name;
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageLarge() {
        return imageLarge;
    }

    public void setImageLarge(String imageLarge) {
        this.imageLarge = imageLarge;
    }

    public String getImageLicense() {
        return imageLicense;
    }

    public void setImageLicense(String imageLicense) {
        this.imageLicense = imageLicense;
    }

    public String getImageAuthor() {
        return imageAuthor;
    }

    public void setImageAuthor(String imageAuthor) {
        this.imageAuthor = imageAuthor;
    }

    public String getImageAuthorLink() {
        return imageAuthorLink;
    }

    public void setImageAuthorLink(String imageAuthorLink) {
        this.imageAuthorLink = imageAuthorLink;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

}
