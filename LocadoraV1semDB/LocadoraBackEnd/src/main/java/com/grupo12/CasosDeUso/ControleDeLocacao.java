package com.grupo12.CasosDeUso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.grupo12.Entidades.Dominio.Locacao.Locacao;
import com.grupo12.Entidades.Dominio.Veiculo.Carro;
import com.grupo12.Entidades.Servicos.Servicos;
import com.grupo12.Interface.DTO.CarroCustoDTO;
import com.grupo12.Interface.DTO.FiltroDTO;
import com.grupo12.Interface.Persistencia.Frota.FrotaDB;
import com.grupo12.Interface.Persistencia.Historico.HistoricoDB;
import com.grupo12.Interface.Persistencia.Locados.LocadosDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControleDeLocacao {
  private FrotaDB frotaDB;
  private LocadosDB locadosDB;
  private HistoricoDB historicoDB;
  private Servicos servicos;
  @Autowired
  public ControleDeLocacao(Servicos servicos) {
    frotaDB = new FrotaDB();
    frotaDB.carrega();
    locadosDB = new LocadosDB();
    historicoDB = new HistoricoDB();
    this.servicos = servicos;
  }

  public boolean podeVender(String placa) {
    return servicos.recupera(placa);
  }

  public Collection<Carro> frotaDeCarros() {
    return frotaDB.todos();
  }

  public Collection<Carro> carrosLocados() {
    return locadosDB.todos();
  }

  public Collection<Locacao> historicoCarros() {
    return historicoDB.todos();
  }

  public List<Carro> FiltroDisponiveis(FiltroDTO filtro){
    List<Carro> disponiveis = frotaDB.todos().stream()
      .filter(c->c.isArcondicionado() == filtro.isArcondicionado())
      .filter(c->c.isDirecao() == filtro.isDirecao())
      .filter(c->c.isCambioautomatico() == filtro.isCambio())
      .collect(Collectors.toList());
      return disponiveis;
  }

  //da pra melhorar
  public List<CarroCustoDTO> ListaCarrosDisponiveis(FiltroDTO filtro) {
    List<Carro> disponiveis = FiltroDisponiveis(filtro);
    List<CarroCustoDTO> informacoes = new ArrayList<>(disponiveis.size());
    disponiveis.forEach(c->{
      Locacao l = new Locacao(c.getPlaca(), c.getMarca(), c.getMarca(), 
      c.isArcondicionado(), c.isDirecao(), c.isCambioautomatico(),
      filtro.getInicioLocacao(), filtro.getFimLocacao(), false);
      informacoes.add(new CarroCustoDTO(filtro.getInicioLocacao(),
                                        filtro.getFimLocacao(),
                                        c.getPlaca(),
                                        c.getMarca(),
                                        c.getModelo(),
                                        c.isArcondicionado(),
                                        c.isDirecao(),
                                        c.isCambioautomatico(),
                                        servicos.calculaDiaria(l),
                                        servicos.calculaSeguro(l),
                                        servicos.calculaDesconto(l),
                                        servicos.calculaTotal(l)));
    });
    return informacoes;
}

  public void criaLocacao(CarroCustoDTO carro) {
    Locacao locacao = new Locacao(
      carro.getPlaca(), carro.getMarca(), carro.getModelo(),carro.isArcondicionado(), 
      carro.isDirecao(), carro.isCambio(),carro.getInicioLocacao(),carro.getFimLocacao(), false);
    historicoDB.cadastra(locacao);
  }

  public boolean confirmaLocacao(CarroCustoDTO carro) {
    boolean res = true;
	  if(frotaDB.existente(carro.getPlaca())){
      locadosDB.cadastra(frotaDB.recupera(carro.getPlaca()));
      criaLocacao(carro);
      frotaDB.remove(carro.getPlaca());
    } else {
      res = false;
    }
    return res;
  }

  public boolean devolveCarro(String placa) {
    boolean res = true;
     if(locadosDB.existente(placa)) {
    frotaDB.cadastra(locadosDB.recupera(placa));
    marcaDevolvido(placa);
    locadosDB.remove(placa);
    } else {
        res = false;
      }
      return res;
  }

  public void marcaDevolvido(String placa) {
    historicoDB.recupera(placa).setDevolvido();
  }

}
