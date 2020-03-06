package core;


/**
 *Contiene los colores que puede tener una carta.
 * @author Leonardo
 */
public enum EColor {
    
    BLUE(0,"BLUE",false),
    RED(1,"RED",false),
    GREEN(2,"GREEN",false),
    YELLOW(3,"YELLOW",false),
	BLACK(4,"BLACK",true);
    
    private final String name;
    private final boolean special; /**Las cartas especiales solo pueden ser de color negro.*/
    private final int id; /**Identificador numérico para las figuras.*/
    
    EColor(int id ,String figura, boolean special){
        this.name = figura;
        this.id = id;
        this.special= special;
    }

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the special
	 */
	public boolean isSpecial() {
		return special;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
    
    
}
