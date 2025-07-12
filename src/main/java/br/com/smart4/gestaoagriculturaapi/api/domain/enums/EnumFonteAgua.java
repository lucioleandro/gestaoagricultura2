package br.com.smart4.gestaoagriculturaapi.api.domain.enums;

public enum EnumFonteAgua {
	
	IP("Irrigação Pública"),
	R("Rio"),
	C("Correfo"),
	PF("Poço Profundo"),
	PA("Poço Amazonas / Cacimba"),
	B("Barragem / Represa / Açude"),
	L("Lagoa"),
	N("Nenhuma");
	
	private final String nome;

	public String getNome() {
		return nome;
	}
	
	public String toString() {
    	return nome;
    }

	EnumFonteAgua(String nome) {
		this.nome = nome;
	}

	
}
