package compilador.lexico.automatas;

import compilador.lexico.Token;

public abstract class Automata
{
	public char[] valores;
	public int iterador = 0;
	public int inicio = 0;
	public Token analizar(char[] valores) {
		this.valores = valores;
		return q0();
	}
	public int getIterador() {
		return iterador;
	}
	public void setIterador(int iterador) {
		this.iterador = iterador;
	}
	public int getInicio() {
		return inicio;
	}
	public void setInicio(int inicio) {
		this.inicio = inicio;
	}
	public Token analizar(char[] valores,int inicio) {
		this.valores = valores;
		this.inicio = inicio;
		this.iterador = inicio;
		return q0();
	}
	public abstract Token q0();
	
	public Token generarToken()
	{
		String lexema = "";
		for (int i = inicio; i < iterador; i++)
			lexema+= valores[i];
		return new Token(0, lexema);
	}
	
}
