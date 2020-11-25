package com.grupo12.Interface.Persistencia.Frota;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import com.grupo12.Entidades.Dominio.Veiculo.Carro;
import com.grupo12.Entidades.Repositorio.Frota;

//é basicamente uma listona no momento, melhor só ir fazendo os metodos conforme necessario e depois implementar o banco de dados
public class FrotaDB implements Frota {
    private final List<Carro> carros=new ArrayList<>();;
    @Override
    public void carrega() {
        carros.add(new Carro("ABC129", "ACME", "M4", false, false, false));
        carros.add(new Carro("ABC124", "ACME", "M2", true, false, false));
        carros.add(new Carro("ABC125", "ACME", "M1", true, true, false));
        carros.add(new Carro("ABC126", "ACME", "M2", true, true, true));
        carros.add(new Carro("ABC128", "ACME", "M4", true, true, true));
        carros.add(new Carro("ABC127", "ACME", "M3", false, true, true));
        carros.add(new Carro("ABC123", "ACME", "M1", false, false, false));
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
        return carros;
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
