package compilador;

import compilador.lexico.AnalisisLexico;
import compilador.lexico.Token;
import compilador.sintactico.Sintactico;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Principal
{
    public static  void main (String... arg)
    {
        Sintactico s = new Sintactico(tokensAsignacion());
        s.instrucciones();
        System.out.println(s.error);

    }
    static List<Token> tokensSentencias (){
        List<Token> tokens = new ArrayList<>();
        Token tk = new Token(0,"si");
        tk.setTipo("palabra_reservada");
        tokens.add(tk);
        tk = null;
        tk = new Token(0,"(");
        tk.setTipo("delimitador");
        tokens.add(tk);
        tk = null;
        tk = new Token(0," Valor");
        tk.setTipo("identificador");
        tokens.add(tk);
        tk = null;
        tk = new Token(0,"<=");
        tk.setTipo("Operador_logico");
        tokens.add(tk);
        tk = null;
        tk = new Token(0,"Valor");
        tk.setTipo("identificador");
        tokens.add(tk);
        tk = null;
        tk = new Token(0,")");
        tk.setTipo("delimitador");
        tokens.add(tk);
        tk = null;
        tk = new Token(0,"{");
        tk.setTipo("delimitador");

        tokens.add(tk);tk = null;
        tk = new Token(0,"}");
        tk.setTipo("delimitador");
        tokens.add(tk);
        return tokens;
    }

    static List<Token> tokensDeclaracion()
    {
        List<Token> tokens = new ArrayList<>();
        Token tk;
        tk = new Token(0,"Real");
        tk.setTipo("Tipo_dato");
        tokens.add(tk);
        tk = null;
        tk = new Token(0,"Valor");
        tk.setTipo("identificador");
        tokens.add(tk);

        tk = null;
        tk = new Token(0,";");
        tk.setTipo("delimitador");
        tokens.add(tk);
        return tokens;
    }

    static List<Token> tokensAsignacion()
    {
        List<Token> tokens = new ArrayList<>();
        Token tk;
        tk = new Token(0,"Id");
        tk.setTipo("identificador");
        tokens.add(tk);
        tk = null;
        tk = new Token(0,"=>");
        tk.setTipo("asignacion");
        tokens.add(tk);
        tk = null;
        tk = new Token(0,"4.2");
        tk.setTipo("numero_real");
        tokens.add(tk);
        tk = null;
        tk = new Token(0,";");
        tk.setTipo("delimitador");
        tokens.add(tk);

        return tokens;
    }
}
