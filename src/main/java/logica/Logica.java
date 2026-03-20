package logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import dominio.Tarea;
import dominio.Estado;


public class Logica {
	
	

	
	private ArrayList<Tarea> tareasPorHacer = new ArrayList<>(); 
	private ArrayList<Tarea> tareasEnProceso = new ArrayList<>();
	private ArrayList<Tarea> tareasTerminado = new ArrayList<>(); 
	
	private int contadorTareasPorHacer=0;
	private int contadorTareasEnProceso=0;
	private int contadorTareasTerminado=0; 
	
	
	
	
	public Logica() {
		
		
		
	}
	
	//verificador de la longitud del texto del string
	
	public boolean longitudDelTextoPermitida(String textoTarea) {
		
		if(textoTarea.length()<=50) {
			
			return true;
		}
		
		else{
			
			return false; 
			
		}
		
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
	
	
	
	
	public Estado aniadirTareaAlArray(String texto) {
		
		Tarea tarea = new Tarea(texto);
		
		ArrayList<Tarea> ArrayDeLaTarea = obtenerArrayDeLaTarea(tarea); 
		
		ArrayDeLaTarea.add(tarea);
		
		Estado estadoTarea = tarea.getEstado();
		
		aumentarContadorDeTareas(estadoTarea);
		
		return estadoTarea;
		
		
	}
	
	
	public Estado eliminarTareaDelArray(Tarea tarea) {
		
		ArrayList<Tarea> ArrayDeLaTarea = obtenerArrayDeLaTarea(tarea); 
		
		ArrayDeLaTarea.remove(tarea);
		
		Estado estadoTarea = tarea.getEstado();
		
		disminuirContadorDeTareas(estadoTarea);
		
		return estadoTarea; 
		
	}
	
	
	//testear el metodo
	
	public void cambiarEstadoTarea(Tarea tarea, Estado estadoNuevo) {
		
		if(tarea.getEstado()!=estadoNuevo) { //si el estado nuevo es distinto al actual
			
			tarea.setEstado(estadoNuevo);
			
		}
		
	}
	
	
	public Estado moverTarea(Tarea tarea , Estado estadoNuevo) {
		
		Estado estadoAntiguo = tarea.getEstado(); //obtenemos el estado original de la tarea (se usa get sobre la tarea para obtenerlo en lugar de
												// guardar el valor de eliminarTareaDelArray para mayor claridad. 
		
		eliminarTareaDelArray(tarea); // elimina la tarea del array actual 
		
		cambiarEstadoTarea(tarea,estadoNuevo); //cambia el estado de la tarea al nuevo
		
		aniadirTareaAlArray(tarea.getTexto()); //añade la tarea al array que le corresponde segun su nuevo estado
		
		return estadoAntiguo;
		
	}
	
	
	
	
	
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
	
	
	
	
	
	public void cargarEstado() {
		
		tareasPorHacer.clear();  //se limpian los arrayList para evitar sobrecargar al cargar 2 veces seguidas. 
		tareasEnProceso.clear();
		tareasTerminado.clear(); 
		
	
		
		//se lee el primer archivo
		
		try {
			FileReader fr = new FileReader("tareasPorHacer.txt");
			
			BufferedReader bf = new BufferedReader(fr); 

				
				String linea;
				
			while((linea= bf.readLine()) != null) {
					
				Tarea tarea = new Tarea(linea);
					
				tareasPorHacer.add(tarea);
					
					
			}
			
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
					
			Tarea tarea = new Tarea(linea);
				
			tareasEnProceso.add(tarea);
					
					
			}
			
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
					
					Tarea tarea = new Tarea(linea);
					
					tareasTerminado.add(tarea);
					
					
				}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
	}
	
	
	//getters de los contadores de tareas
	
	public int getContadorTareasPorHacer() {
		
		return contadorTareasPorHacer;
		
	}
	
	public int getContadorTareasEnProceso() {
		
		return contadorTareasEnProceso;
		
	}

	public int getContadorTareasTerminado() {
	
	return contadorTareasTerminado;
	
}
	


}
