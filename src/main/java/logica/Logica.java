package logica;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import dominio.Estado;
import dominio.Tarea;

/**
 * Se encarga de gestionar la logica de las tareas,
 * 
 * Administra las tareas segun estado.
 * 
 * Permite guardar y cargar el estado del programa.
 * 
 */

public class Logica {
	
	//ArrayList de tareas de cada estado 

	private ArrayList<Tarea> tareasPorHacer = new ArrayList<>(); 
	private ArrayList<Tarea> tareasEnProceso = new ArrayList<>();
	private ArrayList<Tarea> tareasTerminado = new ArrayList<>(); 
	
	
	//contadores de tareas de cada estado
	
	private int contadorTareasPorHacer=0;
	private int contadorTareasEnProceso=0;
	private int contadorTareasTerminado=0; 
	
	
	/**
	 * Devuelve el array correspondiente a la Tarea segun su estado 
	 * 
	 * @param tarea la tarea cuyo array se desea obtener
	 * @return arrayDeLaTarea el arrayList tareasEnProceso si el Estado es EN_PROCESO , tareasTerminado si el Estado es TERMINADO, 
	 * tareasPorHacer si el Estado es POR_HACER, null si el estado no es uno de los 3 aceptados.  
	 */

	
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
	
	/**
	 * 
	 * Aumenta en uno el contador de tareas correspondiente al estado entregado. 
	 * 
	 * 
	 * @param estado estado que determina el contador que aumentara. 
	 */
	
	
	public void aumentarContadorDeTareas(Estado estado) {
		
		if(estado == Estado.POR_HACER) {
			
			contadorTareasPorHacer++;

		}
		
		else if(estado == Estado.EN_PROCESO) {
			
			contadorTareasEnProceso++;
			
		}
		
		else if(estado == Estado.TERMINADO) {
			
			contadorTareasTerminado++; 
			
		}
		
		
	}
	
	/**
	 * Disminuye en uno el contador de tareas correspondiente al estado entregado. 
	 * 
	 * 
	 * @param estado estado que determina el contador que disminuira. 
	 */
	
	
	public void disminuirContadorDeTareas(Estado estado) {
		
		if(estado == Estado.POR_HACER) {
			
			contadorTareasPorHacer--;
			
		}
		
		else if(estado == Estado.EN_PROCESO) {
			
			contadorTareasEnProceso--;
			
		}
		
		else if(estado == Estado.TERMINADO) {
			
			contadorTareasTerminado--; 
			
		}
		
		
	}
	
	
	/**
	 * Añade la tarea proporcionada a su array correspondiente segun su estado.
	 * 
	 * Aumenta en uno el contador de tareas correspondiente a la tarea segun su Estado.
	 * 
	 * @param tarea la tarea que se desea añadir
	 */
	
	public void aniadirTareaAlArray(Tarea tarea) {
		
		ArrayList<Tarea> ArrayDeLaTarea = obtenerArrayDeLaTarea(tarea); 
		
		ArrayDeLaTarea.add(tarea);
		
		aumentarContadorDeTareas(tarea.getEstado());
		
	}
	
	
	/**
	 * Elimina la tarea proporcionada del array en el que se encuentra segun su estado.
	 * 
	 * Disminuye en uno el contador de tareas correspondiente a la tarea segun su Estado.
	 * 
	 * @param tarea la tarea que se desea eliminar
	 */
	
	public void eliminarTareaDelArray(Tarea tarea) {
		
		ArrayList<Tarea> ArrayDeLaTarea = obtenerArrayDeLaTarea(tarea); 
		
		ArrayDeLaTarea.remove(tarea);
		
		Estado estadoTarea = tarea.getEstado();
		
		disminuirContadorDeTareas(estadoTarea);
		
		
	}
	
	
	/**
	 * Cambia el estado actual de la tarea a el estado nuevo entregado. 
	 * 
	 * @param tarea la tarea cuyo estado se desea cambiar.
	 * @param estadoNuevo el estado nuevo que se desea la tarea tenga. 
	 */
	
	public void cambiarEstadoTarea(Tarea tarea, Estado estadoNuevo) {
		
		if(tarea.getEstado()!=estadoNuevo) { //si el estado nuevo es distinto al actual
			
			tarea.setEstado(estadoNuevo);
			
		}
		
	}
	
