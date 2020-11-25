package com.grupo12.Entidades.Descontos;

import com.grupo12.DataLocal;

public class ValidacaoDescontoNormal implements RegraDesconto {

    @Override
    public int disconto() {
        return 100;
    }

}
