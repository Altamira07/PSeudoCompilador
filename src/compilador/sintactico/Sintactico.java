package compilador.sintactico;

import compilador.lexico.TablaSimbolos;
import compilador.lexico.Token;
import javafx.scene.control.Tab;
import javafx.scene.input.TouchEvent;

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
            Token tk = (i < tokens.size()) ? tokens.get(i) : null;
            if( tk != null && tk.getLexema().equals("mientras"))
            {
                mientras();
                i++;
                tk = (i < tokens.size()) ? tokens.get(i) : null;
                if(tk != null && tk.getLexema().equals("{"))
                    instrucciones();
                tk = (i < tokens.size()) ? tokens.get(i) : null;
                if(tk != null && tk.getLexema().equals("}"))
                {
                    return;
                }else{
                        error +=  "Se esperaba }";
                }
            }else if(tk != null && tk.getLexema().equals("si"))
            {
                si();
                i++;
                tk = (i < tokens.size()) ? tokens.get(i) : null;
                if(tk != null && tk.getLexema().equals("{"))
                    instrucciones();
                else{
                    error+= "Se esperaba {";
                }
                tk = (i < tokens.size()) ? tokens.get(i) : null;
                if(tk != null && tk.getLexema().equals("}"))
                {
                    return;
                }else{
                    error +=  "Se esperaba }";

                }

            }else if(tk != null && tk.getTipo().equals("Tipo_dato"))
            {
                declaracion();
            }else if (tk != null && tk.getTipo().equals("identificador")) {
                asignacion();
            }
            if(i < tokens.size())
            instrucciones();

    }

    void asignacion(){
        i++;
        Token tk = (i < tokens.size()) ? tokens.get(i) : null;

        if(tk != null && tk.getLexema().equals("=>"))
        {
            valor();
            return;
        }else{
            error+="\n se esperaba => ";
        }
    }
    void valor(){
        i++;
        Token tk;
        tk = (i < tokens.size()) ? tokens.get(i) : null;
        if(tk != null && tk.getTipo().equals("cadena"))
        {
            i++;
            tk = (i < tokens.size()) ? tokens.get(i) : null;
            if(tk.getLexema().equals(";"))
                return;
            else
                error+= "\n se esperaba ;";
        }else if(tk != null && tk.getLexema().equals("verdadero") || tk.getLexema().equals("falso") ){
            i++;
            tk = (i < tokens.size()) ? tokens.get(i) : null;
            if(tk.getLexema().equals(";"))
                return;
            else
                error+= "\n se esperaba ;";
        }else if( tk != null && tk.getTipo().equals("numero_real")){
            i++;
            tk = (i < tokens.size()) ? tokens.get(i) : null;
            if(tk != null && tk.getTipo().equals("Operador_aritmetico"))
            {
               expMat();
            }else if ( tk != null && tk.getLexema().equals(";")){
                return;
            }else {
                error+= "Se esperaba ;";
            }
        }else
        {
            error+= "Se esperaba tipo de dato";
        }
        return;
    }

    void expMat()
    {
        i++;
        Token tk = tokens.get(i);
        tk = (i < tokens.size()) ? tokens.get(i) : null;
        if(tk != null && tk.getTipo().equals("numero_real"))
        {
            i++;
            tk = (i < tokens.size()) ? tokens.get(i) : null;
            if(tk != null && tk.getTipo().equals("Operador_aritmetico"))
            {
                expMat();
            }else if(tk != null && tk.getLexema().equals(";")){
                return;
            }else{
                error+= "Se esperaba ;";
            }
        }
    }

    void declaracion(){
        i++;
        Token tk;
        tk = (i < tokens.size()) ? tokens.get(i) : null;
        if(tk != null && tk.getTipo().equals("identificador"))
        {
            i++;
            tk = tokens.get(i);
            if(tk != null && tk.getLexema().equals(";"))
            {
                return;
            }else{
                error+="Se esperaba ;";
            }
        }else{
            error+="Se esperaba un identificador";
        }
        return;
    }

    void si()
    {
        condiciones();
    }
    void condiciones (){
        i++;
        Token tk = (i < tokens.size()) ? tokens.get(i) : null;

        if(tk != null && tk.getLexema().equals("("))
        {
            while (bandera){
                sentenciaBooleana();
            }
            tk = (i < tokens.size()) ? tokens.get(i) : null;
            if(tk != null && tk.getLexema().equals(")"))
            {
                return;
            }else{
                error+="\n se esperaba )";
            }

        }else{
            error+= "\n se esperaba (";
        }
    }
    void mientras(){
        condiciones();
    }
    void sentenciaBooleana() {
        i++;
        Token tk = (i < tokens.size()) ? tokens.get(i) : null;

        if ( tk != null && (tk.getTipo().equals("identificador") || tk.getTipo().equals("numero_real") || tk.getLexema().equals("verdadero") || tk.getLexema().equals("falso"))) {
            i++;
            tk = (i < tokens.size()) ? tokens.get(i) : null;
            if (tk != null && tk.getTipo().equals("Operador_logico")) {
                i++;
                tk = (i < tokens.size()) ? tokens.get(i) : null;
                if (tk != null && tk.getTipo().equals("identificador") || tk.getTipo().equals("numero_real") || tk.getLexema().equals("verdadero") || tk.getLexema().equals("falso")) {

                    return;
                }else
                    error+="Se esperaba identificador | numero real | booleano \n";
            }else
                error+="Se esperaba operador logico\n";

        }
        bandera = false;

    }
}
