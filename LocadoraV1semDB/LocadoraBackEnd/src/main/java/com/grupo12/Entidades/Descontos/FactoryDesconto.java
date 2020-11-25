package com.grupo12.Entidades.Descontos;

import com.grupo12.DataLocal;

import org.springframework.stereotype.Component;

@Component
//clase pronta porem precisa de uma condicao e calculo de desconto melhor
public class FactoryDesconto {
    public RegraDesconto getRegraVerificacao(DataLocal inicio, DataLocal fim){
        int dias = inicio.getDia() - fim.getDia();
        if (dias > 5){
            return new ValidacaoDescontoEspecial();
        }else{
            return new ValidacaoDescontoNormal();
        }
    }
}
