package compilador.lexico;

public class Token {
	private int id;
	public Token(int id, String lexema) {
		super();
		this.id = id;
		this.lexema = lexema;
	}

	public int getIdN() {
		return idN;
	}

	public void setIdN(int idN) {
		this.idN = idN;
	}

	private int idN;
	private String tipo;
	private String lexema;
	
	public Token(int id, String tipo, String lexema) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.lexema = lexema;
	}
	
	public Token(int id, int idN, String tipo, String lexema) {
		super();
		this.id = id;
		this.idN = idN;
		this.tipo = tipo;
		this.lexema = lexema;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getLexema() {
		return lexema;
	}
	public void setLexema(String lexema) {
		this.lexema = lexema;
	}
	
	@Override
	public String toString() {
		return  id + "\t" + tipo +"\t"+idN+ "\t" + lexema;
	}
	
}
