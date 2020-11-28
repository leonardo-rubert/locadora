package com.grupo12.Entidades.Descontos;

public class DescontoEspecial implements RegraDesconto {
    @Override
    public int disconto() {
        return 200;
    }
}
