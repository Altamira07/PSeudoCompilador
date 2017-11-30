package compilador.semantico;

import compilador.lexico.Token;

import java.util.ArrayList;

public class TablaSemantica
{
    public static TablaSemantica tablaSemantica = new TablaSemantica();
    ArrayList<ArrayList<Token>> expresiones = new ArrayList<>();
    public void clear()
    {
        expresiones.clear();
    }
    public void add(ArrayList<Token> expresion)
    {
        expresiones.add(expresion);
    }

    public ArrayList<Token> getExpresion(int i) {
        return expresiones.get(i);
    }
    public int size(){
        return expresiones.size();
    }
    public void read(){
        System.out.println("Leyendo");
        for(ArrayList<Token> expression:expresiones )
        {
            for(Token tk : expression)
            {
                System.out.printf(tk.getLexema());
            }
            System.out.println();
        }
    }

}
