package br.com.poc.monitoracao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import br.com.poc.monitoracao.model.MensagemResponse;
import br.com.poc.monitoracao.processos.BusinessProcessContext;
import br.com.poc.monitoracao.processos.ExecutorProcessos;
import io.micrometer.core.annotation.Timed;

/**
 * Classe responsável por tratar as mensagens recebidas.
 *
 * @author mforti
 */
@Component
public class MonitoracaoService {

     @Autowired
	 private ExecutorProcessos executorProcessos;

     @Autowired
     private MonitoriaService monitoria;


	/**
	 * Método responsável por processar a mensagem recebida
	 */
	@Timed(value = "monitoracao_response_time")
	public Message<byte[]> process(final Message<byte[]> inputMessage)  {

		BusinessProcessContext context = new BusinessProcessContext(inputMessage.getPayload());

		this.executorProcessos.process(context);

	    this.monitoria.incrementarContadorResponse();

		MensagemResponse response = MensagemResponse.parse(context);
		return MessageBuilder.withPayload(response.getPayload().getBytes())
                .copyHeaders(inputMessage.getHeaders()).build();
	}


}