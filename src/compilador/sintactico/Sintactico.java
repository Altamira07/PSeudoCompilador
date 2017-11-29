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

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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
                    tk = tokens.get(i-1);
                        error +="\n Se esperaba } linea: "+tk.linea+" posicion: "+tk.posicion;
                }
            }else if(tk != null && tk.getLexema().equals("si"))
            {
                si();
                i++;
                tk = (i < tokens.size()) ? tokens.get(i) : null;
                if(tk != null && tk.getLexema().equals("{"))
                    instrucciones();
                else{
                    tk = tokens.get(i-1);
                    error +="\n Se esperaba { linea: "+tk.linea+" posicion: "+tk.posicion;
                }
                tk = (i < tokens.size()) ? tokens.get(i) : null;
                if(tk != null && tk.getLexema().equals("}"))
                {
                    return;
                }else{
                    tk = tokens.get(i-1);
                    error +="\n Se esperaba } linea: "+tk.linea+" posicion: "+tk.posicion;
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
            tk = tokens.get(i-1);
            error +="\n Se esperaba => linea: "+tk.linea+" posicion: "+tk.posicion;
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
            else{
                tk = tokens.get(i-1);
                error +="\n Se esperaba ; linea: "+tk.linea+" posicion: "+tk.posicion;
            }

        }else if(tk != null && tk.getLexema().equals("verdadero") || tk.getLexema().equals("falso") ){
            i++;
            tk = (i < tokens.size()) ? tokens.get(i) : null;
            if(tk.getLexema().equals(";"))
                return;
            else{
                tk = tokens.get(i-1);
                error +="\n Se esperaba ; linea: "+tk.linea+" posicion: "+tk.posicion;
            }
        }else if( tk != null && tk.getTipo().equals("numero_real")){
            i++;
            tk = (i < tokens.size()) ? tokens.get(i) : null;
            if(tk != null && tk.getTipo().equals("Operador_aritmetico"))
            {
               expMat();
            }else if ( tk != null && tk.getLexema().equals(";")){
                return;
            }else {
                tk = tokens.get(i-1);
                error +="\n Se esperaba ; linea: "+tk.linea+" posicion: "+tk.posicion;
            }
        }else
        {
            tk = tokens.get(i-1);
            error +="\n Se esperaba tipo de dato linea: "+tk.linea+" posicion: "+tk.posicion;
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
                tk = tokens.get(i-1);
                error +="\n Se esperaba ; linea: "+tk.linea+" posicion: "+tk.posicion;
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
                tk = tokens.get(i-1);
                error +="\n Se esperaba ; linea: "+tk.linea+" posicion: "+tk.posicion;
            }
        }else{
            tk = tokens.get(i-1);
            error +="\n Se esperaba identificador linea: "+tk.linea+" posicion: "+tk.posicion;
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
                tk = tokens.get(i-1);
                error +="\n Se esperaba ) linea: "+tk.linea+" posicion: "+tk.posicion;
            }

        }else{
            tk = tokens.get(i-1);
            error +="\n Se esperaba ( linea: "+tk.linea+" posicion: "+tk.posicion;
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
                {
                    tk = tokens.get(i-1);
                    error +="\n Se esperaba identificador|numero real | booleano linea: "+tk.linea+" posicion: "+tk.posicion;
                }

            }else
            {
                tk = tokens.get(i-1);
                error +="\n Se esperaba operador logico linea: "+tk.linea+" posicion: "+tk.posicion;
            }


        }
        bandera = false;

    }
}
