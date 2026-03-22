package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import dominio.Estado;
import dominio.Tarea;
import logica.Logica; 
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Ventana de la aplicacion encargada de mostrar las tareas y su gestion. 
 * 
 */

public class Pantalla extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private Logica logica; 
	private CardLayout layout = new CardLayout(0, 0);
	
	//paneles
	
	private JPanel contentPane;
	private JPanel crearTarea;
	private JPanel tareas;
	private JPanel panelTextField;
	
	private JPanel panelListas;
	
	
	//Items
	
	private JMenuItem itemCrearTarea; 
	private JMenuItem itemTareas;
	private JMenuItem itemGuardarEstado; 
	private JMenuItem itemCargarEstado; 
	
	//modelos de listas
	
	private DefaultListModel<Tarea> modeloListPorHacer;
	private DefaultListModel<Tarea> modeloListEnProceso;
	private DefaultListModel<Tarea> modeloListTerminado;
	
	//listas
	
	private JList<Tarea> listPorHacer; 
	private JList<Tarea> listEnProceso;
	private JList<Tarea> listTerminado; 
	
	//textFields
	
	private JTextField textFieldTarea;
	private JTextField textFieldVerificador;
	
	private JTextField textFieldNumeroPorHacer;
	private JTextField textFieldNumeroEnProceso;
	private JTextField textFieldNumeroTerminado;
	
	//boton crear y eliminar tarea
	
	private JButton btnCrearTarea;
	private JButton btnEliminarTarea; 
	
	//botones mover
	
	private JButton btnMoverATerminado; 
	private JButton btnMoverAEnProceso;
	private JButton btnMoverAPorHacer; 
	
	//scroll pane
	
	private JScrollPane scrollListPorHacer; 
	private JScrollPane scrollListEnProceso;
	private JScrollPane scrollListTerminado;
	

	/**
	 * Crea y configura el Jframe que muestra los datos en pantalla.
	 * 
	 * Configura todas las componentes necesarias para la interaccion y visibilidad del programa
	 * 
	 * @param logica la referencia a la instancia de Logica utilizada para obtener los metodos de gestion de tareas. 
	 */
	
	public Pantalla(Logica logica) {
		
		this.logica = logica; // se asigna la logica inyectada por el constructor a la referencia de la clase Logica
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(layout); //setea el layout al contentPane
		
		agregarPaneles(); //se añaden los 2 paneles principales usados en el cardLayout
		
		agregarJMenuBar(); //se añade la JMenuBar al jframe
		
		agregarComponentesPanelCrearTarea(); //se añaden los componentes del panel crearTarea
		
		agregarComponentesPanelTareas(); //se añaden los componentes del panel tareas
		
		//botones mover 
		
		
		agregarBotonesMover(); //se añaden los botones mover
		
		
		//action listeners de botones
		
		
		btnCrearTarea.addActionListener(e->{
			
			
			crearTarea(textFieldTarea.getText()); // se crea la tarea con el texto de "textFieldTarea"
				
			
		});
		
		btnEliminarTarea.addActionListener(e->{
			
			
			eliminarTarea(); 
			
			
		});
		
		
		btnMoverAPorHacer.addActionListener(e->{
			
			moverTareaDeLista(Estado.POR_HACER);
					
		});
		
		
		
		btnMoverAEnProceso.addActionListener(e->{
			
			moverTareaDeLista(Estado.EN_PROCESO);
			
		});
		
		
		
		btnMoverATerminado.addActionListener(e->{
			
			moverTareaDeLista(Estado.TERMINADO);
			
			
			
		});
		
		
		//action listeners de items
		
		itemCrearTarea.addActionListener(e->{ //al clickear el item aparece en pantalla el panel "Crear Tarea"
			
			layout.show(contentPane, "crearTarea"); 
			
		});
		
		itemTareas.addActionListener(e->{
			
			layout.show(contentPane, "tareas" ); //al clickear el item aparece en pantalla el panel "Tareas" 
			
		});
		
		
		itemGuardarEstado.addActionListener(e->{
			
			botonGuardarEstado(); 
			
		});
		
		
		itemCargarEstado.addActionListener(e->{
			
			botonCargarEstado();
			
		});
		
		
		//configuracion del JFrame
		
		this.setTitle("Kanban Board");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	
	}
	
	
	/**
	 * Crea,configura y añade los componentes necesarios para el funcionamiento de la barra de menu.
	 * 
	 */

	
	private void agregarJMenuBar() {
		
		var barra = new JMenuBar(); //se crea la instancia de la JMenuBar
		
		setJMenuBar(barra); //setea la barra al frame
		
		var menu = new JMenu("Menu"); //se crea y añade el primer jmenu a la barra
		barra.add(menu);
		
		var estado = new JMenu("Estado"); //se crea y añade el segundo jmenu a la barra
		barra.add(estado);
		
		itemCrearTarea = new JMenuItem("Crear Tarea"); //se crea y añade el primer item a menu
		menu.add(itemCrearTarea); 
		
		itemTareas = new JMenuItem("Tareas"); //se crea y añade el segundo item a menu
		menu.add(itemTareas);
		
		itemGuardarEstado = new JMenuItem("Guardar estado"); //se crea y añade el tercer item a estado
		estado.add(itemGuardarEstado);
		
		itemCargarEstado = new JMenuItem("Cargar estado"); //se crea y añade el primer item a estado
		estado.add(itemCargarEstado); 
		
		
	}
	
	/**
	 * Crea y añade los botones para mover las tareas de lista.
	 */
	
	private void agregarBotonesMover() {
		
		btnMoverATerminado = new JButton("Mover");
		btnMoverATerminado.setBounds(587, 416, 267, 33);
		panelListas.add(btnMoverATerminado);
		
		btnMoverAEnProceso = new JButton("Mover");
		btnMoverAEnProceso.setBounds(298, 416, 267, 33);
		panelListas.add(btnMoverAEnProceso);
		
		btnMoverAPorHacer = new JButton("Mover");
		btnMoverAPorHacer.setBounds(10, 416, 267, 33);
		panelListas.add(btnMoverAPorHacer);
		
		
	}
	
	/**
	 * Crea,configura y añade los paneles principales "crearTarea" y "tareas". 
	 */
	
	private void agregarPaneles() {
		
		//crear tarea
		
		crearTarea = new JPanel();
		crearTarea.setBackground(new Color(128, 128, 128));
		contentPane.add(crearTarea, "crearTarea");
		crearTarea.setLayout(null);
		
		//tareas
		
		tareas = new JPanel();
		tareas.setBackground(new Color(128, 128, 128));
		contentPane.add(tareas, "tareas");
		tareas.setLayout(null);
		
		
	}
	
	/**
	 * Crea,configura y agrega los paneles,textField's , botones y  labels ubicados en el panel "crearTarea".
	 * 
	 */
	
	private void agregarComponentesPanelCrearTarea() {
		
		//paneles

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 248, 159));
		panel.setBounds(77, 103, 706, 458);
		crearTarea.add(panel);
		panel.setLayout(null);
		
		JPanel panelTituloCrearTarea = new JPanel();
		panelTituloCrearTarea.setBounds(85, 45, 541, 84);
		panel.add(panelTituloCrearTarea);
		panelTituloCrearTarea.setLayout(null);
		panelTituloCrearTarea.setBackground(new Color(111, 180, 255));
		
		//textField's
		
		panelTextField = new JPanel();
		panelTextField.setBackground(new Color(255, 111, 111));
		panelTextField.setBounds(85, 140, 541, 219);
		panel.add(panelTextField);
		panelTextField.setLayout(null);
		
		textFieldTarea = new JTextField();
		textFieldTarea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldTarea.setBounds(118, 75, 342, 30);
		panelTextField.add(textFieldTarea);
		textFieldTarea.setColumns(10);
		
		//labels
		
		JLabel lblCrearTarea_1 = new JLabel("Crear Tarea");
		lblCrearTarea_1.setBounds(10, 11, 521, 62);
		panelTituloCrearTarea.add(lblCrearTarea_1);
		lblCrearTarea_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrearTarea_1.setForeground(Color.WHITE);
		lblCrearTarea_1.setFont(new Font("Tahoma", Font.PLAIN, 48));
		
		JLabel lblTarea = new JLabel("Tarea:");
		lblTarea.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTarea.setForeground(new Color(255, 255, 255));
		lblTarea.setBounds(35, 75, 77, 25);
		panelTextField.add(lblTarea);
		
		//boton crear tarea
		
		btnCrearTarea = new JButton("Crear");
		btnCrearTarea.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCrearTarea.setBounds(211, 137, 119, 40);
		panelTextField.add(btnCrearTarea);
		
		JPanel panelTextFieldVerificador = new JPanel();
		panelTextFieldVerificador.setBounds(180, 384, 353, 47);
		panel.add(panelTextFieldVerificador);
		panelTextFieldVerificador.setBackground(new Color(255, 128, 128));
		panelTextFieldVerificador.setLayout(null);
		
		textFieldVerificador = new JTextField();
		textFieldVerificador.setBounds(10, 11, 332, 26);
		panelTextFieldVerificador.add(textFieldVerificador);
		textFieldVerificador.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldVerificador.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldVerificador.setEditable(false);
		textFieldVerificador.setColumns(10);
		
		
	}
	
	/**
	 * Crea,configura y agrega los paneles,textField's, botones , listas y labels ubicados en el panel "tareas".
	 * 
	 */
	
	private void agregarComponentesPanelTareas() {
		
		//paneles
		
		panelListas = new JPanel();
		panelListas.setBackground(new Color(128, 128, 128));
		panelListas.setBounds(10, 166, 854, 452);
		tareas.add(panelListas);
		panelListas.setLayout(null);
		
		JPanel panelPorHacer = new JPanel();
		panelPorHacer.setBackground(new Color(255, 111, 111));
		panelPorHacer.setBounds(20, 107, 267, 48);
		tareas.add(panelPorHacer);
		panelPorHacer.setLayout(null);
		
		JPanel panelEnProceso = new JPanel();
		panelEnProceso.setBackground(new Color(111, 180, 255));
		panelEnProceso.setBounds(308, 107, 267, 48);
		tareas.add(panelEnProceso);
		panelEnProceso.setLayout(null);
		
		JPanel panelTerminado = new JPanel();
		panelTerminado.setBackground(new Color(0, 242, 146));
		panelTerminado.setBounds(597, 107, 267, 48);
		tareas.add(panelTerminado);
		panelTerminado.setLayout(null);
		
		JPanel panelTituloTareas = new JPanel();
		panelTituloTareas.setBackground(new Color(111, 180, 255));
		panelTituloTareas.setBounds(20, 11, 393, 73);
		tareas.add(panelTituloTareas);
		panelTituloTareas.setLayout(null);
		
		//instanciacion de modelos de listas
		
		modeloListPorHacer= new DefaultListModel();     
		modeloListEnProceso = new DefaultListModel();
		modeloListTerminado = new DefaultListModel(); 
		
		//listas
		
		listPorHacer = new JList(modeloListPorHacer); 
		listPorHacer.setBounds(10, 11, 267, 392);
		
		scrollListPorHacer = new JScrollPane(listPorHacer);  //se añade scroll a listPorHacer
		scrollListPorHacer.setBounds(10, 11, 267, 392);
		panelListas.add(scrollListPorHacer);
		
		
		listEnProceso = new JList(modeloListEnProceso);
		listEnProceso.setBounds(298, 11, 267, 392);
		
		scrollListEnProceso = new JScrollPane(listEnProceso); //se añade scroll a listEnProceso
		scrollListEnProceso.setBounds(298, 11, 267, 392);
		panelListas.add(scrollListEnProceso);
		
		listTerminado = new JList(modeloListTerminado);
		listTerminado.setBounds(587, 11, 267, 392);
		
		scrollListTerminado = new JScrollPane(listTerminado); //se añade scroll a listT
		scrollListTerminado.setBounds(587, 11, 267, 392);
		panelListas.add(scrollListTerminado);
		
		
		
		//textFields
		
		textFieldNumeroPorHacer = new JTextField();
		textFieldNumeroPorHacer.setEditable(false);
		textFieldNumeroPorHacer.setText("0");
		textFieldNumeroPorHacer.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldNumeroPorHacer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldNumeroPorHacer.setBounds(211, 11, 34, 27);
		panelPorHacer.add(textFieldNumeroPorHacer);
		textFieldNumeroPorHacer.setColumns(10);
		
	
		textFieldNumeroEnProceso = new JTextField();
		textFieldNumeroEnProceso.setEditable(false);
		textFieldNumeroEnProceso.setText("0");
		textFieldNumeroEnProceso.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldNumeroEnProceso.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldNumeroEnProceso.setColumns(10);
		textFieldNumeroEnProceso.setBounds(211, 10, 34, 27);
		panelEnProceso.add(textFieldNumeroEnProceso);
		
		
		textFieldNumeroTerminado = new JTextField();
		textFieldNumeroTerminado.setEditable(false);
		textFieldNumeroTerminado.setText("0");
		textFieldNumeroTerminado.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldNumeroTerminado.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldNumeroTerminado.setColumns(10);
		textFieldNumeroTerminado.setBounds(212, 11, 34, 27);
		panelTerminado.add(textFieldNumeroTerminado);
		
		//label's
		
		var lblPorHacer = new JLabel("Por Hacer:");
		lblPorHacer.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPorHacer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPorHacer.setForeground(new Color(255, 255, 255));
		lblPorHacer.setBounds(10, 11, 179, 26);
		panelPorHacer.add(lblPorHacer);
				
		var lblNumeroEnProceso = new JLabel("En Proceso:");
		lblNumeroEnProceso.setBounds(10, 11, 177, 25);
		lblNumeroEnProceso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumeroEnProceso.setForeground(Color.WHITE);
		lblNumeroEnProceso.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelEnProceso.add(lblNumeroEnProceso);
				
		var lblNumeroTerminado = new JLabel("Terminado:");
		lblNumeroTerminado.setBounds(10, 11, 180, 25);
		lblNumeroTerminado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumeroTerminado.setForeground(Color.WHITE);
		lblNumeroTerminado.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelTerminado.add(lblNumeroTerminado);
				
		JLabel lblTituloTareas = new JLabel("Kanban Board");
		lblTituloTareas.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloTareas.setFont(new Font("Tahoma", Font.PLAIN, 48));
		lblTituloTareas.setForeground(new Color(255, 255, 255));
		lblTituloTareas.setBounds(10, 11, 373, 51);
		panelTituloTareas.add(lblTituloTareas);
		
	
		
		//boton eliminar tarea
		
		btnEliminarTarea = new JButton("Eliminar Tarea Seleccionada");
		btnEliminarTarea.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEliminarTarea.setBounds(498, 29, 298, 37);
		tareas.add(btnEliminarTarea);
		
		
	
		
	}
	
	/**
	 * Regresa la tareaSeleccionada en la ultima lista seleccionada.
	 * 
	 * @return la tarea seleccionada en una jlist.
	 */
	
	public Tarea obtenerTareaSeleccionada() {
		
		Tarea tareaSeleccionada=null; 
		
		if(listPorHacer.getSelectedValue()!=null) {
			
			tareaSeleccionada = (Tarea) listPorHacer.getSelectedValue();
		}
		
		else if(listEnProceso.getSelectedValue()!=null) {
			
			tareaSeleccionada = (Tarea) listEnProceso.getSelectedValue();
			
		}
		
		else if(listTerminado.getSelectedValue()!=null) {
			
			tareaSeleccionada = (Tarea) listTerminado.getSelectedValue();
			
		}
		
		return tareaSeleccionada; 
		
	}
	
	
	
	
	/**
	 * Actualiza el contador de la gui con el numero actual de tareas del estado POR_HACER.
	 * 
	 */
	
	public void actualizarContadorTareasPorHacer() {
		
		String numeroTareasPorHacer = String.valueOf(logica.getContadorTareasPorHacer());
		textFieldNumeroPorHacer.setText(numeroTareasPorHacer);
		
	}
	
	/**
	 * Actualiza el contador de la gui con el numero actual de tareas del estado EN_PROCESO.
	 * 
	 */
	
	public void actualizarContadorTareasEnProceso() {
		
		String numeroTareasEnProceso = String.valueOf(logica.getContadorTareasEnProceso());
		textFieldNumeroEnProceso.setText(numeroTareasEnProceso);
		
	}
	
	/**
	 * Actualiza el contador de la gui con el numero actual de tareas del estado TERMINADO.
	 * 
	 */
	
	public void actualizarContadorTareasTerminado() {
		
		String numeroTareasTerminado = String.valueOf(logica.getContadorTareasTerminado());
		textFieldNumeroTerminado.setText(numeroTareasTerminado);
		
		
	}
	
	/**
	 * Actualiza el contador de la gui con el numero actual de tareas del estado entregado. 
	 * 
	 * @param estado el estado correspondiente al contador que se desea actualizar. 
	 */
	
	public void actualizarContadorTareas(Estado estado){
		
		
		if(estado == Estado.POR_HACER) {
			
			 actualizarContadorTareasPorHacer();
			
		}
		
		else if(estado == Estado.EN_PROCESO) {
			
			actualizarContadorTareasEnProceso();
			
		}
		
		else if(estado == Estado.TERMINADO) {
			
			actualizarContadorTareasTerminado(); 
			
		}
			
	}
	
	/**
	 * Devuelve el modelo de la lista correspondiente al estado de la tarea entregada. 
	 * 
	 * @param tarea la tarea cuyo modelo de lista correspondiente se desea obtener.
	 * @return el modelo de lista correspondiente a la tarea. 
	 */
	
	public DefaultListModel obtenerListModelTarea(Tarea tarea) {
		
		DefaultListModel modeloLista = null;
		
		if(tarea.getEstado()==Estado.POR_HACER) {
			
			 modeloLista = modeloListPorHacer;
			
		}
		
		else if(tarea.getEstado() == Estado.EN_PROCESO) {
			
			modeloLista = modeloListEnProceso; 
			
		}
		
		else if(tarea.getEstado()== Estado.TERMINADO) {
			
			modeloLista = modeloListTerminado; 
			
		}
		
		return modeloLista;
		
		
	}
	
	/**
	 * Mueve la tarea seleccionada a lista correspondiente al estado entregado, si es que el estado no es igual al de la tarea.
	 * 
	 * Cambia el estado de la tarea al entregado.
	 * 
	 * Actualiza el contador de tareas de la lista de la que se movio la tarea. 
	 * 
	 * Actualiza el contador de tareas de la lista a la que se movio la tarea.
	 * 
	 * @param estadoNuevo el estado correspondiente a la lista a la que se desea mover la tarea.
	 */
	
	public void moverTareaDeLista(Estado estadoNuevo) {
		
		Tarea tareaSeleccionada = obtenerTareaSeleccionada(); //se obtiene la tarea
		
		if(tareaSeleccionada!=null) {
			
			
			Estado estadoOriginal = tareaSeleccionada.getEstado(); //se obtiene el estado original
			
			
			//se elimina la tarea de la lista original
			
			DefaultListModel listModelTareaOriginal = obtenerListModelTarea(tareaSeleccionada); 
			
			listModelTareaOriginal.removeElement(tareaSeleccionada); 
			
			//se mueve la tarea de estado y array
			
			logica.moverTarea(tareaSeleccionada, estadoNuevo); //se mueve la tarea
				
			
			//se agrega la tarea a la lista nueva
			
			DefaultListModel listModelTareaNuevo= obtenerListModelTarea(tareaSeleccionada); 
			
			listModelTareaNuevo.addElement(tareaSeleccionada);			
			
			
			actualizarContadorTareas(estadoOriginal); //se cambia el contador de tareas del estado original
			actualizarContadorTareas(estadoNuevo);  //se cambia el contador de tareas del estado nuevo
		
		
			
		}
		
	}
		
	/**
	 * Carga las tareas existentes a sus respectivas listas.
	 * 
	 * Actualiza todos los contadores de tareas.
	 * 
	 * 
	 */
	
	public void cargarTareas() {
		
		
		for(Tarea tarea: logica.getTareasPorHacer()) {
			
			modeloListPorHacer.addElement(tarea);
			
		}
		
		for(Tarea tarea: logica.getTareasEnProceso()) {
			
			modeloListEnProceso.addElement(tarea);
			
		}
		
		for(Tarea tarea: logica.getTareasTerminado()) {
			
			modeloListTerminado.addElement(tarea);
			
		}
		
		actualizarContadorTareasPorHacer();
		actualizarContadorTareasEnProceso();
		actualizarContadorTareasTerminado(); 
		
	}
	
	/**
	 * Crea una tarea con el texto entregado con estado POR_HACER.
	 * 
	 * Actualiza el contador de tareas POR_HACER
	 * 
	 * Entrega un mensaje para identificar cuando se realizo la operacion con exito
	 * 
	 * @param texto el texto de la tarea a crear
	 * 
	 */
	
	public void crearTarea(String texto) {
		
	try {
		
		Tarea tarea = new Tarea(texto);
				
		logica.aniadirTareaAlArray(tarea); 
				
		modeloListPorHacer.addElement(tarea);
				
		actualizarContadorTareas(Estado.POR_HACER);
				
		textFieldTarea.setText(null); //se limpia el textField
		textFieldVerificador.setText("Tarea creada con exito"); 
		
			
		} catch (IllegalArgumentException ex) {
				
		textFieldVerificador.setText("Introduzca un texto de longitud menor o igual a " + String.valueOf(Tarea.getMaxCaracteres()));
		           
		}
		
	}
	
	/**
	 * Elimina la tarea seleccionada de la ultima lista seleccionada.
	 * 
	 * Actualiza el contador de tareas de la lista seleccionada. 
	 * 
	 */
	
	
	public void eliminarTarea() {
		
		Tarea tareaSeleccionada = obtenerTareaSeleccionada();
		
		if(tareaSeleccionada!=null) {
			
			logica.eliminarTareaDelArray(tareaSeleccionada); //se elimina la tarea  del array
			
			DefaultListModel modeloListTarea = obtenerListModelTarea(tareaSeleccionada);  //se obtiene la lista en la que estaba la tarea
			
			modeloListTarea.removeElement(tareaSeleccionada); //se elimina la tarea de la lista en la que estaba y baja en uno el contador
			
			Estado estadoTarea = tareaSeleccionada.getEstado(); //se obtiene el estado que tenia
			
			actualizarContadorTareas(estadoTarea); //reescribe el contador de tareas con la cantidad actual
			
		}
		
	}
	
	/**
	 * Despliega un JOptionPane con las opciones YES y NO, en caso de seleccionar YES guarda el estado.
	 * 
	 */
	
	public void botonGuardarEstado() {
		
		int opcion = JOptionPane.showConfirmDialog(null,"¿Quieres guardar el estado actual?","Se sobreescribira el guardado anterior",
				JOptionPane.YES_NO_OPTION);

		if(opcion == JOptionPane.YES_OPTION){    //confirmacion de guardar estado
			
			logica.guardarEstado();
			
		} 
		
	}
	
	/**
	 *Despliega un JOptionPane con las opciones YES y NO, en caso de seleccionar YES carga el estado.
	 * 
	 */
	
	public void botonCargarEstado() {
		
		int opcion = JOptionPane.showConfirmDialog(null,"¿Quieres cargar el estado?","Se reemplazara el estado actual",JOptionPane.YES_NO_OPTION);

		if(opcion == JOptionPane.YES_OPTION){    //confirmacion de guardar estado
			
			modeloListPorHacer.clear(); //se limpian las listas antes de cargar el estado para no arrastrar las tareas viejas al nuevo estado. 
			modeloListEnProceso.clear();
			modeloListTerminado.clear(); 
			
			logica.cargarEstado(); 
			cargarTareas(); 
			
			
		} 
		
	}
}
