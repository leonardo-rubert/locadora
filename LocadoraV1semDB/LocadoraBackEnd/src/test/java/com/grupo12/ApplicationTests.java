package com.grupo12;

//import com.grupo12.Entidades.Descontos.FactoryDesconto;
import com.grupo12.Entidades.Descontos.FactoryDesconto;
import com.grupo12.Entidades.Dominio.Locacao.Locacao;
//import com.grupo12.Entidades.Dominio.Seguro.RegraSeguro;
//import com.grupo12.Entidades.Dominio.Seguro.RegraSeguro;
import com.grupo12.Entidades.Servicos.Servicos;
//import com.grupo12.Entidades.Dominio.Locacao.*;
//import com.grupo12.Interface.Persistencia.Frota.FrotaDB;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {
	private Servicos ser = null;

	@BeforeEach
	void setUp(){ser = new Servicos(null, null, null);
	}

	
	@Test
	void descontoTeste() {
		FactoryDesconto d = new FactoryDesconto();
		DataLocal a = new DataLocal();
		DataLocal b = new DataLocal();
		a.setMes(12);
		b.setMes(1);
		Assertions.assertEquals(0.5, d.getRegraVerificacao(a, b).disconto());
	}

	@Test
	void descontoNormalTeste() {
		FactoryDesconto d = new FactoryDesconto();
		DataLocal a = new DataLocal();
		DataLocal b = new DataLocal();
		a.setMes(11);
		b.setMes(1);
		Assertions.assertEquals(0.2, d.getRegraVerificacao(a, b).disconto());
	}

	@Test
	void calcDiaTeste() {
		DataLocal c = new DataLocal();
		DataLocal b = new DataLocal();
		c.setMes(11);
		b.setMes(12);
		Locacao a = new Locacao("ABC129", "ACME", "M4", false, false, false, c, b, false);
		double e = ser.calculaDiaria(a);
		Assertions.assertEquals(1500, e);
	}

	@Test
	 void calcDiasTeste(){
		DataLocal c = new DataLocal();
		DataLocal b = new DataLocal();
		c.setMes(11);
		b.setMes(12);
		Locacao a = new Locacao("ABC129", "ACME", "M4", false, false, false, c, b, false);
		double e = ser.calculaDias(a);
		Assertions.assertEquals(30, e);
	}
	@Test
	void calcSeguroTeste(){
		DataLocal c = new DataLocal();
		DataLocal b = new DataLocal();
		c.setMes(11);
		b.setMes(12);
		Locacao a = new Locacao("ABC129", "ACME", "M4", false, false, false, c, b, false);
		double e = ser.calculaDesconto(a);
		Assertions.assertEquals(0.5, e);
	}
	@Test
	void calcTotalTeste(){
		DataLocal c = new DataLocal();
		DataLocal b = new DataLocal();
		c.setMes(11);
		b.setMes(12);
		Locacao a = new Locacao("ABC129", "ACME", "M4", false, false, false, c, b, false);
		double e = ser.calculaTotal(a);
		Assertions.assertEquals(30, e);
	}
}
