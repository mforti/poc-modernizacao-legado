
package br.com.poc.monitoracao.validacoes.impl.comum;

import org.springframework.stereotype.Component;

import br.com.poc.monitoracao.dominio.CodigoResposta;
import br.com.poc.monitoracao.processos.BusinessProcessContext;
import br.com.poc.monitoracao.validacoes.ValidacaoComum;

/**
 * Classe que implementa a validação de lista restritiva, supondo comum a todos os produtos. Vazio, apenas demonstração
 * 
 * @author mforti
 */
@Component
public class ValidacaoListaRestritiva implements ValidacaoComum {

    
    @Override
    public void validar(final BusinessProcessContext context) {
       //Regras de negócio para lista restritiva. Apenas demonstracao
    	context.setCodigoResposta(CodigoResposta.SUCESSO);
    }

   

}