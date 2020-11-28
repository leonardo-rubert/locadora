package com.grupo12.Interface;

import java.util.List;

import com.grupo12.CasosDeUso.ControleDeLocacao;
import com.grupo12.Entidades.Dominio.Locacao.Locacao;
import com.grupo12.Entidades.Dominio.Seguro.RegraSeguro;
import com.grupo12.Entidades.Dominio.Veiculo.Carro;
import com.grupo12.Interface.DTO.CarroCustoDTO;
import com.grupo12.Interface.DTO.FiltroDTO;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locadora")
public class LocadoraController {

  private ControleDeLocacao ctrlLocacao;
  public LocadoraController(RegraSeguro regraSeguro, ControleDeLocacao ctrlLocacao) {
     this.ctrlLocacao = ctrlLocacao;
  }

  @GetMapping("/carrosDisponiveis")
  @CrossOrigin(origins = "*")
  public List<CarroCustoDTO> carrosDisponiveis(FiltroDTO filtro) {
    return ctrlLocacao.ListaCarrosDisponiveis(filtro);
  }

  @PostMapping("/confirmaLocacao")
  @CrossOrigin(origins = "*")
  public boolean confirmaLocacao(@RequestBody final CarroCustoDTO carro) {
    return ctrlLocacao.confirmaLocacao(carro);
  }

  @GetMapping("/devolucao")
  @CrossOrigin(origins = "*")
  public boolean devolveCarro(@RequestParam final String placa) {
    return ctrlLocacao.devolveCarro(placa);
  }

  @GetMapping("/locados")
  @CrossOrigin(origins = "*")
  public List<Carro> carrosLocados() {
    return (List<Carro>) ctrlLocacao.carrosLocados();
  }

  @GetMapping("/historico")
  @CrossOrigin(origins = "*")
  public List<Locacao> historicoCarros() {
    return (List<Locacao>) ctrlLocacao.historicoCarros();
  }
}
