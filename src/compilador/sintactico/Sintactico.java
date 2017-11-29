package compilador.sintactico;

import compilador.lexico.TablaSimbolos;
import compilador.lexico.Token;
import javafx.scene.control.Tab;

import java.util.List;

public class Sintactico {
    public  int i = -1;
    List<Token> tokens;
    public String error ="";
    boolean bandera = true;
    public Sintactico (List<Token> tokens)
    {
        this.tokens = tokens;
    }

    public void instrucciones (){
        i++;
        Token tk = tokens.get(i);
        if(tk.getLexema().equals("mientras"))
        {
            mientras();
            i++;
            if(tokens.get(i).getLexema().equals("{"))
                instrucciones();
            if(tokens.get(i).getLexema().equals("}"))
            {
                return;
            }else{
                    error +=  "Se esperaba }";
            }
        }else if(tk.getLexema().equals("si"))
        {
            si();
            i++;
            if(tokens.get(i).getLexema().equals("{"))
                instrucciones();
            if(tokens.get(i).getLexema().equals("}"))
            {
                return;
            }else{
                error +=  "Se esperaba }";
            }

        }
        return;
    }

    void declaracion(){

    }

    void si()
    {
        bool();
    }
    void bool (){
        i++;
        Token tk = tokens.get(i);
        if(tk.getLexema().equals("("))
        {
            while (bandera){
                sentenciaBooleana();
            }
            tk = tokens.get(i);
            if(tk.getLexema().equals(")"))
            {
                return;
            }

        }
    }
    void mientras(){
        bool();
    }
    public void sentenciaBooleana() {
        i++;
        Token tk = tokens.get(i);
        if (tk.getTipo().equals("identificador") || tk.getTipo().equals("numero_real") || tk.getLexema().equals("verdadero") || tk.getLexema().equals("falso")) {
            i++;
            tk = tokens.get(i);
            if (tk.getTipo().equals("Operador_logico")) {
                i++;
                tk = tokens.get(i);
                if (tk.getTipo().equals("identificador") || tk.getTipo().equals("numero_real") || tk.getLexema().equals("verdadero") || tk.getLexema().equals("falso")) {

                    return;
                }else
                    error+="Se esperaba identificador | numero real | booleano \n";
            }else
                error+="Se esperaba operador logico\n";

        }
        bandera = false;

    }
}
