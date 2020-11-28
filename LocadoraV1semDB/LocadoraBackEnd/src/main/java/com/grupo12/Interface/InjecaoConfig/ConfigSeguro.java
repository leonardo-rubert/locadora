package com.grupo12.Interface.InjecaoConfig;

import com.grupo12.Entidades.Dominio.Seguro.RegraSeguro;
import com.grupo12.Entidades.Dominio.Seguro.RegraSeguroBarato;
import com.grupo12.Entidades.Dominio.Seguro.RegraSeguroCaro;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigSeguro{
    @Bean
    @ConditionalOnProperty(name = "regra.seguro", havingValue = "caro")
    public RegraSeguro opcaoRegraClassica() {
        return new RegraSeguroCaro();
    }
 
    @Bean
    @ConditionalOnProperty(name = "regra.seguro", havingValue = "barato", matchIfMissing = true)
    public RegraSeguro opcaoRegraBarato() {
        return new RegraSeguroBarato();
    }
}