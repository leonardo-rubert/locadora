package com.grupo12.Entidades.Dominio.Seguro;

import com.grupo12.Entidades.Dominio.Locacao.Locacao;

public class RegraSeguroCaro implements RegraSeguro {

    @Override
    public double calcular(Locacao locacao) {
        double total = 200;
        if (locacao.isArcondicionado()) {
            total+=100;
        }
        if (locacao.isDirecao()) {
            total+=150;
        }
        if (locacao.isCambio()){
            total+=150;
        }
        return total;
    }
    
}
