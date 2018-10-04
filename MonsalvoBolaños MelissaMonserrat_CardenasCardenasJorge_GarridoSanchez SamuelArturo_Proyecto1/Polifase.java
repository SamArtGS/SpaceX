import java.io.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.nio.*;
//SE añaden todas las clases que nos puedan servir durante la ejecución
///PARA LA FUNCION DESCENDENTE CAMBIAR LA CLASE QUICK SORT A MENOR

public class Polifase{
	List<List<Integer>> listaDeLista = new ArrayList<>(); //Se inicializa una lista de listas que será la encargada de guardar los bloques de tamaño fijo menos el último 
	//(En el caso que no sea divisible)
	int m;
	String direccion;//Inicialiación de la dirección del archivi
	String tipo;
	public ArrayList<Integer> agregarAList(String direccion){ //método encargado de leer el archivo y colocar todos sus elementos dentro de un arraylist
		ArrayList<Integer> lista = new ArrayList<Integer>();
		File archivoP = new File(direccion); //para ser insertado por medio de 
		try (Scanner entrada = new Scanner(archivoP)) {//
          	  while (entrada.hasNextInt()) { //mientras queden enteros por leer
                int numeroDoc = entrada.nextInt();//Método para asignar a la entrada a un número auxiliar
                lista.add(numeroDoc); //agrega el elemento a la lista
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());//captura de excepciones para que no muera el programa
        } catch (Exception e) {
            System.out.println(e.toString());
     }
     
     return lista;
 	}
 	public void sort(int m,String direccion,String tipo){
 		/*Este método es el principal y es el que se manda a llamar cuando se quiere implementar en la interfaz gráfica con los
 		correspondientes parámetros para su función*/
 		List<Integer> NumTxt = new ArrayList<Integer>();
 		ArrayList<ArrayList<Integer>> NumTxt2 = new ArrayList<ArrayList<Integer>>();
 		//Inicializamos las listas y les asignamos sus respectivos bloques. En este caso es una lista de tamaño del archivo
 		NumTxt = agregarAList(direccion);
 		int sobrante = NumTxt.size()%m;
		int cantidades = NumTxt.size()/m;
		//necesitamos estos elementos para poder crear una lista con el último bloque ya que puede ser de tamaño distinto de m
		imprimirListaInt(NumTxt);
		//Método que se encarga de crear las sublistas y devuelve una lista de listas
		NumTxt2 = crearSub(NumTxt,sobrante,cantidades,m,tipo);

		//Una vez creada las sublistas con el método MoveToTXT nos encargamos de acomodar las sublistas (las pares con f1 y las impares con f2)
		//Para que puedan ser asignadas al archivo F1 o F2 que les corresponda dependiendo si ocupan una posición par o impar en la lista de listas
		moveTotxt(NumTxt2,m,tipo);
		System.out.println();
		System.out.println("- - - - - - - - - - - - - - - -");


		//Una vez que ya hemos pasado todo a los archivos F1 F2 queda trae de nuevo los elementos de cada uno
		List<Integer> NumTxtF1N = new ArrayList<Integer>();
 		ArrayList<ArrayList<Integer>> NumTxF1 = new ArrayList<ArrayList<Integer>>();
 		String k1 = "F1";
 		NumTxtF1N = agregarAList(k1+".txt");
 		//nuevamente se crean las sublistas con los elementos que contenga
 		NumTxF1 = crearSub2(NumTxtF1N,NumTxtF1N.size()%m,NumTxtF1N.size()/m,m,tipo);
 		System.out.println("- - - - - - - - - - - - - - - -");
 		List<Integer> NumTxtF2N = new ArrayList<Integer>();
 		ArrayList<ArrayList<Integer>> NumTxtF2 = new ArrayList<ArrayList<Integer>>();
 		String k2 = "F2";
 		//se utiliza las variables de tipo k para ser genérico el método agregarAList
 		NumTxtF2N = agregarAList(k2+".txt");
 		//Las sublistas a generar esta vez corresponden a un método distinto y es porque
 		// las listas impares en cada uno de los archivos generados en F1 y F2 en sus respectivas
 		//Listas de listas se deben ir a otra lista(Separar pares de impares de sus listas de listas)
 		//y juntarlos aplicando el algoritmo de ordenamineto empleado
 		NumTxtF2 = crearSub2(NumTxtF2N,NumTxtF2N.size()%m,NumTxtF2N.size()/m,m,tipo);
		System.out.println("- - - - - - - - - - - - - - - -");
		List<Integer> NumTxtF0 = new ArrayList<Integer>();
		//

		moveTotxtF0F3(NumTxF1,NumTxtF2,m,NumTxtF1N,NumTxtF2N,tipo);
		System.out.println("- - - - - - - - - - - - - - - -");
		//Nuevamente mandamos a llamar a los archivos generados F0 y F3 con la inteción de realizar el mismo
		//procedimiento	
		List<Integer> NumF0 = new ArrayList<Integer>();
 		ArrayList<ArrayList<Integer>> NumTxF0 = new ArrayList<ArrayList<Integer>>();
 		String k3 = "F0";
 		NumF0 = agregarAList(k3+".txt");
 		NumTxF0 = crearSub2(NumF0,NumF0.size()%(m),NumF0.size()/(2*m),2*m,tipo);
 		//Se manda a llamar a los elementos de los archivos y se les separa para convertirlos en listas de listas
 		List<Integer> NumF3 = new ArrayList<Integer>();
 		ArrayList<ArrayList<Integer>> NumTxF3 = new ArrayList<ArrayList<Integer>>();
 		String k4 = "F3";
 		NumF3 = agregarAList(k4+".txt");
 		//Nótese que ésta ocasión el tamaño de las sublistas serán de 2*m, el doble para poder integar las mini listas ordenadas a una mayor
 		//y así estar a un penúltimo paso para terminar
 		NumTxF3 = crearSub2(NumF3,NumF3.size()%(m),NumF3.size()/(2*m),2*m,tipo);
 		moveTotxtF1F2(NumTxF0,NumTxF3,2*m,NumF0,NumF3,tipo);

 		//FINAL
 		//En el paso final solo se tiene que juntar la gran lista ordenada con la pequeña lista de tamaño distinto de m, (el pedacito que quedó en el principio)
		ArrayList<Integer> Final = new ArrayList<Integer>();
		ArrayList<Integer> Final2 = new ArrayList<Integer>();
 		String kf = "F1-1";
 		Final = agregarAList(kf+".txt");
 		imprimirListaIntFiles(Final,kf);
 		String kf2 = "F2-2";
 		Final2 = agregarAList(kf2+".txt");
 		imprimirListaIntFiles(Final2,kf2);
 		//Se manda a llamar a todos los elementos de F1 que es la gran lista de elementos ordenada y la pequeña lista de tamaño disinto almacenada en F2
 		for(Integer temp : Final2){
 			Final.add(temp);
 		}
 		Final=orden(Final,tipo);
 		imprimirListaInt(Final);
 		//Cuando juntamos ambas sublistas y las ponemos en una sola le aplicamos por última vez el
 		//algoritmo de ordenamiento para obtener la lista ordenada
 		moveTotxtFinal(Final); //se almacena en el archivo final
 		JOptionPane.showMessageDialog(null, "El archivo ha sido ordenado. Su archivo se llama Final.txt");//Avisa al usuario que se ha rodenado el archivo
 		//se imprime un mensaje que el archivo ha sido ordenado

 	}
 	public void moveTotxtFinal(ArrayList<Integer> lis){
 		File arch = new File("Final.txt");
 		try{
		FileWriter archi = new FileWriter(arch);
		BufferedWriter archiBuffer = new BufferedWriter(archi);
		PrintWriter escritura1 = new PrintWriter(archi);
/*Si usamos sólo FileInputStream, FileOuputStream, 
FileReader o FileWriter, cada vez que hagamos 
una lectura o escritura, 
se hará fisicamente en el disco duro. Si escribimos 
o leemos pocos 
caracteres cada vez, el proceso se hace costoso y
lento, con muchos accesos a disco duro.*/
		for(Integer tm: lis){
        	escritura1.print(tm+" ");
        } //recorremos la lista y escribimos sobre el archivo
		archi.close();
		archiBuffer.close(); //es necesario cerrar tanto el bufer como el writer para que surta efecto
		}catch(IOException e){};
 	}
 	public void moveTotxtF0F3(ArrayList<ArrayList<Integer>> lista,ArrayList<ArrayList<Integer>> lista2,int m,List<Integer> lis,List<Integer> lis2,String tipo){
 		File arch  = new File("F0.txt");
 		File arch2 = new File("F3.txt");
 		//para mover las respectivas lista de listas a los archivos que les corresponden se deben establecer cuales listas se van a cada uno
 		ArrayList<Integer> f0 = new ArrayList<Integer>();
 		ArrayList<Integer> f3 = new ArrayList<Integer>();
 		int i=0,j=0;
 		//Se recorre la lista de listas correspondiente a F1
 		for(ArrayList<Integer> temp : lista){
 			//En cada recorrido de las sublistas se a recorriendo a la vez sus elementos
			for(Integer tm: temp){
				//la variable j determinará si esta es una sublista par o impar de la lista de listas correspondiente
			if(j%2==0){
        		f0.add(tm);
        	}else{
        		f3.add(tm);
        	}
        }
        j++;
    	}
    	//Se recorre la lista de listas correspondiente a F1
    	for(ArrayList<Integer> temp : lista2){
    		//En cada recorrido de las sublistas se a recorriendo a la vez sus elementos
			for(Integer tm: temp){
								//la variable j determinará si esta es una sublista par o impar de la lista de listas correspondiente
			if(i%2==0){
        		f0.add(tm);
        	}else{ //dependiendo de su paridad es en la lista que se agregará
        		f3.add(tm);
        	}
        }
        i++;
    	}
    	f0=orden(f0,tipo); //se ordenan los elementos dentro de cada lista con el método orden
    	//el parámetro tipo hará saber si es un ordenamiento ascendente o descendente
    	f3=orden(f3,tipo);
    	ArrayList<Integer> r = new ArrayList<Integer>();
    	//Para la pequeña sublista que ha quedado en el olvido se debe agregar con éste método
    	//La pequeña lista corresponde en índices al tamaño de la lista menos el módulo de la lista con respecto a m
    	//Y llega hasta el tamaño de la lista. Con esto garantizamos que integramos la pequeña lista a continuar en la ejecución
 		r.addAll(lis.subList(lis.size()-lis.size()%m,lis.size()));
 		//Como no sabemos si existe tal lista (en el caso que sea exacta la divisón de de lista.size() con la cantidad m del usuario)
 		//entonces por eso colocamos este condicional, si es una lista vacía no la agregamos.
		if(!r.isEmpty()) 
 			for(Integer tm: r){
 				f0.add(tm);
 			}
 		ArrayList<Integer> s = new ArrayList<Integer>();
 		s.addAll(lis2.subList(lis2.size()-lis2.size()%m,lis2.size()));
		if(!s.isEmpty())
 			for(Integer tm: s){
 				f3.add(tm);
 		}
    	imprimirListaInt(f0);
    	imprimirListaInt(f3);
    	//Una vez que tenemos establecido quiénes son las listas f0 y f3 las escribimos en los archivos F0 y F3
 		try{
		FileWriter archi = new FileWriter(arch);
		BufferedWriter archiBuffer = new BufferedWriter(archi);
		PrintWriter escritura1 = new PrintWriter(archi);
		FileWriter archi2 = new FileWriter(arch2);
		BufferedWriter archiBuffer2 = new BufferedWriter(archi2);
		PrintWriter escritura2 = new PrintWriter(archi2);
		//Los métodos de escritura son usados para interar elementos a un archivo
		/*Si usamos sólo FileInputStream, FileOuputStream, 
FileReader o FileWriter, cada vez que hagamos 
una lectura o escritura, 
se hará fisicamente en el disco duro. Si escribimos 
o leemos pocos 
caracteres cada vez, el proceso se hace costoso y
lento, con muchos accesos a disco duro.*/
			//Los archivos de f0 los integramos a F0.txt y a f3 en F3.txt
		for(Integer tm: f0){
        	escritura1.print(tm+" ");
        }
        for(Integer tm: f3){
        	escritura2.print(tm+" ");
        }
		archi.close();
		archiBuffer.close();
		archi2.close();
		archiBuffer2.close();
		//Para que surja efecto es necesario cerrar el buffer y el writer
		}catch(IOException e){};
 	}
 	public void moveTotxtF1F2(ArrayList<ArrayList<Integer>> lista,ArrayList<ArrayList<Integer>> lista2,int m,List<Integer> lis,List<Integer> lis2,String tipo){
 		//Para mover los elementos a F1  y F2 pero para ver las iteraciones NO sobreeescribimos sobre los anteriores generados
 		File arch  = new File("F1-1.txt");
 		File arch2 = new File("F2-2.txt");
 		ArrayList<Integer> f1 = new ArrayList<Integer>();
 		ArrayList<Integer> f2 = new ArrayList<Integer>();
 		int i=0,j=0;//mismo procedimiento de colocar las listas impares con una y las pares con otra
 		for(ArrayList<Integer> temp : lista){
			for(Integer tm: temp){
			if(j%2==0){
        		f1.add(tm);
        	}else{
        		f2.add(tm);
        	}
        }
        j++;
    	}
    	//Solo que esta ocasión hay 2 listas y es necesario realizar el procedimiento con las 2
    	for(ArrayList<Integer> temp : lista2){
			for(Integer tm: temp){
			if(i%2==0){
        		f1.add(tm);
        	}else{
        		f2.add(tm);
        	}
        }
        i++;
    	}
    	//Una vez que se a establecido un orden en la lista con el método oden cabe recalcar
    	//que no es necesario ordenar f2 ya que solo contendrá al elemento lista pequeña con m distinto
    	f1=orden(f1,tipo);
    	ArrayList<Integer> s = new ArrayList<Integer>();
 		s.addAll(lis.subList(lis.size()-lis.size()%m,lis.size()));
		if(!s.isEmpty())
 			for(Integer tm: s){
 				f2.add(tm);
 		}
 		//nuevamente checamos si hay alguna lista de tamaño menor a 2m si is existe la agregamos a f2 que solo existe una
 		ArrayList<Integer> r = new ArrayList<Integer>();
 		r.addAll(lis2.subList(lis2.size()-lis2.size()%m,lis2.size()));
		if(!r.isEmpty())
 			for(Integer tm: r){
 				f2.add(tm);
 		}

 		//La lista semifinal consiste en todos los bloques de listas de tamaño m 
 		// juntas y todos sus elementos ordenados y una pequeña sublista que correspodne a la del principio que tenía un bloque
 		//con una sublista de tamaño menor a m
 		System.out.println("LISTA SEMIFINAL");
    	imprimirListaInt(f1);
    	imprimirListaInt(f2);
 		try{
		FileWriter archi = new FileWriter(arch);
		BufferedWriter archiBuffer = new BufferedWriter(archi);
		PrintWriter escritura1 = new PrintWriter(archi);
		FileWriter archi2 = new FileWriter(arch2);
		BufferedWriter archiBuffer2 = new BufferedWriter(archi2);
		PrintWriter escritura2 = new PrintWriter(archi2);
		//nuevamente ya que tenemos los arraylist correspondientes procedemos a escribirlos en archivo de texto planos
		for(Integer tm: f1){
        	escritura1.print(tm+" ");
        }
        for(Integer tm: f2){
        	escritura2.print(tm+" ");
        }
		archi.close();
		archiBuffer.close();
		archi2.close();
		archiBuffer2.close();
		}catch(IOException e){};
 	}
 	//la creación de sublistas consiste en separar la lista original en pequeñas sublistas que nos permitan manipular mejor una lista de listas
 	public ArrayList<ArrayList<Integer>> crearSub(List<Integer> lista,int sobrante,int cantidades,int m,String tipo){
 		ArrayList<ArrayList<Integer>> listaDeLista = new ArrayList<>();
 		int i = 0;
 		//Creamos las sublistas y las agregamos a la lista de listas
 		for(i=0;i<cantidades;i++){
 			ArrayList<Integer> k = new ArrayList<Integer>();
 			k.addAll(lista.subList(m*i,m*(i+1)));
 			imprimirListaInt(k);
 			listaDeLista.add(k=orden(k,tipo));

 		}
 		//el último elemento dentro de la lista original puede contener una lista de tamaño menor a m por lo que debe ser consierada igual
 		//añade la lista que va del índice de la última lista de tamaño m = cantidades*m y hasta el final de la lista con lista.size()
 		ArrayList<Integer> r = new ArrayList<Integer>();
 		r.addAll(lista.subList(m*cantidades,lista.size()));
 		if(!r.isEmpty()) //si fue entera la división y r es nulo no lo agrega
 			listaDeLista.add(r=orden(r,tipo));
 		imprimirListaList(listaDeLista); //imprimimos esa lista de lista
 		return listaDeLista; //retornamos la lista de lista creada
 	}
 	 	public ArrayList<ArrayList<Integer>> crearSub2(List<Integer> lista,int sobrante,int cantidades,int m,String tipo){
 		ArrayList<ArrayList<Integer>> listaDeLista = new ArrayList<>();
 		int i = 0;
 		//Para crear las sublistas nuevamnete utilizamos el método visto en clase de sublist que insertamos
 		//como parámetros al indice incial y final para que nos devuelva una sublista de la lista original
 		for(i=0;i<cantidades;i++){
 			ArrayList<Integer> k = new ArrayList<Integer>();
 			k.addAll(lista.subList(m*i,m*(i+1)));
 			imprimirListaInt(k);
 			listaDeLista.add(k=orden(k,tipo));
 			//luego son añadidos a la lista de listas empleada 
 		}
 		//ArrayList<Integer> r = new ArrayList<Integer>();
 		//r.addAll(lista.subList(m*cantidades,lista.size()));
 		//if(!r.isEmpty())
 		//	listaDeLista.add(orden(r));
 		imprimirListaList(listaDeLista);
 		//para poder retornarla y tener una lista de lista manipulable
 		return listaDeLista;
 	}
 	public void moveTotxt(ArrayList<ArrayList<Integer>> lista,int m,String tipo){
 		File arch  = new File("F1.txt");
 		File arch2 = new File("F2.txt");
 		//para la primera escritura de archivos que corresponde a F1 y F2 necesitamos los métodos de escritura
 		try{
 		//el escritor
		FileWriter archi = new FileWriter(arch);
		BufferedWriter archiBuffer = new BufferedWriter(archi);
		PrintWriter escritura1 = new PrintWriter(archi);
		FileWriter archi2 = new FileWriter(arch2);
		BufferedWriter archiBuffer2 = new BufferedWriter(archi2);
		PrintWriter escritura2 = new PrintWriter(archi2);
		/*Si usamos sólo FileInputStream, FileOuputStream, 
FileReader o FileWriter, cada vez que hagamos 
una lectura o escritura, 
se hará fisicamente en el disco duro. Si escribimos 
o leemos pocos 
caracteres cada vez, el proceso se hace costoso y
lento, con muchos accesos a disco duro.*/
		int j= 0; //con cada elemento la lista, si corresponde a una sublista con indice en la lista de listas par, lo integramos a F1,
		//caso contrario impar a F2.txt
		for(ArrayList<Integer> temp : lista){
			for(Integer tm: temp){
			if(j%2==0){
        		escritura1.print(tm+" ");
        	}else{
        		escritura2.print(tm+" ");
        	}
        }
        j++;
    	}
		archi.close();
		archiBuffer.close();
		archi2.close();
		archiBuffer2.close();
		//es necesario cerrar los escritores y los bufer para que puedan surgir los cambios
		}catch(IOException e){};
 	}

