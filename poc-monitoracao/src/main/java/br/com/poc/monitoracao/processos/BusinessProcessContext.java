
package br.com.poc.monitoracao.processos;

import br.com.poc.monitoracao.dominio.CodigoResposta;
import br.com.poc.monitoracao.model.MensagemRequest;

/**
 * Contexto da transação 
 *
 * @author mforti
 */
public class BusinessProcessContext {

	private byte[] payload;

    private MensagemRequest mensagemRequest;
    
    private CodigoResposta codigoResposta;


    public void atualizarPayloadMensagemRequest(final MensagemRequest mensagemRequest) {
        this.mensagemRequest = mensagemRequest;
        this.payload = mensagemRequest.getPayload().getBytes();
        this.codigoResposta = CodigoResposta.SUCESSO;
	}
    
    public BusinessProcessContext(final byte[] payload) {
        this.payload = payload;
       
    }

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

	public MensagemRequest getMensagemRequest() {
		return mensagemRequest;
	}

	public void setMensagemRequest(MensagemRequest mensagemRequest) {
		this.mensagemRequest = mensagemRequest;
	}

	public CodigoResposta getCodigoResposta() {
		return codigoResposta;
	}

	public void setCodigoResposta(CodigoResposta codigoResposta) {
		this.codigoResposta = codigoResposta;
	}


}
