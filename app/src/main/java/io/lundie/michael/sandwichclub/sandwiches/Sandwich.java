package io.lundie.michael.sandwichclub.sandwiches;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sandwich implements Parcelable {

    private String mainName;
    private List<String> alsoKnownAs = null;
    private String placeOfOrigin;
    private String description;
    private String image;
    private List<String> ingredients = null;
    private String imageLarge;
    private String imageLicense;
    private String imageAuthor;
    private String imageLink;

    /**
     * No args constructor for use in serialization
     */
    public Sandwich() {
    }

    public Sandwich(String mainName, List<String> alsoKnownAs, String placeOfOrigin, String description, String image, List<String> ingredients) {
        this.mainName = mainName;
        this.alsoKnownAs = alsoKnownAs;
        this.placeOfOrigin = placeOfOrigin;
        this.description = description;
        this.image = image;
        this.ingredients = ingredients;
    }

    public Sandwich(Parcel in) {
        this.mainName = in.readString();
        alsoKnownAs = new ArrayList<>();
        in.readStringList(alsoKnownAs);
        this.placeOfOrigin = in.readString();
        this.description = in.readString();
        this.image = in.readString();
        ingredients = new ArrayList<>();
        in.readStringList(ingredients);
        this.imageLicense = in.readString();
        this.imageAuthor = in.readString();
        this.imageLink = in.readString();
    }

    public static final Creator<Sandwich> CREATOR = new Creator<Sandwich>() {
        @Override
        public Sandwich createFromParcel(Parcel in) {
            return new Sandwich(in);
        }

        @Override
        public Sandwich[] newArray(int size) {
            return new Sandwich[size];
        }
    };

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public List<String> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    public void setAlsoKnownAs(List<String> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
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

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setImageLarge(String imageLarge) {
        this.imageLarge = imageLarge;
    }

    public void setImageLicense(String imageLicense) {
        this.imageLicense = imageLicense;
    }

    public String getImageLicense() {
        return imageLicense;
    }

    public void setImageAuthor(String imageAuthor) {
        this.imageAuthor = imageAuthor;
    }

    public String getImageAuthor() {
        return imageAuthor;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getImageLink() {
        return imageLink;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mainName);
        dest.writeStringList(alsoKnownAs);
        dest.writeString(placeOfOrigin);
        dest.writeString(description);
        dest.writeString(image);
        dest.writeStringList(ingredients);
        dest.writeString(imageLarge);
        dest.writeString(imageLicense);
        dest.writeString(imageAuthor);
        dest.writeString(imageLink);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sandwich sandwich = (Sandwich) o;
        return mainName.equals(sandwich.mainName) &&
                Objects.equals(alsoKnownAs, sandwich.alsoKnownAs) &&
                Objects.equals(placeOfOrigin, sandwich.placeOfOrigin) &&
                Objects.equals(description, sandwich.description) &&
                Objects.equals(image, sandwich.image) &&
                Objects.equals(ingredients, sandwich.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
    }
}
