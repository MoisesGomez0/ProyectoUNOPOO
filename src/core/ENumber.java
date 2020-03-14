package core;


/**
 *Contiene los números y símbolos que puede tener una carta.
 * @author Leonardo
 */
public enum ENumber {
    
    CERO(0,0,"CERO",0),
    ONE(1,1,"ONE",1), 
    TWO(2,2,"TWO",2),
    THREE(3,3,"THREE",3),
    FOUR(4,4,"FOUR",4),
    FIVE(5,5,"FIVE",5),
    SIX(6,6,"SIX",6),
    SEVEN(7,7,"SEVEN",7),
    EIGHT(8,8,"EIGHT",8),
    NINE(9,9,"NINE",9),
    DTWO(10,0,"DTWO",20),/**Las cartas especiales no tienen valor numérico.*/
    REVERSE(11,0,"REVERSE",20),
    SKIP(12,0,"SKIP",20),
    WILD(13,0,"WILD",50),
    DFOUR(14,0,"DFOUR",50);
	
    
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
     * Combierte un String a un objeto ENumber.
     * @param number
     * @return Objeto ENumber del valor especificado.
     */
    public static ENumber parse(String number) {
    	switch (number.strip()) {
		case ("CERO"):
			return ENumber.CERO;
		
		case ("ONE"):
			return ENumber.ONE;
		
		case ("TWO"):
			return ENumber.TWO;
		
		case ("THREE"):
			return ENumber.THREE;
		
		case ("FOUR"):
			return ENumber.FOUR;
		
		case ("FIVE"):
			return ENumber.FIVE;
		
		case ("SIX"):
			return ENumber.SIX;
		
		case ("SEVEN"):
			return ENumber.SEVEN;
		
		case ("EIGHT"):
			return ENumber.EIGHT;
		
		case ("NINE"):
			return ENumber.NINE;
		
		case ("DTWO"):
			return ENumber.DTWO;
		
		case ("REVERSE"):
			return ENumber.REVERSE;	
		
		case ("SKIP"):
			return ENumber.SKIP;	
		
		case ("WILD"):
			return ENumber.WILD;	
		
		case ("DFOUR"):
			return ENumber.DFOUR;		
		
		default:
			throw new IllegalArgumentException("Parámetro no permitido.");
		}    	
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
