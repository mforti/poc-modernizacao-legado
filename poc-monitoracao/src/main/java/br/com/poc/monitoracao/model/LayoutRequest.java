package br.com.poc.monitoracao.model;

import java.util.Arrays;

public enum LayoutRequest {

    TIPO_TRANSACAO(0, 4), 
    NSU_TRANSACAO(4, 10),
    DATA_HORA_TRANSACAO(10, 24),
    VALOR_TRANSACAO(24, 36), 
    PRODUTO(36, 42);

    // Posição inicial no substring
    private int posicaoInicial;

    // Posição Final no substring
    private int posicaoFinal;

    private LayoutRequest(final int posicaoInicial, final int posicaoFinal) {
        this.posicaoInicial = posicaoInicial;
        this.posicaoFinal = posicaoFinal;
    }

    public int getPosicaoInicial() {
        return this.posicaoInicial;
    }

    public int getPosicaoFinal() {
        return this.posicaoFinal;
    }


    public static LayoutRequest porCampo(final String campo) {
        return Arrays.stream(values())
                        .filter(layout -> layout.name().equalsIgnoreCase(campo))
                        .findAny()
                        .orElseThrow(() -> new IllegalArgumentException(
                                        "Campo inválido: " + campo));
    }

}
