package metodos;

import MU.modelo.MuFunction;

public abstract class MetodosNumericos {
	
	public static String[] metodoBiseccion(MuFunction<Double, Double> f,
			double a, double b, int iter, double tolerancia) {
		double result = a;
		String iteraciones="";
		
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
			iteraciones+="I: "+i+", Raiz: "+result+" Error: "+error+"\n";
		}
		String[] result2={iteraciones,"La Raiz es: "+result+"\n","Numero de iteraciones: "+i+"\n"};
		return result2;
	}

	public static String[] metodoNewton(MuFunction<Double, Double> f,
			double valorInicial, int iteraciones, double tolerancia) {
		String info="";
		double result = valorInicial, xb = result - 999;
		double n = 0, del_x = 0.01;

		while (Math.abs(result - xb) > tolerancia) {
			n ++;
			xb = result;
			if (n > iteraciones)
				break;
			double y = f.apply(result);
			double y_driv = (f.apply(result + del_x) - y) / del_x;
			result = xb - y / y_driv;
			info+=" n=" + n + " x= " + result + " y = " + y+"\n";
		}
		if(n>iteraciones)
			n--;
		String[]result2 = {info,"La raiz es: "+result+"\n","Numero de iteraciones: "+n};
		
		return result2;
	}

	public static String[] metodoDeLaSecante(
			MuFunction<Double, Double> f, double x0, double x1,
			int iteraciones, double tolerancia) {
		String info="";
		double x2 = x0;
		int k = 0;
		while ( Math.abs(f.apply(x2)) > tolerancia && k < iteraciones) {
			x2 = x0 -f.apply(x0)*(x1-x0)/(f.apply(x1)-f.apply(x0));
			x0 = x1;
			x1 =x2;
			k++;
			info+= "I: "+k+" Raiz: "+x2+" Error: "+Math.abs(f.apply(x2))+"\n";
		}
		String[]result2 ={info," La Raiz es: "+x2,"\n Numero de iteraciones: "+k+"\n"};
		return result2;

	}

	public static String metodoPuntoFijo(MuFunction<Double, Double> f,
			double valorInicial, int iteraciones, double tolerancia) {
		String result2="";
		double result = valorInicial;
		boolean funciono = false;
		int i = 1;
		while (i < iteraciones && !funciono) {
			result = f.apply(valorInicial);
			i++;
			if (Math.abs(valorInicial - result) < tolerancia)
				funciono = true;
			valorInicial = result;
			result2 += "I: "+i+" Raiz: "+result+" Error: "+Math.abs(valorInicial - result)+"\n";
		}
		if (funciono)
			result2+= "La raiz es: "+result+"\n Numero de iteraciones: "+i+"\n";
		else
			result2 = "Fracaso luego de "+i+" iteraciones";

		return result2;
	}

	public static String interpolacionLagrange(Double[] xs, Double[] imagenes,
			double x) {
		double result = 0;
		String result2="";
		Polinomio[] po = getCoeficientes(xs);
		int n = imagenes.length;
		for (int i = 0; i < n; i++) {
			result += (po[i].evaluar(x) * imagenes[i]);
			result2+="I: "+(i+1)+" Resultado: "+result+"\n";
		}
		result2 +=" El resultado es:"+result;
		return result2;

	}

	private static Polinomio[] getCoeficientes(Double[] xs) {
		int n = xs.length;
		Polinomio[] L = new Polinomio[n];
		for (int i = 0; i < n; i++) {
			double denom = 1;
			L[i] = new Polinomio();
			for (int j = 0; j < n; j++) {
				if (i != j) {
					double[] co = { -xs[j], 1 };
					L[i] = L[i].multiplicar(new Polinomio(co));
					denom *= (xs[i] - xs[j]);
				}
			}
			L[i] = L[i].dividir(denom);
		}
		return L;
	}

}
