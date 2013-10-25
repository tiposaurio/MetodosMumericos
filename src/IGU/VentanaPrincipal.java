package IGU;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;

import MU.IGU.componentes.MuBoton;
import MU.IGU.contenedores.MuFrame;
import MU.IGU.oyentes.MuBotonEvent;
import MU.IGU.oyentes.MuBotonListener;

public class VentanaPrincipal extends MuFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5803880304077326648L;
	private MuBoton metodoBiseccion, metodoNewton, metodoSecante,
			metodoPuntoFijo, interpolacionLagrange,interpolacionNewton, licencia,referencias;
	private MuBotonListener botonListener;
	private static final VentanaPrincipal instance = new VentanaPrincipal();

	private VentanaPrincipal() {
		super();

	}

	public static VentanaPrincipal getInstance() {
		return instance;
	}

	public void init() {
		super.init();
		metodoBiseccion = new MuBoton("Metodo de biseccion");
		metodoNewton = new MuBoton("Metodo de newton");
		metodoPuntoFijo = new MuBoton("Metodo Punto fijo");
		metodoSecante = new MuBoton("Metodo de la secante");
		interpolacionLagrange = new MuBoton("Interpolacion de Lagrange");
		interpolacionNewton = new MuBoton("Interpolacion de Newton");
		licencia = new MuBoton("Licencia");
		referencias = new MuBoton("Referencias");
		botonListener = new MuBotonListener() {

			@Override
			public void botonPressedEvent(MuBotonEvent e) {
				if (e.getSource() == metodoBiseccion) {
					setVisible(false);
					VentanaBiseccion biseccion = VentanaBiseccion.getInstance();
					biseccion.setLocation(getLocation());
					biseccion.setVisible(true);

				} else if (e.getSource() == metodoNewton) {
					setVisible(false);
					VentanaNewton.getInstance().setLocation(getLocation());
					VentanaNewton.getInstance().setVisible(true);
				} else if (e.getSource() == metodoPuntoFijo) {
					VentanaPuntoFijo.getInstance().setLocation(getLocation());
					VentanaPuntoFijo.getInstance().setVisible(true);
					setVisible(false);

				} else if (e.getSource() == metodoSecante) {
					VentanaSecante.getInstance().setLocation(getLocation());
					VentanaSecante.getInstance().setVisible(true);
					setVisible(false);

				} else if (e.getSource() == interpolacionLagrange) {
					VentanaInterpolacionLagrange.getInstance().setLocation(
							getLocation());
					VentanaInterpolacionLagrange.getInstance().setVisible(true);
					setVisible(false);
				}else if (e.getSource() == interpolacionNewton) {
					VentanaInterpolacionNewton.getInstance().setLocation(
							getLocation());
					VentanaInterpolacionNewton.getInstance().setVisible(true);
					setVisible(false);
				} else if (e.getSource() == licencia) {
					VentanaLicencia.getInstance().setVisible(true);
				}else if (e.getSource()==referencias){
					VentanaReferencias.getInstance().setVisible(true);
				}

			}
		};
	}

	public void setup() {
		super.setup();
		setMaximumSize(new Dimension(300, 300));
		cuerpo.setLayout(new BoxLayout(cuerpo, BoxLayout.Y_AXIS));
		metodoBiseccion.addMuBotonListener(botonListener);
		metodoNewton.addMuBotonListener(botonListener);
		metodoPuntoFijo.addMuBotonListener(botonListener);
		metodoSecante.addMuBotonListener(botonListener);
		interpolacionLagrange.addMuBotonListener(botonListener);
		interpolacionNewton.addMuBotonListener(botonListener);
		licencia.addMuBotonListener(botonListener);
		referencias.addMuBotonListener(botonListener);
		metodoBiseccion.setMaximumSize(new Dimension(200, 25));
		metodoNewton.setMaximumSize(new Dimension(200, 25));
		metodoPuntoFijo.setMaximumSize(new Dimension(200, 25));
		metodoSecante.setMaximumSize(new Dimension(200, 25));
		interpolacionLagrange.setMaximumSize(new Dimension(200, 25));
		metodoBiseccion.setMinimumSize(new Dimension(200, 25));
		metodoNewton.setMinimumSize(new Dimension(200, 25));
		metodoPuntoFijo.setMinimumSize(new Dimension(200, 25));
		metodoSecante.setMinimumSize(new Dimension(200, 25));
		interpolacionLagrange.setMinimumSize(new Dimension(200, 25));
		interpolacionNewton.setMaximumSize(new Dimension(200, 25));
		interpolacionNewton.setMinimumSize(new Dimension(200, 25));
		licencia.setMaximumSize(new Dimension(200, 25));
		licencia.setMinimumSize(new Dimension(200, 25));
		referencias.setMaximumSize(new Dimension(200, 25));
		referencias.setMinimumSize(new Dimension(200, 25));
		setTitle("Metedos Mumericos");
		setForeground(Color.WHITE);
		metodoBiseccion.setForeground(Color.WHITE);
		metodoNewton.setForeground(Color.WHITE);
		metodoPuntoFijo.setForeground(Color.WHITE);
		metodoSecante.setForeground(Color.WHITE);
		interpolacionLagrange.setForeground(Color.WHITE);
		interpolacionNewton.setForeground(Color.WHITE);
		licencia.setForeground(Color.WHITE);
		referencias.setForeground(Color.WHITE);
		cuerpo.add(metodoBiseccion);
		cuerpo.add(metodoNewton);
		cuerpo.add(metodoPuntoFijo);
		cuerpo.add(metodoSecante);
		cuerpo.add(interpolacionLagrange);
		cuerpo.add(interpolacionNewton);
		cuerpo.add(referencias);
		cuerpo.add(licencia);
		setLocationRelativeTo(null);
	}

}
