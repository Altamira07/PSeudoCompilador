package compilador;

import compilador.lexico.AnalisisLexico;
import compilador.lexico.Token;
import compilador.lexico.automatas.NumerosReales;
import compilador.semantico.Semantico;
import compilador.semantico.TablaSemantica;
import compilador.sintactico.Sintactico;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Test
{
    public static  void main (String... arg)
    {
       analisisTest();
    }

    static void analisisTest()
    {
        Sintactico s = new Sintactico(null);
        AnalisisLexico al = new AnalisisLexico();
        File archivo = new File("CasosDeUso/sintactico/caso1.lh");
        al.analizar(archivo);
        //al.getTb().imprimeTabla();
        System.out.println(al.getError());
        s.setTokens(al.getTb().getTk());
        s.analizar();
        System.out.println(s.getError());
        TablaSemantica.tablaSemantica.read();
        Semantico semantico = new Semantico();
        semantico.analizar();
        System.out.println(semantico.getError());
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
