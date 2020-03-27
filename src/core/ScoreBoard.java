package core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class ScoreBoard {
	
	private ArrayList<ScoreBoardPlayer> players = new ArrayList<>();
	
	public ScoreBoard() {
		FileManager fm = new FileManager("");
		String json = fm.read("scoreBoard.json");
		json = json.replaceAll(",\n", "&split&");
		json = json.replaceAll("\t", "");
		json = json.replaceAll("\n", "");
		json = json.replaceAll("\\{\"ranking\":\\[", "");
		json = json.replaceAll("\\]\\}", "");
		String[] array = json.split("&split&");
		for (String string : array) {
			players.add(new ScoreBoardPlayer(string));
		}
	}
	
	public ScoreBoard(boolean readed) {
		if (readed) {
			FileManager fm = new FileManager("");
			String json = fm.read("scoreBoard.json");
			json = json.replaceAll(",\n", "&split&");
			json = json.replaceAll("\t", "");
			json = json.replaceAll("\n", "");
			json = json.replaceAll("\\{\"ranking\":\\[", "");
			json = json.replaceAll("\\]\\}", "");
			String[] array = json.split("&split&");
			for (String string : array) {
				players.add(new ScoreBoardPlayer(string));
			}
		}

	}
	
	public ScoreBoard(String json) {
		json = json.replaceAll(",\n", "&split&");
		json = json.replaceAll("\t", "");
		json = json.replaceAll("\n", "");
		json = json.replaceAll("\\{\"ranking\":\\[", "");
		json = json.replaceAll("\\]\\}", "");
		String[] array = json.split("&split&");
		for (String string : array) {
			players.add(new ScoreBoardPlayer(string));
		}
	}
	
	/**
	 * Actualiza la tabla con los nuevos puntos de un jugador. Si no encuentra el jugador añade uno a la tabla.
	 * @param name Nombre del jugador.
	 * @param lastPoints Puntos que el jugador ganó en la ultima partida.
	 */
	public void updatePlayer(String name, int lastPoints) {
		String date = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDateTime.now()); 
		try {
			ScoreBoardPlayer player = this.searchPlayerByName(name);
			player.setDate(date);
			player.setLastResult(lastPoints);
			player.setRank(player.getPoints() + lastPoints);
			player.setRank(this.calculetateRank(player.getPoints()));
		} catch (Exception e) {/**Si no encuentra al jugador.*/
			ScoreBoardPlayer player = new ScoreBoardPlayer(0, name, lastPoints, lastPoints, date);
			player.setRank(this.calculetateRank(player.getPoints()));/**Último en el ranking.*/
			this.players.add(player);
		}
	}
	
	/**
	 * Busca a un jugador por su nombre en la tabla.
	 * @param name Nombre del jugador.
	 * @return El jugador.
	 */
	private ScoreBoardPlayer searchPlayerByName(String name) {
		for (ScoreBoardPlayer player : this.players) {
			if (player.getName().equals(name)) {
				return player;
			}
		}
		throw new IllegalArgumentException("No se encontró el jugador.");
	}
	
	private int calculetateRank(int points) {
		if(points <= this.players.get(this.players.size()-1).getPoints()) {
			return this.players.size();
		}
		
		if(points > this.players.get(0).getPoints()) {
			return 1;
		}
		
		return 0; 
	}
	
	public void saveMemory() {
		FileManager fm = new FileManager("");
		fm.write("scoreBoard.json", this.toString());
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("");
		
		result.append("{\n");
		result.append("\t\"ranking:\n");
		result.append("\t\t[\n");
		
		if (this.players.size()==1) {
			result.append(String.format("\t\t\t%s\n",this.players.get(0).toString()));
		}else {
			for (int i = 0; i < this.players.size()-1; i++) {
				result.append(String.format("\t\t\t%s,\n",this.players.get(i).toString()));
			}
			result.append(String.format("\t\t\t%s\n",this.players.get(this.players.size()-1).toString()));
		}
		result.append("\t\t]");
		result.append("}");
		return result.toString();
	}
	
	public static ScoreBoard parse(String json) {
		return new ScoreBoard(json);
	}
	
	/**
	 * @return the players
	 */
	public ArrayList<ScoreBoardPlayer> getPlayers() {
		return players;
	}

	/**
	 * @param players the players to set
	 */
	public void setPlayers(ArrayList<ScoreBoardPlayer> players) {
		this.players = players;
	}

	public static void main(String[] args) {
		 System.out.println(DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDateTime.now()));  

	}
}
