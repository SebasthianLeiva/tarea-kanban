package main;

import logica.Logica;
import vista.Pantalla; 

public class Main {
	
	public static void main(String[] args) {
		
		var logica = new Logica();
		
		var pantalla = new Pantalla(logica); 
		
		pantalla.setResizable(false);
		pantalla.setLocationRelativeTo(null);
		pantalla.setVisible(true);
		
	}
	

}
