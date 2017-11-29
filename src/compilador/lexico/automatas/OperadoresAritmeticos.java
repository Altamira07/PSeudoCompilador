package compilador.lexico.automatas;


import compilador.lexico.Token;

public class OperadoresAritmeticos extends Automata
{

	@Override
	public Token analizar(char[] valores) {
		this.valores = valores;
		return q0();
	}
	public Token q0()
	{if(iterador < valores.length)
		switch (valores[iterador]) {
		case '+':
			return q1();
		case '-':
			return q1();
		case '*':
			return q1();
		case '/':
			return q1();

		}
		return null;
	}
	private Token q1()
	{
		return generarToken();
	}
	@Override
	public Token analizar(char[] valores, int inicio) {
		this.valores = valores;
		this.inicio = inicio;
		this.iterador = inicio;
		return q0();
	}
}
