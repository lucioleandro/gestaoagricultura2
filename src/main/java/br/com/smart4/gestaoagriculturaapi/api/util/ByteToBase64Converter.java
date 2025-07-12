package br.com.smart4.gestaoagriculturaapi.api.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Base64;

public class ByteToBase64Converter implements JsonSerializer<byte[]> {

	@Override
	public JsonElement serialize(byte[] arquivo, Type typeOfSrc, JsonSerializationContext context) {
		byte[] convertido = Base64.getEncoder().encode(arquivo);
		
		return new JsonPrimitive(new String(convertido));
	}
	
		
}
