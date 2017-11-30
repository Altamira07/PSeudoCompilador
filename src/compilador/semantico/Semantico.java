package compilador.semantico;

import compilador.lexico.Token;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class Semantico
{
    ArrayList<Asociado> tablaAsociado = new ArrayList<>();
    TablaSemantica ts = TablaSemantica.tablaSemantica;


    private String error= "";
    public void analizar()
    {
        ArrayList<Token> expresion;
        for(int i = 0; i<ts.size();i++)
        {
            expresion = ts.getExpresion(i);
            for(Token tk : expresion)
            {
                if(tk.getTipo().equals("Tipo_dato"))
                {
                    declaracion(expresion);
                    break;
                }else if(tk.getTipo().equals("identificador") && expresion.get(1).getLexema().equals("=>"))
                {
                    asignacion(expresion);
                }
                else if(tk.getTipo().equals("identificador") || tk.getTipo().equals("numero_real") || tk.getLexema().equals("verdadero")|| tk.getLexema().equals("falso"))
                {
                    expBooleana(expresion);
                    break;
                }
                else
                    break;
            }
        }
    }

    void declaracion(ArrayList<Token> expresion)
    {
        Token t1 = expresion.get(0);
        Token t2 = expresion.get(1);
        tablaAsociado.add(new Asociado(t1,t2));
    }
    void asignacion(ArrayList<Token> expresion)
    {
        if(buscarAsociacion(expresion.get(0).getLexema())){

        }else
            error += "\n No se ha declarado "+ expresion.get(0).getLexema();
    }
    void expBooleana(ArrayList<Token> expresion)
    {
        boolean bandera = true;
        Token tk = null;
        for(int i = 0;i<expresion.size();i++)
        {
            tk = expresion.get(i);
            if(tk.getTipo().equals("identificador"))
            {
               bandera = buscarAsociacion(tk.getLexema());
               break;
            }

        }
        if(bandera){
            Asociado a = null;
            Asociado b = null;
            for(int i = 0; i<expresion.size();i+=4)
            {
                Token t1 = expresion.get(i);
                Token t2 = expresion.get(i+2);
                if(t1.getTipo().equals("identificador")){
                    a = buscar(t1.getLexema());
                }else{
                    Token temp = new Token();
                    if(t1.getTipo().equals("numero_real"))
                        temp.setLexema("Real");
                    if(t1.getTipo().equals("palabra_reservada_i"))
                        temp.setLexema("Booleano");
                    a = new Asociado(temp,t1);
                }
                if(t2.getTipo().equals("identificador")){
                    b = buscar(t1.getLexema());
                }else{
                    Token temp = new Token();
                    if(t2.getTipo().equals("numero_real"))
                        temp.setLexema("Real");
                    if(t2.getTipo().equals("palabra_reservada_i"))
                        temp.setLexema("Booleano");
                    b = new Asociado(temp,t2);

                }
                if(!a.getTipoDato().getLexema().equals(b.getTipoDato().getLexema()))
                {
                    error+="\n No se pude realizar esta comparacion "+t1.getLexema()+" "+ expresion.get(i+1).getLexema() +" "+t2.getLexema()+" diferentes tipos de datos ";
                }
                t1 = null;
                t2 = null;

            }
        }else{
            error +="\n No se ha declarado " + tk.getLexema();
        }
    }
    Asociado buscar (String lexema)
    {
        for(Asociado a : tablaAsociado)
        {
            if(a.getIdentificador().getLexema().equals(lexema))
                return a;
        }
        return  null;
    }

    boolean buscarAsociacion(String lexema)
    {
        for(Asociado a : tablaAsociado)
        {
            if(a.getIdentificador().getLexema().equals(lexema))
                return true;
        }
        return  false;
    }
    public ArrayList<Asociado> getTablaAsociado() {
        return tablaAsociado;
    }

    public void setTablaAsociado(ArrayList<Asociado> tablaAsociado) {
        this.tablaAsociado = tablaAsociado;
    }

    public TablaSemantica getTs() {
        return ts;
    }

    public void setTs(TablaSemantica ts) {
        this.ts = ts;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
