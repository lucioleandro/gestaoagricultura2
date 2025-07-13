package br.com.smart4.gestaoagriculturaapi.api.domains.enums;

public enum BirdEnum {
	
	Galinha("Galinha"),
	GALINHA_ANGOLA("Galinha Angola"),
	PERU("PERU"),
	CODORNA("CODORNA"),
	OUTROS("OUTROS");
	
	private final String nome;
	
	public String getNome() {
		return nome;
	}
	
	public String toString() {
    	return nome;
    }

	BirdEnum(String nome) {
		this.nome = nome;
	}
}
