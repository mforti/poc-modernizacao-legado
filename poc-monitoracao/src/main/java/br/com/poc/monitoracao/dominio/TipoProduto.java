package br.com.poc.monitoracao.dominio;

import java.util.Arrays;
import java.util.Objects;

public enum TipoProduto {

	PAGAMENTO_CONTA("000001"), TRANSFERENCIA("000002"), SAQUE("000003"), INVALIDO("999999");

	private String tipoProduto;

	private TipoProduto(final String tipoProduto) {
		this.tipoProduto = tipoProduto;

	}

	public static TipoProduto byCodigo(final String produto) {
		return Arrays.stream(values()).filter(e -> Objects.equals(produto, e.tipoProduto)).findFirst().orElse(INVALIDO);
	}

	public String getTipoProduto() {
		return tipoProduto;
	}

}
