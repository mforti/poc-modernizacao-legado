
package br.com.poc.monitoracao.validacoes.impl.pagamentoconta;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.poc.monitoracao.dominio.CodigoResposta;
import br.com.poc.monitoracao.processos.BusinessProcessContext;
import br.com.poc.monitoracao.processos.OrdemValidacaoPagamentoConta;
import br.com.poc.monitoracao.validacoes.ValidacaoPagamentoConta;

/**
 * Classe que implementa uma das validações de pagamento de contas. Vazio, apenas demonstração
 * 
 * @author mforti
 */
@Component
@Order(OrdemValidacaoPagamentoConta.ORDEM_VALIDACAO_MOCK1_PAGAMENTO_CONTA)
public class ValidacaoPagamentoContaMock1 implements ValidacaoPagamentoConta {

    @Override
    public void validar(final BusinessProcessContext context) {
       //Regras de negócio. Apenas demonstracao
    	context.setCodigoResposta(CodigoResposta.SUCESSO);
    }
}
