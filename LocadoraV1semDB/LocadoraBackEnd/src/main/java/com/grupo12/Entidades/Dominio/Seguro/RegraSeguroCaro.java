package com.grupo12.Entidades.Dominio.Seguro;

public class RegraSeguroCaro implements RegraSeguro {

    @Override
    public double calcular(boolean arcondicionado ,boolean direcao ,boolean cambio) {
        double total = 200;
        if (arcondicionado) {
            total+=100;
        }
        if (direcao) {
            total+=150;
        }
        if (cambio){
            total+=150;
        }
        return total;
    }
    
}
