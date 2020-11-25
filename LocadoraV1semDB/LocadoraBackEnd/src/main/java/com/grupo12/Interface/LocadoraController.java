package com.grupo12.Interface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.grupo12.CasosDeUso.ControleDeLocacao;
import com.grupo12.Entidades.Dominio.Seguro.RegraSeguro;
import com.grupo12.Entidades.Dominio.Veiculo.Carro;
import com.grupo12.Interface.DTO.CarroCustoDTO;
import com.grupo12.Interface.DTO.FiltroDTO;
import com.grupo12.Interface.Persistencia.Frota.FrotaDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locadora")
//classe responsavel pelos endpoints, basicamente chama as funcoes de outras classes
public class LocadoraController {

  private final List<Carro> carros;
  private final RegraSeguro regraSeguro; // dentro de servico
  public LocadoraController(RegraSeguro regraSeguro) {
    FrotaDB frota = new FrotaDB();
     frota.carrega(); //temporario ate a implementacao do banco de dados
     carros = (List<Carro>) frota.todos();
     this.regraSeguro = regraSeguro;

  }

  @GetMapping("/carrosDisponiveis")
  @CrossOrigin(origins = "*")
  public List<CarroCustoDTO> carrosDisponiveis(FiltroDTO filtro) {// fazer este metodo no controle de locacao e apenas a chamada do metodo aqui
    // Está selecionando apenas pelos equipamentos
    // Não está verificando se o carro está livre naquelas datas // fazer este metodo em servico
    List<Carro> disponiveis = carros.stream()
      .filter(c->c.isArcondicionado() == filtro.isArcondicionado())
      .filter(c->c.isDirecao() == filtro.isDirecao())
      .filter(c->c.isCambioautomatico() == filtro.isCambio())
      .collect(Collectors.toList());
    List<CarroCustoDTO> informacoes = new ArrayList<>(disponiveis.size());
    // Não está calculando o valor das diárias, seguro, desconto ou total //fazer estes metodos em servicos
    disponiveis.forEach(c->{
      informacoes.add(new CarroCustoDTO(filtro.getInicioLocacao(),
                                        filtro.getFimLocacao(),
                                        c.getPlaca(),
                                        c.getMarca(),
                                        c.getModelo(),
                                        c.isArcondicionado(),
                                        c.isDirecao(),
                                        c.isCambioautomatico(),
                                        1000.0, // Total das diárias
                                        regraSeguro.calcular(c),  // Custo do seguro
                                        200.0,   // Total do desconto
                                        900.0)); // Valor a pagar
    });
    return informacoes;
  }

  @PostMapping("/confirmaLocacao")
  @CrossOrigin(origins = "*")
  public boolean confirmaLocacao(@RequestBody final CarroCustoDTO carro) {
    // Está confirmando qualquer coisa
    return true;
  }
}
