package br.com.smart4.gestaoagriculturaapi.api.utils;

public class ResponseMessage {
	
	private String message;

	public ResponseMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
