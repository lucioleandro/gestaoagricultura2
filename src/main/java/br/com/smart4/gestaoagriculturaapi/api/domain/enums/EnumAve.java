package br.com.smart4.gestaoagriculturaapi.api.domain.enums;

public enum EnumAve {
	
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

	EnumAve(String nome) {
		this.nome = nome;
	}
}
