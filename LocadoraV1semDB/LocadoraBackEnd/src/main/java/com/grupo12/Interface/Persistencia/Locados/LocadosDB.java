package com.grupo12.Interface.Persistencia.Locados;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import com.grupo12.Entidades.Dominio.Veiculo.Carro;
import com.grupo12.Entidades.Repositorio.Locados;

import org.springframework.stereotype.Component;
@Component
public class LocadosDB implements Locados {
    private final List<Carro> carros=new ArrayList<>();
    @Override
    public void carrega() {

    }

    @Override
    public void persiste() {

    }

    @Override
    public void cadastra(Carro elemento) {
        carros.add(elemento);
    }

    @Override
    public Carro recupera(String chave) {
        return carros
          .stream()
          .filter(v->v.getPlaca().equals(chave))
          .findAny()
          .orElseThrow();
    }

    @Override
    public Collection<Carro> todos() {
        return carros;
    }

    @Override
    public boolean existente(String chave) {
        return carros
        .stream()
        .anyMatch(v->v.getPlaca().equals(chave));
    }

    @Override
    public Collection<Carro> pesquisa(Predicate<Carro> pred) {
        return null;
    }

    @Override
    public void atualiza(Carro elemento) {

    }

    @Override
    public void remove(String chave) {
        Carro carro = recupera(chave);
        carros.remove(carro);
    }
    
}
