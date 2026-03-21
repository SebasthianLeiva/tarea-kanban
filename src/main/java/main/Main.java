package main;


import logica.Logica; 
import vista.Pantalla; 

/**
 * Ensambla el programa uniendo las clases Logica y Pantalla por inyeccion de dependencias.
 * Contiene el metodo main que hace de punto de entrada al programa e inicia la ejecucion.
 * 
 */

public class Main {
	
	
	public static void main(String[] args) {
		
		var logica = new Logica();
		
		var pantalla = new Pantalla(logica); 
		
		
		
	}
	

}
