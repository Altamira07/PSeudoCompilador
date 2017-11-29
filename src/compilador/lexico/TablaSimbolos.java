package compilador.lexico;

import java.util.ArrayList;

public class TablaSimbolos 
{
	private ArrayList<Token> tk = new ArrayList<>();
	public void agregar(Token token)
	{
		tk.add(token);
	}
	public void imprimeTabla()
	{
		for (Token tk : tk)
		{
			System.out.println(tk.toString());
		}
	}
	public ArrayList<Token> getTk() {
		return tk;
	}
	public void setTk(ArrayList<Token> tk) {
		this.tk = tk;
	}
}
