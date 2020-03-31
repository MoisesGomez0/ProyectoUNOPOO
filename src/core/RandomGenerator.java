package core;

import java.security.SecureRandom;
/**
 * Clase abstracta generadora de valores aleatorios.
 * @author leonardo
 *
 */
public abstract class RandomGenerator {

	/**
	 * Genera un número entero aleatorio en un rango especifico:
	 * @param min Valor mínimo del número a obtener.
	 * @param max Valor máximo del número a obtener.
	 * @return Un número entero.
	 */
    public static int randomInt(int min, int max){
        int result;
        
        int diferentialRange= (max-min)+1; 
        SecureRandom r = new SecureRandom();
        result = (min) + r.nextInt(diferentialRange);
        
        return result;
    }
    
    /**
     * 
     * @return Una carta UNO aleatoria.
     */
    public static Card UNOCard() {
    	
    	EColor color = cardColor();
    	EValue number = CardNumbers();
    	
    	if (color.isSpecial()) {
    		number = blackCardNumbers();
    	}
    	
    	return new Card(number,color);
    }
    
    /**
     * 
     * @return Una Carta que no es especial UNO aleatoria.
     */
    public static Card noSpecialUNOCard() {
    	return new Card(noBlackCardNumbers(),noBlackCardColor());
    }
    /**
     * 
     * @return Una Carta que no es de acción UNO aleatoria.
     */
    public static Card noActionUNOCard() {
    	return new Card(noActionCardNumbers(),noBlackCardColor());
    }
    /**
     * 
     * @return Un color aleatorio para una carta de UNO de tipo EColor.
     */
    public static EColor cardColor(){
        EColor[] colors = {
        		EColor.BLUE,
        		EColor.RED, 
        		EColor.GREEN,
        		EColor.YELLOW,
        		EColor.BLACK
        };
        
        return colors[randomInt(0, 4)];    
    }
    
    /**
     * 
     * @return Un color aleatorio exceptuando el negro para para una carata UNO de tipo EColor.
     */
    public static EColor noBlackCardColor() {
        EColor[] colors = {
        		EColor.BLUE,
        		EColor.RED, 
        		EColor.GREEN,
        		EColor.YELLOW,
        };
        
        return colors[randomInt(0, 3)];    
    }    	
    
    /**
     * 
     * @return Un número o simbolo aleatorio para una carta de UNO de tipo EValue.
     */
    public static EValue CardNumbers(){
        EValue[] value = {
        		EValue.CERO,
        		EValue.ONE,
        		EValue.TWO,
        		EValue.THREE,
        		EValue.FOUR,
        		EValue.FIVE,
        		EValue.SIX,
        		EValue.SEVEN,
        		EValue.EIGHT,
        		EValue.NINE,
        		EValue.DTWO,
        		EValue.REVERSE,
        		EValue.SKIP,
        		EValue.WILD,
        		EValue.DFOUR
        };
        
        return value[randomInt(0, 14)];
    }
    
    /**
     * 
     * @return Un número o símbolo aleatorio para las cartas que NO son especiales (cartas que no sean de color negro).
     */
	public static EValue noBlackCardNumbers() {
        EValue[] value = {
        		EValue.CERO,
        		EValue.ONE,
        		EValue.TWO,
        		EValue.THREE,
        		EValue.FOUR,
        		EValue.FIVE,
        		EValue.SIX,
        		EValue.SEVEN,
        		EValue.EIGHT,
        		EValue.NINE,
        		EValue.DTWO,
        		EValue.REVERSE,
        		EValue.SKIP
        };
        
        return value[randomInt(0, 12)];    	
    }
    
    public static EValue noActionCardNumbers() {
        EValue[] value = {
        		EValue.CERO,
        		EValue.ONE,
        		EValue.TWO,
        		EValue.THREE,
        		EValue.FOUR,
        		EValue.FIVE,
        		EValue.SIX,
        		EValue.SEVEN,
        		EValue.EIGHT,
        		EValue.NINE
        };
        
        return value[randomInt(0, 9)];       	
    }
    
    /**
     * 
     * @return Un símbolo aleatorio para las cartas especiales (de color negro);
     */
    public static EValue blackCardNumbers() {
    	EValue[] value = {
    			EValue.WILD,
    			EValue.DFOUR
    	};
    	
    	return value[randomInt(0,1)];
    }
    


}
