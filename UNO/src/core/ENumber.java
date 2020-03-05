package core;


/**
 *Contiene los números y símbolos que puede tener una carta.
 * @author Leonardo
 */
public enum ENumber {
    
    CERO(0,0,"cero",0),
    ONE(1,1,"uno",1), 
    TWO(2,2,"dos",2),
    THREE(3,3,"tres",3),
    FOUR(4,4,"cuatro",4),
    FIVE(5,5,"cinco",5),
    SIX(6,6,"seis",6),
    SEVEN(7,7,"siete",7),
    EIGHT(8,8,"ocho",8),
    NINE(9,9,"nueve",9),
    DTWO(10,0,"Draw two",20),/**Las cartas especiales no tienen valor numérico.*/
    REVERSE(11,0,"Reverse",20),
    SKIP(12,0,"Skip",20),
    WILD(13,0,"Wild",50),
    DFOUR(14,0,"Draw four",50);
	
    
    private final int value;
    private final String name;
    private final int id; /**Identificador numérico para los valores de las cartas.*/
    private final int scoreValue;
    
    /**
     * Constructor
     * @param id Valor(distinto al valor numérico) único para identificar cada carta.
     * @param value Valor numérico de la carta.
     * @param name Nombre de la carta.
     */
    ENumber(int id, int value, String name, int scoreValue){
        this.value = value;
        this.name =  name;
        this.id = id;
        this.scoreValue = scoreValue;
    }

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the scoreValue
	 */
	public int getScoreValue() {
		return scoreValue;
	}
    
    
}
