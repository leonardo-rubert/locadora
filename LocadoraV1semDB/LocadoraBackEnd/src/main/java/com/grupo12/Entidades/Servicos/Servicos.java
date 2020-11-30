package com.grupo12.Entidades.Servicos;

//import com.grupo12.DataLocal;
import com.grupo12.Entidades.Descontos.FactoryDesconto;
import com.grupo12.Entidades.Dominio.Locacao.Locacao;
import com.grupo12.Entidades.Dominio.Seguro.RegraSeguro;
import com.grupo12.Interface.Persistencia.Frota.FrotaDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Servicos {
  private RegraSeguro regraSeguro;
  private FactoryDesconto factoryDesconto;
  private FrotaDB frotaDB;
  @Autowired
  public Servicos(RegraSeguro regraSeguro, FactoryDesconto factoryDesconto,  FrotaDB frotaDB) {
      this.regraSeguro = regraSeguro;
      this.factoryDesconto = factoryDesconto;
      this.frotaDB = frotaDB;
  }

  public double calculaDesconto(Locacao locacao){
    return factoryDesconto.getRegraVerificacao(locacao.getInicioLocacao(),
                                                locacao.getFimLocacao()).disconto();
  }

  public  double calculaDiaria(Locacao locacao){
    return calculaDias(locacao) * 50;
  }

  public double calculaSeguro(Locacao locacao) {
    return (locacao.getFimLocacao().getDia() - locacao.getInicioLocacao().getDia())
    * regraSeguro.calcular(locacao.isArcondicionado(), locacao.isDirecao(), locacao.isCambio());
  }

  public double calculaTotal(Locacao locacao) {
    return calculaDiaria(locacao) + calculaSeguro(locacao) * calculaDesconto(locacao);
  }

  public  double calculaDias(Locacao locacao) {
	return ( Math.abs(locacao.getInicioLocacao().getDia() - locacao.getFimLocacao().getDia())) +
         ((Math.abs(locacao.getInicioLocacao().getMes() - locacao.getFimLocacao().getMes())) * 30) +
         ((Math.abs(locacao.getInicioLocacao().getAno() - locacao.getFimLocacao().getAno())) * 365);
  }

  public boolean recupera(String placa) {
    return frotaDB.existente(placa);
  }
}
