package io.lundie.michael.sandwichclub.network;

import java.util.List;

public class SandwichNameSchema {
    private String mainName;
    private List<String> alsoKnownAs = null;

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
}
