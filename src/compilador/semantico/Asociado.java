package compilador.semantico;

import compilador.lexico.Token;

public class Asociado
{
    private Token tipoDato;
    private Token identificador;

    @Override
    public String toString() {
        return "Asociado{" +
                "tipoDato=" + tipoDato +
                ", identificador=" + identificador +
                '}';
    }

    public Asociado(Token tipoDato, Token identificador) {
        this.tipoDato = tipoDato;
        this.identificador = identificador;
    }

    public Token getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(Token tipoDato) {
        this.tipoDato = tipoDato;
    }

    public Token getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Token identificador) {
        this.identificador = identificador;
    }
}
