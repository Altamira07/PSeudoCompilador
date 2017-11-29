package compilador.lexico.automatas;


import compilador.lexico.Token;

public class OperadoresLogicos extends Automata
{

	@Override
	public Token analizar(char[] valores) {
		this.valores = valores;
		return q0();
	}
	public Token q0()
	{if(iterador < valores.length)
		switch (valores[iterador++])
		{
		case '=':
			return q1();
		case '!':
			return q1();
		case '<':
			return q1();
		case '>':
			return q1();
		default:
			break;
		}
		
		return null;
	}
	
	
	private Token q1()
	{
		return (iterador<valores.length && valores[iterador++] == '=' ) ? q4(): null;
	}
	
	private Token q4()
	{
		return generarToken();
	}
}
