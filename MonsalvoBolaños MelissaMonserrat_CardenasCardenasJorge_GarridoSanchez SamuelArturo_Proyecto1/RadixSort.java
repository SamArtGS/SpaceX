/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author HP14
 */
public class RadixSort {

    List<File> arch = new ArrayList<File>();
    File Faux= new File("File10.txt"); 
    String tipo;
    
     //Lee el archivo, recibe el nombre del archivo a leer en una cadena y devuelve su contenido en una lista de enteros.
    public List<Integer> leerl(String nombreArchivo){
        File f;
        FileReader fr;
        BufferedReader br;
        try{
        f=new File(nombreArchivo);
        fr= new FileReader(f);
        br= new BufferedReader(fr);
        
        List<String> lp = new ArrayList<String>();//Almacena los renglones recuperados del archivo en forma de cadenas
        List<Integer> l = new ArrayList<Integer>();//Almacena los elementos recuperados del archivo en forma de enteros
        String linea;//Cadena que almacena las lineas del archivo
        while((linea=br.readLine())!=null){//Mientras la linea recuperada del archivo no sea nula
            lp.add( linea );//Añade esa linea a una lista
        }
        
        /*Obtiene los elementos separados por coma de la ultima linea leia y los almacena
            en una arreglo de strings
        */
        String []texto = lp.get(lp.size()-1).split(",");//Separa los elementos de una cadena separados por comas
        for(int i=0;i<texto.length;i++)//Para todos los elementos separados
            l.add(Integer.parseInt(texto[i]));//Agregalos en una lista, pero primero realiza un casteo para que sean enteros
        //cerrar
        br.close();
        fr.close();
        //regresa la lista de enteros
        return l;
 
        }catch (Exception e){
            //Si hay algun error en la creación del archivo imprime este mensaje al usuario
        JOptionPane.showMessageDialog(null,"Error"+e);
   
    }
        //Si algo falla, regresa nulo
        return null;
  
}
    
    /*  Lee el archivo recibe el nombre del archivo a leer en una cadena y devuelve su contenido en una lista de Strings.
        Básicamente es igual al método anterior, pero aquí se devuelve una lista de
        Strings.
    */
     public List<String> lee(String nombreArchivo){
        File f;
        FileReader fr;
        BufferedReader br;
      
        try{
        f=new File(nombreArchivo);
        fr= new FileReader(f);
        br= new BufferedReader(fr);
        List<String> lp = new ArrayList<String>();
        List<String> l = new ArrayList<String>();

        String linea;
        while((linea=br.readLine())!=null){

            lp.add( linea );
        }
         /*Obtiene los elementos separados por coma de la ultima linea leia y los almacena
            en una arreglo de strings
        */
  
        String []texto = lp.get(lp.size()-1).split(",");
        //Agrega los elementos del arreglo obtenido a una lista de Strings
        for(int i=0;i<texto.length;i++)
            l.add(texto[i]);
        
        
        br.close();
        fr.close();
        return l;
 
        }catch (Exception e){
        JOptionPane.showMessageDialog(null,"Error"+e);
   
    }
        return null;
  
}
    
    /*  Escribe una cadena en un archivo y da un aslto de linea, escribe lineas completas de elementos
        Recibe como cadena el nombre del archivo en el que se escribirá y como lista de enteros la lista
        que se escribirá, no devuleve nada.
     */
     public void escr(String nombre, String lista) throws IOException{
    File f;
    FileWriter w;
    BufferedWriter bw;
    PrintWriter wr;
  
    try{
    f=new File(nombre);
    w=new FileWriter(f,true);
    bw=new BufferedWriter(w);
    wr=new PrintWriter(bw);
    
    bw.newLine();//salto de linea
    wr.write(lista);//Agrega la cadena al archivo
    //cierre de los flujos de canales de datos, al cerrarlos 
    wr.close();
    bw.close();
    
    }catch (Exception e){
        JOptionPane.showMessageDialog(null,"Error"+e);
    }
    
  
} 
     
