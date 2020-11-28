package com.grupo12.Entidades.Dominio.Locacao;

import com.grupo12.DataLocal;

public class Locacao {
    private String placa;
    private String marca;
    private String modelo;
    private boolean arcondicionado;
    private boolean direcao;
    private boolean cambio;
    private DataLocal inicioLocacao;
    private DataLocal fimLocacao;
    private boolean devolvido;
    public Locacao(String placa,String marca, String modelo, 
                    boolean arcondicionado ,boolean direcao ,boolean cambio , 
                    DataLocal inicioLocacao, DataLocal fimLocacao, boolean devolvido){
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.arcondicionado = arcondicionado;
        this.direcao = direcao;
        this.cambio = cambio;
        this.inicioLocacao = inicioLocacao;
        this.fimLocacao = fimLocacao;
        this.devolvido = devolvido;
    }
    public DataLocal getInicioLocacao() {
        return inicioLocacao;
    }
    public String getMarca() {
        return marca;
    }
    public String getModelo() {
        return modelo;
    }
    public boolean isArcondicionado() {
        return arcondicionado;
    }

    public boolean isDirecao() {
        return direcao;
    }

    public boolean isCambio() {
        return cambio;
    }
    public DataLocal getFimLocacao() {
        return fimLocacao;
    }
    public boolean isDevolvido(){
        return devolvido;
    }
    public void setDevolvido(){
        devolvido = true;
    }
    public String getPlaca() {
        return placa;
    }
}
