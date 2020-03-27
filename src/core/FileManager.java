package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * Se encarga de gestionar el procesamiento de lectura y escritura de archivos
 * 
 * @author moises
 * @version 0.1.0
 * @apiNote CUAL ES LA RUTA!!!!
 * 
 * */
public class FileManager {
	
	private String pathToWork = "";
	
	/**
	 * Constructor donde se puede establece una ruta específica.
	 * @param path ruta.
	 */
	public FileManager(String path) {
		this.pathToWork = path;
	}
	
	/**
	 * Constructor vacio de la clase.
	 * */
	public FileManager() {}	
	
	public String getPathToWork() {
		return this.pathToWork;
	}
	
	/**
	 * Establece la dirección donde se guardaran los archivos una vez el objeto está instanciado.
	 * @param pathToWork ruta.
	 * */
	public void setPathToWork(String pathToWork) {
		this.pathToWork = pathToWork;
	}
	
	/**
	 * @return retorna la ruta donde se están guardando los archivos.
	 */
	public String wpath() {
		File f = new File(pathToWork + ".");
		return f.getAbsolutePath();
	}
	
	
	/**
	 * 
	 * Recibe el nombre de un archivo, lee el contenido y lo retorna como un String.
	 * @param fileName nombre del archivo.
	 * @return contenido del archivo.
	 */
	public String read(String fileName) {
		StringBuilder content = new StringBuilder("");
		try {
			FileInputStream fis = new FileInputStream(pathToWork+fileName);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			try {
				String line;
				while((line = br.readLine()) != null) {
					content.append(line);
					content.append("\n");
				}
				
			}finally {
				br.close();
			}
		}catch(Exception e){
			
		}
		
		return content.toString();
	}
	
	/**
	 * Escribe un archivo en la memoria, si ya existe lo remplaza.
	 * @param fileName Nombre del archivo a crear.
	 * @param content Contenido a escribir dentro del archivo.
	 */
	public void write(String fileName, String content) {
		this.create(fileName, content);
	}
	
	/**
	 * Escribe un archivo en la memoria, si ya existe lo remplaza.
	 * @param fileName Nombre del archivo a crear.
	 * @param content Contenido a escribir dentro del archivo.
	 */
	public void create(String fileName, String content) {
		try {
		FileOutputStream fos = new FileOutputStream(pathToWork+fileName);
			try {
				byte[] b = content.getBytes();
				fos.write(b);
			}finally {
				fos.close();
			}
		}catch(Exception e){
			
		}
	}
	
	/**Pruebas de la clase.*/
	public static void main(String[] args) {
		FileManager fm = new FileManager("src/memory/");
		fm.write("game.json", "No hay tal cosa.");
	}
}