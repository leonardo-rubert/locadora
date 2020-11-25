package com.grupo12.Entidades.Descontos;

import com.grupo12.DataLocal;

public class ValidacaoDescontoEspecial implements RegraDesconto {
    @Override
    public int disconto() {
        return 200;
    }
}
