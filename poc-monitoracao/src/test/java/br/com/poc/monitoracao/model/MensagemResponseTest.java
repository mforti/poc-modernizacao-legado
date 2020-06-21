package br.com.poc.monitoracao.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.poc.monitoracao.dominio.CodigoResposta;
import br.com.poc.monitoracao.processos.BusinessProcessContext;


/**
 * Responsável pelos testes unitários de {@link MensagemResponseEmissor}.
 *
 * @author <a href="mailto:marcel.forti@elo.com.br">Marcel Forti</a>
 * @version $Id$
 */
public class MensagemResponseTest {

    @Test
    public void testParserRequest() {
        MensagemRequest request = givenMensagemRequest();
        BusinessProcessContext context = new BusinessProcessContext(request.getPayload().getBytes());
        context.atualizarPayloadMensagemRequest(request);
        context.setCodigoResposta(CodigoResposta.SUCESSO);

        MensagemResponse response = MensagemResponse.parse(context);

        assertEquals("Data Hora inválida", request.getDataHoraTransacao(), response.getDataHoraTransacao());
        assertEquals("Tipo Transacao inválida", request.getTipoTransacao(), response.getTipoTransacao());
        assertEquals("Produto inválido", request.getProduto(), response.getProduto());
        assertEquals("Valor Transação inválido", request.getValorTransacao(),
                        response.getValorTransacao());
        assertEquals("NSU inválido", request.getNsuTransacao(),
                        response.getNsuTransacao());
        assertEquals("Codigo de resposta inválida", CodigoResposta.SUCESSO.getCodigo(),
                        response.getCodigoResposta());

    }
    
    public static MensagemRequest givenMensagemRequest() {
        StringBuilder sb = new StringBuilder();
        sb.append("020000012321062020120000000000030000000001");

        return MensagemRequest.parse(sb.toString());
    }
 
}