    /*Escribe en el documento sin salto de linea, es para escribir elementos en los archivos
     * la unica diferencia con el anterior es que este método nopresenta la instrucción de
     * salto de linea.
     */
     public void escrele(String nombre, String lista) throws IOException{
    File f;
    FileWriter w;
    BufferedWriter bw;
    PrintWriter wr;
   
    try{
    f=new File(nombre);
    w=new FileWriter(f,true);
    bw=new BufferedWriter(w);
    wr=new PrintWriter(bw);
    wr.write(lista);
    
    wr.close();
    bw.close();
    }catch (Exception e){
        JOptionPane.showMessageDialog(null,"Error"+e);
    }
    
  
} 
      /*Agrega un salto de linea al archivo. Recibe como cadena, el nombre del archivo donde se insertará
        el salto, no devulve nada.
     */
     public void salto(String nombre) throws IOException{
    File f;
    FileWriter w;
    BufferedWriter bw;
    PrintWriter wr;
    try{
    f=new File(nombre);
    w=new FileWriter(f,true);
    bw=new BufferedWriter(w);
    wr=new PrintWriter(bw);
    bw.newLine();//salto de linea
   
    wr.close();
    bw.close();
    }catch (Exception e){
        JOptionPane.showMessageDialog(null,"Error"+e);
    }
    
  
}      
        
     /*Inicializa una lista de archivos, los archivos auxiliares.
        Recibe como argumento la lista de archivos a inizializar, 
        no devuelve nada.
     */
     public void inicializar(List<File> arch){
         //Crea los archivos auxiliares
            File f0= new File("File0.txt"); 
            File f1= new File("File1.txt");
            File f2= new File("File2.txt");
            File f3= new File("File3.txt");
            File f4= new File("File4.txt");
            File f5= new File("File5.txt");
            File f6= new File("File6.txt");
            File f7= new File("File7.txt");
            File f8= new File("File8.txt");            
            File f9= new File("File9.txt");
             //Agrega los archivos en una lista
       arch.add(f0);
       arch.add(f1);
       arch.add(f2);
       arch.add(f3);
       arch.add(f4);
       arch.add(f5);
       arch.add(f6);
       arch.add(f7);
       arch.add(f8);
       arch.add(f9);
       
       
       
       
               
   }
     
     /* Realiza una busqueda lineal sobre la lista recuperada y determina cual es elemento mayor
        para calcular su longitud. Recibe como argumento la lista de enteros donde se realiza la búsqueda 
        y devuelve un entero con cantidad de dígitos de dicho numero.
      */
     public int mayor(List<Integer> num){
            int numeromayor=0;//Guarda al elemento mayor 
            for(int i=0; i<num.size(); i++){//Para cada elemento de la lista
                if(num.get(i)>numeromayor){ //Si el elemento extraido es mayor que el elementomayor
                    numeromayor = num.get(i);//Entonces el elemento mayor, sera el elemento de la lista en la posición evaluada
                }
               }
            /*Ahora se calcula el numero de digitos del numero mayor, para ello lo transformas en cadena
                y utilizamos el método length
            */
            numeromayor=Integer.toString(numeromayor).length();
            return numeromayor;
        }
      
     /*Imprime el contenido de un archivo. Recibe una cadena con el 
        nombre del archivo que se desea imprimir.
     */
      public void imprimirL(String NombreArchivo){
          //Lista donde se almacenan temporalmente los elementos de la ultima linea del archivo.
          List<Integer> lista=new ArrayList<Integer>();
          //Lee la ultima linea del archivo.
                  lista=leerl(NombreArchivo);
                  //Para cada elemento recuperado del archivo
                  for(int el:lista){
                      //imprime el elemento más una coma
                      System.out.print(el+",");
                  }
      }
      //Elimina archivos. Recibe como argumento el archivo a eliminar
      public void eliminar(File archivo) {
          //Si el archivo existe
        if (archivo.exists()) {
            //Eliminalo
            archivo.delete();
        }

}
      //Elimina todos los archivos auxiliares. No recibe ni devulve nada.
      public void eliminartodo(){
          //Si la lista de archivos auxiliares no esta vacía
        if (arch!=null){
            for(File f: arch)//Para cada archivo de la lista
                    eliminar(f);//Elimina el archivo
            eliminar(Faux);//Elimina la copia del archivo original
                       }
        
          
      }
      /*Copia la lista de elementos en un archivo auxiliar para mostrar las
        iteraciones. Recibe en cadenas el nombre del archivo original y del archivo copia
        donde se almacena.
      */
      public void copiar(String archivo,String copia) throws IOException{
         //Lista de Strins auxiliar para almacenar el contenido de un archivo
          List<String> s=new ArrayList<String>();
          //Lee el archivo original y obtiene una lista de strings del contenido
          s=lee(archivo);
          //Realiza un salto de linea sobre la copia antes de escribir en él.
          salto(copia);
          //Escribe todos los elementos recuperados sobre el archivo copia
          for(String n: s)
            escrele(copia,n+",");
         
      }

