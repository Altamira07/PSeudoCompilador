package gui;

import javafx.beans.property.SimpleStringProperty;

public class TokenT {
    private  final SimpleStringProperty id;
    private final SimpleStringProperty lexema;
    private final SimpleStringProperty tipo;

    public TokenT(SimpleStringProperty id, SimpleStringProperty lexema, SimpleStringProperty tipo) {
        this.id = id;
        this.lexema = lexema;
        this.tipo = tipo;
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getLexema() {
        return lexema.get();
    }

    public SimpleStringProperty lexemaProperty() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema.set(lexema);
    }

    public String getTipo() {
        return tipo.get();
    }

    public SimpleStringProperty tipoProperty() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo.set(tipo);
    }
}
