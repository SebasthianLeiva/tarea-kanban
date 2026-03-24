package logicaTest;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dominio.Estado;
import dominio.Tarea;
import logica.Logica;




public class EliminarTareaDelArrayTest {
	
	@Test
	
	public void testEliminarTareaPresenteEnArray() {
		
		Logica logica = new Logica();
		Tarea tarea = new Tarea("Test");
		
		
		//se añade la tarea al array para poder eliminarla
		
		logica.aniadirTareaAlArray(tarea);
		
        //se elimina la tarea del array
        
        logica.eliminarTareaDelArray(tarea);
        
        //se verifica que la tarea se haya eliminado correctamente verificando el contador y el array 
        
        assertEquals(0,logica.getContadorTareasPorHacer());
        assertFalse(logica.getTareasPorHacer().contains(tarea));
		
		
		
	}
	
	@Test
	
	public void testEliminarTareaNoPresenteEnArray() {
		
		Logica logica = new Logica();
		Tarea tarea = new Tarea("Test");
		
		
		//se elimina la tarea que nunca se añadio al array del array
		
		assertThrows(IllegalStateException.class, () -> {
	        logica.eliminarTareaDelArray(tarea);
	    });
		
		
	}
	
	
	

}
