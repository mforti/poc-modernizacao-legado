
package br.com.poc.monitoracao.validacoes;

import br.com.poc.monitoracao.processos.BusinessProcessContext;

/**
 * Interface das validações pelas quais uma transação passa.
 *
 * @author mforti
 */
@FunctionalInterface
public interface Validacao {

    /**
     * Executa a validação
     * @param context Contexto a ser validado pela regra
     */
	public void validar(BusinessProcessContext context);

}
