package br.com.poc.monitoracao.config;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;



/**
 * Configuração do producer para enviar as transações para o kafka 
 *
 * @author mforti
 */
@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.producer.acks}")
    private String acks;

    @Value("${spring.kafka.producer.properties.request.timeout.ms}")
    private int kafkaTimeout;

    @Value("${spring.kafka.producer.batch-size}")
    private int kafkaBatchSize;
    
    @Value("${spring.kafka.producer.buffer-memory}")
    private String bufferMemory;

    @Value("${spring.kafka.producer.retries}")
    private int kafkaRetries;

    @Value("${spring.kafka.producer.properties.linger.ms}")
    private int kafkaLingesMs;
    
    @Value("${spring.kafka.producer.properties.max.block.ms}")
    private int kafkaMaxBlockMs;

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaHostPort;

    @Value("${monitoracao.topic}")
    private String topicoAdvice;
    

    @Bean("producerConfigs")
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.BATCH_SIZE_CONFIG, this.kafkaBatchSize);
        props.put(ProducerConfig.RETRIES_CONFIG, this.kafkaRetries);
        props.put(ProducerConfig.ACKS_CONFIG, this.acks);
        props.put(ProducerConfig.LINGER_MS_CONFIG, this.kafkaLingesMs);
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, this.kafkaTimeout);
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, this.kafkaMaxBlockMs);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, this.bufferMemory);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.kafkaHostPort);

        return props;
    }

    @Bean("producerConfigsMonitoracao")
    public Map<String, Object> producerConfigsMonitoracao() {
        Map<String, Object> props = new HashMap<>(this.producerConfigs());

        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                        "org.apache.kafka.common.serialization.ByteBufferSerializer");

        return props;
    }


    @Bean("producerFactoryMonitoracao")
    public ProducerFactory<String, ByteBuffer> producerFactoryMonitoracao() {
        return new DefaultKafkaProducerFactory<>(this.producerConfigsMonitoracao());
    }


    @Bean("kafkaTemplateMonitoracao")
    public KafkaTemplate<String, ByteBuffer> kafkaTemplateMonitoracao() {
        KafkaTemplate<String, ByteBuffer> producer = new KafkaTemplate<>(this.producerFactoryMonitoracao());
        producer.setDefaultTopic(this.topicoAdvice);
        return producer;
    }

   
}