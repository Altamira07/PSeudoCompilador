package compilador.sintactico;

import compilador.lexico.Token;
import compilador.semantico.TablaSemantica;

import java.util.ArrayList;
import java.util.List;

public class Sintactico {
    public int i = -1;
    List<Token> tokens;
    public String error = "";
    boolean bandera = true;
    ArrayList<Token> expresion = null;
    public Sintactico(List<Token> tokens) {
        this.tokens = tokens;
    }

    public void clear() {
        i = -1;
        tokens = null;
        bandera = true;
        error = "";
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

    public void analizar() {
        while (i < tokens.size())
            instrucciones();
    }

    public void instrucciones() {
        i++;
        Token tk = (i < tokens.size()) ? tokens.get(i) : null;
        if (tk != null && tk.getLexema().equals("mientras")) {
            mientras();
            i++;
            tk = (i < tokens.size()) ? tokens.get(i) : null;
            if (tk != null && tk.getLexema().equals("{"))
                instrucciones();
            tk = (i < tokens.size()) ? tokens.get(i) : null;
            if (tk != null && tk.getLexema().equals("}")) {
                return;
            } else {
                tk = tokens.get(i - 1);
                error += "\n Se esperaba } linea: " + tk.linea + " posicion: " + tk.posicion;
            }
        } else if (tk != null && tk.getLexema().equals("si")) {
            si();
            i++;
            tk = (i < tokens.size()) ? tokens.get(i) : null;
            if (tk != null && tk.getLexema().equals("{"))
                instrucciones();
            else {
                tk = tokens.get(i - 1);
                error += "\n Se esperaba { linea: " + tk.linea + " posicion: " + tk.posicion;
            }
            tk = (i < tokens.size()) ? tokens.get(i) : null;
            if (tk != null && tk.getLexema().equals("}")) {
                return;
            } else {
                tk = tokens.get(i - 1);
                error += "\n Se esperaba } linea: " + tk.linea + " posicion: " + tk.posicion;
            }

        } else if (tk != null && tk.getTipo().equals("Tipo_dato")) {
            expresion = new ArrayList<>();
            expresion.add(tk);
            declaracion();
            TablaSemantica.tablaSemantica.add(expresion);
            expresion = null;
            return;
        } else if (tk != null && tk.getTipo().equals("identificador")) {
            expresion = new ArrayList<>();
            expresion.add(tk);
            asignacion();
            TablaSemantica.tablaSemantica.add(expresion);
            expresion = null;
        } else
            return;

    }

    void asignacion() {
        i++;
        Token tk = (i < tokens.size()) ? tokens.get(i) : null;

        if (tk != null && tk.getLexema().equals("=>")) {
            expresion.add(tk);
            valor();
            return;
        } else {
            tk = tokens.get(i - 1);
            error += "\n Se esperaba => linea: " + tk.linea + " posicion: " + tk.posicion;
        }
    }

    void valor() {
        i++;
        Token tk;
        tk = (i < tokens.size()) ? tokens.get(i) : null;
        if (tk != null && tk.getTipo().equals("cadena")) {
            expresion.add(tk);
            i++;
            tk = (i < tokens.size()) ? tokens.get(i) : null;
            if (tk.getLexema().equals(";"))
            {
                expresion.add(tk);
                return;
            }
            else {
                tk = tokens.get(i - 1);
                error += "\n Se esperaba ; linea: " + tk.linea + " posicion: " + tk.posicion;
            }

        } else if (tk != null && tk.getLexema().equals("verdadero") || tk.getLexema().equals("falso")) {
            i++;
            expresion.add(tk);
            tk = (i < tokens.size()) ? tokens.get(i) : null;
            if (tk.getLexema().equals(";"))
            {
                expresion.add(tk);
                return;
            }
            else {
                tk = tokens.get(i - 1);
                error += "\n Se esperaba ; linea: " + tk.linea + " posicion: " + tk.posicion;
            }
        } else if (tk != null && tk.getTipo().equals("numero_real")) {
            i++;
            expresion.add(tk);
            tk = (i < tokens.size()) ? tokens.get(i) : null;
            if (tk != null && tk.getTipo().equals("Operador_aritmetico")) {
                expresion.add(tk);
                expMat();
            } else if (tk != null && tk.getLexema().equals(";")) {
                expresion.add(tk);
                return;
            } else {
                tk = tokens.get(i - 1);
                error += "\n Se esperaba ; linea: " + tk.linea + " posicion: " + tk.posicion;
            }
        } else {
            tk = tokens.get(i - 1);
            error += "\n Se esperaba tipo de dato linea: " + tk.linea + " posicion: " + tk.posicion;
        }
        return;
    }

    void expMat() {
        i++;
        Token tk = tokens.get(i);
        tk = (i < tokens.size()) ? tokens.get(i) : null;
        if (tk != null && tk.getTipo().equals("numero_real")) {
            expresion.add(tk);
            i++;
            tk = (i < tokens.size()) ? tokens.get(i) : null;
            if (tk != null && tk.getTipo().equals("Operador_aritmetico")) {
                expresion.add(tk);
                expMat();
            } else if (tk != null && tk.getLexema().equals(";")) {
                expresion.add(tk);
                return;
            } else {
                tk = tokens.get(i - 1);
                error += "\n Se esperaba ; linea: " + tk.linea + " posicion: " + tk.posicion;
            }
        }
    }

    void declaracion() {
        i++;
        Token tk;
        tk = (i < tokens.size()) ? tokens.get(i) : null;
        if (tk != null && tk.getTipo().equals("identificador")) {
            expresion.add(tk);
            i++;
            tk = tokens.get(i);
            if (tk != null && tk.getLexema().equals(";")) {
                expresion.add(tk);
                return;
            } else {
                tk = tokens.get(i - 1);
                error += "\n Se esperaba ; linea: " + tk.linea + " posicion: " + tk.posicion;
            }
        } else {
            tk = tokens.get(i - 1);
            error += "\n Se esperaba identificador linea: " + tk.linea + " posicion: " + tk.posicion;
        }
        return;
    }

    void si() {
        expresion = new ArrayList<>();
        condiciones();
        TablaSemantica.tablaSemantica.add(expresion);
        expresion = null;
    }
    void condiciones() {
        i++;
        Token tk = (i < tokens.size()) ? tokens.get(i) : null;

        if (tk != null && tk.getLexema().equals("(")) {
            while (bandera) {
                sentenciaBooleana();
                i++;
                tk = (i<tokens.size()) ? tokens.get(i): null;
                if(tk.getLexema().equals("||") || tk.getLexema().equals("&&")){
                    expresion.add(tk);
                    bandera = true;
                }else {
                    bandera = false;
                }
            }
            bandera = true;
            tk = (i < tokens.size()) ? tokens.get(i) : null;
            if (tk != null && tk.getLexema().equals(")")) {
                return;
            } else {
                tk = tokens.get(i - 1);
                error += "\n Se esperaba ) linea: " + tk.linea + " posicion: " + tk.posicion;
            }

        } else {
            tk = tokens.get(i - 1);
            error += "\n Se esperaba ( linea: " + tk.linea + " posicion: " + tk.posicion;
        }
    }

    void mientras() {
        expresion = new ArrayList<>();
        condiciones();
        TablaSemantica.tablaSemantica.add(expresion);
        expresion = null;
    }

    void sentenciaBooleana() {
        i++;
        Token tk = (i < tokens.size()) ? tokens.get(i) : null;
        if (tk != null && (tk.getTipo().equals("identificador") || tk.getTipo().equals("numero_real") || tk.getLexema().equals("verdadero") || tk.getLexema().equals("falso"))) {
            expresion.add(tk);
            i++;
            tk = (i < tokens.size()) ? tokens.get(i) : null;
            if (tk != null && tk.getTipo().equals("Operador_logico")) {
                expresion.add(tk);
                i++;
                tk = (i < tokens.size()) ? tokens.get(i) : null;
                if (tk != null && tk.getTipo().equals("identificador") || tk.getTipo().equals("numero_real") || tk.getLexema().equals("verdadero") || tk.getLexema().equals("falso")) {
                    expresion.add(tk);
                    return;
                } else {
                    tk = tokens.get(i - 1);
                    error += "\n Se esperaba identificador|numero real | booleano linea: " + tk.linea + " posicion: " + tk.posicion;
                }
            } else {
                tk = tokens.get(i - 1);
                error += "\n Se esperaba operador logico linea: " + tk.linea + " posicion: " + tk.posicion;
            }
        }
        bandera = false;

    }
}
