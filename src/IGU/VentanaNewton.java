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

public class VentanaNewton extends MuFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2301795422175248980L;

	private static final VentanaNewton instance = new VentanaNewton();
	private MuBoton calcular, atras;
	private MuBotonListener botonListener;
	private JTextArea areaRaiz;
	private JScrollPane paneRaiz;
	private MuLabel labelFuncion, labelValorInicial, labelIteraiones,
			labelTolerancia;
	private JTextField campoFuncion, campoValorInicial, campoIteraciones,
			campoTolerancia;
	private Plot2DPanel grafica;

	private VentanaNewton() {
		super();
	}

	public static VentanaNewton getInstance() {
		return instance;
	}

	public void init() {
		super.init();
		calcular = new MuBoton("Calcular");
		atras = new MuBoton("Atras");
		areaRaiz = new JTextArea();
		paneRaiz = new JScrollPane(areaRaiz);
		labelFuncion = new MuLabel("Funcion");
		labelIteraiones = new MuLabel("Iteraciones");
		labelTolerancia = new MuLabel("Tolerancia");
		labelValorInicial = new MuLabel("Valor inicial");
		campoFuncion = new JTextField();
		campoIteraciones = new JTextField();
		campoTolerancia = new JTextField();
		campoValorInicial = new JTextField();
		grafica = new Plot2DPanel();

		botonListener = new MuBotonListener() {

			@Override
			public void botonPressedEvent(MuBotonEvent e) {
				if (e.getSource() == calcular) {
					if (!campoFuncion.getText().isEmpty()
							&& !campoIteraciones.getText().isEmpty()
							&& !campoTolerancia.getText().isEmpty()
							&& !campoValorInicial.getText().isEmpty()) {
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

						String[] result = MetodosNumericos
								.metodoNewton(f, Double
										.parseDouble(campoValorInicial
												.getText()), Integer
										.parseInt(campoIteraciones.getText()),
										Double.parseDouble(campoTolerancia
												.getText()));
						areaRaiz.setText("");
						for(String str : result)
							areaRaiz.append(str);
						
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

				} else if (e.getSource() == atras) {
					setVisible(false);
					VentanaPrincipal.getInstance().setLocation(getLocation());
					VentanaPrincipal.getInstance().setVisible(true);
				}

			}
		};

	}

	public void setup() {
		super.setup();
		setTitle("MuNewton");
		cuerpo.setLayout(new BoxLayout(cuerpo, BoxLayout.X_AXIS));
		MuPanel panelGrande = new MuPanel();
		MuPanel panel = new MuPanel();
		panelGrande.setLayout(new BoxLayout(panelGrande, BoxLayout.Y_AXIS));
		panel.setLayout(new GridLayout(5, 2));
		setForeground(Color.WHITE);
		atras.setForeground(Color.WHITE);
		calcular.setForeground(Color.WHITE);
		labelFuncion.setForeground(Color.WHITE);
		labelIteraiones.setForeground(Color.WHITE);
		labelTolerancia.setForeground(Color.WHITE);
		labelValorInicial.setForeground(Color.WHITE);

		panel.add(atras);
		panel.add(calcular);
		panel.add(labelFuncion);
		panel.add(campoFuncion);
		panel.add(labelValorInicial);
		panel.add(campoValorInicial);
		panel.add(labelIteraiones);
		panel.add(campoIteraciones);
		panel.add(labelTolerancia);
		panel.add(campoTolerancia);
		panelGrande.add(panel);
		panelGrande.add(grafica);
		cuerpo.add(panelGrande);
		cuerpo.add(paneRaiz);
		atras.addMuBotonListener(botonListener);
		calcular.addMuBotonListener(botonListener);
		paneRaiz.setPreferredSize(new Dimension(200, 200));
		setSize(450, 200);
	}
}
