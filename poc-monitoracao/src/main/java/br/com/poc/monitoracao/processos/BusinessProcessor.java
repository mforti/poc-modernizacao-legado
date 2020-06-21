
package br.com.poc.monitoracao.processos;


/**
 *
 * Interface para processos que serão executados no processamento.
 *
 * @author mforti
 */
public interface BusinessProcessor {

    /**
     * Método principal de execução do processo
     * 
     * @param context
     * @throws ISOException
     */
    void process(BusinessProcessContext context);

    /**
     * Define se o processo será executado ou não
     *
     * @param context
     *          
     * @return Execução ou não do processo.
     */
	default boolean aceita(final BusinessProcessContext context) {
        return true;
	}


}
