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
		String[] array = json.split(",");
		
		this.rank = Integer.parseInt(array[0].split(":")[1]);
		this.name = array[1].split(":")[1];
		this.points = Integer.parseInt(array[2].split(":")[1]);
		this.lastResult = Integer.parseInt(array[3].split(":")[1]);
		this.date = array[4].split(":")[1];
		
		
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(String.format("{\"rank\":%s,\"name\":\"%s\",\"points\":%s,\"lastResult\":%s,\"date\":%s}",
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
		FileManager fm = new FileManager("");
		String content = fm.read("scoreBoard.json");
		System.out.println(content);
		System.out.println("=====");
		content = content.replaceAll(",\n", "&split&");
		content = content.replaceAll("\t", "");
		content = content.replaceAll("\n", "");
		System.out.println(content);
		System.out.println("=====");
		content = content.replaceAll("\\{\"ranking\":\\[", "");
		content = content.replaceAll("\\]\\}", "");
		String[] array = content.split("&split&");
		content = array[0];
		System.out.println("----");
		System.out.println(content);
		content = content.replaceAll("\\}", "");
		content = content.replaceAll("\\{", "");
		content = content.replaceAll("\"\\w+\":", "");
		System.out.println("----");
		System.out.println(content);
		
	}
}
