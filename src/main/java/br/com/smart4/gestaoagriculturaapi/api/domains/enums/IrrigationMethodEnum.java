package br.com.smart4.gestaoagriculturaapi.api.domains.enums;

public enum IrrigationMethodEnum {
	
	S("Sulco"),
	AC("Aspersão Convencional"),
	MA("Microaspersão"),
	PV("Pivô Central"),
	SI("Sub-Irrigação"),
	B("Bacia"),
	GT("Gotejamento");
	
	
	private final String nome;
	
	public String getNome() {
		return nome;
	}
	
	public String toString() {
    	return nome;
    }

	IrrigationMethodEnum(String nome) {
		this.nome = nome;
	}
}
