package org.michenux.yourappidea.restaurante.cardapio;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alessandro.gurgel on 7/6/15.
 */
public class CardapioItem {

    public CardapioItem(int id, String name, String imageName, String description, double price, String category, double rating, List<Opcional> opcionais ) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.imageName = imageName;
        this.description = description;
        this.id = id;
        this.opcionais = opcionais;
        this.rating = rating;
    }

    public CardapioItem(int id, String name, String imageName, String description, double price, String category , double rating) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.imageName = imageName;
        this.description = description;
        this.id = id;
        this.opcionais = new ArrayList<Opcional>();
        this.rating = rating;
    }

    private List<Opcional> opcionais;

    private String name;

    private String category;

    private double price;

    private String imageName;

    private String description;

    private int id;

    private double rating;

    public List<Opcional> getOpcionais() {
        return opcionais;
    }

    public String[] getOpcionaisTextos() {
        String[] result = new String[opcionais.size()];
        int i = 0;
        for (Opcional o : opcionais)
        {
            result[i++] = o.getOpcao();
        }
        return result;
    }

    public void setOpcionais(List<Opcional> opcionais) {
        this.opcionais = opcionais;
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

}
