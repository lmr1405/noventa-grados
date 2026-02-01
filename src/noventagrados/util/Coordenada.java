package noventagrados.util;

/**
 * Representa una coordenada en una cuadrícula.
 * <p>
 * La clase {@code Coordenada} contiene dos campos: {@code fila} y
 * {@code columna}, que indican la posición en la cuadrícula.
 * </p>
 * 
 * <p>
 * La posición está representada por los valores de fila y columna.
 * </p>
 * 
 * <ul>
 * <li><b>fila</b>: La fila de la coordenada en la cuadrícula.</li>
 * <li><b>columna</b>: La columna de la coordenada en la cuadrícula.</li>
 * </ul>
 * 
 * @param fila    La fila de la coordenada.
 * @param columna La columna de la coordenada.
 * 
 * @author Luis Menendez Ramos
 * @version 1.0
 * @since 1.0
 */
public record Coordenada(int fila, int columna) {

	/**
	 * Obtiene la fila y la columna y lo convierte en cadena texto concatenado.
	 * 
	 * @return La fila y la columna en forma de cadena de texto
	 * @see String#valueOf(int) valueOf Devuelve la representacion de cadena del
	 *      argumento int
	 */
	public String aTexto() {

		return String.valueOf(fila) + String.valueOf(columna);
	}
}
