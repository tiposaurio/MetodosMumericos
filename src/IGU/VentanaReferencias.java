package IGU;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import MU.IGU.contenedores.MuDialog;

public class VentanaReferencias extends MuDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8620412881265308383L;
	private JTextArea referencias;
	private JScrollPane paneReferencias;
	private static final VentanaReferencias instance = new VentanaReferencias(); 
	private VentanaReferencias() {
		super(VentanaPrincipal.getInstance());
	}
	public static VentanaReferencias getInstance(){
		return instance;
	}
	public void init(){
		super.init();
		referencias = new JTextArea("Algunas partes del codigo para éste programa fueron tomadas de los siguientes sitios.\n"+
"\n"+
"http://galia.fc.uaslp.mx/~medellin/classesPN.htm\n"+
"https://sites.google.com/site/numethod20102/interpolacion\n"+
"https://www.youtube.com/watch?v=jAseeF9pmMU\n"+
"\n"+
"Librerias usadas:\n"+
"JMathPlot\n"+
"JEP\n");
		paneReferencias = new JScrollPane(referencias);
	}
	public void setup(){
		super.setup();
		setTitle("Referencias");
		setForeground(Color.WHITE);
		setSize(500, 500);	 
	     referencias.setEditable(false);
		cuerpo.setLayout(new BorderLayout());
		cuerpo.add(paneReferencias);
	}
}

