
package br.com.poc.monitoracao.services;

import org.springframework.beans.factory.annotation.Autowired;

import io.micrometer.core.instrument.MeterRegistry;

/**
 * Classe para monitoria
 *
 * @author mforti
 */
public class MonitoriaService {

    private static final String CONTADOR_REQUESTS = "monitoracao_request_total";
    private static final String CONTADOR_RESPONSES = "monitoracao_response_total";
 
    @Autowired
    private MeterRegistry registry;


    /**
     * Incrementa o contador de recebimento de requests
     */
    public void incrementarContadorRequest(){
        this.registry.counter(CONTADOR_REQUESTS).increment();
    }

    /**
     * Incrementa o contador de envio de respostas
     *
     */
    public void incrementarContadorResponse() {
        this.registry.counter(CONTADOR_RESPONSES).increment();
    }

 

}
