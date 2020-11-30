package com.grupo12.CasosDeUso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.grupo12.DataLocal;
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

  public boolean validaDataAtual(DataLocal inicio) throws ParseException {
    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
    DataLocal atual = new DataLocal();
    Date d1 = sdformat.parse(atual.getAno()+"-"+atual.getMes()+"-"+atual.getDia());
    Date d2 = sdformat.parse(inicio.getAno()+"-"+inicio.getMes()+"-"+inicio.getDia());
    if(d1.compareTo(d2) > 0) {
      return false;
    } else if (d1.compareTo(d2) < 0) {
      return true;
    } else {
      return true;
    }
  }

  public boolean validaDataPeriodo(DataLocal inicio, DataLocal fim) throws ParseException {
    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
    Date d1 = sdformat.parse(fim.getAno()+"-"+fim.getMes()+"-"+fim.getDia());
    Date d2 = sdformat.parse(inicio.getAno()+"-"+inicio.getMes()+"-"+inicio.getDia());
    if(d1.compareTo(d2) > 0) {
      return true;
   } else if(d1.compareTo(d2) < 0) {
      return false;
   } else {
    return false;
   }
  }

  public boolean validaData(FiltroDTO filtro){
    try {
      if (!(validaDataPeriodo(filtro.getInicioLocacao(),filtro.getFimLocacao())
      && validaDataAtual(filtro.getInicioLocacao()))) {
        return true;
      } else {
        return false;
      }
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
	return false;
  }

  public List<Carro> FiltroDisponiveis(FiltroDTO filtro){
    List<Carro> disponiveis = frotaDB.todos().stream()
      .filter(c->c.isArcondicionado() == filtro.isArcondicionado())
      .filter(c->c.isDirecao() == filtro.isDirecao())
      .filter(c->c.isCambioautomatico() == filtro.isCambio())
      .collect(Collectors.toList());
      return disponiveis;
  }

  public List<CarroCustoDTO> ListaCarrosDisponiveis(FiltroDTO filtro) {
    
    if (validaData(filtro)) {
      return new ArrayList<>();
    }

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
    for (Locacao c : historicoDB.pesquisa(placa) ) {
      c.setDevolvido();
    }
  }
}
