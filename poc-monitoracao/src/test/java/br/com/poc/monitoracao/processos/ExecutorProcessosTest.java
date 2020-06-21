package br.com.poc.monitoracao.processos;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Testes do service {@link ExecutorProcessos}
 *
 * @author mforti
 */
@RunWith(MockitoJUnitRunner.class)
public class ExecutorProcessosTest {

    @Mock
    private BusinessProcessor processorComAceiteProcessamento;

    @Mock
    private BusinessProcessor processorSemAceiteProcessamento;

    @Mock
    private BusinessProcessor processorComExcecao;

    @Spy
    private final List<BusinessProcessor> services = new ArrayList<>();

    @InjectMocks
    private ExecutorProcessos executorProcessos;

    @Test
    public void testProcessamento() {
        when(this.processorComAceiteProcessamento.aceita(any())).thenReturn(true);
        when(this.processorSemAceiteProcessamento.aceita(any())).thenReturn(false);

        this.services.clear();
        this.services.add(this.processorComAceiteProcessamento);
        this.services.add(this.processorSemAceiteProcessamento);

        byte[] payload = null;
        this.executorProcessos.process(new BusinessProcessContext(payload));

        verify(this.processorComAceiteProcessamento, atLeastOnce()).process(any());
        verify(this.processorSemAceiteProcessamento, never()).process(any());
    }

    @Test
    public void testProcessorComExcecao() {
        when(this.processorComExcecao.aceita(any())).thenReturn(true);
        doThrow(new RuntimeException("Teste de Processo com Exceção")).when(this.processorComExcecao).process(any());

        this.services.clear();
        this.services.add(this.processorComExcecao);
        byte[] payload = null;
        this.executorProcessos.process(new BusinessProcessContext(payload));

        // Processo deve ser executado sem quem uma exceção seja lançada
        verify(this.processorComExcecao, atLeastOnce()).process(any());
    }
}
