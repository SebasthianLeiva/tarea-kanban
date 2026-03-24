package logicaTest;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import dominio.Estado;
import logica.Logica;


public class ObtenerContadorTareasTest {

	@Test
	public void testObtenerContadorTareasEstadoValido() {

	    Logica logica = new Logica();

	    //se aumenta el contador de tareas POR_HACER
	    
	    logica.aumentarContadorDeTareas(Estado.POR_HACER);
	    
	    //se guarda el resultado obtenido con el metodo

	    int resultado = logica.obtenerContadorTareas(Estado.POR_HACER);
	    
	    //se verifica que resultado sea el valor correcto

	    assertEquals(1, resultado);
	}
	
	@Test
	public void testObtenerContadorTareasEstadoNulo() {

	    Logica logica = new Logica();
	    
	    //se pasa un argumento nulo

	    assertThrows(IllegalArgumentException.class, () -> {
	        logica.obtenerContadorTareas(null);
	    });
	}
	
}
