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
	private static final int maxCaracteres = 37; // es el numero de caracteres que caben en las listas de tamaño actual sin scroll horizontal.  
	

	/**
	 * 
	 * crea una tarea con el texto del argumento y con el estado POR_HACER
	 * 
	 * Verifica que el texto no sea nulo y que no supere el maximo de caracteres permitido
	 * 
	 * @param texto la informacion de la tarea
	 * 
	 * @throws IllegalArgumentException si el texto es nulo o excede la cantidad de caracteres definida
	 */
	

	public Tarea(String texto) {
		
		if(texto == null || texto.length() > maxCaracteres) {
			
		       throw new IllegalArgumentException("el texto es nulo o su longitud es mayor a la permitida");
		       
		}
		
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
	 * Devuelve la cantidad maxima de caracteres definida para el texto de la tarea.
	 * @return la cantidad maxima de caracteres del texto
	 */
	
	public static int getMaxCaracteres() {
		
		return maxCaracteres;
		
	}
	
	/**
	 * Devuelve el texto de la tarea 
	 */
	
	@Override
	
	public String toString() {
	
		return texto;

	}
	
	

}
