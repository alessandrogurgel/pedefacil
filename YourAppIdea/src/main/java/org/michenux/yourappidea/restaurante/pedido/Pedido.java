package org.michenux.yourappidea.restaurante.pedido;

import android.support.v4.util.Pair;

import org.michenux.yourappidea.restaurante.cardapio.CardapioItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by alessandro.gurgel on 7/20/15.
 */
public class Pedido {

    private List<Pair<CardapioItem, Integer>> items;
    private Date solicitacao;
    private Date fechamento;

    public void setSolicitacao(Date solicitacao) {
        this.solicitacao = solicitacao;
    }

    public void setFechamento(Date fechamento) {
        this.fechamento = fechamento;
    }

    public List<Pair<CardapioItem, Integer>> getItems() {
        return items;
    }

    public Date getSolicitacao() {
        return solicitacao;
    }

    public Date getFechamento() {
        return fechamento;
    }

    public Pedido() {
        items = new ArrayList<Pair<CardapioItem, Integer>>();
    }

    public void addItem(CardapioItem cardapioItem, Integer quantity) {
        Pair<CardapioItem, Integer> toRemove = null;
        for (Pair<CardapioItem, Integer> item : items )
        {
            if (cardapioItem.getId() == item.first.getId()){
                quantity = quantity + item.second;
                toRemove = item;
                break;
            }
        }
        if (toRemove != null )
        {
            items.remove(toRemove);
        }
        items.add( new Pair<CardapioItem, Integer>(cardapioItem, quantity));
    }

    public String getTotal() {
        double total = 0;
        for (Pair<CardapioItem, Integer> item : items ) {
            total = total + (item.second * item.first.getPrice());
        }
        return String.format("%.2f", total);
    }

    public void removeItem(int id)
    {
        Pair<CardapioItem, Integer> toRemove = null;
        for (Pair<CardapioItem, Integer> item : items )
        {
            if (item.first.getId() == item.first.getId()){
                toRemove = item;
                break;
            }
        }
        if (toRemove != null )
        {
            items.remove(toRemove);
        }
    }
}
