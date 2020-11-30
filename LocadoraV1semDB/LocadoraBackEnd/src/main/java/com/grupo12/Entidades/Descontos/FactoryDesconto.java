package com.grupo12.Entidades.Descontos;

import com.grupo12.Entidades.Dominio.Locacao.Locacao;

import org.springframework.stereotype.Component;


@Component
public class FactoryDesconto {
    public RegraDesconto getRegraVerificacao(Locacao locacao) {
        if (locacao.getInicioLocacao().getMes() == 12 
        || locacao.getInicioLocacao().getMes() == 1 
        || locacao.getInicioLocacao().getMes() == 2)
        {
            return new DescontoEspecial();
        } else {
            return new DescontoNormal();
        }
    }
}