     //Realiza el ordenamiento por RadixSort
     public void radixArchivo(String nombreArchivo) throws IOException{ 
       //lista de archivos auxiliare
       //Inicializa la lista agregando 10 archivos
       inicializar(arch);
       //Listas donde se almacenan los elementos recuperados del archivo
       List<Integer> ele = new ArrayList<Integer>();
       List<String> st = new ArrayList<String>();
       /*Variables auxiliares para el calculo del indice que indica el numero del archivo auxiliar
            en el que se esciben los digitos
       */
       int clave,mayor,a,div=1;
       //Arreglo booleano que registra los cambios en los archivos auxiliares
       boolean []band=new boolean[arch.size()]; 
       String name;
       
       //obtiene los elementos del archivo en forma de enteros y cadenas
       ele=leerl(nombreArchivo);
       //La cantidad de digitos del numero mayor
       mayor=mayor(ele);
       
       int canDig=mayor;
       
       do{ 
           //recupera los elementos de los archivos
           ele=leerl(nombreArchivo);
           st=lee(nombreArchivo);
           
           //Inicializas el arreglo de control de cambio en falso
       for(int i=0; i<arch.size();i++){
           band[i]=false;
            }
     
       //Asigna cada elemento a un archivo auxiliar
        for(int i=0;i<ele.size();i++){//Para cada elemento recuperado
                //Almacena en a un elemento
                a=ele.get(i);
                //Va eliminando los digitos ya leidos
                clave = (a/div);
                //Va recuperando el digito de menos significativo 
                clave=clave%10;
                /*Dependiendo de la clave, lo asigna a un archivo auxiliar,
                escribe directamente el elemento en el archivo
                */
                escrele(arch.get(clave).getName(),ele.get(i)+",");
                //Si hubocambios en el archivo i, se documenta
                band[clave]=true;
     
            }         
//juntar los archivos
           salto(nombreArchivo);
           for(int j=0; j<arch.size(); j++){ //para todos los archivos auxiliares 
               //Evita que se escriban los elementos de los archivos en los que no se insertaron elementos
                if(band[j]==true){//si se realizo algun cambio en el archivo
                    st=lee(arch.get(j).getName());//Almacena todos los elementos del archivo auxiliar
                    for(int i=0;i<st.size();i++){//Para cada elemento obtenido
                        escrele(nombreArchivo,st.get(i)+",");//escribelos en el archivo original
                    }
                }
           }
           //Limpia la lista donde se almacenan los elementos, para que no se repitan en la siguiente iteración
           st.clear();
           ele.clear();
           //evaluado el digito menos significativo, se descarta y se lee el siguiente numero
            div=div*10;
            canDig--;//Se resta la cantidad de digitos por analizar
            //Salto de linea en todos los archivos auxiliares
            
       System.out.print("\n\n iterción*******************\n");
       for(int i=0;i<=9;i++){
           if(band[i]==true){
               name="File"+i+".txt";
               System.out.print(name+": ");
               imprimirL(name);
               System.out.println();
           } 
       }
       System.out.print("\n"+Faux.getName()+": ");
       imprimirL(Faux.getName());   
      
       salto("File0.txt");
       salto("File1.txt");
       salto("File2.txt");
       salto("File3.txt");
       salto("File4.txt");
       salto("File5.txt");
       salto("File6.txt");
       salto("File7.txt");
       salto("File8.txt");
       salto("File9.txt");
       salto(Faux.getName());
         //se realiza hasta que ya no haya números que analizar     
       }while(canDig>0);
       
       
 
     }

     
     /* Ordena los elementos en forma descendente.
        Realiza los mismos pasos que el anterior, solo que aquí el modo de obtener la clave
        de asignación de los archivos cambia. Almacena los numeros con clave 1 en el archivo 9, los del 
        9 en el archivo 0 y así con todos.
     
     */

