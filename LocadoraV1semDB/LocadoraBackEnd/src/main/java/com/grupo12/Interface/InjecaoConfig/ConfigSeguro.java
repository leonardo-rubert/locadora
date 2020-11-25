package com.grupo12.Interface.InjecaoConfig;

import com.grupo12.Entidades.Dominio.Seguro.RegraSeguro;
import com.grupo12.Entidades.Dominio.Seguro.RegraSeguroGrande;
import com.grupo12.Entidades.Dominio.Seguro.RegraSeguroOriginal;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//unica classe 100% é usada basicamente pra selecionar a regra de seguro usada no calcula la em servicos
public class ConfigSeguro{
    @Bean
    @ConditionalOnProperty(name = "regra.seguro", havingValue = "original")
    public RegraSeguro opcaoRegraClassica() {
        return new RegraSeguroOriginal();
    }
 
    @Bean
    @ConditionalOnProperty(name = "regra.seguro", havingValue = "grandes", matchIfMissing = true)
    public RegraSeguro opcaoRegraGrande() {
        return new RegraSeguroGrande();
    }
}