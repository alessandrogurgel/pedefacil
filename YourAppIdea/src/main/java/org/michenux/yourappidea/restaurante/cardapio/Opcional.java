package org.michenux.yourappidea.restaurante.cardapio;

/**
 * Created by alessandro.gurgel on 7/16/15.
 */
public class Opcional {
    private String opcao;
    private int id;

    public Opcional(int id, String opcao) {
        this.id = id;
        this.opcao = opcao;
    }

    public String getOpcao() {
        return opcao;
    }

    public void setOpcao(String opcao) {
        this.opcao = opcao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
