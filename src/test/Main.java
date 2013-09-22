package test;


import IGU.VentanaPrincipal;

public class Main {
	public static void main(String[] args) {
		VentanaPrincipal a  = VentanaPrincipal.getInstance();
		a.setSize(300, 300);
		a.setVisible(true);

	}
}
