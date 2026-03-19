package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Logica;
import java.awt.CardLayout;
import javax.swing.JLabel;

public class Pantalla extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Logica logica; 
	private CardLayout layout = new CardLayout(0, 0);
	
	//componentes
	
	private JMenuItem item1; //Crear tarea
	private JMenuItem item2; //tareas
	private JMenuItem item3; //guardar estado
	private JMenuItem item4; //cargar estado
	

	
	public Pantalla(Logica logica) {
		
		this.logica = logica; // se asigna la logica inyectada por el constructor a la referencia de la clase Logica
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(layout); //setea el layout al contentPane
		
		agregarComponentes(); //se añaden los componentes mas estaticos como paneles y jlabel's
		
		agregarJMenuBar(); //se añade la JMenuBar al jframe
		
		
		item1.addActionListener(e->{ //al clickear el item aparece en pantalla el panel "Crear Tarea"
			
			layout.show(contentPane, "crearTarea"); 
			
		});
		
		item2.addActionListener(e->{
			
			layout.show(contentPane, "tareas" ); //al clickear el item aparece en pantalla el panel "Tareas" 
			
		});
		
		
	
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
	
	private void agregarComponentes() {
		
		JPanel crearTarea = new JPanel();
		contentPane.add(crearTarea, "crearTarea");
		crearTarea.setLayout(null);
		
		JLabel lblCrearTarea = new JLabel("Crear tarea");
		lblCrearTarea.setBounds(290, 38, 116, 14);
		crearTarea.add(lblCrearTarea);
		
		JPanel tareas = new JPanel();
		contentPane.add(tareas, "tareas");
		tareas.setLayout(null);
		
		JLabel lblTareas = new JLabel("Tareas");
		lblTareas.setBounds(298, 35, 46, 14);
		tareas.add(lblTareas);
		
		
	}
}
