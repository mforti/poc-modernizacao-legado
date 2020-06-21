package br.com.poc.monitoracao.model;

import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;


/**
 * Responsável pelos testes unitários de {@link MensagemRequest}.
 *
 * @author mforti
 */
public class MensagemRequestTest {

    @Test
    public void testParserRequest() {
        MensagemRequest mensagem = givenMensagemRequest();

        Class<MensagemRequest> clazz = MensagemRequest.class;
        try {
            Field[] fields = clazz.getDeclaredFields();
            for (Field atributo : fields) {
                LayoutRequest.porCampo(atributo.getName());
                Method metodo = this.getMethod(atributo.getName(), MensagemRequest.class);
                if (metodo != null) {
                    assertNotNull(metodo.invoke(mensagem).toString());
                }
            }

        } catch (SecurityException | IllegalArgumentException | IllegalAccessException | InvocationTargetException ex) {
        }
    }

    private Method getMethod(final String nome, final Class<?> clazz) {
        StringBuilder sb = new StringBuilder();
        sb.append("get")
                        .append(nome.toUpperCase().charAt(0))
                        .append(nome.substring(1, nome.length()));
        Method metodo = null;
        try {
            metodo = clazz.getMethod(sb.toString());
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException ex) {
        }
        return metodo;
    }

    public static MensagemRequest givenMensagemRequest() {
        StringBuilder sb = new StringBuilder();
        sb.append("020000012321062020120000000000030000000001");

        return MensagemRequest.parse(sb.toString());
    }
}
