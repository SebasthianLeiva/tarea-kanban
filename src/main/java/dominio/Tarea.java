package dominio;

public class Tarea {
	
	private String titulo;
	private String texto;
	private Estado estado; 
	
	public Tarea(String titulo, String texto) {
		
		this.titulo = titulo;
		this.texto= texto;
		this.estado = Estado.POR_HACER;
		
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
	@Override
	
	public String toString() {
		
		return titulo; 
		
	}
	
	

}
