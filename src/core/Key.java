package core;

import java.util.ArrayList;

public class Key {
	
	
	private final String NONE= "NONE"; /**Constante que representa que no hay nada en el valor (distinto de null)*/
	
	private String name;/**Nombre de la llave.*/
	private String value;/**Valor asignado a la llave.*/
	private ArrayList<String> stringValues = new ArrayList<>();
	private Dictionary dictionaryValue;/**Una llave también puede tener un diccionario como valor*/
	
	/**Constructor vacío.*/
	public Key() {}
	
	/**
	 * Constructor sobrecargado.
	 * 
	 * Lallave solo puede tener un valor. Por lo tanto si tiene un String como valor, el atributo dictionaryValue
	 * será null y stringValues  tendrá un NONE como único elemento.
	 * 
	 * @param name Nombre de la llave.
	 * @param value Valor de la llave.
	 */
	public Key (String name, String value) {
		this.setName(name);
		this.setValue(value);
		this.dictionaryValue = null;
		
		this.stringValues.clear();
		this.stringValues.add(NONE);
	}
	
	/**
	 * Constructor sobrecargado.
	 * 
	 * La llave solo puede tener un valor. Por lo tanto  si tiene un arreglo como valor, el atributo value 
	 * será NONE y dictionaryValue será null.
	 * 
	 * @param name Nombre de la llave.
	 * @param stringValues Arreglo de Strings como valor de la llave.
	 */
	public Key (String name, ArrayList<String> stringValues) {
		this.name = name;
		this.stringValues = stringValues;
		this.value = NONE;
		this.dictionaryValue = null;
	}
	/**
	 * Constructor sobrecargado.
	 * @param name Nombre de la Llave.
	 * @param value Valor int de la llave.
	 */
	public Key (String name, int value) {
		this.setName(name);
		this.setValue(Integer.toString(value));
		this.dictionaryValue = null;
		
		this.stringValues.clear();
		this.stringValues.add(NONE);		
	}
	
	public Key (String name, Dictionary dictionaryValue) {
		this.dictionaryValue = dictionaryValue;
		
		this.setName(name);
		this.setValue(NONE);
		
		this.stringValues.clear();
		this.stringValues.add(NONE);
	}
	
	/**
	 * Constructor sobrecargado.
	 * @param name Nombre de la llave.
	 */
	public Key (String name) {
		this.setName(name);
		
		this.value=NONE;
		this.dictionaryValue=null;
		
		this.stringValues.clear();
		this.stringValues.add(NONE);
		
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("");
		 /**
		  * Contiene un String como valor.
		  */
		if (this.dictionaryValue == null && this.stringValues.get(0) == NONE){
			
			if (this.value.strip().matches("^-?\\d+(.\\d+)?$")) {/**Si el valor es un número entero o flotante.*/
				result.append(String.format("\"%s\":%s",this.name.strip(),this.value.strip()));			
				
			}else {
				result.append(String.format("\"%s\":\"%s\"",this.name.strip(),this.value.strip()));				
				
			}
		}else 
			/**
			 * Contiene un objeto Dictionary. 
			 */			
			if(this.value == NONE && this.stringValues.get(0) == NONE) {
			return this.dictionaryValue.toString();
		}else
			/**
			 * Contiene un arreglo de strings.
			 */
			if(this.value == NONE && this.stringValues.get(0) != NONE && this.dictionaryValue == null){
				int tab = 1;
				if (stringValues.size() == 1){
					result.append(String.format("\"%s\":[%s]", this.name,this.stringValues));
				}else {
					result.append(String.format("\"%s\":\n%s[\n",this.name,"\t".repeat(tab)));
					for (String string : stringValues.subList(0, stringValues.size()-1)) {
						result.append(String.format("%s%s,\n","\t".repeat(tab+1),string));
					}
					result.append(String.format("%s%s\n","\t".repeat(tab+1),stringValues.get((stringValues.size()-1))));/*Último valor sin coma al final.*/
					result.append(String.format("%s]", "\t".repeat(tab)));					
				}
		}
		
		return result.toString();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;	
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
		
		this.dictionaryValue = null;
		
		this.stringValues.clear();
		this.stringValues.add(NONE);
		
	}

	/**
	 * @return the stringValues
	 */
	public ArrayList<String> getStringArrayValues() {
		return stringValues;
	}

	/**
	 * @param stringValues the stringValues to set
	 */
	public void setStringArrayValues(ArrayList<String> stringArrayValues) {
		this.stringValues = stringArrayValues;
		this.value = NONE;
		this.dictionaryValue = null;
	}

	/**
	 * @return the dictionaryValue
	 */
	public Dictionary getDictionaryValue() {
		return dictionaryValue;
	}

	/**
	 * @param dictionaryValue the dictionaryValue to set
	 */
	public void setDictionaryValue(Dictionary dictionaryValue) {
		this.dictionaryValue = dictionaryValue;
		
		this.setName(name);
		this.setValue(NONE);
		
		this.stringValues.clear();
		this.stringValues.add(NONE);
	}
	
	/**Pruebas de la clase.*/
	public static void main(String[] args) {
		ArrayList<String> l = new ArrayList<>();
		l.add("hello");
		l.add("world");
		l.add("No hay tal cosa");
		l.add("UNO");
		Key key0 = new Key("hola",1);
		Key key1 = new Key("hola","mundo");
		Key key2 = new Key("hola",l);
		
		System.out.println(key0);
		System.out.println(key1);
		System.out.println(key2);
	}
	
}
