
package br.com.poc.monitoracao.processos;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *
 * Cadeia de execução de processos 
 *
 * @author mforti
 */
@Component
public class ExecutorProcessos {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorProcessos.class);

    @Autowired
    private List<BusinessProcessor> services;

    /**
     * Processa a transação recebida.
     *
     * @see BusinessProcessor
     * @param context
     */
    public void process(final BusinessProcessContext context) {

        for (BusinessProcessor service : this.services) {

            if (service.aceita(context)) {
                try {
                    service.process(context);
                } catch (Exception e) {
                    LOGGER.error("Ocorreu um erro na execução do processo: {}", service.getClass().getSimpleName(), e);
                   
                }            
            }
        }

    }

}
