package br.com.poc.monitoracao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.ip.tcp.TcpReceivingChannelAdapter;
import org.springframework.integration.ip.tcp.TcpSendingMessageHandler;
import org.springframework.integration.ip.tcp.connection.AbstractConnectionFactory;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.ByteArrayLengthHeaderSerializer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import br.com.poc.monitoracao.factory.MeteredTcpNioServerConnectionFactory;
import br.com.poc.monitoracao.services.MonitoracaoService;

/**
 * Classe responsável por inicializar as configurações do Socket Server.
 *
 * @author mforti
 */
@Configuration
public class SocketServerConfig {

    /** Quantidade de Bytes que indicam o tamanho da mensagem **/
    private static final int MESSAGE_HEADER_SIZE = 2;

    @Autowired
    private MonitoracaoService service;

    @Bean(name = "byteArrayLengthHeaderSerializer")
    public ByteArrayLengthHeaderSerializer serializer(@Value("${monitoracao.message.size.max}") Integer maxMessageSize) {
        ByteArrayLengthHeaderSerializer byteArrayLengthHeaderSerializer = new ByteArrayLengthHeaderSerializer(
                        MESSAGE_HEADER_SIZE);
        byteArrayLengthHeaderSerializer.setMaxMessageSize(maxMessageSize);
        return byteArrayLengthHeaderSerializer;
    }

    @Bean(name = "server")
    public MeteredTcpNioServerConnectionFactory serverFactory(@Value("${monitoracao.port}") int port, @Value("${monitoracao.factory.connection.backlog}") int backlogSize) {
        MeteredTcpNioServerConnectionFactory factoryBean =
                new MeteredTcpNioServerConnectionFactory(port);

        factoryBean.setSerializer(this.serializer(null));
        factoryBean.setDeserializer(this.serializer(null));
        factoryBean.setSoKeepAlive(true);
        factoryBean.setBacklog(backlogSize);
        return factoryBean;

    }

    /** ip:tcp-inbound-channel-adapter **/
    @Bean(name = "inAdapter.server")
    public TcpReceivingChannelAdapter fromTcpAdapter(final AbstractServerConnectionFactory serverFactory) {
        TcpReceivingChannelAdapter adapter = new TcpReceivingChannelAdapter();
        adapter.setConnectionFactory(serverFactory);
        adapter.setOutputChannelName("inboundChannel");
        return adapter;
    }

    /** channel **/
    @Bean(name = "inboundChannel")
    public MessageChannel fromTcp() {
        return new DirectChannel();
    }

    /** ip:tcp-outbound-channel-adapter **/

    @ServiceActivator(inputChannel = "outboundChannel", requiresReply = "true")
    @Bean
    public TcpSendingMessageHandler toTcpAdapter(final AbstractConnectionFactory serverFactory) {
        TcpSendingMessageHandler tcpOutboundAdp = new TcpSendingMessageHandler();
        tcpOutboundAdp.setConnectionFactory(serverFactory);
        return tcpOutboundAdp;
    }

    /** service-activator **/
    @Transformer(inputChannel = "inboundChannel", outputChannel = "outboundChannel")
    public Message<byte[]> service(final Message<byte[]> message)  {
        return this.service.process(message);
    }
}
