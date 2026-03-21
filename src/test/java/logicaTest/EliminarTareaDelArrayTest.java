package logicaTest;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dominio.Estado;
import dominio.Tarea;
import logica.Logica;




public class EliminarTareaDelArrayTest {
	
	@Test
	
	public void testEliminarTareaDelArray() {
		
		Logica logica = new Logica();
		Tarea tarea = new Tarea("Test");
		
		
		//se añade la tarea al array para poder eliminarla
		
		logica.aniadirTareaAlArray(tarea);
		
		//se verifica que la tarea se haya añadido correctamente verificando el contador y el array
		
		assertEquals(1, logica.getContadorTareasPorHacer());
        assertTrue(logica.getTareasPorHacer().contains(tarea));
        
        //se elimina la tarea del array
        
        logica.eliminarTareaDelArray(tarea);
        
        //se verifica que la tarea se haya eliminado correctamente verificando el contador y el array 
        
        assertEquals(0,logica.getContadorTareasPorHacer());
        assertFalse(logica.getTareasPorHacer().contains(tarea));
		
		
		
	}
	

}