	/**
	 * Mueve la tarea entregada del array actual al nuevo correspondiente al estado entregado. 
	 * 
	 * Disminuye en uno el contador de tareas correspondiente al estado antiguo y aumenta en uno el contador de tareas correspondiente al estado nuevo. 
	 * 
	 * @param tarea la tarea que se desea trasladar de array. 
	 * @param estadoNuevo el estado correspondiente al array al que se movera la tarea. 
	 */ 
	
	
	public void moverTarea(Tarea tarea , Estado estadoNuevo) {
		

		eliminarTareaDelArray(tarea); // elimina la tarea del array actual
		
		cambiarEstadoTarea(tarea,estadoNuevo); //cambia el estado de la tarea al nuevo
		
		aniadirTareaAlArray(tarea); //añade la tarea al array que le corresponde segun su nuevo estado
		
	}
	
	
	/**
	 * Guarda el estado actual del programa sobreescribiendo el estado guardado anterior.
	 * 
	 * se escriben tres archivos de texto, cada uno almacena textos de tareas de un tipo de estado diferente.
	 * 
	 * "tareasPorHacer.txt" : textos de tareas con estado POR_HACER
	 * 
	 * "tareasEnProceso.txt" : textos de tareas con estado EN_PROCESO
	 * 
	 * "tareasTerminado.txt" : textos de tareas con estado TERMINADO
	 * 
	 * 
	 * @throws IOException si al escribir los archivos ocurre un error
	 * 
	 */
	
	
	public void guardarEstado() {
		
		try {
			
			//se escribe el primer archivo
			
			FileWriter fw1 = new FileWriter("tareasPorHacer.txt");
			
			for(Tarea tarea : tareasPorHacer) {
						
				fw1.write(tarea.getTexto() + "\n");
				
			}
			
			fw1.close();
			
			
			//se escribe el segundo archivo
			
			FileWriter fw2 = new FileWriter("tareasEnProceso.txt");
			
			for(Tarea tarea : tareasEnProceso) {
						
				fw2.write(tarea.getTexto() + "\n");
				
			}
			
			fw2.close();
			
			
			//se escribe el tercer archivo
			
			FileWriter fw3 = new FileWriter("tareasTerminado.txt");
			
			for(Tarea tarea : tareasTerminado) {
						
				fw3.write(tarea.getTexto() + "\n");
				
			}
			
			fw3.close();
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
	
	
	/**
	 * Carga el ultimo estado guardado del programa sobreescribiendo el actual. 
	 * 
	 * Antes de cargar se reinician las listas y contadores. 
	 * 
	 * 
	 * se leen tres archivos de texto, cada uno almacena textos de tareas de un tipo de estado diferente.
	 * 
	 * "tareasPorHacer.txt" : textos de tareas con estado POR_HACER
	 * 
	 * "tareasEnProceso.txt" : textos de tareas con estado EN_PROCESO
	 * 
	 * "tareasTerminado.txt" : textos de tareas con estado TERMINADO
	 * 
	 * 
	 * @throws IOException si al escribir los archivos ocurre un error
	 * 
	 */
	
	
	public void cargarEstado() {
		
		tareasPorHacer.clear();  //se limpian los arrayList para evitar sobrecargar al cargar 2 veces seguidas. 
		tareasEnProceso.clear();
		tareasTerminado.clear(); 
		
		contadorTareasPorHacer = 0; //se reinician los contadores de tareas
		contadorTareasEnProceso = 0;
		contadorTareasTerminado= 0;
		
		
	
		
		//se lee el primer archivo
		
		try {
			FileReader fr = new FileReader("tareasPorHacer.txt");
			
			BufferedReader bf = new BufferedReader(fr); 

				
				String linea;
				
			while((linea= bf.readLine()) != null) {
					
				try{
				
				Tarea tarea = new Tarea(linea);	
				aniadirTareaAlArray(tarea);
				
				} catch (IllegalArgumentException ex) {
		            
		        }
					
					
			}
			
			bf.close(); //se cierra el archivo
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		//se lee el segundo archivo
		
		try {
			
			FileReader fr = new FileReader("tareasEnProceso.txt");
			
			BufferedReader bf = new BufferedReader(fr); 
			
			String linea;
			
			
			while((linea= bf.readLine()) != null) {
				
				try{
				
				Tarea tarea = new Tarea(linea);	
				tarea.setEstado(Estado.EN_PROCESO);
				aniadirTareaAlArray(tarea);
				
				} catch (IllegalArgumentException ex) {
		           
		        }
					
					
			}
			
			bf.close(); //se cierra el archivo
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//se lee el tercer archivo
		
		try {
			FileReader fr = new FileReader("tareasTerminado.txt");
			
			BufferedReader bf = new BufferedReader(fr); 
			
			String linea;
			
			while((linea= bf.readLine()) != null) {
				
				try{
				
				Tarea tarea = new Tarea(linea);	
				tarea.setEstado(Estado.TERMINADO);
				aniadirTareaAlArray(tarea);
				
				} catch (IllegalArgumentException ex) {
		           
		        }
					
	
			}
			
			bf.close(); //se cierra el archivo
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
	}
	

	/**
	 * Devuelve la cantidad de tareas con estado POR_HACER.
	 * 
	 * 
	 * @return contador de tareas POR_HACER.
	 */
	
	public int getContadorTareasPorHacer() {
		
		return contadorTareasPorHacer;
		
	}
	
	/**
	 * Devuelve la cantidad de tareas con estado EN_PROCESO.
	 *
	 * @return contador de tareas EN_PROCESO.
	 */
	
	public int getContadorTareasEnProceso() {
		
		return contadorTareasEnProceso;
		
	}
	
	/**
	 * Devuelve la cantidad de tareas con estado TERMINADO.
	 *
	 * @return contador de tareas TERMINADO.
	 */

	public int getContadorTareasTerminado() {
	
	return contadorTareasTerminado;
	
	}
	
	
	/**
	 * Devuelve el arrayList de tareas con estado POR_HACER
	 * 
	 * @return el arrayList con tareas de estado POR_HACER
	 */
	
	
	public ArrayList<Tarea> getTareasPorHacer(){
		
		return tareasPorHacer; 
		
	}
	
	/**
	 * Devuelve el arrayList de tareas con estado EN_PROCESO.
	 * 
	 * @return el arrayList con tareas de estado EN_PROCESO.
	 */
	
	public ArrayList<Tarea> getTareasEnProceso(){
		
		return tareasEnProceso; 
		
	}
	
	/**
	 * Devuelve el arrayList de tareas con estado TERMINADO.
	 * 
	 * @return el arrayList con tareas de estado TERMINADO.
	 */
	
	public ArrayList<Tarea> getTareasTerminado(){
		
		return tareasTerminado; 
		
	}
	

}
