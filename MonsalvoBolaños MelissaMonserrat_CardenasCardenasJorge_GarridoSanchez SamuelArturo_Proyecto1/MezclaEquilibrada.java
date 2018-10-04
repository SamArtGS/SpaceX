import java.io.*;//Clases: File, 
import java.util.*;//Clases: ArrayList, 
import javax.swing.JOptionPane;

/**
*Esta clase define un onjeto que ordena un archivo de texto mediante el algortimo de ordenamiento externo de Mezcla Equilibrada
*Se requiere de una archivo de texto con números enteros separados por comas y sin espacios, sobre una sola línea del archivo.
*Implementa las operaciones de ordenamiento tanto ascendente como descendente, simpre y cuando se le especifique en los argumentos del constructor el tipo de ordenamiento
*@author Jorge Cardenas Cardenas
*@version 1.0
*/

public class MezclaEquilibrada {

	File fileSort;
	String aux1 = "", aux2 = "";//cadenas sobre las que se guardaran las particiones
	String cadena= "";//cadena que recibe una linea especificada del archivo medinate un objeto tipo OperacionesArchivos
	int m = 1;//número de linea que lee el archivo a ordenar, de aqui se deriva el número de linea a leer de aux1 y aux2

/**
*Construye un objeto cona partir de el archivo y tipo de ordenamiento.
*Sí los archivos auxilares requeridos para el ordenamiento (aux1, aux2) existen, estos se eliminan
*Dependiendo del tipo de busqueda se ejecutan los métodos de ordenamiento
*@param file Objeto tipo File del archivo a ordenar
*@param sort verdadero para ordenamiento ascendente, falso para ordenamiento descendente
*/

	public MezclaEquilibrada(File file, boolean sort){
		this.fileSort = file;
		if((new File("aux1.txt")).exists())
			(new File("aux1.txt")).delete();//si aux1.txt existe, este se elimina
		if((new File("aux2.txt")).exists())
			(new File("aux2.txt")).delete();//si aux2.txt existe, este se elimina
		if(sort)//si sort es verdadero, el ordenamieto es ascendente
			mezclarAscendente();
		else //de lo contrario el ordenamiento de desendente 
			mezclarDescendente();

	}
/**
*Ordena de forma ascendente una archivo de texto con enteros mediante Mezcla Equilibrada
*Este método crea tres objeto del tipo OperacionesArchivos, tanto para el archivo original tanto como para sus auxiliares.
*/

