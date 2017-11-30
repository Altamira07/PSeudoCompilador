package compilador.lexico.automatas;


import compilador.lexico.Token;

public class NumerosReales extends Automata
{
	private char[] transicion = "1234567890".toCharArray();
	public NumerosReales() {
		super();
	}
	public Token q0()
	{
	    if(iterador < valores.length)
	        for(int i = 0 ; i < transicion.length;i++)
            {
                if(transicion[i] == valores[iterador])
                {
                    return q1();
                }
            }
	    return null;
	}
	

	
	public Token q1()
	{
	    iterador++;
	    if(iterador<valores.length)
        {
            if(valores[iterador] == '.') {
                return q2();
            }
            for(int i = 0 ; i < transicion.length;i++)
            {
                if(transicion[i] == valores[iterador])
                {
                    return q1();
                }
            }
        }
	    return null;
	}
    public Token q2()
    {
        iterador++;
        if(iterador<valores.length)
        {
            for(int i =0; i<transicion.length;i++)
            {
                if(transicion[i] == valores[iterador])
                    return q2();
            }
            return null;
        }else if(valores[iterador-1] == '.')
            return null;
        return generarToken();
    }


}
