package br.com.poc.monitoracao.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.poc.monitoracao.dominio.CodigoResposta;
import br.com.poc.monitoracao.model.MensagemRequest;
import br.com.poc.monitoracao.processos.BusinessProcessContext;
import br.com.poc.monitoracao.processos.impl.RequestParserProcessor;


/**
 * Teste unitário para a classe {@link EmissorRequestParserProcessor}.
 *
 * @author mforti
 */
@RunWith(MockitoJUnitRunner.class)
public class RequestParserProcessorTest {

    @InjectMocks
    private RequestParserProcessor processor;

    @Mock
    private BusinessProcessContext context;

    @Mock
    private MonitoriaService monitoria;

      @Captor
    private ArgumentCaptor<MensagemRequest> argumentCaptor; 

    @Test
    public void testProcessamentoSucesso() {
        final MensagemRequest request = givenMensagemRequest();
        when(this.context.getPayload()).thenReturn(request.getPayload().getBytes());
        when(this.context.getCodigoResposta())
                        .thenReturn(CodigoResposta.SUCESSO);
        this.processor.process(this.context);
        verify(this.context).atualizarPayloadMensagemRequest(this.argumentCaptor.capture());
        verify(this.context, times(1)).atualizarPayloadMensagemRequest(Mockito.any());

        assertTrue(this.processor.aceita(this.context));
    }
    
    @Test
    public void testProcessamentoFalha() {
        final MensagemRequest request = givenMensagemRequest();
        when(this.context.getPayload()).thenReturn(request.getPayload().getBytes());
        when(this.context.getCodigoResposta())
        .thenReturn(CodigoResposta.FALHA);
        this.processor.process(this.context);
        verify(this.context).atualizarPayloadMensagemRequest(this.argumentCaptor.capture());
        verify(this.context, times(1)).atualizarPayloadMensagemRequest(Mockito.any());

        assertFalse(this.processor.aceita(this.context));

    }

    
    public static MensagemRequest givenMensagemRequest() {
        StringBuilder sb = new StringBuilder();
        sb.append("020000012321062020120000000000030000000001");

        return MensagemRequest.parse(sb.toString());
    }

}
