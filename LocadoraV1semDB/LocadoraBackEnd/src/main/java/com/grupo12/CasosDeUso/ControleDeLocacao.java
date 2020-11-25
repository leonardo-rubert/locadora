package com.grupo12.CasosDeUso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.grupo12.Entidades.Dominio.Veiculo.Carro;
import com.grupo12.Interface.DTO.CarroCustoDTO;
import com.grupo12.Interface.DTO.FiltroDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component

public class ControleDeLocacao {
  public ControleDeLocacao() {
  }

  //marca o carro como locado
  public void cadastraLocacao(){}
  //devolve o carro
  public void devolve(){}
  public void carrosLocados(){}
  public void carrosDisponiveis(){}
}
