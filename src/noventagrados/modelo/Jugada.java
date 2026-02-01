package noventagrados.modelo;

/**
 * Representa una jugada en el juego Noventa Grados, definida por una celda de
 * origen y una celda de destino en el tablero. Cada jugada incluye las
 * coordenadas de las celdas involucradas y se puede expresar en un formato de
 * texto que muestra dichas coordenadas.
 * 
 * @param origen  La celda de origen de la jugada.
 * @param destino La celda de destino de la jugada.
 * 
 * @author <a href="mailto:lmr1027@alu.ubu.es">Luis Menendez</a>
 * @version 1.0
 * @since 1.0
 */

public record Jugada(Celda origen, Celda destino) {

	/**
	 * Devuelve el texto asociado al par de coordenadas correspondientes a las
	 * celdas origen y destino separadas por un guion en formato (e.g., "00-11")
	 * 
	 * @return Una cadena de texto que representa las coordenadas de origen y
	 *         destino
	 */
	public String aTexto() {
		String origenTexto = origen.consultarCoordenada().fila() + "" + origen.consultarCoordenada().columna();
		String destinoTexto = destino.consultarCoordenada().fila() + "" + destino.consultarCoordenada().columna();
		return origenTexto + "-" + destinoTexto;
	}

}