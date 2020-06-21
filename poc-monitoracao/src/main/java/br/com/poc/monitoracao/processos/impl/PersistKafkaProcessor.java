
package br.com.poc.monitoracao.processos.impl;

import static br.com.poc.monitoracao.processos.OrdemProcessos.ORDEM_PERSISTE_KAFKA;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.poc.monitoracao.processos.BusinessProcessContext;
import br.com.poc.monitoracao.processos.BusinessProcessor;
import br.com.poc.monitoracao.services.PersisteMensagemKafkaService;

/**
 * Classe orquestradora de envio da transações ao kafka.
 *
 * @author mforti
 */
@Component
@Order(ORDEM_PERSISTE_KAFKA)
public class PersistKafkaProcessor implements BusinessProcessor {

	@Autowired
	private PersisteMensagemKafkaService kafkaService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PersisteMensagemKafkaService.class);

	/**
	 * Realiza o envio de mensagens ao kafka
	 *
	 * @param context
	 * @return
	 */
	@Override
	public void process(final BusinessProcessContext context) {

		String chave = context.getMensagemRequest().getDataHoraTransacao() + context.getMensagemRequest().getProduto();
		try {
			this.kafkaService.envia(context.getPayload(), chave);
		} catch (InterruptedException |  ExecutionException e) {
			LOGGER.error( "Interrupted!", e);
		    Thread.currentThread().interrupt();

		} 
	}

	@Override
	public boolean aceita(final BusinessProcessContext context) {
		return context.getCodigoResposta().isSucesso();
	}

}
