package logicaTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dominio.Tarea;
import logica.Logica;


public class ObtenerArrayDeLaTareaTest {
	
	@Test
	public void testObtenerArrayDeLaTareaValida() {
		
		Logica logica = new Logica();
		Tarea tarea = new Tarea("test");
		
		ArrayList<Tarea> arrayListDeTarea = logica.obtenerArrayDeLaTarea(tarea);
		
		// se verifica que el array obtenido sea el de las tareas con estado POR_HACER
		
		assertEquals(logica.getTareasPorHacer(),arrayListDeTarea);
		
	}
	
	
	@Test
	public void testObtenerArrayDeLaTareaNula() {
		
		Logica logica = new Logica();
		
		//se obtiene el array de la tarea nula
		
		assertThrows(IllegalArgumentException.class, () -> {
	        logica.obtenerArrayDeLaTarea(null);
	    });
		
	}

}
