package org.michenux.yourappidea.restaurante.pedido;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alessandro.gurgel on 7/21/15.
 */
public class PedidoHistContentProvider
{
    private List<Pedido> pedidos;

    private static final PedidoHistContentProvider instance;

    private PedidoHistContentProvider()
    {
        this.pedidos = new ArrayList<Pedido>();
    }

    public static final PedidoHistContentProvider getInstance()
    {
        return instance;
    }

    static{
        instance = new PedidoHistContentProvider();
    }

    public void adicionarPedido(Pedido pedido) {
        pedidos.add( pedido );
    }
}
