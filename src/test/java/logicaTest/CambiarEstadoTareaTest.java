package logicaTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dominio.Estado;
import dominio.Tarea;
import logica.Logica;


public class CambiarEstadoTareaTest {
	
	@Test
	public void testCambiarEstadoAMismoEstado() {
		
	    Logica logica = new Logica();
	    Tarea tarea = new Tarea("Test");
	    
	    //se obtiene el estado inicial

	    Estado estadoOriginal = tarea.getEstado();
	    
	    //se cambia el estado actual al mismo, el estado original

	    logica.cambiarEstadoTarea(tarea, estadoOriginal);
	    
	    //se verifica que sea el estado original y el actual sean el mismo

	    assertEquals(estadoOriginal, tarea.getEstado());
	    
	}
	
	@Test
	public void testCambiarEstadoADiferenteEstado() {
		
	    Logica logica = new Logica();
	    Tarea tarea = new Tarea("Test");
	    
	    //se cambia el estado actual a uno diferente

	    logica.cambiarEstadoTarea(tarea, Estado.EN_PROCESO);
	    
	    //se verifica que el estado actual sea el mismo que el estado al que se cambio.

	    assertEquals(Estado.EN_PROCESO, tarea.getEstado());
	    
	}
	
		

}
