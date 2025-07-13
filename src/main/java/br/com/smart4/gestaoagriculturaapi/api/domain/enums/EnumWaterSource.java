package br.com.smart4.gestaoagriculturaapi.api.domain.enums;

public enum EnumWaterSource {
	
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

	EnumWaterSource(String nome) {
		this.nome = nome;
	}

	
}
