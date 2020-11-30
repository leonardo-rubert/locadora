package com.grupo12.Entidades.Descontos;

public class DescontoEspecial implements RegraDesconto {
    @Override
    public double disconto() {
        return 0.5;
    }
}