	public void mezclarAscendente(){
		while(true){//el ciclo se repite un numero indeterminado de veces, hasta que un caso base se cumpla(Archivo ordenado)
			OperacionesArchivos opfile, fileAux1, fileAux2;//declaracion de Objetos OperacionesArchivos
			opfile = new OperacionesArchivos(fileSort);//inicialización del objeto con el archivo a ordenar
			fileAux1 = new OperacionesArchivos(new File("aux1.txt")); //creacion de aux1.txt, contendra las particiones generadas
			fileAux2 = new OperacionesArchivos(new File("aux2.txt")); //creacion de aux2.txt, contendra las particiones generadas
			cadena = opfile.readFile(2 * m - 1); //se leen las lines que corresponden a la sucesión: 1, 3, 5, 7, ..., (lineas que contienen los números)
			if(cadena == null){
				JOptionPane.showMessageDialog(null, "Archivo vac\u00edo");
				break;//caso base de fracaso
			}//valida que el archivo no este vacio, sí lo esta la ejecución termina
			String [] numeros = cadena.split(",");//split separa los elementos de una cadena guardandolos en un arreglo cada que se encuentre una coma
			String o2 = numeros[0], o1;//o1 , o2: varibles auxiliares para la comparación elementos del array
			int i = 0;// contador del numero de elementos totales
			while(i < numeros.length) {
				do{
					aux1 = aux1 + o2;
					o1 = o2;
					i++;
				} while(i < numeros.length && (Integer.parseInt(o2 = numeros[i]) >= Integer.parseInt(o1)) && add1Comas());
					aux1 = aux1 + ("'");
				if(i >= numeros.length)
					continue;
				do{
					aux2 = aux2 + (o2);
					o1 = o2; 
					i++;
				} while(i < numeros.length && (Integer.parseInt(o2 = numeros[i]) >= Integer.parseInt(o1)) && add2Comas());
					aux2 = aux2 + ("'");
			}//Partiona el arreglo diviediendo los datos ya ordenados, se almacenan en aux1 las primeras particiones, y aux2, las segundas particiones
			if(aux2.length() == 0){//si aux2 esta vacía => no hay particiones, por lo que la lista ya se encuntra, o ya se ha ordenado  
				aux1 = aux1.subSequence(0, aux1.length()-1).toString();// se elimina la notacion de particion (')
				opfile.writeFile(System.getProperty("line.separator") + "*** Lista Ordenada ***" + System.getProperty("line.separator") + aux1);//se escribe sobre el archivo original, el arreglo ordenado
				JOptionPane.showMessageDialog(null, "El archivo ha sido ordenado.");//Avisa al usuario que se ha rodenado el archivo
				break;// Rompe el ciclo while y termina la ejecución, caso base de exito
			}
			if(m==1){
				fileAux1.writeFile("*** Partición " + m + " ***" + System.getProperty("line.separator") + aux1);
				fileAux2.writeFile("*** Partición " + m + " ***" + System.getProperty("line.separator") + aux2);
			}// se es la primera línea, no se necesita un salto de línea al inicio
			else{
				fileAux1.writeFile(System.getProperty("line.separator") + "*** Partición " + m + " ***" + System.getProperty("line.separator") + aux1);
				fileAux2.writeFile(System.getProperty("line.separator") + "*** Partición " + m + " ***" + System.getProperty("line.separator") + aux2);
			}// si no es la primera línea, se requiere de un salto de linea, para no escribir sobre la linea los nuemros
			System.out.println("*** Partición 1 ***\n" + aux1);
			System.out.println("*** Partición 2 ***\n" + aux1);
			aux1 = "";//Se reeestablece aux1
			aux2 = "";//Se restablece aux2
			String cadena1 = fileAux1.readFile(m * 2);// contendra las lineas de numeros de aux1 : 2, 4, 8, ...
			String []subcadena1 = cadena1.split("'");// cada posicion del nuevo array contendra una particion
			ArrayList<ArrayList<String>> list1 = new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<String>> list2 = new ArrayList<ArrayList<String>>();
			for (String elem : subcadena1) { 
				list1.add(new ArrayList(Arrays.asList(elem.split(","))));
			}// cada elemento del array (cada partición), se añade a un ArrayList de ArrayList de Strings, haciendo de nuevo separacion por comas, cada sub-arary es una particion, sus elementos, los numeros que la componen
			String cadena2 = fileAux2.readFile(m * 2);
			String []subcadena2 = cadena2.split("'");
			cadena = "";
			for (String elem : subcadena2) {
				list2.add(new ArrayList(Arrays.asList(elem.split(","))));
			}
			int p = 0;
			for (int j = 0; j < list2.size(); j++){
				mixNumbersA(list1.get(j), list2.get(j)); // para cada sub ArrayList, de list2 y list2, se le aplica el metodo de ordenamiento
			}
			if(list1.size() > list2.size()){
				for (String  elem: list1.get(list1.size() - 1)) {
					cadena = cadena + elem + ",";// cada elemento se añade a la variable cadena, separado por comas
				}
			}// solo se puede dar el caso donde el numero de particiones en ambos archivos on iguales, o que aux1, tenga una perticion de más(se válida tal caso)
			cadena = cadena.subSequence(0, cadena.length()-1).toString();// se elimina la ultima coma de la cadena
			opfile.writeFile(System.getProperty("line.separator") + "*** Mezcla " + m + " ***" + System.getProperty("line.separator") + cadena);
			System.out.println(System.getProperty("line.separator") + "*** Mezcla " + m + " ***" + System.getProperty("line.separator") + cadena);
			cadena = "";
			m++; // el número de linea que se lee se incrementa en uno		
		}
	}

/**
*Ordena de forma descendente una archivo de texto con enteros mediante Mezcla Equilibrada
*Este método crea tres objeto del tipo OperacionesArchivos, tanto para el archivo original tanto como para sus auxiliares.
*/

