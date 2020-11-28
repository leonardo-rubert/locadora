package com.grupo12.Entidades.Dominio.Seguro;

public class RegraSeguroCaro implements RegraSeguro {

    @Override
    public double calcular(boolean arcondicionado ,boolean direcao ,boolean cambio) {
        double total = 200;
        if (arcondicionado) {
            total+=200;
        }
        if (direcao) {
            total+=200;
        }
        if (cambio){
            total+=200;
        }
        return total;
    }
    
}
