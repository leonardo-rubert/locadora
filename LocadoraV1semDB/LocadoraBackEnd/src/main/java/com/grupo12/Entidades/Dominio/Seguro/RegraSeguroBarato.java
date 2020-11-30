package com.grupo12.Entidades.Dominio.Seguro;

import com.grupo12.Entidades.Dominio.Locacao.Locacao;

public class RegraSeguroBarato implements RegraSeguro {

    @Override
    public double calcular(Locacao locacao) {
        double total = 100;
        if (locacao.isArcondicionado()) {
            total+=100;
        }
        if (locacao.isDirecao()) {
            total+=100;
        }
        if (locacao.isCambio()){
            total+=100;
        }
        return total;
    }
}
