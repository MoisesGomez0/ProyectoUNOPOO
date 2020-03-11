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
        
        /**Se espera que el nombre del archivo de la imagen como (value_figuer.png)*/
        this.setImage(String.format("src/Images/%s_%s.png",value,color));
            
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
        Card card = new Card(ENumber.CERO,EColor.BLUE);
        System.out.printf("\t%s",card.toString());
        System.out.printf("\n\t%s",card.toString());
    }





}
