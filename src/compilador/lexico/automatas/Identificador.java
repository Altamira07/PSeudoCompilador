package compilador.lexico.automatas;

import analizador.lexico.Token;

public class Identificador extends Automata {
	final char[] q0 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	final char[] q2 = "abcdefghijklmnopqrstuvwxyz_-".toCharArray();

	public Token q0() {
		if (iterador < valores.length)
			for (int i = 0; i < q0.length; i++) 
			{
				if (q0[i] == valores[iterador])
					return q2();
			}

		return null;
	}

	private Token q2() {
		iterador++;
		if (iterador < valores.length) {
			for (int i = 0; i < q2.length; i++)
				if (q2[i] == valores[iterador])
					return q2();
			return null;
		}
			
		return q1();

	}

	private Token q1() {
		
		return generarToken();
	}
}
