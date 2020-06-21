
package br.com.poc.monitoracao.processos.impl;

import static br.com.poc.monitoracao.processos.OrdemProcessos.ORDEM_REQUEST_PARSER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.poc.monitoracao.model.MensagemRequest;
import br.com.poc.monitoracao.processos.BusinessProcessContext;
import br.com.poc.monitoracao.processos.BusinessProcessor;
import br.com.poc.monitoracao.services.MonitoriaService;

/**
 * Classe orquestradora dos Requests.
 *
 * @author mforti
 */
@Component
@Order(ORDEM_REQUEST_PARSER)
public class RequestParserProcessor implements BusinessProcessor {

	@Autowired
	private MonitoriaService monitoria;

	/**
	 * Realiza o processamento do recebimento da request
	 *
	 * @param context
	 * @return
	 */
	@Override
	public void process(final BusinessProcessContext context) {

		MensagemRequest request = MensagemRequest.parse(new String(context.getPayload()));
		context.atualizarPayloadMensagemRequest(request);
		
		this.monitoria.incrementarContadorRequest();
	}

	@Override
	public boolean aceita(final BusinessProcessContext context) {
		return context.getCodigoResposta().isSucesso();
	}

}
