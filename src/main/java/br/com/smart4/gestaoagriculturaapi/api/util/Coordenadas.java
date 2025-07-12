package br.com.smart4.gestaoagriculturaapi.api.util;

public class Coordenadas {
	
	private String latitude;
	private String longitude;
	
	public Coordenadas(String latitude, String longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}
	
}
