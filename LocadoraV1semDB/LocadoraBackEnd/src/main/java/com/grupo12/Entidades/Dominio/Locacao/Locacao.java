package com.grupo12.Entidades.Dominio.Locacao;

import com.grupo12.DataLocal;
import com.grupo12.Entidades.Dominio.Veiculo.Carro;

//basicamente os dados da locacao para adicionar no bando de dados 
public class Locacao {
    private Carro carro;
    private DataLocal inicioLocacao;
    private DataLocal fimLocacao;
    public Locacao(Carro carro, DataLocal inicioLocacao, DataLocal fimLocacao){
        this.carro = carro;
        this.inicioLocacao = inicioLocacao;
        this.fimLocacao = fimLocacao;
    }
    //getters e setters
}
