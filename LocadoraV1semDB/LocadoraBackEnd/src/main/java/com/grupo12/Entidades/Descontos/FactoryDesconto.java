package com.grupo12.Entidades.Descontos;

import com.grupo12.DataLocal;

import org.springframework.stereotype.Component;


@Component
public class FactoryDesconto {
    public RegraDesconto getRegraVerificacao(DataLocal inicio, DataLocal fim){
        if (inicio.getMes() == 12 || inicio.getMes() == 1 || inicio.getMes() == 2){
            return new DescontoEspecial();
        }else{
            return new DescontoNormal();
        }
    }
}
