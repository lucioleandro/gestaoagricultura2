package br.com.smart4.gestaoagriculturaapi.api.utils;

public class Coordinates {
	
	private String latitude;
	private String longitude;
	
	public Coordinates(String latitude, String longitude) {
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
