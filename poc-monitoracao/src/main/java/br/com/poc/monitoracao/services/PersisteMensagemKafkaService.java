
package br.com.poc.monitoracao.services;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * Servico de envio das mensagens para o kafka
 *
 * @author mforti
 */
@Service
public class PersisteMensagemKafkaService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersisteMensagemKafkaService.class);

	@Autowired
    @Qualifier("kafkaTemplateMonitoracao")
	private KafkaTemplate<String, ByteBuffer> producer;

    @Async
    public SendResult<String, ByteBuffer> envia(final byte[] messageBytes, final String chave)
        throws InterruptedException, ExecutionException {

        LOGGER.debug("Enviando mensagem. Chave: {}", chave);
		ByteBuffer b = ByteBuffer.wrap(messageBytes);
        Message<ByteBuffer> m = MessageBuilder.withPayload(b)
                        .setHeader(KafkaHeaders.MESSAGE_KEY, chave)
                        .build();
		ListenableFuture<SendResult<String, ByteBuffer>> future = this.producer.send(m);
		SendResult<String, ByteBuffer> result = future.get();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Mensagem enviada. Chave: {}", chave);
		}
		return result;
	}
}
