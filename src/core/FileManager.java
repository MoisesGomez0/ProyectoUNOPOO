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
 * 
 * */
public class FileManager {
	
	private String pathToWork = "";
	
	/**
	 * 
	 * @param path Indica la ruta donde se leerán y crarán archivos.
	 */
	public FileManager(String path) {
		this.pathToWork = path;
	}
	
	public FileManager() {}	
	
	public String getPathToWork() {
		return this.pathToWork;
	}
	
	public void setPathToWork(String pathToWork) {
		this.pathToWork = pathToWork;
	}
	
	public String wpath() {
		File f = new File(pathToWork + ".");
		return f.getAbsolutePath();
	}
	
	
	/**
	 * Lee un archivo.
	 * @param fileName Nombre del archivo a leer.
	 * @return El contenido del archivo.
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
			return "fail";
		}
		System.out.println(content.toString());
		return content.toString();
	}
	/**
	 * Ecribe en un archivo.
	 * @param fileName Nombre del archivo en que se escribirán los datos.
	 * @param content Contenido que se le agrega al archivo.
	 */
	public void write(String fileName, String content) {
		this.create(fileName, content);
	}
	
	/**
	 * Ecribe en un archivo.
	 * @param fileName Nombre del archivo en que se escribirán los datos.
	 * @param content Contenido que se le agrega al archivo.
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
	
	/**
	 * Elimina un archivo.
	 * @param fileName Nombre del archivo a eliminar.
	 * @return true si eliminó el archivo con éxito.
	 */
	public boolean deleteFile(String fileName) {
		File file = new File(fileName);
		return file.delete();
	}
}
