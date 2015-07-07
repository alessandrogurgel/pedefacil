package org.michenux.yourappidea.restaurante.cardapio;

/**
 * Created by alessandro.gurgel on 7/6/15.
 */
public class Cardapio {

    public Cardapio(String name, String categoy, double price, String imagePath) {
        this.name = name;
        this.categoy = categoy;
        this.price = price;
        this.imagePath = imagePath;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoy() {
        return categoy;
    }

    public void setCategoy(String categoy) {
        this.categoy = categoy;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    private String categoy;

    private double price;

    private String imagePath;

}
