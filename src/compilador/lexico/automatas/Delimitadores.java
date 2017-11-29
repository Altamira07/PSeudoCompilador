package compilador.lexico.automatas;

import analizador.lexico.Token;

public class Delimitadores extends Automata
{

	
	public Token q0()
	{
		if(iterador < valores.length)
		switch (valores[iterador]) {
		case '}':
			return q1();
		case '{':
			return q1();
		case '(':
			return q1();
		case ')':
			return q1();
		case ';':
			return q1();

		}
		return null;
	}

	private Token q1()
	{
		return generarToken();
	}
	
}
