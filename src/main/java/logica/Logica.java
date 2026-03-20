package logica;

import java.util.ArrayList;

import dominio.Estado;
import dominio.Tarea;

/**
 * Almacena las tareas en un arrayList 
 */

public class Logica {
	
	private ArrayList<Tarea> tareasPorHacer = new ArrayList<>(); 
	private ArrayList<Tarea> tareasEnProceso = new ArrayList<>();
	private ArrayList<Tarea> tareasTerminado = new ArrayList<>(); 
	
	
	
	
	public Logica() {
		
		
	}
	
	
	public ArrayList<Tarea> obtenerArrayDeLaTarea(Tarea tarea) {
		
		ArrayList<Tarea> arrayDeLaTarea = null; 
		
		if(tarea.getEstado()==Estado.EN_PROCESO) {
			
			arrayDeLaTarea = tareasEnProceso;
		}
		
		else if (tarea.getEstado()== Estado.TERMINADO) {
			
			arrayDeLaTarea = tareasTerminado; 
			
		}
		
		else if(tarea.getEstado()== Estado.POR_HACER){
			
			arrayDeLaTarea = tareasPorHacer; 
			
		}
		
		return arrayDeLaTarea; 
		
	}
	
	
	
	public void aniadirTareaAlArray(Tarea tarea) {
		
		ArrayList<Tarea> ArrayDeLaTarea = obtenerArrayDeLaTarea(tarea); 
		
		ArrayDeLaTarea.add(tarea);
		
	}
	
	
	public void eliminarTareaDelArray(Tarea tarea) {
		
		ArrayList<Tarea> ArrayDeLaTarea = obtenerArrayDeLaTarea(tarea); 
		
		ArrayDeLaTarea.remove(tarea);
		
	}
	
	
	//testear el metodo
	
	public void cambiarEstadoTarea(Tarea tarea, Estado estadoNuevo) {
		
		if(tarea.getEstado()!=estadoNuevo) { //si el estado nuevo es distinto al actual
			
			tarea.setEstado(estadoNuevo);
			
		}
		
	}
	
	
	public void moverTarea(Tarea tarea , Estado estadoNuevo) {
		
		eliminarTareaDelArray(tarea); // elimina la tarea del array actual
		
		cambiarEstadoTarea(tarea,estadoNuevo); //cambia el estado de la tarea al nuevo
		
		aniadirTareaAlArray(tarea); //añade la tarea al array que le corresponde segun su nuevo estado
		
	}
	
	
	
	
	
	
	
	

}
