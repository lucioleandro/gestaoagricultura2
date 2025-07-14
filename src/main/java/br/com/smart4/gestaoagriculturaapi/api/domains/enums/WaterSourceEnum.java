package br.com.smart4.gestaoagriculturaapi.api.domains.enums;

public enum WaterSourceEnum {
	
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

	@Override
	public String toString() {
    	return nome;
    }

	WaterSourceEnum(String nome) {
		this.nome = nome;
	}

	
}
