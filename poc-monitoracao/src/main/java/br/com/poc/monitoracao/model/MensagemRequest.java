
package br.com.poc.monitoracao.model;

/**
 * Modelo para a Mensagem de Request 
 *
 * @author mforti
 */
public class MensagemRequest {

    private String tipoTransacao;
    private String nsuTransacao;
    private String dataHoraTransacao;
    private String valorTransacao;
    private String produto;
    
    private String payload;
   
    public MensagemRequest() {

    }
  
    public static MensagemRequest parse(final String payload) {
        return new MensagemRequest(payload);
    }


    public MensagemRequest(final String payload) {
    	this.payload = payload;
    	
    	this.tipoTransacao = payload.substring(LayoutRequest.TIPO_TRANSACAO.getPosicaoInicial(), LayoutRequest.TIPO_TRANSACAO.getPosicaoFinal());
    	this.nsuTransacao = payload.substring(LayoutRequest.NSU_TRANSACAO.getPosicaoInicial(), LayoutRequest.NSU_TRANSACAO.getPosicaoFinal());
    	this.dataHoraTransacao = payload.substring(LayoutRequest.DATA_HORA_TRANSACAO.getPosicaoInicial(), LayoutRequest.DATA_HORA_TRANSACAO.getPosicaoFinal());
    	this.valorTransacao = payload.substring(LayoutRequest.VALOR_TRANSACAO.getPosicaoInicial(), LayoutRequest.VALOR_TRANSACAO.getPosicaoFinal());
    	this.produto =  payload.substring(LayoutRequest.PRODUTO.getPosicaoInicial(), LayoutRequest.PRODUTO.getPosicaoFinal());
    	  
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

   

}
