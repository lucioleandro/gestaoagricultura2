package br.com.smart4.gestaoagriculturaapi.api.domains.enums;

public enum EnumQuestionType {

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

	EnumQuestionType(String nome) {
		this.nome = nome;
	}
}
