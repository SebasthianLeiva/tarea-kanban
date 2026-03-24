package logicaTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dominio.Estado;
import dominio.Tarea;
import logica.Logica;


public class AumentarYDisminuirContadorTest {
	
	
	@Test
	public void aumentarContadorPorHacerTest() {
		
		Logica logica = new Logica();
		
		//se aumenta en 1 el contador de tareas con estado POR_HACER
		
		logica.aumentarContadorDeTareas(Estado.POR_HACER);
		
		//se verifica que el contador de tareas POR_HACER sea 1.
		
		assertEquals(1,logica.getContadorTareasPorHacer());
			
	}
	
	
	@Test
	public void aumentarContadorEnProcesoTest() {
		
		Logica logica = new Logica();
	
		//se aumenta en 1 el contador de tareas con estado EN_PROCESO
		
		logica.aumentarContadorDeTareas(Estado.EN_PROCESO);
		
		//se verifica que el contador de tareas EN_PROCESO sea 1.
		
		assertEquals(1,logica.getContadorTareasEnProceso());
			
	}
	
	
	@Test
	public void aumentarContadorTerminadoTest() {
		
		Logica logica = new Logica();
		
		//se aumenta en 1 el contador de tareas con estado TERMINADO
		
		logica.aumentarContadorDeTareas(Estado.TERMINADO);
		
		//se verifica que el contador de tareas TERMINADO sea 1.
		
		assertEquals(1,logica.getContadorTareasTerminado());
			
	}
	
	
	@Test
	public void disminuirContadorMayorA0PorHacerTest() {
		
		Logica logica = new Logica();
		
		//se aumenta el contador de tareas POR_HACER para generar el caso en que no es igual a 0. 
		
		logica.aumentarContadorDeTareas(Estado.POR_HACER);
		
		//se disminuye en 1 el contador de tareas con estado POR_HACER
		
		logica.disminuirContadorDeTareas(Estado.POR_HACER);
		
		assertEquals(0,logica.getContadorTareasPorHacer());
		
		
	}
	
	
	@Test
	public void disminuirContadorMayorA0EnProcesoTest() {
		
		Logica logica = new Logica();
		
		//se aumenta el contador de tareas EN_PROCESO para generar el caso en que no es igual a 0. 
		
		logica.aumentarContadorDeTareas(Estado.EN_PROCESO);
		
		//se disminuye en 1 el contador de tareas con estado EN_PROCESO
		
		logica.disminuirContadorDeTareas(Estado.EN_PROCESO);
		
		assertEquals(0,logica.getContadorTareasEnProceso());
		
		
	}
	
	
	@Test
	public void disminuirContadorMayorA0TerminadoTest() {
		
		Logica logica = new Logica();
		
		//se aumenta el contador de tareas TERMINADO para generar el caso en que no es igual a 0. 
		
		logica.aumentarContadorDeTareas(Estado.TERMINADO);
		
		//se disminuye en 1 el contador de tareas con estado TERMINADO.
		
		logica.disminuirContadorDeTareas(Estado.TERMINADO);
		
		assertEquals(0,logica.getContadorTareasTerminado());
		
		
	}
	

	@Test
	public void disminuirContadorIgualA0PorHacerTest() {
		
		Logica logica = new Logica();
		
		//se disminuye en 1 el contador de tareas con estado POR_HACER con valor 0
		
		assertThrows(IllegalStateException.class, () -> {
			
	        logica.disminuirContadorDeTareas(Estado.POR_HACER);
	        
	    });
	}
		
		
	
	
	
	@Test
	public void disminuirContadorIgualA0EnProcesoTest() {
		
		Logica logica = new Logica();
		
		//se disminuye en 1 el contador de tareas con estado EN_PROCESO con valor 0
		
		assertThrows(IllegalStateException.class, () -> {
			
	        logica.disminuirContadorDeTareas(Estado.EN_PROCESO);
	        
	    });
	}
		
	
	
	
	@Test
	public void disminuirContadorIgualA0TerminadoTest() {
		
		Logica logica = new Logica();
		
		//se disminuye en 1 el contador de tareas con estado TERMINADO con valor 0
		
		assertThrows(IllegalStateException.class, () -> {
			
	        logica.disminuirContadorDeTareas(Estado.TERMINADO);
	        
	    });
	}
		
	
	
}
