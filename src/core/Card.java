package core;

/**
 *
 * @author Leonardo
 */
public class Card {
    
    private EColor color;
    private ENumber value;
    private boolean hidden; /**true si la carta está boca abajo, false si está boca arriba.*/
    private String image; /**URL de la imagen de la carta.*/
    
    /**
     * Constructor vacío de la clase
     */
    public Card() {}
    /**
     * Constructor de la clase.
     * @param value Número o símbolo que se le asigna a la carta.
     * @param color	Color que se le asigna a la carta.
     */
    public Card(ENumber value, EColor color){
        this.setColor(color);
        this.setValue(value);
        this.setHidden(false);        
        
        /**Se espera que el nombre del archivo de la imagen como (value_color.png)*/
        this.setImage(String.format("src/Images/%s_%s.png",value,color));
            
    }
    public Card(String card) {
    	if (!card.matches(String.format("^%s$", ERegex.CARD))) {
    		throw new IllegalArgumentException("Formato para Card no es el adecuado.");
    	}
    
    	try {
			this.value = ENumber.parse(card.split("_")[0].strip());
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
        
        if (isHidden() == false) {
        	if (this.color == EColor.BLACK) {
        		result = getValue().getName();
        	}
            result = String.format("\"%s_%s\"", getValue().getName(), getColor().getName());           
        }else{
            result = String.format("???");
        }
        
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
	public ENumber getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(ENumber value) {
		this.value = value;
	}

	/**
	 * @return the hidden
	 */
	public boolean isHidden() {
		return hidden;
	}

	/**
	 * @param hidden the hidden to set
	 */
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
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
