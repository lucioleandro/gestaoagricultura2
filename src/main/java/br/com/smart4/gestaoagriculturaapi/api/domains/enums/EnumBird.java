package br.com.smart4.gestaoagriculturaapi.api.domains.enums;

public enum EnumBird {
	
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

	EnumBird(String nome) {
		this.nome = nome;
	}
}
