
package br.com.poc.monitoracao.dominio;

import java.util.Arrays;
import java.util.Objects;

/**
 * Enum para codigos de retorno na mensagem.
 *
 * @author mforti
 */
public enum CodigoResposta {

	SUCESSO("00", true),
	FALHA("99", false);

    String codigo;
    Boolean sucesso;

    private CodigoResposta(final String codigo, final Boolean sucesso) {
        this.codigo = codigo;
        this.sucesso = sucesso;
    }

    /**
     * Obtém o valor do atributo codigo
     *
     * @return O valor do atributo codigo
     */
    public String getCodigo() {
        return this.codigo;
    }

    /**
     * Retorna sucesso ou falha em caso de transação autorizada ou não
     *
     * @return O valor do atributo sucesso
     */
    public Boolean isSucesso() {
        return this.sucesso;
    }

    /**
     * Retorna o codigo de retorno referente ao codigo recebe
     *
     * @return codigo do retorno
     */
    public static CodigoResposta byCodigo(final String codigo) {
        if (codigo == null) {
            return null;
        }
        return Arrays.stream(values())
                        .filter(e -> Objects.equals(codigo, e.codigo))
                        .findFirst()
                        .orElse(null);
    }

}
