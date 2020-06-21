package br.com.poc.monitoracao.processos;

/**
 * Ordem de execução para os processos
 *
 * @author mforti
 */
public final class OrdemProcessos {
	public static final int ORDEM_REQUEST_PARSER = 0;
    public static final int ORDEM_EXECUTOR_VALIDACOES = 1;
    public static final int ORDEM_PERSISTE_KAFKA = 2;
   
    private OrdemProcessos() {
    }
}