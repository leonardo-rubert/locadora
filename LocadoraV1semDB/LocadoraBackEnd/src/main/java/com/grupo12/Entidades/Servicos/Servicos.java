package com.grupo12.Entidades.Servicos;

import com.grupo12.Entidades.Descontos.FactoryDesconto;
import com.grupo12.Entidades.Dominio.Locacao.Locacao;
import com.grupo12.Entidades.Dominio.Seguro.RegraSeguro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Servicos {
  private RegraSeguro regraSeguro;
  private FactoryDesconto factoryDesconto;
  @Autowired
  public Servicos(RegraSeguro regraSeguro, FactoryDesconto factoryDesconto) {
      this.regraSeguro = regraSeguro;
      this.factoryDesconto = factoryDesconto;
  }

  public double calculaDesconto(Locacao locacao){
    return factoryDesconto.getRegraVerificacao(locacao).disconto();
  }

  public  double calculaDiaria(Locacao locacao){
    return calculaDias(locacao) * 50;
  }

  public double calculaSeguro(Locacao locacao) {
    return (calculaDias(locacao))
    * regraSeguro.calcular(locacao);
  }

  public double calculaTotal(Locacao locacao) {
    return (calculaDiaria(locacao) + calculaSeguro(locacao)) * calculaDesconto(locacao);
  }

  public  double calculaDias(Locacao locacao) {
	return ( Math.abs(locacao.getInicioLocacao().getDia() - locacao.getFimLocacao().getDia())) +
         ((Math.abs(locacao.getInicioLocacao().getMes() - locacao.getFimLocacao().getMes())) * 30) +
         ((Math.abs(locacao.getInicioLocacao().getAno() - locacao.getFimLocacao().getAno())) * 365);
  }
}
