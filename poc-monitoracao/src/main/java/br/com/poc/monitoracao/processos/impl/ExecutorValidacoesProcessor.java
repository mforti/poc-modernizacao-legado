
package br.com.poc.monitoracao.processos.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.poc.monitoracao.dominio.TipoProduto;
import br.com.poc.monitoracao.processos.BusinessProcessContext;
import br.com.poc.monitoracao.processos.BusinessProcessor;
import br.com.poc.monitoracao.processos.OrdemProcessos;
import br.com.poc.monitoracao.validacoes.Validacao;
import br.com.poc.monitoracao.validacoes.ValidacaoComum;
import br.com.poc.monitoracao.validacoes.ValidacaoPagamentoConta;
import br.com.poc.monitoracao.validacoes.ValidacaoSaque;
import br.com.poc.monitoracao.validacoes.ValidacaoTransferencia;

/**
 * Classe orquestradora de validacoes
 *
 * @author mforti
 */
@Component
@Order(OrdemProcessos.ORDEM_EXECUTOR_VALIDACOES)
public class ExecutorValidacoesProcessor implements BusinessProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorValidacoesProcessor.class);

    @Autowired
    private List<ValidacaoPagamentoConta> validacoesPagamentoConta;

    @Autowired
    private List<ValidacaoSaque> validacoesSaque;

    @Autowired
    private List<ValidacaoTransferencia> validacoesTransferencia;

    @Autowired
    private List<ValidacaoComum> validacoesComuns;

    @Override
    public void process(final BusinessProcessContext context) {

        TipoProduto tipoProduto = TipoProduto.byCodigo(context.getMensagemRequest().getProduto());
       
        this.executaValidacoes(context, this.validacoesComuns);

        if (this.aceita(context)) {

            switch (tipoProduto) {
                case PAGAMENTO_CONTA:
                    this.executaValidacoes(context, this.validacoesPagamentoConta);
                    break;
                case TRANSFERENCIA:
                    this.executaValidacoes(context, this.validacoesTransferencia);
                    break;
                case SAQUE:
                    this.executaValidacoes(context, this.validacoesSaque);
                    break;
                default:
                    throw new UnsupportedOperationException(
                                    "Tipo de produto não tratado: " + tipoProduto);
            }
        }

        LOGGER.debug("Fim das validações");
    }

    private void executaValidacoes(final BusinessProcessContext context, final List<? extends Validacao> validacoes) {
        for (Validacao validacao : validacoes) {
            if (this.aceita(context)) {
                validacao.validar(context);
            } else {
                break;
            }
        }
    }

   

    @Override
    public boolean aceita(final BusinessProcessContext context) {
        return context.getCodigoResposta().isSucesso();
    }

}
