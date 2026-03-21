package logicaTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dominio.Estado;
import dominio.Tarea;
import logica.Logica;



public class MoverTareaTest {

	@Test
	public void testMoverTareaConDiferenteEstado() {
		
		Logica logica = new Logica();
        Tarea tarea = new Tarea("Test");
        
        //se añade la tarea al array
        
        logica.aniadirTareaAlArray(tarea);
        
        //se mueve la tarea del array "tareasPorHacer" al array "tareasEnProceso" 
        
        logica.moverTarea(tarea, Estado.EN_PROCESO);
        
        //se verifica el estado de la tarea
        
        assertEquals(Estado.EN_PROCESO, tarea.getEstado());
        
        //se verifica que la tarea no este en "tareasPorHacer" y que si este en "tareasEnProceso"
        
        assertFalse(logica.getTareasPorHacer().contains(tarea));
        assertTrue(logica.getTareasEnProceso().contains(tarea));
        
        //se verifican los contadores
        
        assertEquals(0, logica.getContadorTareasPorHacer());
        assertEquals(1, logica.getContadorTareasEnProceso());
        
		
	}
	
	
	@Test
	public void testMoverTareaConMismoEstado() {
		
		Logica logica = new Logica();
	    Tarea tarea = new Tarea("Test");
	    
	    //se añade la tarea al array

	    logica.aniadirTareaAlArray(tarea);
	    
	    //la tarea no se mueve del array "tareasPorHacer" debido a que el estado nuevo estado es el mismo que el anterior
        
        logica.moverTarea(tarea, Estado.POR_HACER);
		
        //se verifica el estado de la tarea
        
        assertEquals(Estado.POR_HACER,tarea.getEstado());
        
        //se verifica que la tarea siga en "tareasPorHacer"
        
        assertTrue(logica.getTareasPorHacer().contains(tarea));
        
        //se verifica el contador de "tareasPorHacer"
        
        assertEquals(1,logica.getContadorTareasPorHacer());
        
	}
	
	
}
