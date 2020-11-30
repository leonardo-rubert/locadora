package com.grupo12.Entidades.Descontos;

public class DescontoNormal implements RegraDesconto {

    @Override
    public double disconto() {
        return 0.2;
    }

}
