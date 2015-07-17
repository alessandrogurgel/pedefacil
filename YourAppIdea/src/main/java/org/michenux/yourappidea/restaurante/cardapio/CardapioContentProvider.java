package org.michenux.yourappidea.restaurante.cardapio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alessandro.gurgel on 7/7/15.
 */
public class CardapioContentProvider
{
    private static final CardapioContentProvider instance;

    private List<CardapioItem> cardapio;

    private CardapioContentProvider()
    {
        super();
        initCardapio();
    }

    private void initCardapio()
    {
        Opcional bemPassado = new Opcional(1,"Bem passado");
        Opcional malPassado = new Opcional(2, "Mal passado");
        Opcional apimentado = new Opcional(3, "Apimentado");
        Opcional bastanteCalda = new Opcional(4, "Bastante calda");
        Opcional cebola = new Opcional(5, "Com cebola");
        Opcional semSalada = new Opcional(6, "Sem salada");
        Opcional queijoRalado = new Opcional(7, "Queijo ralado");
        Opcional semCobertura = new Opcional(8, "Sem cobertura");

        List<Opcional> carneOpcionais = Arrays.asList(bemPassado, malPassado, apimentado, cebola);
        List<Opcional> sorveteOpcinais = Arrays.asList(bastanteCalda, semCobertura);
        List<Opcional> sanduicheOpcinais = Arrays.asList(semSalada);
        List<Opcional> massaOpcinais = Arrays.asList(queijoRalado);



        cardapio = new ArrayList<CardapioItem>();
        CardapioItem c1 = new CardapioItem(0, "Crab Cake", "pic01", "A green surig of coriander on a sumptious slice of crab meat delight.", 22.99, "Frutos do Mar");
        CardapioItem c2 = new CardapioItem(1, "Chocolate Pancakes", "pic02", "A rich creamy chocolate delight of thick creamy chocolate sauce on a hot fresh pancake.", 12.50, "Sobremesas", sorveteOpcinais);
        CardapioItem c3 = new CardapioItem(2, "Steak Sirloin", "pic03", "A Think rump of meat roasted to perfection and accompanied by an illegal sauce.", 44.99, "Carnes", carneOpcionais);;
        CardapioItem c4 = new CardapioItem(3, "Chicken Burger", "pic04", "2 pieces of fresh bread with slices of the finest chicken between them.", 30.99, "Sanduíches", sanduicheOpcinais);
        CardapioItem c5 = new CardapioItem(4, "Swizz Ham and Cheese", "pic05", "Last meal of felons in Switzerland.\n A little more descriptive text comes here to fill space.\n We charge You Rs.", 15.00, "Aperitivos");
        CardapioItem c6 = new CardapioItem(5, "Sea Food Platter", "pic06", "Freshly caught Prawns, Crabs and other shellfish marinated in a spicy tomato sauce for your rioting taste buds.", 62.20, "Frutos do Mar");
        CardapioItem c7 = new CardapioItem(6, "Deluxe Sandwich", "pic07", "2 pieces of fresh bread with slices of the finest chicken roasted with awesome oil between them.\n Eat it to experience it!", 22.00, "Sanduíches", sanduicheOpcinais);
        CardapioItem c8 = new CardapioItem(7, "Pepperoni Pizza", "pic08", "An Italian Delight for the palate to enjoy. Feshly baked in a traditional oven, this is something you dont want to miss.", 33.99, "Pizzas", massaOpcinais);
        CardapioItem c9 = new CardapioItem(8, "Chinese Meal", "pic09", "Rice, Some Balls and Veggies. \n mmm... made in china.", 18.50, "Comida Chinesa");
        CardapioItem c10 = new CardapioItem(9, "Chocolate Dosa Rolled", "pic10", "A rolled delight of culinary masterpiece that nations have gone to war for its recipe.", 14.00, "Sobremesas", sorveteOpcinais);
        CardapioItem c11 = new CardapioItem(10, "Allegro Pasta", "pic11", "A unique blend of a secret sauce as well as traditional pasta cooking techniques handed over from father to son over 20 generations.", 41.00, "Massas", massaOpcinais);
        CardapioItem c12 = new CardapioItem(11, "Chocolate Muffin", "pic12", "Delicious", 12.75, "Sobremesas", sorveteOpcinais);

        cardapio.add(c1);
        cardapio.add(c2);
        cardapio.add(c3);
        cardapio.add(c4);
        cardapio.add(c5);
        cardapio.add(c6);
        cardapio.add(c7);
        cardapio.add(c8);
        cardapio.add(c9);
        cardapio.add(c10);
        cardapio.add(c11);
        cardapio.add(c12);
    }

    public CardapioItem getCardapioById(int id) {
        return cardapio.get(id);
    }

    public static final CardapioContentProvider getInstance() {
        return instance;
    }

    public List<CardapioItem> getCardapio() {
        return cardapio;
    }

    static{
        instance = new CardapioContentProvider();
    }
}