      public void radixInverso(String nombreArchivo) throws IOException{ 
       inicializar(arch);      
       List<Integer> ele = new ArrayList<Integer>();
       List<String> st = new ArrayList<String>();
       String name;
       int clave,mayor,a,div=1,cont;
       boolean []band=new boolean[arch.size()];  
       ele=leerl(nombreArchivo);
       mayor=mayor(ele);
       int canDig=mayor;
       
       do{ 
           
           ele=leerl(nombreArchivo);
           st=lee(nombreArchivo);
           
           
       for(int i=0; i<arch.size();i++){
           band[i]=false;
            }
       
        for(int i=0;i<ele.size();i++){
            
                a=ele.get(i);
                clave = (a/div);
                clave=clave%10;
                //instrucción que cambia la asignación de clave
                clave=9-clave;
   
                escrele(arch.get(clave).getName(),ele.get(i)+",");
                band[clave]=true;
               
               
            }         
//juntar los archivos
          salto(nombreArchivo);
           for(int j=0; j<arch.size(); j++){     
                if(band[j]==true){
                
                    st=lee(arch.get(j).getName());
                    
                    for(int i=0;i<st.size();i++){
                        escrele(nombreArchivo,st.get(i)+",");
                    }
                }
           }
           
           st.clear();
           ele.clear();
            div=div*10;
            canDig--;
            
     System.out.print("\n iterción*******************\n");
       for(int i=0;i<=9;i++){
           if(band[i]==true){
               name="File"+i+".txt";
               System.out.print(name+": ");
               imprimirL(name);
               System.out.println();
           } 
       }
       System.out.print("\n"+Faux.getName()+": ");
       imprimirL(Faux.getName());   
         
       salto("File2.txt");
       salto("File0.txt");
       salto("File1.txt");
       salto("File3.txt");
       salto("File4.txt");
       salto("File5.txt");
       salto("File6.txt");
       salto("File7.txt");
       salto("File8.txt");
       salto("File9.txt");
       salto(Faux.getName());                
       }while(canDig>0);
     }
     String nombre;
      //Metodo principal que llama a los demás métodos de la clase
      public void radix(String nombre,String tipo) throws IOException{
            //Almacena la opción elegida por el usuario
            switch (tipo){
                case "Ascendente"://Radix ascendente
                    //Elimina todos los archivos auxiliares existentes
                    eliminartodo();
                    //Copia la lista del archivo original, en un archivo auxiliar para mostrar las iteraciones                   
                    copiar(nombre,Faux.getName());
                    //Imprime la lista original, del archivo proveniente
                    System.out.println("Lista original: ");             
                    imprimirL(nombre);
                    //Realiza el ordenamiento asecendente sobre el archivo auxiliar
                    radixArchivo(Faux.getName());
                    //Escribe la última línea de elementos ordenados del archivo auxiliar al archivo original
                    copiar(Faux.getName(),nombre);
                    //Imprime la lista ordenada desde el archivo original 
                    System.out.println("\n\nLista ordenada: ");                 
                    imprimirL(nombre);
                    JOptionPane.showMessageDialog(null, "El archivo ha sido ordenado");//Avisa al usuario que se ha rodenado el archivo
                    break;
                case "Descendente"://Radix descendente
                    //Elimina todos los archivos auxiliares existentes
                    eliminartodo();
                    //Copia la lista del archivo original, en un archivo auxiliar para mostrar las iteraciones 
                    copiar(nombre,Faux.getName());
                    //Imprime la lista original, del archivo proveniente
                    System.out.println("Lista original: ");
                    imprimirL(nombre);
                    //Realiza el ordenamiento descendiente sobre el archivo auxiliar
                    radixInverso(Faux.getName());
                    //Escribe la última línea de elementos ordenados del archivo auxiliar al archivo original
                    copiar(Faux.getName(),nombre);
                    //Imprime la lista ordenada desde el archivo original
                    System.out.println("\nLista ordenada: ");
                    imprimirL(nombre);
                    JOptionPane.showMessageDialog(null, "El archivo ha sido ordenado");//Avisa al usuario que se ha rodenado el archivo
                    break;
                default:
                    break;

            }
      
      }
    public RadixSort(String tipo,String nombre){
      this.tipo=tipo;
      this.nombre = nombre;

    }
    /*public static void main(String[] args) throws IOException {
        RadixSort m = new RadixSort();
       
        String lista="123,4242,116,3,24,8301,94410,43,1333";
        m.escr("Lista2.txt",lista);
        m.radix("Lista2.txt");
        
        //m.radixArchivo("Lista2.txt");
       //m.radixInverso("Lista2.txt");
        //Prueva p=new Prueva();
       //p.escr("Lista2.txt", lista);
       //p.radix("Lista2.txt");
       //p.ra("Lista2.txt");
       //p.raL("Lista2.txt");
    }*/
    
}
