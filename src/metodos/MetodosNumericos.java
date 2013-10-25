package metodos;

import MU.modelo.MuFunction;

public abstract class MetodosNumericos {
	/**
	 * Autor Héctor Eduardo Medellín Anaya Modificaciones Mujuanp
	 * 
	 * @param f
	 * @param a
	 * @param b
	 * @param iter
	 * @param tolerancia
	 * @return
	 */
	public static String[] metodoBiseccion(MuFunction<Double, Double> f,
			double a, double b, int iter, double tolerancia) {
		double result = a;
		String iteraciones = "";

		int i = 0;
		double error = tolerancia + 1;
		while (f.apply(result) != 0 && i < iter && error > tolerancia) {
			double rt = result;
			result = (b - a) / 2 + a;
			if (f.apply(a) * f.apply(result) > 0)
				a = result;
			else if (f.apply(b) * f.apply(result) > 0)
				b = result;
			error = Math.abs((result - rt) / result);
			i++;
			iteraciones += "I: " + i + ", Raiz: " + result + " Error: " + error
					+ "\n";
		}
		String[] result2 = { iteraciones, "La Raiz es: " + result + "\n",
				"Numero de iteraciones: " + i + "\n" };
		return result2;
	}

	/**
	 * Autor Héctor Eduardo Medellín Anaya Modificaciones Mujuanp
	 * 
	 * @param f
	 * @param valorInicial
	 * @param iteraciones
	 * @param tolerancia
	 * @return
	 */
	public static String[] metodoNewton(MuFunction<Double, Double> f,
			double valorInicial, int iteraciones, double tolerancia) {
		String info = "";
		double result = valorInicial, xb = result - 999;
		double n = 0, del_x = 0.01;

		while (Math.abs(result - xb) > tolerancia) {
			n++;
			xb = result;
			if (n > iteraciones)
				break;
			double y = f.apply(result);
			double y_driv = (f.apply(result + del_x) - y) / del_x;
			result = xb - y / y_driv;
			info += " n=" + n + " x= " + result + " y = " + y + "\n";
		}
		if (n > iteraciones)
			n--;
		String[] result2 = { info, "La raiz es: " + result + "\n",
				"Numero de iteraciones: " + n };

		return result2;
	}

	public static String[] metodoDeLaSecante(MuFunction<Double, Double> f,
			double x0, double x1, int iteraciones, double tolerancia) {
		String info = "";
		double x2 = x0;
		int k = 0;
		while (Math.abs(f.apply(x2)) > tolerancia && k < iteraciones) {
			x2 = x0 - f.apply(x0) * (x1 - x0) / (f.apply(x1) - f.apply(x0));
			x0 = x1;
			x1 = x2;
			k++;
			info += "I: " + k + " Raiz: " + x2 + " Error: "
					+ Math.abs(f.apply(x2)) + "\n";
		}
		String[] result2 = { info, " La Raiz es: " + x2,
				"\n Numero de iteraciones: " + k + "\n" };
		return result2;

	}

	/**
	 * Autor Héctor Eduardo Medellín Anaya Modificaciones Mujuanp
	 * 
	 * @param f
	 * @param valorInicial
	 * @param iteraciones
	 * @param tolerancia
	 * @return
	 */
	public static String metodoPuntoFijo(MuFunction<Double, Double> f,
			double valorInicial, int iteraciones, double tolerancia) {
		String result2 = "";
		double result = valorInicial;
		boolean funciono = false;
		int i = 1;
		while (i < iteraciones && !funciono) {
			result = f.apply(valorInicial);
			i++;
			if (Math.abs(valorInicial - result) < tolerancia)
				funciono = true;
			valorInicial = result;
			result2 += "I: " + i + " Raiz: " + result + " Error: "
					+ Math.abs(valorInicial - result) + "\n";
		}
		if (funciono)
			result2 += "La raiz es: " + result + "\n Numero de iteraciones: "
					+ i + "\n";
		else
			result2 = "Fracaso luego de " + i + " iteraciones";

		return result2;
	}

	public static String interpolacionLagrange(double[] xs, double[] imagenes,
			double x) {

		Polinomio[] po = InterpolacionLagrange.getCoeficientes(xs);

		return "f(" + x + ")= "
				+ InterpolacionLagrange.solucionar(po, imagenes, x);

	}

	public static String interpolacionNewton(double[] xs, double[] imagenes,
			double x) {
		String result = "";
		double[] b = InterpolacionNewton.solucionar(xs, imagenes);
		for (int i = 0; i < b.length; i++)
			result += "a" + i + "= " + b[i] + "\n";
		result += "f(" + x + ") = "
				+ InterpolacionNewton.calcularValor(x, b, xs) + "\n";

		return result;
	}

	public static String SplineCubic(double[] xs, double[] ys) {
		String result = "";
		double[][] spline = SplinesCubicos.solucionar(xs, ys);
		return result;
	}

}
