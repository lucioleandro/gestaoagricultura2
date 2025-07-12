package br.com.smart4.gestaoagriculturaapi.api.domain.enums;

public enum EnumTipoPergunta {

	ME("Multipla Escolha"),
	A("Aberta"),
	UE("Única Escolha");
	
	private final String nome;
	
	public String getNome() {
		return nome;
	}
	
	public String toString() {
    	return nome;
    }

	EnumTipoPergunta(String nome) {
		this.nome = nome;
	}
}
