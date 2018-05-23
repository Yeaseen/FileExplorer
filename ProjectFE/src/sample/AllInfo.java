package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;

/**
 * Created by Asus on 4/23/2017.
 */
public class AllInfo {

    public ImageView icon;
    public String name;
    public String size;
    public String date;
    public String loc;




    public AllInfo(ImageView icon, String name, String size, String date) {
        this.icon = icon;
        this.name = name;
        this.size = size;
        this.date = date;
    }

    public ImageView getIcon() {
        return icon;
    }

    public void setIcon(ImageView icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
     this.date = date;
    }
}
