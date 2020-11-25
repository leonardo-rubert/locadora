package com.grupo12.Entidades.Dominio.Seguro;

import com.grupo12.Entidades.Dominio.Veiculo.Carro;

public class RegraSeguroOriginal implements RegraSeguro {

    @Override
    public double calcular(Carro carro) {
        return 200;
    }
    
}
