package compilador.lexico.automatas;


import compilador.lexico.Token;

/**
 * Palabras reservadas para tipo de datos
 * 
 * @author Luisito
 *
 */
public class PalabrasReservadasT extends Automata {

	@Override
	public Token analizar(char[] valores) {
		this.valores = valores;
		return q0();
	}

	public Token q0() {
		if(iterador < valores.length)
		switch (valores[iterador++]) {

		case 'R':
			return q1();
		case 'C':
			return q2();
		case 'B':
			return q3();
		}
		return null;

	}
//Booleano
	private Token q3() {
		return (iterador < valores.length && valores[iterador++] == 'o') ? q12() : null;
	}

	private Token q12() {
		return (iterador < valores.length && valores[iterador++] == 'o') ? q13() : null;
	}

	private Token q13() {
		return (iterador < valores.length &&valores[iterador++] == 'l') ? q14() : null;
	}

	private Token q14() {
		return (iterador < valores.length &&valores[iterador++] == 'e') ? q15() : null;
	}

	private Token q15() {
		return (iterador < valores.length && valores[iterador++] == 'a') ? q16() : null;
	}

	private Token q16() {
		return (iterador < valores.length &&valores[iterador++] == 'n') ? q17() : null;
	}

	private Token q17() {
		return (iterador < valores.length &&valores[iterador++] == 'o') ? q19() : null;
	}
	
	
	
	public Token q2() {
		return (iterador < valores.length &&valores[iterador++] == 'a') ? q7() : null;
	}

	public Token q7() {
		return (iterador < valores.length &&valores[iterador++] == 'd') ? q8() : null;
	}

	public Token q8() {
		return (iterador < valores.length &&valores[iterador++] == 'e') ? q9() : null;
	}

	public Token q9() {
		return (iterador < valores.length &&valores[iterador++] == 'n') ? q10() : null;
	}

	public Token q10() {
		return (iterador < valores.length &&valores[iterador++] == 'a') ? q19() : null;
	}


	public Token q1() {
		return (iterador < valores.length &&valores[iterador++] == 'e') ? q4() : null;
	}

	public Token q4() {
		return (iterador < valores.length &&valores[iterador++] == 'a') ? q5() : null;

	}

	public Token q5() {
		return (iterador < valores.length && valores[iterador++] == 'l') ? q19() : null;
	}


	public Token q19() {
		
		return generarToken();
		
	}

}
