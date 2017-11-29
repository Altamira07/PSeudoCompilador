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
        List<Token> tokens = new ArrayList<>();
        Token tk = new Token(0,"mientras");
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
        tk.setTipo("Operador_logic");
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
        Sintactico s = new Sintactico(tokens);
        s.instrucciones();
        System.out.println(s.error);

    }
}
