package org.michenux.yourappidea.restaurante;

import java.util.HashMap;

/**
 * Created by alessandro.gurgel on 7/12/15.
 */
public class CategoryContentProvider {

    private static final CategoryContentProvider instance;

    private HashMap<String,String> categoryImageFiles;

    private CategoryContentProvider() {

        categoryImageFiles = new HashMap<String,String>();
        categoryImageFiles.put("Frutos do Mar", "category_prawn");
        categoryImageFiles.put("Sobremesas", "category_dessert");
        categoryImageFiles.put("Comida Chinesa", "category_chinese");
        categoryImageFiles.put("Sanduíches", "category_sandwich");
        categoryImageFiles.put("Aperitivos", "category_aperitivo");
        categoryImageFiles.put("Pizzas", "category_pizza");
        categoryImageFiles.put("Massas", "category_spaguetti");
        categoryImageFiles.put("Carnes", "category_steak");

    }

    public static final CategoryContentProvider getInstance() {return instance;}

    public String getImageFromCategory(String category){
        return categoryImageFiles.get(category);
    }

    static{
        instance = new CategoryContentProvider();
    }
}
