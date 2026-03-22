package logicaTest;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dominio.Estado;
import dominio.Tarea;
import logica.Logica;



public class GuardarYCargarEstadoTest {
	
	@Test 
	public void testGuardarYCargarEstado() {
		
		
		Logica logicaEjecucion1 = new Logica();
		
		//tarea 1 , estado: POR_HACER
		
		Tarea tarea1Ejecucion1 = new Tarea("tarea1");
		
		//tarea2 , estado : EN_PROCESO
		
		Tarea tarea2Ejecucion1 = new Tarea("tarea2");
		
		logicaEjecucion1.cambiarEstadoTarea(tarea2Ejecucion1, Estado.EN_PROCESO);
		
		//tarea3 , estado : TERMINADO
		
		Tarea tarea3Ejecucion1 = new Tarea("tarea3");
		
		logicaEjecucion1.cambiarEstadoTarea(tarea3Ejecucion1, Estado.TERMINADO);
		
		
		
		//se añaden las tres tareas de diferente estado a sus respectivos arrays. 
		
		logicaEjecucion1.aniadirTareaAlArray(tarea1Ejecucion1);
		logicaEjecucion1.aniadirTareaAlArray(tarea2Ejecucion1);
		logicaEjecucion1.aniadirTareaAlArray(tarea3Ejecucion1);
		
		
		//se sobreescriben cada uno de los tres archivos de texto con los textos de las tareas de su array correspondiente.
		
		logicaEjecucion1.guardarEstado();
		
		
		
		//se crea una nueva instancia de Logica, simulando una nueva ejecucion del programa
		
		Logica logicaEjecucion2 = new Logica();
		
		//se leen los tres archivos de texto, se crean tareas usando el texto en los archivos y se añaden al array correspondiente.
		
		logicaEjecucion2.cargarEstado();
		
		
		//se obtienen las tareas cargadas en cada array en la ejecucion segunda del programa.
		
		Tarea tarea1Ejecucion2 = logicaEjecucion2.getTareasPorHacer().get(0);
		Tarea tarea2Ejecucion2 = logicaEjecucion2.getTareasEnProceso().get(0);
		Tarea tarea3Ejecucion2 = logicaEjecucion2.getTareasTerminado().get(0);
		
		//se verifica que los contadores de tareas tengan el numero esperado.
		
		assertEquals(1,logicaEjecucion2.getContadorTareasPorHacer());
		assertEquals(1,logicaEjecucion2.getContadorTareasEnProceso());
		assertEquals(1,logicaEjecucion2.getContadorTareasTerminado());
		
		
		
		//se verifica que las tareas cargadas en la ejecucion 2 tengan el mismo texto que las guardadas en la ejecucion 1
		
		assertEquals(tarea1Ejecucion1.getTexto(),tarea1Ejecucion2.getTexto());
		assertEquals(tarea2Ejecucion1.getTexto(),tarea2Ejecucion2.getTexto());
		assertEquals(tarea3Ejecucion1.getTexto(),tarea3Ejecucion2.getTexto());
		
		
		
	}

}
