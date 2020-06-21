
package br.com.poc.monitoracao.validacoes.impl.transferencia;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.poc.monitoracao.dominio.CodigoResposta;
import br.com.poc.monitoracao.processos.BusinessProcessContext;
import br.com.poc.monitoracao.processos.OrdemValidacaoTransferencia;
import br.com.poc.monitoracao.validacoes.ValidacaoTransferencia;

/**
 * Classe que implementa uma das validações de Transferencias. Vazio, apenas demonstração
 * 
 * @author mforti
 */
@Component
@Order(OrdemValidacaoTransferencia.ORDEM_VALIDACAO_MOCK2_TRANSFERENCIA)
public class ValidacaoTransferenciaMock2 implements ValidacaoTransferencia {

    @Override
    public void validar(final BusinessProcessContext context) {
       //Regras de negócio. Apenas demonstracao
    	context.setCodigoResposta(CodigoResposta.SUCESSO);
    }
}
