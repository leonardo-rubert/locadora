package com.grupo12.Interface.Persistencia.Historico;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.grupo12.Entidades.Dominio.Locacao.Locacao;
import com.grupo12.Entidades.Repositorio.Historico;

public class HistoricoDB implements Historico {
    private final List<Locacao> locacoes=new ArrayList<>();
    @Override
    public void carrega() {

    }

    @Override
    public void persiste() {

    }

    @Override
    public void cadastra(Locacao elemento) {
        locacoes.add(elemento);

    }

    @Override
    public Locacao recupera(String chave) {
        return locacoes
          .stream()
          .filter(v->v.getPlaca().equals(chave))
          .findAny()
          .orElseThrow();
    }

    @Override
    public Collection<Locacao> todos() {
        return locacoes;
    }

    @Override
    public boolean existente(String chave) {
        return false;
    }

    @Override
    public Collection<Locacao> pesquisa(Predicate<Locacao> pred) {
        return null;
    }

    public Collection<Locacao> pesquisa(String placa){
        return locacoes
        .stream()
        .filter(v->v.getPlaca().equals(placa))
        .collect(Collectors.toList());
    }

    @Override
    public void atualiza(Locacao elemento) {

    }

    @Override
    public void remove(String chave) {

    }
    
}
