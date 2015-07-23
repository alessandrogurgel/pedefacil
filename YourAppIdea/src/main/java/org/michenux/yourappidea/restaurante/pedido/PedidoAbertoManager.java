package org.michenux.yourappidea.restaurante.pedido;

import android.support.v4.util.Pair;

import org.michenux.yourappidea.restaurante.cardapio.CardapioItem;

import java.util.List;

/**
 * Created by alessandro.gurgel on 7/21/15.
 */
public class PedidoAbertoManager
{
    private  Pedido pedido;

    private static final PedidoAbertoManager instance;

    private PedidoAbertoManager() {
        pedido = new Pedido();
    }

    public static final PedidoAbertoManager getInstance() {
        return  instance;
    }

    static {
        instance = new PedidoAbertoManager();
    }

    public void adicionarPrato(CardapioItem cardapioItem, Integer quantity)
    {
       pedido.addItem(cardapioItem, quantity);

    }

    public List<Pair<CardapioItem, Integer>> getItems() {
        return pedido.getItems();
    }

    public String getTotal() {
        return pedido.getTotal();
    }


    public void removeItem(int id) {
        pedido.removeItem(id);
    }
}
