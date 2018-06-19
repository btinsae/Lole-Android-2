package fanos.com.lole.model;

import java.io.Serializable;

/**
 * Created by Birhane on 2/17/2018.
 */

public class ItemCategory implements Serializable{
    private  int icon;
    private String name;

    public ItemCategory(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
