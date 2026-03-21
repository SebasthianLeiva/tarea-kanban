package logicaTest;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import dominio.Tarea;
import logica.Logica;


public class AniadirTareaAlArrayTest {
	
	@Test 
	public void testAniadirTarea() {

		Logica logica = new Logica();
	    Tarea tarea = new Tarea("Test");
	    
	    //se añade la tarea al array

	    logica.aniadirTareaAlArray(tarea);

	    //se verifica la adicion de la tarea verifando el contador y el array 
	    
	    assertEquals(1, logica.getContadorTareasPorHacer());
	    assertTrue(logica.getTareasPorHacer().contains(tarea));
	    
	}

}
