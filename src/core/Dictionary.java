package core;

import java.util.ArrayList;

/**
 * Estructura de datos que permite manipular JSONs.
 * 
 * Los ínidices del keys y values deben ser iguales.
 * p e.j. Si se requiere la información de la llave que se encuentra en el indice 0
 * del ArrayList keys, se b
 * @author leonardo
 * @version 0.1.0 Es una versión muy primitiva de lo que realmente es un diccionario.
 */
public class Dictionary {
	
	private ArrayList<Key> keys = new ArrayList<>();

	/**Constructor vacío de la clase.*/
	public Dictionary() {}
	
	public Key get(String keyName) { 
		if (this.getKeys().isEmpty()) {/**Si no existen llaves no existen valores.*/
			throw new IllegalArgumentException("El diccionario está vacío.");
		}
		
		ArrayList<String> keysNames = new ArrayList<>();
		for (Key key : getKeys()) {
			keysNames.add(key.getName()); /**Agrega los nombres de las claves en un arreglo para facilitar su busqueda*/
		}
		
		int keyIndex = keysNames.indexOf(keyName.strip());
		
		if (keyIndex == -1) {/**Si no encuentra la llave especificada.*/
			throw new IllegalArgumentException(String.format("No se encontró ninguna key: ", keyName.strip()));
		}
		
		return this.getKeys().get(keyIndex);
	}
	
	/**
	 * Agrega una llave al diccionario.
	 * @param keyName Nombre de la llave.
	 */
	public void add(String keyName) {
		this.getKeys().add(new Key(keyName));
	}
	/**
	 * Agrega una llave al diccionario.
	 * @param keyName Nombre de la llave.
	 * @param keyValue Valor String de la llave.
	 */
	public void add(String keyName, String keyValue) {
		this.getKeys().add(new Key(keyName,keyValue));
	}
	
	/**
	 * Agrega una llave al diccionario.
	 * @param keyName Nombre de la llave.
	 * @param keyValue Valor entero de la llave.
	 */
	public void add(String keyName, int keyValue) {
		this.getKeys().add(new Key(keyName,keyValue));
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("");
		
		result.append("{\n\t");
		
		result.append("}");
		
		return result.toString();
	}
	
	
	/**
	 * @return the keys
	 */
	public ArrayList<Key> getKeys() {
		return keys;
	}

	/**
	 * @param keys the keys to set
	 */
	public void setKeys(ArrayList<Key> keys) {
		this.keys = keys;
	}

	/**Pruebas de la clase*/
	public static void main(String[] args) {
		ArrayList<String> a = new ArrayList<>();
		a.add("key");
		System.out.println(a.indexOf("key"));
	}
}
