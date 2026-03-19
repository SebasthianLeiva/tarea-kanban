package dominio;

/**
 * Representa una tarea del Kanban. 
 * 
 * contiene el atributo texto el cual almacena la informacion de la tarea.
 * contiene el atributo Enum estado. 
 * 
 */

public class Tarea {
	
	private String texto;
	private Estado estado; 
	
	/**
	 * 
	 * Inicializa los atributos texto y estado.
	 * 
	 * Inicializa el estado de la tarea como POR_HACER. 
	 * 
	 * @param texto la informacion de la tarea
	 */
	
	public Tarea(String texto) {
		
		this.texto= texto;
		this.estado = Estado.POR_HACER;
		
	}
	
	/**
	 * Devuelve el texto de la tarea.
	 * 
	 * @return el texto de la tarea
	 */


	public String getTexto() {
		return texto;
	}
	
	/**
	 * Establece el texto de la tarea.
	 * 
	 * @param texto el texto de la tarea
	 */

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	/**
	 * Devuelve el estado de la tarea.
	 * 
	 * @return estado el estado de la tarea
	 */

	public Estado getEstado() {
		return estado;
	}

	/**
	 * Establece el estado de la tarea
	 * 
	 * @param estado el estado de la tarea
	 */
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	/**
	 * Devuelve el texto de la tarea 
	 */
	
	@Override
	
	public String toString() {
		
		return texto;
		
	}
	
	

}
