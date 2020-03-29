package core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;


public class ScoreBoard {
	
	private ArrayList<ScoreBoardPlayer> players = new ArrayList<>();
	
	public ScoreBoard() {
		try {
			String json = this.load("scoreBoard.json");
			json = json.replaceAll(",\n", "&split&");
			
			json = json.replaceAll("\t", "");
			json = json.replaceAll("\n", "");
			json = json.replaceAll("\\{\"ranking\":\\[", "");
			json = json.replaceAll("\\]\\}", "");
			
			String[] array = json.split("&split&");
			
			for (String string : array) {
				players.add(new ScoreBoardPlayer(string));
			}
		} catch (Exception e) {
			/**No instancia la scoreBoard.*/
		}
	}
	
	public ScoreBoard(boolean readed) {
		if (readed) {
			String json = this.load("scoreBoard.json");
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
			player.setPoints(player.getPoints() + lastPoints);
		} catch (IllegalArgumentException e) {/**Si no encuentra al jugador.*/
			ScoreBoardPlayer player = new ScoreBoardPlayer(0, name, lastPoints, lastPoints, date);
			player.setRank(this.calculetateRank(player.getPoints()));/**Último en el ranking.*/
			this.players.add(player);
			System.out.println(e.getMessage());
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
	
	/**
	 * Calcula el rango que se obtendrá según los puntos.
	 * @param points Puntos a evaluar.
	 * @return Rango en que debe estar.
	 */
	private int calculetateRank(int points) {
		if (this.players.isEmpty()) {
			return 0;
		}
		
		if(points <= this.players.get(this.players.size()-1).getPoints()) {
			return this.players.size()+1;
		}
		
		if(points > this.players.get(0).getPoints()) {
			return 1;
		}
		
		for (int i = 0; i < this.players.size(); i++) {
			ScoreBoardPlayer player = this.players.get(i);
			
			
			if(points < player.getPoints() &&
			   points > this.players.get(i+1).getPoints()||
			   points == player.getPoints()) {
				return player.getRank()+1;
			}
		}
		
		
		return 0; 
	}
	
	private String load(String file) {
		FileManager fm = new FileManager();
		System.out.println(fm.wpath());
		return fm.read(file);
	}
	
	/**
	 * Ordena a los jugadores por rango.
	 * @return Una tabla ordenada.
	 */
	public ScoreBoard sortByPoints() {
		ArrayList<Integer> points = new ArrayList<>();
		ArrayList<ScoreBoardPlayer> newPlayers = new ArrayList<>();
		ScoreBoard result = new ScoreBoard(false);
		
		for (ScoreBoardPlayer player: this.players) {
			points.add(player.getPoints());
		}
		Collections.sort(points);
		
		int rank = this.players.size();
		for (int i:points) {
			for(ScoreBoardPlayer player : this.players) {
				if(i==player.getPoints()) {
					player.setRank(rank);
					newPlayers.add(player);
					rank--;
					break;
				}
			}
		}
		
		this.players = newPlayers;
		result.setPlayers(newPlayers);
		
		return result;
		
	}
	
	public void saveMemory() {
		this.sortByPoints();
		FileManager fm = new FileManager("");
		fm.write("scoreBoard.json", this.toString());
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("");
		
		result.append("{\n");
		result.append("\t\"ranking\":\n");
		result.append("\t\t[\n");
		
		if (this.players.isEmpty()) {
			return "";
		}
		
		if (this.players.size()==1) {
			result.append(String.format("\t\t\t%s\n",this.players.get(0).toString()));
		}else {
			for (int i = 0; i < this.players.size()-1; i++) {
				result.append(String.format("\t\t\t%s,\n",this.players.get(i).toString()));
			}
			result.append(String.format("\t\t\t%s\n",this.players.get(this.players.size()-1).toString()));
		}
		result.append("\t\t]\n");
		result.append("}");
		return result.toString();
	}
	
	public String toHTML() {
		this.sortByPoints();
		StringBuilder result = new StringBuilder("<table><thead><tr><td>Rank</td><td>Nombre</td><td>Puntos</td><td>En último juego</td>");
		result.append("<td>Fecha</td><tr></thead><tbody>");
		if (!this.players.isEmpty()) {
			for (int i = this.players.size()-1;i >= 0;i--) {
				ScoreBoardPlayer player = this.players.get(i);
				result.append("<tr>");
				result.append(String.format("<td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td>", 
						player.getRank(),
						player.getName(),
						player.getPoints(),
						(player.getLastResult()==0) ? "-" : String.format("+%s",player.getLastResult()),
								player.getDate()
						));
				result.append("</tr>");
			}	
		}
		result.append("</tbody></table>");
		
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

	//Pruebas de la clase.
	public static void main(String[] args) {
		ScoreBoard sb = new ScoreBoard();
		sb.saveMemory();
	}
}
