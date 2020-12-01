package com.grupo12;

import java.text.ParseException;

import com.grupo12.CasosDeUso.ControleDeLocacao;
import com.grupo12.Entidades.Descontos.FactoryDesconto;
import com.grupo12.Entidades.Dominio.Locacao.Locacao;
import com.grupo12.Entidades.Servicos.Servicos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {
	private Servicos servico;
	private ControleDeLocacao ctrl;
	private FactoryDesconto desconto;
	private Locacao l1;
	private Locacao l2;
	private DataLocal i1;
	private DataLocal i2;

	@BeforeEach
	void setUp() {
		i1 = new DataLocal();
		i2 = new DataLocal();
		l1 = new Locacao("ABC129", "ACME", "M4", false, false, false, i1, i2, false);
		l1 = new Locacao("ABC129", "ACME", "M4", false, false, false, i1, i2, false);
	}

	@Test
	void calcDiariaTeste() {
		servico = new Servicos(null, null);
		l1.getFimLocacao().setDia(10);
		l1.getInicioLocacao().setDia(1);
		Assertions.assertEquals(9,servico.calculaDias(l1));
	}

	void calcDiariariaTeste() {
		servico = new Servicos(null, null);
		l1.getFimLocacao().setDia(10);
		l1.getInicioLocacao().setDia(1);
		Assertions.assertEquals(450,servico.calculaDiaria(l1));
	}

	@Test
	void calcDescontoNormalTeste() {
		desconto = new FactoryDesconto();
		i1.setMes(11);
		desconto.getRegraVerificacao(l1);
		Assertions.assertEquals(0.2, desconto.getRegraVerificacao(l1).disconto());
	}

	@Test
	void calcDescontoEspecialTeste() {
		desconto = new FactoryDesconto();
		i1.setMes(12);
		desconto.getRegraVerificacao(l1);
		Assertions.assertEquals(0.5, desconto.getRegraVerificacao(l1).disconto());
	}

	@Test
	void validaDataMenorQueAtualTesteFalso() {
		ctrl = new ControleDeLocacao(null);
		i1.setAno(i1.getDia()-1);
		try {
			Assertions.assertEquals(false, ctrl.validaDataAtual(i1));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	void validaDataMenorQueAtualTesteTrue() throws ParseException {
		ctrl = new ControleDeLocacao(null);
		i1.setDia(i1.getDia()+1);
		Assertions.assertEquals(true, ctrl.validaDataAtual(i1));
	}

	@Test
	void validaDataFinalMenorInicialTrue() throws ParseException {
		ctrl = new ControleDeLocacao(null);
		i2.setDia(i1.getDia()+1);
		Assertions.assertEquals(true, ctrl.validaDataPeriodo(i1, i2));
	}

	@Test
	void validaDataFinalMenorInicialFalse() throws ParseException {
		ctrl = new ControleDeLocacao(null);
		i2.setDia(i1.getDia()-1);
		Assertions.assertEquals(false, ctrl.validaDataPeriodo(i1, i2));
	}
}
