package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	
	
	//JTextField's y Items
	
	private JMenuItem item1; //Crear tarea
	private JMenuItem item2; //tareas
	private JMenuItem item3; //guardar estado
	private JMenuItem item4; //cargar estado
	
	//modelos de listas
	
	private DefaultListModel modeloListPorHacer;
	private DefaultListModel modeloListEnProceso;
	private DefaultListModel modeloListTerminado;
	
	//listas y textFields
	
	private JList listPorHacer; 
	private JList listEnProceso;
	private JList listTerminado; 
	
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
	
	//
	

	
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
		
		//botones mover //////////////////7
		
		
		agregarBotonesMover(); //se añaden los botones mover
		
		
		//action listeners
		
		
		btnCrearTarea.addActionListener(e->{
			
			Tarea tarea = logica.aniadirTareaAlArray(textFieldTarea.getText()); //se añade la tarea 
			
			modeloListPorHacer.addElement(tarea);
			
			cambiarCantidadTareas(Estado.POR_HACER);
			
			
			
			
		});
		
		btnEliminarTarea.addActionListener(e->{
			
			
			Tarea tareaSeleccionada = obtenerTareaSeleccionada();
			
			if(tareaSeleccionada!=null) {
				
				Tarea tarea = logica.eliminarTareaDelArray(tareaSeleccionada); //se elimina la tarea  del array
				
				DefaultListModel modeloListTarea = obtenerListModelTarea(tarea);  //se obtiene la lista en la que estaba la tarea
				
				modeloListTarea.removeElement(tarea); //se elimina la tarea de la lista en la que estaba
				
				Estado estadoTarea = tarea.getEstado(); //se obtiene el estado que tenia
				
				cambiarCantidadTareas(estadoTarea); //se cambia la cantidad de tareas
				
			}
			
			
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
		
		////////////////////////
		
		item1.addActionListener(e->{ //al clickear el item aparece en pantalla el panel "Crear Tarea"
			
			layout.show(contentPane, "crearTarea"); 
			
		});
		
		item2.addActionListener(e->{
			
			layout.show(contentPane, "tareas" ); //al clickear el item aparece en pantalla el panel "Tareas" 
			
		});
		
		//configuracion del JFrame
		
		this.setTitle("Kanban Board");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	
	}
	

	
	private void agregarJMenuBar() {
		
		var barra = new JMenuBar(); //se crea la instancia de la JMenuBar
		
		setJMenuBar(barra); //setea la barra al frame
		
		var menu = new JMenu("Menu"); //se crea y añade el primer jmenu a la barra
		barra.add(menu);
		
		var estado = new JMenu("Estado"); //se crea y añade el segundo jmenu a la barra
		barra.add(estado);
		
		item1 = new JMenuItem("Crear Tarea"); //se crea y añade el primer item a menu
		menu.add(item1); 
		
		item2 = new JMenuItem("Tareas"); //se crea y añade el segundo item a menu
		menu.add(item2);
		
		item3 = new JMenuItem("Guardar estado"); //se crea y añade el tercer item a estado
		estado.add(item3);
		
		item4 = new JMenuItem("Cargar estado"); //se crea y añade el primer item a estado
		estado.add(item4); 
		
		
	}
	
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
	
	private void agregarComponentesPanelCrearTarea() {
		
		//paneles

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 248, 159));
		panel.setBounds(80, 135, 706, 399);
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
		textFieldTarea.setBounds(109, 50, 422, 20);
		panelTextField.add(textFieldTarea);
		textFieldTarea.setColumns(10);
		
		textFieldVerificador = new JTextField();
		textFieldVerificador.setBounds(109, 149, 422, 20);
		panelTextField.add(textFieldVerificador);
		textFieldVerificador.setEditable(false);
		textFieldVerificador.setColumns(10);
		
		//labels
		
		JLabel lblCrearTarea_1 = new JLabel("Crear Tarea");
		lblCrearTarea_1.setBounds(10, 11, 521, 62);
		panelTituloCrearTarea.add(lblCrearTarea_1);
		lblCrearTarea_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrearTarea_1.setForeground(Color.WHITE);
		lblCrearTarea_1.setFont(new Font("Tahoma", Font.PLAIN, 48));
		
		JLabel lblTarea = new JLabel("Tarea:");
		lblTarea.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTarea.setForeground(new Color(255, 255, 255));
		lblTarea.setBounds(27, 51, 54, 14);
		panelTextField.add(lblTarea);
		
		JLabel lblVerificador = new JLabel("Verificador:");
		lblVerificador.setForeground(Color.WHITE);
		lblVerificador.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblVerificador.setBounds(10, 150, 89, 14);
		panelTextField.add(lblVerificador);
		
		
		
	}
	
	
	
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
		panelListas.add(listPorHacer);
		
		listEnProceso = new JList(modeloListEnProceso);
		listEnProceso.setBounds(298, 11, 267, 392);
		panelListas.add(listEnProceso);
		
		listTerminado = new JList(modeloListTerminado);
		listTerminado.setBounds(587, 11, 267, 392);
		panelListas.add(listTerminado);
		
		
		
		//textFields
		
		textFieldNumeroPorHacer = new JTextField();
		textFieldNumeroPorHacer.setText("0");
		textFieldNumeroPorHacer.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldNumeroPorHacer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldNumeroPorHacer.setBounds(211, 11, 34, 27);
		panelPorHacer.add(textFieldNumeroPorHacer);
		textFieldNumeroPorHacer.setColumns(10);
		
	
		textFieldNumeroEnProceso = new JTextField();
		textFieldNumeroEnProceso.setText("0");
		textFieldNumeroEnProceso.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldNumeroEnProceso.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldNumeroEnProceso.setColumns(10);
		textFieldNumeroEnProceso.setBounds(211, 10, 34, 27);
		panelEnProceso.add(textFieldNumeroEnProceso);
		
		
		textFieldNumeroTerminado = new JTextField();
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
		
		
		//boton crear tarea
		
		btnCrearTarea = new JButton("Crear");
		btnCrearTarea.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCrearTarea.setBounds(229, 97, 89, 23);
		panelTextField.add(btnCrearTarea);
		
		//boton eliminar tarea
		
		btnEliminarTarea = new JButton("Eliminar Tarea Seleccionada");
		btnEliminarTarea.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEliminarTarea.setBounds(498, 29, 298, 37);
		tareas.add(btnEliminarTarea);
		
		
	
		
	}
	
	
	
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
	
	
	
	
	public void cambiarCantidadTareas(Estado estado){
		
		
		if(estado == Estado.POR_HACER) {
			
			 cambiarTareasPorHacer();
			
		}
		
		else if(estado == Estado.EN_PROCESO) {
			
			cambiarTareasEnProceso();
			
		}
		
		else if(estado == Estado.TERMINADO) {
			
			cambiarTareasTerminado(); 
			
		}
		
		
		
	}
	
	//dividir en 3 metodos y uno que con condicionales decida cual usar. 
	
	public void cambiarTareasPorHacer() {
		
		String numeroTareasPorHacer = String.valueOf(logica.getContadorTareasPorHacer());
		textFieldNumeroPorHacer.setText(numeroTareasPorHacer);
		
	}
	
	public void cambiarTareasEnProceso() {
		
		String numeroTareasEnProceso = String.valueOf(logica.getContadorTareasEnProceso());
		textFieldNumeroEnProceso.setText(numeroTareasEnProceso);
		
	}
	
	public void cambiarTareasTerminado() {
		
		String numeroTareasTerminado = String.valueOf(logica.getContadorTareasTerminado());
		textFieldNumeroTerminado.setText(numeroTareasTerminado);
		
		
	}
	
	
	
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
	
	
	public void moverTareaDeLista(Estado estadoNuevo) {
		
		Tarea tareaSeleccionada = obtenerTareaSeleccionada(); //se obtiene la tarea
		
		Estado estadoOriginal = tareaSeleccionada.getEstado(); //se obtiene el estado original
		
		
		//se elimina la tarea de la lista original
		
		DefaultListModel listModelTareaOriginal = obtenerListModelTarea(tareaSeleccionada); 
		
		listModelTareaOriginal.removeElement(tareaSeleccionada); 
		
		//se mueve la tarea de estado y array
		
		logica.moverTarea(tareaSeleccionada, estadoNuevo); //se mueve la tarea
			
		
		//se agrega la tarea a la lista nueva
		
		DefaultListModel listModelTareaNuevo= obtenerListModelTarea(tareaSeleccionada); 
		
		listModelTareaNuevo.addElement(tareaSeleccionada);			
		
		
		cambiarCantidadTareas(estadoOriginal); //se cambia el contador de tareas del estado original
		cambiarCantidadTareas(estadoNuevo);  //se cambia el contador de tareas del estado nuevo
		
	}
	
}
