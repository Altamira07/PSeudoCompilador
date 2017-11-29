package compilador.lexico.automatas;


import compilador.lexico.Token;

public class Cadenas extends Automata
{

	public Token q0()
	{
		if(iterador < valores.length)
		{
			if(valores[iterador] == '\'') {
				return q1();
				
			}
			
			
		}
		return null;
	}
	
	private Token q1()
	{
		iterador++;
		if(iterador<valores.length)
		{
			if(valores[iterador] == '\'')
				return  q2();
			else 
				if(valores.length != iterador)
					return q1();
		}
		return null;
	}
	
	private Token q2()
	{
		iterador++;
		return generarToken() ;
	}
	
	
}
