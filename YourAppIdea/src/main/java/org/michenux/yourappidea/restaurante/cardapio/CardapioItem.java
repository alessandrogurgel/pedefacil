package org.michenux.yourappidea.restaurante.cardapio;

/**
 * Created by alessandro.gurgel on 7/6/15.
 */
public class CardapioItem {

    public CardapioItem(int id, String name, String imageName, String description, double price, String categoy ) {
        this.name = name;
        this.categoy = categoy;
        this.price = price;
        this.imageName = imageName;
        this.description = description;
        this.id = id;
    }

    private String name;

    private String categoy;

    private double price;

    private String imageName;

    private String description;

    private int id;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

}
