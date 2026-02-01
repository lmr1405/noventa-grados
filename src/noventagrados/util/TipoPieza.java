package noventagrados.util;

/**
 * Representa las posibles piezas en el juego noventa grados
 * <p>
 * Cada pieza viene representada por un caracter asociado, estas piezas son:
 * <ul>
 * <li>{@code PEON}: Representado por el caracter 'P'.</li>
 * <li>{@code REINA}: Representado por el caracter 'R'.</li>
 * </ul>
 * 
 * @author Luis Menendez Ramos
 * @version 1.0
 * @since 1.0
 * @see java.lang.Enum
 */

public enum TipoPieza {
	/**
	 * Pieza <s>PEON</s> representado por 'P'.
	 */
	PEON('P'),
	/**
	 * Pieza <s>REINA</s> representado por 'R'.
	 */
	REINA('R');

	/**
	 * El caracter que representa visualmente la pieza.
	 */
	private char caracter;

	/**
	 * Constructor privado que asocia un caracter a cada pieza.
	 * 
	 * @param caracter El caracter que representa la pieza
	 */
	private TipoPieza(char caracter) {
		this.caracter = caracter;
	}

	/**
	 * Devuelve el caracter asociado a este color.
	 * 
	 * @return El caracter que representa la pieza
	 */
	public char toChar() {
		return caracter;
	}
}
