package ir.ounegh.vardast;

/**
 * Created by aseme on 13/12/2017.
 */

public class Category {
    int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

    @Override
    public String toString() {
        return getName();
    }
}
