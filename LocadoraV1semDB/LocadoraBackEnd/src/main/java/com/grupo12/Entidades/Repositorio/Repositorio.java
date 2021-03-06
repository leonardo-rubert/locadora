package com.grupo12.Entidades.Repositorio;

import java.util.Collection;
import java.util.function.Predicate;

import org.springframework.stereotype.Repository;
@Repository
public interface Repositorio<E, K> {
    void carrega();
    void persiste();
    void cadastra(E elemento);
    E recupera(K chave);
    Collection<E> todos();
    boolean existente(K chave);
    Collection<E> pesquisa(Predicate<E> pred);
    void atualiza(E elemento);
    void remove(K chave);
}
