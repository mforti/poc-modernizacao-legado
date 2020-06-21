package br.com.poc.monitoracao.factory;

import java.util.List;

import org.springframework.integration.ip.tcp.connection.TcpNioServerConnectionFactory;

/**
 * Classe wrapper responsável pela fabrica de conexões do server e medir as conexões existentes.
 *
 * @author mforti
 */
public class MeteredTcpNioServerConnectionFactory  extends TcpNioServerConnectionFactory {

    //@Autowired
   // private MonitoriaService monitoria;

    public MeteredTcpNioServerConnectionFactory(final int port) {
        super(port);
    }

    @Override
    protected void harvestClosedConnections() {
        super.harvestClosedConnections();
        List<String> connectionIds = super.getOpenConnectionIds();
      //  this.monitoria.atualizarConnectionGauge(connectionIds);
    }
}
