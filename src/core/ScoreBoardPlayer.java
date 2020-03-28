package core;

public class ScoreBoardPlayer {
	
	private int rank;
	private String name;
	private int points;
	private int lastResult;
	private String date;
	
	/**Constructor vacío.*/
	public ScoreBoardPlayer() {}
	
	/**
	 * Constructor sobreCargado de la clase.
	 * @param rank
	 * @param name
	 * @param points
	 * @param lastResult
	 * @param date
	 */
	public ScoreBoardPlayer(int rank,
							String name,
							int points,
							int lastResult,
							String date) {
		this.rank = rank;
		this.name = name;
		this.points = points;
		this.lastResult = lastResult;
		this.date = date;
	}
	
	/**
	 * Instancia el objeto con un String en formato JSON.
	 * @param json String JSON que contiene la información.
	 */
	public ScoreBoardPlayer(String json) {
		if (!json.matches(ERegex.SCPLAYER.toString())) {
			throw new IllegalArgumentException("Formato no permitido.");
		}

		json = json.replaceAll("\\}", "");

		json = json.replaceAll("\\{", "");

		json = json.replaceAll("\"\\w+\":", "");

		json = json.replaceAll("\\{", "");
		json = json.replaceAll("\"", "");
		
		String[] array = json.split(",");
		
		this.rank = Integer.parseInt(array[0]);
		this.name = array[1];
		this.points = Integer.parseInt(array[2]);
		this.lastResult = Integer.parseInt(array[3]);
		this.date = array[4];
		
		
	}
	
	/**
	 * Compara el rango de dos jugadores.
	 * @param player Jugador con el que comparar el rango.
	 * @return -1 si es menor, 0 si es igual, 1 si es mayor.
	 */
	public int compareRank(ScoreBoardPlayer player) {
		if (this.getRank() < player.getRank()) {
			return 1;
		}else if(this.getRank() > player.getRank()) {
			return -1;
		}

		return 0;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(String.format("{\"rank\":%s,\"name\":\"%s\",\"points\":%s,\"lastResult\":%s,\"date\":\"%s\"}",
					this.rank,
					this.name,
					this.points,
					this.lastResult,
					this.date));
		
		return result.toString();
	}
	
	/**
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(int rank) {
		this.rank = rank;
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
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * @return the lastResult
	 */
	public int getLastResult() {
		return lastResult;
	}

	/**
	 * @param lastResult the lastResult to set
	 */
	public void setLastResult(int lastResult) {
		this.lastResult = lastResult;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	//Pruebas de la clase.
	public static void main(String[] args) {
	}
}
