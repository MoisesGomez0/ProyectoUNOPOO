package core;

/**
 *
 * @author Leonardo
 */
public class Card {
    
    private EColor color;
    private EValue value;
    
    /**
     * Constructor vacío de la clase
     */
    public Card() {}
    /**
     * Constructor de la clase.
     * @param value Número o símbolo que se le asigna a la carta.
     * @param color	Color que se le asigna a la carta.
     */
    public Card(EValue value, EColor color){
        this.setColor(color);
        this.setValue(value);      
        
            
    }
    public Card(String card) {
    	if (!card.matches(String.format("^%s$", ERegex.CARD))) {
    		throw new IllegalArgumentException("Formato para Card no es el adecuado.");
    	}
    
    	try {
			this.value = EValue.parse(card.split("_")[0].strip());
		} catch (Exception e) {
			throw new IllegalArgumentException("Falló el parse de value en Card.");
		}
    	
    	try {
    		this.color = EColor.parse(card.split("_")[1].strip());		
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Folló la función parse de color en Card.");
		}
    	
    }
    
    @Override
    public String toString(){
        String result = null;
        
        result = String.format("\"%s_%s\"", getValue().getName(), getColor().getName());
        
        return result;
    }
    /**
     * Verifica si dos cartas son iguales(tiene el mismo color y símbolo).
     * @param card Carta con la que se comparará.
     * @return true si son iguales,si no, false.
     */
    public boolean equals(Card card) {
    	if (this.value == card.getValue() && this.color == card.getColor()) {
    		return true;
    	}
    	
    	return false;
    }
    
    
    /**
	 * @return the color
	 */
	public EColor getColor() {
		return color;
	}

	/**
	 * @param color the figure to set
	 */
	public void setColor(EColor color) {
		this.color = color;
	}

	/**
	 * @return the value
	 */
	public EValue getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(EValue value) {
		this.value = value;
	}



	//Pruebas con la clase.
    public static void main(String[] args){
    	String a = "CERO_GREEN";
    	
    	Card card1= new Card("CERO_RED");
    	Card card2 = new Card("CERO_GREEN");
    	System.out.println(card1.equals(card2));
    	System.out.println(card2.equals(new Card(a)));
    	System.out.println("==");
    	
    }





}
