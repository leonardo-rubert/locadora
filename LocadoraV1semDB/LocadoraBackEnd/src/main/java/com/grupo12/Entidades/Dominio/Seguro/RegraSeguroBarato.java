package com.grupo12.Entidades.Dominio.Seguro;


public class RegraSeguroBarato implements RegraSeguro {

    @Override
    public double calcular(boolean arcondicionado ,boolean direcao ,boolean cambio) {
        double total = 100;
        if (arcondicionado) {
            total+=100;
        }
        if (direcao) {
            total+=100;
        }
        if (cambio){
            total+=100;
        }
        return total;
    }
}
