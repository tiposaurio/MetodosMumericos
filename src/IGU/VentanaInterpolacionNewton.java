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

import MU.IGU.componentes.MuBoton;
import MU.IGU.componentes.MuLabel;
import MU.IGU.contenedores.MuFrame;
import MU.IGU.contenedores.MuPanel;
import MU.IGU.oyentes.MuBotonEvent;
import MU.IGU.oyentes.MuBotonListener;
import MU.modelo.MuFunction;

public class VentanaInterpolacionNewton extends MuFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -870729701256997400L;
	private static final VentanaInterpolacionNewton instance = new VentanaInterpolacionNewton();
	private MuBoton calcular, atras;
	private MuBotonListener botonListener;
	private JTextArea areaRaiz;
	private JScrollPane paneRaiz;
	private MuLabel labelValoresX, labelValorInicial, labelImagenesX;
	private JTextField campoValoresX, campoValorInicial, campoImagenesX;
	private Plot2DPanel grafica;

	private VentanaInterpolacionNewton() {
		super();
	}

	public static VentanaInterpolacionNewton getInstance() {
		return instance;
	}

	public void init() {
		super.init();
		calcular = new MuBoton("Calcular");
		atras = new MuBoton("Atras");
		areaRaiz = new JTextArea();
		paneRaiz = new JScrollPane(areaRaiz);
		labelValoresX = new MuLabel("Valores de x");
		labelImagenesX = new MuLabel("Imagenes de x");
		labelValorInicial = new MuLabel("Valor inicial");
		campoValoresX = new JTextField();
		campoImagenesX = new JTextField();
		campoValorInicial = new JTextField();
		grafica = new Plot2DPanel();

		botonListener = new MuBotonListener() {

			@Override
			public void botonPressedEvent(MuBotonEvent e) {
				if (e.getSource() == calcular) {
					if (!campoValoresX.getText().isEmpty()
							&& !campoImagenesX.getText().isEmpty()
							&& !campoValorInicial.getText().isEmpty()) {

						MuFunction<String[], double[]> map = new MuFunction<String[], double[]>() {

							@Override
							public double[] apply(String[] xs) {
								double[] result = new double[xs.length];
								for (int ch = 0; ch < result.length; ch++)
									result[ch] = Double.parseDouble(xs[ch]);

								return result;
							}
						};
						double[] xs = map.apply(campoValoresX.getText().split(
								" +"));
						double[] is = map.apply(campoImagenesX.getText().split(
								" +"));

						String result = MetodosNumericos
								.interpolacionNewton(xs, is, Double
										.parseDouble(campoValorInicial
												.getText()));
						System.out.println(result);
						areaRaiz.setText(result);

						grafica.addLegend("SOUTH");

						double[] x = new double[xs.length];
						double[] y = new double[is.length];

						for (int ch = 0; ch < xs.length; ch++)
							x[ch] = (double) xs[ch];
						for (int ch = 0; ch < is.length; ch++)
							y[ch] = (double) is[ch];

						grafica.addLinePlot(campoValoresX.getText(), x, y);
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
		setTitle("MuInterpolacion de Newton / diferencias divididas");
		cuerpo.setLayout(new BoxLayout(cuerpo, BoxLayout.X_AXIS));
		MuPanel panelGrande = new MuPanel();
		MuPanel panel = new MuPanel();
		panelGrande.setLayout(new BoxLayout(panelGrande, BoxLayout.Y_AXIS));
		panel.setLayout(new GridLayout(4, 2));
		setForeground(Color.WHITE);
		atras.setForeground(Color.WHITE);
		calcular.setForeground(Color.WHITE);
		labelValoresX.setForeground(Color.WHITE);
		labelImagenesX.setForeground(Color.WHITE);
		labelValorInicial.setForeground(Color.WHITE);
		
		panel.add(atras);
		panel.add(calcular);
		panel.add(labelValoresX);
		panel.add(campoValoresX);
		panel.add(labelValorInicial);
		panel.add(campoValorInicial);
		panel.add(labelImagenesX);
		panel.add(campoImagenesX);
		panelGrande.add(panel);
		panelGrande.add(grafica);
	panelGrande.setMaximumSize(new Dimension(200, 400));
		cuerpo.add(panelGrande);
		cuerpo.add(paneRaiz);
setResizable(true);
System.out.print("X: ");
for(Double ch = 0.0 ; ch <= 3; ch+=0.03 ){
	
	System.out.print(" "+ch);
}
System.out.println();
System.out.print("f(X): ");
		for(Double ch = 0.0 ; ch <=3; ch+=0.03 ){
			System.out.print(" "+Math.pow(ch, 2));
		}
		paneRaiz.setPreferredSize(new Dimension(200, 200));
		atras.addMuBotonListener(botonListener);
		calcular.addMuBotonListener(botonListener);
		setSize(450, 400);
	}
}
