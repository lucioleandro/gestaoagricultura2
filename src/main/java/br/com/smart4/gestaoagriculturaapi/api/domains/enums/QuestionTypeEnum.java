package br.com.smart4.gestaoagriculturaapi.api.domains.enums;

public enum QuestionTypeEnum {

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

	QuestionTypeEnum(String nome) {
		this.nome = nome;
	}
}