	public void mezclarDescendente(){
		while(true){
			OperacionesArchivos opfile, fileAux1, fileAux2;
			opfile = new OperacionesArchivos(fileSort);
			fileAux1 = new OperacionesArchivos(new File("aux1.txt")); 
			fileAux2 = new OperacionesArchivos(new File("aux2.txt")); 
			cadena = opfile.readFile(2 * m - 1);
			if(cadena == null){
				JOptionPane.showMessageDialog(null, "Archivo vac\u00edo");
				break;
			}
			String [] numeros = cadena.split(",");
			int partition1 = 0, partition2 = 0;
			String o2 = numeros[0], o1;
			int i = 0;
			while(i < numeros.length) {
				do{
					aux1 = aux1 + o2;
					o1 = o2;
					i++;
				} while(i < numeros.length && (Integer.parseInt(o2 = numeros[i]) <= Integer.parseInt(o1)) && add1Comas());
					aux1 = aux1 + ("'");
				if(i >= numeros.length)
					continue;
				do{
					aux2 = aux2 + (o2);
					o1 = o2; 
					i++;
				} while(i < numeros.length && (Integer.parseInt(o2 = numeros[i]) <= Integer.parseInt(o1)) && add2Comas());
					aux2 = aux2 + ("'");
			}
			if(aux2.length() == 0){
				aux1 = aux1.subSequence(0, aux1.length()-1).toString();
				opfile.writeFile(System.getProperty("line.separator") + "*** Lista Ordenada ***" + System.getProperty("line.separator") + aux1);
				JOptionPane.showMessageDialog(null, "El archivo ha sido ordenado");
				break;
			}
			if(m==1){
				fileAux1.writeFile("*** Particion " + m + " ***" + System.getProperty("line.separator") + aux1);
				fileAux2.writeFile("*** Particion " + m + " ***" + System.getProperty("line.separator") + aux2);
			}
			else{
				fileAux1.writeFile(System.getProperty("line.separator") + "*** Particion " + m + " ***" + System.getProperty("line.separator") + aux1);
				fileAux2.writeFile(System.getProperty("line.separator") + "*** Particion " + m + " ***" + System.getProperty("line.separator") + aux2);
			}
			aux1 = "";
			aux2 = "";
			String cadena1 = fileAux1.readFile(m * 2);
			String []subcadena1 = cadena1.split("'");
			ArrayList<ArrayList<String>> list1 = new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<String>> list2 = new ArrayList<ArrayList<String>>();
			for (String elem : subcadena1) {
				list1.add(new ArrayList(Arrays.asList(elem.split(","))));
			}
			String cadena2 = fileAux2.readFile(m * 2);
			String []subcadena2 = cadena2.split("'");
			cadena = "";
			for (String elem : subcadena2) {
				list2.add(new ArrayList(Arrays.asList(elem.split(","))));
			}
			int p = 0;
			for (int j = 0; j < list2.size(); j++){
				mixNumbersD(list1.get(j), list2.get(j));
			}
			if(list1.size() > list2.size()){
				for (String  elem: list1.get(list1.size() - 1)) {
					cadena = cadena + elem + ",";
				}
			}
			cadena = cadena.subSequence(0, cadena.length()-1).toString();
			opfile.writeFile(System.getProperty("line.separator") + "*** Mezcla " + m + " ***" + System.getProperty("line.separator") + cadena);
			cadena = "";
			m++;			
		}
	}

/**
*Recibe dos ArrayList de String, de los cuales compara sus valores numericos, ordenandolos de menor a mayor valor, guardando la lista ordenada, sobre el ArrayList inicial con mayor número de elementos
*/

