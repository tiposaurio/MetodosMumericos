package metodos;

public class InterpolacionNewton {

	private static double[] b;
	private static boolean[][] yaCalculado; // ¿El valor ya fue calculado?
	private static double[][] calculoPrevio; // Valor previamente calculado
	private static double[] x;
	private static double[] fx;

	public static double[] solucionar(double[] x, double[] fx) {
		int n = x.length;
		b = new double[n];
		yaCalculado = new boolean[n][n];
		calculoPrevio = new double[n][n];
		InterpolacionNewton.x = x;
		InterpolacionNewton.fx = fx;
		b[0] = fx[0];
		diferenciasDivididas(x.length - 1, 0);
		return b;
	}

	public static double diferenciasDivididas(int i, int k) {
		if (i == k)
			return fx[i];
		double f1 = 0;
		if (yaCalculado[i - 1][k]) {
			f1 = calculoPrevio[i - 1][k];
		} else {
			f1 = diferenciasDivididas(i - 1, k);
		}
		double f2 = 0;
		if (yaCalculado[i][k + 1]) {
			f2 = calculoPrevio[i][k + 1];
		} else {
			f2 = diferenciasDivididas(i, k + 1);
		}
		double dd = (f1 - f2) / (x[k] - x[i]);
		yaCalculado[i][k] = true;
		calculoPrevio[i][k] = dd;
		if (k == 0) // b[i] = f[i,0]
			b[i] = dd;
		return dd;
	}

	public static double calcularValor(double x, double[] b, double[] xs) {
		double res = 0;
		for (int i = 0; i < b.length; i++) {
			double acum = b[i];
			for (int j = 0; j < i; j++)
				acum *= (x - xs[j]);
			res += acum;
		}
		return res;
	}

	public static void main(String[] args) {
		double[] x = { 2.3, 2.4, 2.5, 2.6 };
		double[] fx = { 17.997784, 15.850776, 14.140572, 12.720154 };
		double[] b = solucionar(x, fx);
		for (int i = 0; i < b.length; i++) {
			System.out.println(b[i]);
		}
		System.out.println("f(" + 2.45 + ") = " + calcularValor(2.45, b, x));
	}

}