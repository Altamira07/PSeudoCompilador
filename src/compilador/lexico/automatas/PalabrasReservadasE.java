package compilador.lexico.automatas;


import compilador.lexico.Token;

public class PalabrasReservadasE extends Automata {


	public Token q0() {
		if(iterador < valores.length)
		switch (valores[iterador++]) {
		case 's':
			return q1();
		case 'e':
		return q3();
		case 'm':
			return q10();
		case 'i':
			return q17();
		case 'v':
			return q23();
		case 'f':
			return q31();
		}
		
		return null;
	}

	private Token q31() {
		return (iterador < valores.length && valores[iterador++] == 'a') ? q32() : null;
	}
	private Token q32() {
		return (iterador < valores.length && valores[iterador++] == 'l') ? q33() : null;
	}
	private Token q33() {
		return (iterador < valores.length && valores[iterador++] == 's') ? q34() : null;
	}
	private Token q34() {
		return (iterador < valores.length && valores[iterador++] == 'o') ? q2() : null;
	}
	
	
	
	
	private Token q23() {
		return (iterador < valores.length && valores[iterador++] == 'e') ? q24() : null;
	}
	private Token q24() {
		return (iterador < valores.length && valores[iterador++] == 'r') ? q25() : null;
	}
	private Token q25() {
		return (iterador < valores.length && valores[iterador++] == 'd') ? q26() : null;
	}
	private Token q26() {
		return (iterador < valores.length &&valores[iterador++] == 'a') ? q27() : null;
	}
	private Token q27() {
		return (iterador < valores.length &&valores[iterador++] == 'd') ? q28() : null;
	}
	private Token q28() {
		return (iterador < valores.length &&valores[iterador++] == 'e') ? q29() : null;
	}
	private Token q29() {
		return (iterador < valores.length &&valores[iterador++] == 'r') ? q30() : null;
	}
	private Token q30() {
		return (iterador < valores.length &&valores[iterador++] == 'o') ? q2() : null;
	}
	
	
	private Token q17() {
		return (iterador < valores.length &&valores[iterador++] == 'm') ? q18() : null;
	}
	private Token q18() {
		return (iterador < valores.length &&valores[iterador++] == 'p') ? q19() : null;
	}
	private Token q19() {
		return (iterador < valores.length &&valores[iterador++] == 'r') ? q20() : null;
	}
	private Token q20() {
		return (iterador < valores.length &&valores[iterador++] == 'i') ? q21() : null;
	}
	private Token q21() {
		return (iterador < valores.length &&valores[iterador++] == 'm') ? q22() : null;
	}
	private Token q22() {
		return (iterador < valores.length &&valores[iterador++] == 'e') ? q2() : null;
	}
	
	
	
	
	
	private Token q10() {
		return (iterador<valores.length &&valores[iterador++] == 'i') ? q11() : null;
	}
	private Token q11() {
		return (iterador<valores.length &&valores[iterador++] == 'e') ? q12() : null;
	}
	private Token q12() {
		return (iterador<valores.length &&valores[iterador++] == 'n') ? q13() : null;
	}
	private Token q13() {
		return (iterador<valores.length &&valores[iterador++] == 't') ? q14() : null;
	}
	private Token q14() {
		return (iterador<valores.length &&valores[iterador++] == 'r') ? q15() : null;
	}
	private Token q15() {
		return (iterador<valores.length &&valores[iterador++] == 'a') ? q16() : null;
	}
	private Token q16() {
		return (iterador<valores.length &&valores[iterador++] == 's') ? q2() : null;
	}

	
	private Token q1() {
		return (iterador<valores.length &&valores[iterador++] == 'i') ? q2() : null;
	}

	private Token q3() {
		return (iterador<valores.length &&valores[iterador++] == 'n') ? q4() : null;
	}

	private Token q4() {
		return (iterador<valores.length &&valores[iterador++] == 't') ? q5() : null;
	}

	private Token q5() {
		return (iterador<valores.length &&valores[iterador++] == 'o') ? q6() : null;
	}	
	
	private Token q6() {
		return (iterador<valores.length &&valores[iterador++] == 'n') ? q7() : null;
	}
	private Token q7() {
		return (iterador<valores.length &&valores[iterador++] == 'c') ? q8() : null;
	}
	private Token q8() {
		return (iterador<valores.length &&valores[iterador++] == 'e') ? q9() : null;
	}
	private Token q9() {
		return (iterador<valores.length &&valores[iterador++] == 's') ? q2() : null;
	}
	private Token q2() {
		return generarToken();
	}

}
