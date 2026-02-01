package noventagrados.util;

/**
 * Define los tipos de colores del turno. Permite consultar el color contrario
 * al actual.
 * <p>
 * Cada color tiene asociado un caracter que lo representa visualmente en el
 * tablero los colores disponibles son:
 * </p>
 * 
 * <ul>
 * <li>{@code BLANCO}, representado por el caracter <b>'B'</b></li>
 * <li>{@code NEGRO}, representado por el caracter <b>'N'</b></li>
 * </ul>
 * 
 * @author Luis Menendez Ramos
 * @version 1.0
 * @since 1.0
 */

public enum Color {

	/**
	 * Color blanco representado por la letra 'B'.
	 */
	BLANCO('B'),
	/**
	 * Color negro representado por la letra 'N'.
	 */
	NEGRO('N');

	/**
	 * El caracter que representa visualmente el color.
	 */
	private char letra;

	/**
	 * Constructor privado que asocia un caracter a cada color.
	 * 
	 * @param letra El caracter representa el color
	 */
	private Color(char letra) {
		this.letra = letra;
	}

	/**
	 * Devuelve el color contrario al color del objeto actual.
	 * 
	 * Este metodo permite consultar el color opuesto de una instancia color.
	 * <p>
	 * Uso de {@code this}: La palabra reservada {@code this} hace referencia al propio
	 * objeto que llama al metodo.
	 * 
	 * @return El color contrario al color del objeto actual.
	 */
	public Color consultarContrario() {
		// expresion ternaria
		return this.equals(BLANCO) ? NEGRO : BLANCO;
	}

	/**
	 * Devuelve el caracter asociado a este color.
	 * 
	 * @return El caracter que representa el color
	 */
	public char toChar() {
		return letra;
	}

}
