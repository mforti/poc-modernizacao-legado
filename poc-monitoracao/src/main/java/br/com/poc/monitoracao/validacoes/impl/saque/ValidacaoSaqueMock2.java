
package br.com.poc.monitoracao.validacoes.impl.saque;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.poc.monitoracao.dominio.CodigoResposta;
import br.com.poc.monitoracao.processos.BusinessProcessContext;
import br.com.poc.monitoracao.processos.OrdemValidacaoSaque;
import br.com.poc.monitoracao.validacoes.ValidacaoSaque;

/**
 * Classe que implementa uma das validações de Saque. Vazio, apenas demonstração
 * 
 * @author mforti
 */
@Component
@Order(OrdemValidacaoSaque.ORDEM_VALIDACAO_MOCK2_SAQUE)
public class ValidacaoSaqueMock2 implements ValidacaoSaque {

    @Override
    public void validar(final BusinessProcessContext context) {
       //Regras de negócio. Apenas demonstracao
    	context.setCodigoResposta(CodigoResposta.SUCESSO);
    }
}