	public void mixNumbersA(ArrayList<String> arr1, ArrayList<String> arr2){//combinación de insertionSort, y mergeSort, ordena la particion de aux1 con la de aux2
		int l1 = arr1.size();//longitud inicial de arr1
		int l2 = arr2.size();//longitud inicial de arr2
		int j = 0;
		if(l1 >= l2){// si arr1 contiene más elementos que la arr2, es mas sencillo hacer insercion de la lista con menos elementos
			for (int i = 0; i < arr1.size(); i++) {
				if(j < l2 && Integer.parseInt(arr2.get(j)) <= Integer.parseInt(arr1.get(i))){
					arr1.add(i,arr2.get(j));//se añade a la lista arr2 en j y se desplaza en uno todos los indices
					j++;
				}//si j es menor que la longitud de arr2, y el elemento de arr2 en j es menor que el elemento en  arr1 em i, arr2 en j debe ir primero
			}// para cada elemento de array1, dado que es dinamico no se ocupa l1
			for (int i = j; i < l2 ; i++) {
				arr1.add(arr2.get(i));				
			}// se cumple si aún quedaron elementos pendientes por añadir a la lista de arr2, dado que son mayores que los ya encontrados en arr1
			for (String  elem: arr1) {
				cadena = cadena + elem + ",";
			}// se guarda cada elemento del ArrayList en una cadena separados estos por comas
		}
		else{
			for (int i = 0; i < arr2.size(); i++) {
				if(j < l1 && Integer.parseInt(arr1.get(j)) <= Integer.parseInt(arr2.get(i))){
					arr2.add(i,arr1.get(j));
					j++;
				}				
			}
			for (int i = j; i < l1 ; i++) {
				arr2.add(arr1.get(i));				
			}
			for (String  elem: arr2) {
				cadena = cadena + elem + ",";
			}
		}// Para este caso es mas sencillo trabajar con arr1, dado que contiene mayor numero de elementos lo que implica menos operaciones de insercion
	}

/**
*Recibe dos ArrayList de String, de los cuales compara sus valores numericos, ordenandolos de mayor a menor valor, guardando la lista ordenada, sobre el ArrayList inicial con mayor número de elementos
*/
	public void mixNumbersD(ArrayList<String> arr1, ArrayList<String> arr2){
		int l1 = arr1.size();
		int l2 = arr2.size();
		int j = 0;
		if(l1 >= l2){
			for (int i = 0; i < arr1.size(); i++) {
				if(j < l2 && Integer.parseInt(arr2.get(j)) >= Integer.parseInt(arr1.get(i))){
					arr1.add(i,arr2.get(j));
					j++;
				}				
			}
			for (int i = j; i < l2 ; i++) {
				arr1.add(arr2.get(i));				
			}
			for (String  elem: arr1) {
				cadena = cadena + elem + ",";
			}
		}
		else{
			for (int i = 0; i < arr2.size(); i++) {
				if(j < l1 && Integer.parseInt(arr1.get(j)) >= Integer.parseInt(arr2.get(i))){
					arr2.add(i,arr1.get(j));
					j++;
				}				
			}
			for (int i = j; i < l1 ; i++) {
				arr2.add(arr1.get(i));				
			}
			for (String  elem: arr2) {
				cadena = cadena + elem + ",";
			}
		}
	}

/**
*Añade una separador (",") a la variable String aux1
*@return boolean necesario para validar que se agrege un separador
*/

	public boolean add1Comas(){// se trabaja sobre aux1, particiones primarias
		aux1 = aux1 + ",";
		return true;
	} // metodo que añade una coma en la creacion de particiones, devulve true, dado que se invoca en un while, siempre y cuando se valide lo que le antecede en el ciclo

/**
*Añade una separador (",") a la variable String aux2
*@return boolean necesario para validar que se agrege un separador
*/
	public boolean add2Comas(){// se trabaja sobre el String aux2, particiones secundarias
		aux2 = aux2 + ","; 
		return true;
	}// metodo que añade una coma en la creacion de particiones, devulve true, dado que se invoca en un while, siempre y cuando se valide lo que le antecede en el ciclo
	
}