package com.grupo12.Interface.Persistencia.Locacoes;

import java.util.Collection;
import java.util.function.Predicate;

import com.grupo12.Entidades.Dominio.Veiculo.Carro;
import com.grupo12.Entidades.Repositorio.Locacoes;

public class LocacoesDB implements Locacoes {
    
    @Override
    public void carrega() {
        // TODO Auto-generated method stub

    }

    @Override
    public void persiste() {
        // TODO Auto-generated method stub

    }

    @Override
    public void cadastra(Carro elemento) {
        // TODO Auto-generated method stub

    }

    @Override
    public Carro recupera(Long chave) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<Carro> todos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean existente(Long chave) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Collection<Carro> pesquisa(Predicate<Carro> pred) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void atualiza(Carro elemento) {
        // TODO Auto-generated method stub

    }

    @Override
    public void remove(Long chave) {
        // TODO Auto-generated method stub

    }
    
}
