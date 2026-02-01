package noventagrados.util;

/**
 * Modela los cuatro sentidos en los que se pueden realizar los movimientos:
 * {@code VERTICAL_N}, {@code VERTICAL_S}, {@code HORIZONTAL_E} y
 * {@code HORIZONTAL_O}.
 * <p>
 * Cada sentido se asocia a un desplazamiento en filas y columnas que define su
 * direccion en un tablero
 * </p>
 * 
 * <ul>
 * <li>{@code VERTICAL_N}: Movimiento hacia el norte, desplazamiento filas
 * negativas.</li>
 * <li>{@code VERTICAL_S}: Movimiento hacia el sur, desplazamiento filas
 * positivas.</li>
 * <li>{@code HORIZONTAL_E}: Movimiento hacia el este, desplazamiento columnas
 * positivas.</li>
 * <li>{@code HORIZONTAL_O}: Movimiento hacia el oeste, desplazamiento columnas
 * negativas</li>
 * </ul>
 * 
 * @author Luis Menendez Ramos
 * @version 1.0
 * @since 1.0
 * @see java.lang.Enum
 */

public enum Sentido {
	/**
	 * Direccion que va vertical hacia el norte.
	 */
	VERTICAL_N(-1, 0),
	/**
	 * Direccion que va vertical hacia el sur.
	 */
	VERTICAL_S(1, 0),
	/**
	 * Direccion que va horizontal hacia el este.
	 */
	HORIZONTAL_E(0, 1),
	/**
	 * Direccion que va horizontal hacia el oeste.
	 */
	HORIZONTAL_O(0, -1);

	/**
	 * Numero de desplazamientos de las filas.
	 */
	private int desplazamientoEnFilas;
	/**
	 * Numero de desplazamientos de las columnas.
	 */
	private int desplazamientoEnColumnas;

	/**
	 * Constructor para inicializar un sentido con su desplazamiento en filas y
	 * columnas.
	 * 
	 * @param desplazamientoEnFilas    Desplazamiento en las filas asociado al
	 *                                 sentido
	 * @param desplazamientoEnColumnas Desplazamiento en las columnas asociado al
	 *                                 sentido
	 */
	private Sentido(int desplazamientoEnFilas, int desplazamientoEnColumnas) {
		this.desplazamientoEnFilas = desplazamientoEnFilas;
		this.desplazamientoEnColumnas = desplazamientoEnColumnas;
	}

	/**
	 * Consulta el desplazamiento en las filas asociado a este sentido.
	 * 
	 * @return El desplazamiento en filas
	 */
	public int consultarDesplazamientoEnFilas() {
		return desplazamientoEnFilas;
	}

	/**
	 * Consulta el desplazamiento en las columnas asociado a este sentido.
	 * 
	 * @return El desplazamiento en columnas
	 */
	public int consultarDesplazamientoEnColumnas() {
		return desplazamientoEnColumnas;
	}

}
