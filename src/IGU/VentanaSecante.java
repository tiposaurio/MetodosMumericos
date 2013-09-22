package IGU;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import metodos.MetodosNumericos;

import org.math.plot.Plot2DPanel;
import org.nfunk.jep.JEP;

import MU.IGU.componentes.MuBoton;
import MU.IGU.componentes.MuLabel;
import MU.IGU.contenedores.MuFrame;
import MU.IGU.contenedores.MuPanel;
import MU.IGU.oyentes.MuBotonEvent;
import MU.IGU.oyentes.MuBotonListener;
import MU.modelo.MuFunction;
import MU.modelo.listas.MuLista;

public class VentanaSecante extends MuFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5238570231198193935L;
	private static final VentanaSecante instance = new VentanaSecante();
	protected MuBoton empezar, atras;
	protected MuLabel labelFuncion, labelTolerancia, labelA, labelB,
			labelIteraciones;
	protected JTextField campoFuncion, campoTolerancia, campoA, campoB,
			campoIteraciones;
	protected JTextArea campoRaiz;
	protected JScrollPane paneRaiz;
	protected MuBotonListener botonListener;
	protected Plot2DPanel grafica;

	public VentanaSecante() {
		super();
	}
	public static VentanaSecante getInstance(){
		return instance;
	}

	public void init() {
		super.init();
		empezar = new MuBoton("Calcular");
		atras = new MuBoton("Atras");
		labelA = new MuLabel("A");
		labelB = new MuLabel("B");
		labelFuncion = new MuLabel("Funcion");
		labelTolerancia = new MuLabel("Tolerancia");
		labelIteraciones = new MuLabel("N. Iteraciones");
		campoA = new JTextField(200);
		campoB = new JTextField(200);
		campoFuncion = new JTextField(200);
		campoIteraciones = new JTextField(200);
		campoRaiz = new JTextArea();
		paneRaiz = new JScrollPane(campoRaiz);
		campoTolerancia = new JTextField(200);
		grafica = new Plot2DPanel();
		botonListener =new MuBotonListener() {

			@Override
			public void botonPressedEvent(MuBotonEvent e) {
				if (e.getSource() == atras) {
					VentanaPrincipal.getInstance().setLocation(getLocation());
					VentanaPrincipal.getInstance().setVisible(true);
					setVisible(false);
				} else if (e.getSource() == empezar) {
					if (!campoA.getText().isEmpty()
							&& !campoB.getText().isEmpty()
							&& !campoFuncion.getText().isEmpty()
							&& !campoIteraciones.getText().isEmpty()

							&& !campoTolerancia.getText().isEmpty()) {
						MuFunction<Double, Double> f = new MuFunction<Double, Double>() {

							@Override
							public Double apply(Double x) {
								JEP jep = new JEP();
								jep.addVariable("x", x);
								jep.addStandardConstants();
								jep.addStandardFunctions();
								jep.parseExpression(campoFuncion.getText());

								return jep.getValue();
							}
						};
						String[] result = MetodosNumericos.metodoDeLaSecante(f,
								Double.parseDouble(campoA.getText()),
								Double.parseDouble(campoB.getText()),
								Integer.parseInt(campoIteraciones.getText()),
								Double.parseDouble(campoTolerancia.getText()));
						campoRaiz.setText("");
						for (String s : result)
							campoRaiz.append(s);
						grafica.addLegend("SOUTH");
						MuLista<Double> a = MuLista.getVacia();
						for (int ch = -1000; ch < 1000; ch++)
							a = a.append(ch * 0.01);

						MuLista<Double> aMap = a.map(f);
						Object[] X = a.toArray();
						Object[] Y = aMap.toArray();

						double[] x = new double[X.length];
						double[] y = new double[Y.length];

						for (int ch = 0; ch < X.length; ch++)
							x[ch] = (double) X[ch];
						for (int ch = 0; ch < Y.length; ch++)
							y[ch] = (double) Y[ch];
						grafica.addLinePlot(campoFuncion.getText(), x, y);
					}

				}
			}
		};
	}

	public void setup() {
		super.setup();
		setTitle("MuSecante");
		setForeground(Color.WHITE);

		cuerpo.setLayout(new BoxLayout(cuerpo, BoxLayout.X_AXIS));
		MuPanel panel = new MuPanel();
		panel.setLayout(new GridLayout(6, 2));
		grafica.setPreferredSize(new Dimension(200, 200));
		campoRaiz.setEditable(false);
		labelFuncion.setForeground(Color.WHITE);
		labelA.setForeground(Color.WHITE);
		labelB.setForeground(Color.WHITE);
		labelIteraciones.setForeground(Color.WHITE);
		labelTolerancia.setForeground(Color.WHITE);
		atras.setForeground(Color.WHITE);
		empezar.setForeground(Color.WHITE);
		atras.setMaximumSize(new Dimension(40, 45));
		atras.setMinimumSize(new Dimension(40, 45));
		setResizable(false);
		panel.add(atras);
		panel.add(empezar);
		panel.add(labelFuncion);
		panel.add(campoFuncion);
		panel.add(labelA);
		panel.add(campoA);
		panel.add(labelB);
		panel.add(campoB);
		panel.add(labelIteraciones);
		panel.add(campoIteraciones);
		panel.add(labelTolerancia);
		panel.add(campoTolerancia);
		
		
		empezar.addMuBotonListener(botonListener);
		atras.addMuBotonListener(botonListener);
		paneRaiz.setMinimumSize(new Dimension(200,200));
	
		MuPanel panelGrande = new MuPanel();
		panelGrande.setLayout(new BoxLayout(panelGrande, BoxLayout.Y_AXIS));

		panelGrande.add(panel);
		panelGrande.add(grafica);
		cuerpo.add(panelGrande);
		cuerpo.add(paneRaiz);
		setSize(450, 200);
	}
}
