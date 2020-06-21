
package br.com.poc.monitoracao.model;

import br.com.poc.monitoracao.processos.BusinessProcessContext;

/**
 * Modelo para a Mensagem de Request 
 *
 * @author mforti
 */
public class MensagemResponse {

    private String tipoTransacao;
    private String nsuTransacao;
    private String dataHoraTransacao;
    private String valorTransacao;
    private String produto;
    private String codigoResposta;
    
    private String payload;
   
    public MensagemResponse() {

    }
  
    public static MensagemResponse parse(final BusinessProcessContext context) {
        return new MensagemResponse(context);
    }


    public MensagemResponse(final BusinessProcessContext context) {
    	 this.payload = new String(context.getPayload())
                 .concat(context.getCodigoResposta().getCodigo());

    	 System.out.println("Payload: " + this.payload);
    	
    	this.tipoTransacao = payload.substring(LayoutResponse.TIPO_TRANSACAO.getPosicaoInicial(), LayoutResponse.TIPO_TRANSACAO.getPosicaoFinal());
    	this.nsuTransacao = payload.substring(LayoutResponse.NSU_TRANSACAO.getPosicaoInicial(), LayoutResponse.NSU_TRANSACAO.getPosicaoFinal());
    	this.dataHoraTransacao = payload.substring(LayoutResponse.DATA_HORA_TRANSACAO.getPosicaoInicial(), LayoutResponse.DATA_HORA_TRANSACAO.getPosicaoFinal());
    	this.valorTransacao = payload.substring(LayoutResponse.VALOR_TRANSACAO.getPosicaoInicial(), LayoutResponse.VALOR_TRANSACAO.getPosicaoFinal());
    	this.produto =  payload.substring(LayoutResponse.PRODUTO.getPosicaoInicial(), LayoutResponse.PRODUTO.getPosicaoFinal());
    	this.setCodigoResposta(payload.substring(LayoutResponse.CODIGO_RESPOSTA.getPosicaoInicial(), LayoutResponse.CODIGO_RESPOSTA.getPosicaoFinal()));
    
    }

	public String getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(String tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public String getNsuTransacao() {
		return nsuTransacao;
	}

	public void setNsuTransacao(String nsuTransacao) {
		this.nsuTransacao = nsuTransacao;
	}

	public String getDataHoraTransacao() {
		return dataHoraTransacao;
	}

	public void setDataHoraTransacao(String dataHoraTransacao) {
		this.dataHoraTransacao = dataHoraTransacao;
	}

	public String getValorTransacao() {
		return valorTransacao;
	}

	public void setValorTransacao(String valorTransacao) {
		this.valorTransacao = valorTransacao;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getCodigoResposta() {
		return codigoResposta;
	}

	public void setCodigoResposta(String codigoResposta) {
		this.codigoResposta = codigoResposta;
	}

   

}
