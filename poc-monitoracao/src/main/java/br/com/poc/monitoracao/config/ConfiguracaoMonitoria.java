
package br.com.poc.monitoracao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.poc.monitoracao.services.MonitoriaService;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;


/**
 * Configuração para monitoria prometheus
 * @author mforti
 *
 */
@Configuration
public class ConfiguracaoMonitoria {

	@Bean
	MeterRegistryCustomizer<MeterRegistry> configurer(
	    @Value("${spring.application.name}") final String applicationName) {
	    return (registry) -> registry.config().commonTags("application", applicationName);
	}

    /**
     * Registra o Aspecto que interceptará os métodos anotados como @Timed.
     *
     * @param registry
     * @return O aspecto configurado.
     */
	@Bean
	public TimedAspect timedAspect(@Autowired final MeterRegistry registry) {
		return new TimedAspect(registry);
	}

    @Bean
    public MonitoriaService monitoria() {
        return new MonitoriaService();
    }

}
