import java.io.*;

/**
*Esta clase define los metodos de un objeto que permiten manipular archivos de texto
*@author Jorge Cardenas Cardenas
*@version 1.0
*/

public class OperacionesArchivos {

	File file;	

	/**
	*Constructor del objeto, guarda en una variable el archivo que recibe
	*@param file Archivo sobre el que se realizara la lectura/escritura 
	*/
	public OperacionesArchivos(File file){
		this.file= file;
	}

	/**
	*Lee una linea especificada del archivo de texto
	*@param line número de linea del archivo a leer
	*@return String Cadena con los caracteres/simbolos encontrados en la linea de texto
	*@throws IOException si no se pudo leer el archivo
	*/
	public String readFile(int line){
		String cadena ="";
		try{
			FileReader fr = new FileReader(this.file);
			BufferedReader br = new BufferedReader(fr);
			for(int i = 0; i < line-1; i++)
				br.readLine();
			cadena = br.readLine();
			br.close();
		}catch(IOException e){
			System.out.println(e);
		}
		return cadena;
	}
	
	/**
	*Sobre-escribe el archivo de texto en la ultima posicion del puntero
	*@param cadena linea a escribir sobre el archivo
	*@exception IOException si no se pudo escribir sobre el archivo
	*/
	public void writeFile(String cadena){
		try{
			FileWriter fw = new FileWriter(this.file, true);
			BufferedWriter bw = new BufferedWriter(fw);
        	PrintWriter pw = new PrintWriter(bw);
        	pw.write(cadena);
        	pw.close();
        	bw.close();
		}catch(IOException e){
			System.out.println(e);
		}
	}

}
	
