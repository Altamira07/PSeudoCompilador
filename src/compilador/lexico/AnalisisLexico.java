package compilador.lexico;

import compilador.lexico.automatas.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class AnalisisLexico {
	String error = "";
	TablaSimbolos tb = new TablaSimbolos();

	Cadenas isCadena = new Cadenas();
	Delimitadores isDelimitador = new Delimitadores();
	Identificador isIdentificador = new Identificador();
	NumerosReales isReal = new NumerosReales();
	OperadoresAritmeticos isAritmetico = new OperadoresAritmeticos();
	OperadoresLogicos isOLogico = new OperadoresLogicos();
	PalabrasReservadasE isPalabraR = new PalabrasReservadasE();
	PalabrasReservadasT isTipoDato = new PalabrasReservadasT();

	public int id = 0;

	public void analizar(String ruta) {
		Scanner lector;
		Token tk = null;
		try {
			lector = new Scanner(new File(ruta));
			while (lector.hasNext()) {
				String cadena = lector.next();
				tk = generarToken(cadena);
				if (tk != null) {
					tk.setId(++id);
					tb.agregar(tk);
				} else {
					error += "Error lexico" + cadena + "\n";
				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tb.imprimeTabla();
		System.out.println(error);
	}

	public void linea(String linea) {
		char[] valores = linea.toCharArray();
		String temp = "";
		Token tk = null;
		int i = 0;

		for (i = 0; i < valores.length; i++) {
			if (agregar(tk, valores, i))
				temp = "";
			tk = null;
			if (valores[i] != ' ') {
				temp = temp + valores[i];
			}
			tk = generarToken(temp);
		}

	}

	public boolean agregar(Token tk, char[] valores, int i) {
		boolean b = false;
		if (tk != null) {
			switch (tk.getIdN()) {
			case 2:
				b = (valores[i] == ' ' || valores[i] == '<' || valores[i] == '>');
				break;
			case 3:
				b = (valores[i] == ' ' || valores[i] == '=' || valores[i] == ';');
				break;
			case 1:
				b = (valores[i] == ' ' || valores[i] == ';' || valores[i] == '(' || valores[i] == '{');
				break;
			case 9:
			case 5:
			case 8:
			case 7:
			case 6:
				b = true;
				break;
			case 4:
				b = (valores[i] == ' ' || valores[i] == ';');
			}
			if (b) {
				tb.agregar(tk);
				tk = null;
				b = false;
				return true;
			}
		}
		return false;

	}

	public Token generarToken(String cadena) {
		Token tk = null;
		if (cadena.equalsIgnoreCase("=>")) {
			tk = new Token(0, "=>");
			tk.setTipo("operador_asignacion");
			tk.setIdN(9);
			return tk;
		} else if ((tk = isPalabraR.analizar(cadena.toCharArray(), 0)) != null) {
			if (cadena.equals("entonces"))
				tk.setTipo("Palabra_reservada");
			else
				tk.setTipo("palabra_reservada_i");
			tk.setIdN(1);
			return tk;
		} else if ((tk = isTipoDato.analizar(cadena.toCharArray(), 0)) != null) {
			tk.setTipo("Tipo_dato");
			tk.setIdN(2);
			return tk;
		} else if ((tk = isIdentificador.analizar(cadena.toCharArray(), 0)) != null) {
			tk.setIdN(3);
			tk.setTipo("identificador");
			return tk;
		} else if ((tk = isDelimitador.analizar(cadena.toCharArray(), 0)) != null) {
			if (cadena.equalsIgnoreCase("}") || cadena.equals(";") || cadena.equals(")")) {
				tk.setTipo("delimitador_t");
				tk.setIdN(99);
			} else if (cadena.equals("{") || cadena.equals("(")) {
				tk.setTipo("delimitador_i");
				tk.setIdN(98);

			}
			tk.setLexema(cadena);
			return tk;
		} else if ((tk = isAritmetico.analizar(cadena.toCharArray(), 0)) != null) {
			tk.setIdN(6);
			tk.setLexema(cadena);
			tk.setTipo("Operador_aritmetico");
			return tk;
		} else if ((tk = isOLogico.analizar(cadena.toCharArray(), 0)) != null) {
			tk.setIdN(7);
			tk.setTipo("Operador_logico");
			return tk;
		} else if ((tk = isCadena.analizar(cadena.toCharArray(), 0)) != null) {
			tk.setIdN(8);
			tk.setTipo("cadena");
			return tk;
		} else if ((tk = isReal.analizar(cadena.toCharArray(), 0)) != null) {
			tk.setIdN(4);
			tk.setTipo("numero_real");
			return tk;
		}

		return null;
	}
}