 	//método constructor que nos permite crear objetos para poder
 	//realizar los métodos establecidos, en caso contrario necesitarían ser estátios
	public Polifase(int m,String direccion,String tipo){
		this.m = m;
		this.direccion = direccion;
		this.tipo=tipo;
	}
	//Imprime una lista de enteros regular
	public static void imprimirListaInt(List<Integer> lis){
		System.out.print("Lista: ");
		for(Integer temp: lis){
			System.out.print(temp+" ");
		}
		System.out.println("\n");
	}
	//Para decir qué elementos dentro de la lista se encuentran se imprimen sus elementos mostrando su contenido en cada archivo
	public static void imprimirListaIntFiles(List<Integer> lis,String k){
		System.out.print(k+": ");
		for(Integer temp: lis){
			System.out.print(temp+" ");
		}
		System.out.println("\n");
	}
	//para la impresión de una lsita sin que tenga la palabra impresa Lista
	public static void imprimirListaInt2(List<Integer> lis){
		for(Integer temp: lis){
			System.out.print(temp+" ");
		}
	}
	//Se emplea una funcion orden que recibe como parámetros a la lista y un String
	//que ese string corresponde al tipo de orden que se le dará a la lista, ascendente o descendente
	//según lo seleccionado en la interfaz gráfica
	public ArrayList<Integer> orden(ArrayList<Integer> m,String upd){
		if(upd.equals("Ascendente")){
			m=quickSort(m);
		}else{
			m=quickSortDes(m);
		}
		return m;
	}
	//Para la impresión de la lista de Listas es necesario un dobre recorrido
	//para cada sublista temp en lista mandamos a llamar el método imprimirListaInt que
	//imprime los enteros de una lista por lo que en cada recorrido de este for se imprimirá una lista completa
	public static void imprimirListaList(List<ArrayList<Integer>> lista){
		for(ArrayList<Integer> temp : lista){
			System.out.println("Particiones: ");
			System.out.print("[");
			imprimirListaInt2(temp);
			System.out.println("]  ");
		}
	}
	//PARA LA ÑERA
	//Con el fin de probar el funcionamiento del algoritmo desde la terminar y de manera rápida 
	public static void main(String[] args) {
		Polifase poli = new Polifase(4,"/Users/samuelarturogarridosanchez/Desktop/MonsalvoBolaños MelissaMonserrat_CardenasCardenasJorge_GarridoSanchez SamuelArturo_Proyecto1 2/Polifase/doc.txt","Descendente");
		poli.sort(4,"/Users/samuelarturogarridosanchez/Desktop/MonsalvoBolaños MelissaMonserrat_CardenasCardenasJorge_GarridoSanchez SamuelArturo_Proyecto1 2/Polifase/doc.txt","Descendente");
	}
	//Método quick sort clásico que se encarga de ordenar las sublistas de manera individual
	//Este es el caso de Ascendente donde tiene todos los elementos comparativos como el pivote
	// y devuelve el arreglo ordenado
	public static ArrayList<Integer> quickSort(ArrayList<Integer> list)
{
    if (list.isEmpty()) 
        return list; 
    ArrayList<Integer> sorted;  
    ArrayList<Integer> smaller = new ArrayList<Integer>(); // Enteros que son menores que el pivote
    ArrayList<Integer> greater = new ArrayList<Integer>(); // Enteros que son mayores que el pivote
    Integer pivot = list.get(0);  // primer elemento en la lista
    int i;
    Integer j;    
    for (i=1;i<list.size();i++)//ciclo de iteraciones con el pivote 
    {
        j=list.get(i);
        if (j.compareTo(pivot)<0)  
            smaller.add(j);
        else
            greater.add(j);
    }
    smaller=quickSort(smaller); 
    greater=quickSort(greater); 
    smaller.add(pivot);          
    smaller.addAll(greater);   
    sorted = smaller;            

    return sorted;
}
//El método Quick Sort desorden es el mismo que el de orden solo que cambia una cosa: La comparación con los pivotes
public static ArrayList<Integer> quickSortDes(ArrayList<Integer> list)
{
    if (list.isEmpty()) 
        return list; // Termina el caso recursivo
    ArrayList<Integer> sorted;
    ArrayList<Integer> smaller = new ArrayList<Integer>(); // Enteros que son menores que el pivote
    ArrayList<Integer> greater = new ArrayList<Integer>(); // Enteros que son mayores que el pivote
    Integer pivot = list.get(0);  // primer elemento en la lista
    int i;
    Integer j; //ciclo de iteraciones con el pivote 
    for (i=1;i<list.size();i++)
    {
        j=list.get(i);
        if (j.compareTo(pivot)>0) // -> ESTE FUE EL CAMBIO, > por <
            smaller.add(j);
        else
            greater.add(j);
    }
    smaller=quickSortDes(smaller);  //Inician los casos recursivos
    								//para finalmente retornar el archivo ordenado
    greater=quickSortDes(greater);  
    smaller.add(pivot);
    smaller.addAll(greater);     
    sorted = smaller;            

    return sorted;
}

}
/*Si usamos sólo FileInputStream, FileOuputStream, 
FileReader o FileWriter, cada vez que hagamos 
una lectura o escritura, 
se hará fisicamente en el disco duro. Si escribimos 
o leemos pocos 
caracteres cada vez, el proceso se hace costoso y
lento, con muchos accesos a disco duro.
Los BufferedReader, BufferedInputStream, 
BufferedWriter y 
BufferedOutputStream añaden un buffer intermedio. 
Cuando leamos o 
escribamos, esta clase controlará los accesos
 a disco.*/