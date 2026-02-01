package noventagrados.control;

import noventagrados.modelo.Celda;

import noventagrados.modelo.Pieza;
import noventagrados.modelo.Tablero;
import noventagrados.util.Color;
import noventagrados.util.Coordenada;
import noventagrados.util.Sentido;
import noventagrados.util.TipoPieza;

/**
 * La clase TableroConsultor permite realizar consultas sobre el estado de un
 * Tablero, como el calculo de sentidos de movimientos ,distancias entre celdas
 * y conteo de piezas. Es generica y parametrizable para subtipos de tablero.
 * 
 * @param <T> un suptipo de Tablero
 * @author <a href="lmr1027@alu.ubu.es">Luis Menendez</a>
 * @version 1.0
 * @since 1.0
 */

//al ser una clase generica, permite que funcione con diferentes tipos de datos. Con extends restringimos a que sean de tipo tablero
public class TableroConsultor<T extends Tablero> {

	/**
	 * El tablero sobre el cual se realizarán las consultas.
	 */
	private final T tablero;

	/**
	 * Crea una nueva instancia de TableroConsultor con un tablero especificado.
	 * 
	 * @param tablero El tablero sobre el cual se realizaran las consultas.
	 */
	public TableroConsultor(T tablero) {
		this.tablero = tablero;
	}

	/**
	 * Calcula el sentido del movimiento entre dos coordenadas.
	 * 
	 * @param origen  La coordenada de origen
	 * @param destino La coordenada de destino
	 * @return el sentido del movimiento, o null si el movimiento no es
	 *         exclusivamente {@code HORIZONTAL} o {@code VERTICAL}
	 */

	/**
	 * Calcula el sentido del movimiento entre dos coordenadas.
	 * 
	 * @param origen  La coordenada de origen
	 * @param destino La coordenada de destino
	 * @return el sentido del movimiento, o null si el movimiento no es
	 *         exclusivamente {@code HORIZONTAL} o {@code VERTICAL}
	 */

	public Sentido calcularSentido(Coordenada origen, Coordenada destino) {
		int diferenciaFilas = destino.fila() - origen.fila();
		int diferenciaColumnas = destino.columna() - origen.columna();

		for (Sentido sentido : Sentido.values()) {
			if (sentido.consultarDesplazamientoEnFilas() == Integer.signum(diferenciaFilas)
					&& sentido.consultarDesplazamientoEnColumnas() == Integer.signum(diferenciaColumnas)) {
				return sentido;
			}
		}
		return null;

	}

	/**
	 * Consulta la distancia en celdas entre dos coordenadas en la misma fila.
	 * 
	 * @param origen  La coordena de origen
	 * @param destino La coordenada de destino
	 * @return La distancia horizontal entre dos coordenada, o -1 si no están en la
	 *         misma fila
	 */
	public int consultarDistanciaEnHorizontal(Coordenada origen, Coordenada destino) {
		if (origen.fila() == destino.fila()) {
			return Math.abs(destino.columna() - origen.columna());
		}
		return -1;
	}

	/**
	 * Consulta la distancia entre dos coordenadas en la misma columna.
	 * 
	 * @param origen  La coordenada de origen
	 * @param destino La coordenada de destino
	 * @return La distancia vertical entre dos coordenas, o -1 si no estan en la
	 *         misma columna.
	 */
	public int consultarDistanciaEnVertical(Coordenada origen, Coordenada destino) {
		if (origen.columna() == destino.columna()) {
			return Math.abs(destino.fila() - origen.fila());
		}
		return -1;
	}

	/**
	 * Consulta el número de pieza de un tipo y color especificos en el tablero.
	 * 
	 * @param tipoPieza el tipo de pieza a contar
	 * @param color     el color de la pieza a contar
	 * @return el número de piezas que coinciden con el tipo y color dados
	 */
	public int consultarNumeroPiezas(TipoPieza tipoPieza, Color color) {
		int contador = 0;

		for (int i = 0; i < tablero.consultarNumeroFilas(); i++) {
			for (int j = 0; j < tablero.consultarNumeroColumnas(); j++) {
				Coordenada coordenada = new Coordenada(i, j);
				Pieza pieza = tablero.consultarCelda(coordenada).consultarPieza();

				if (pieza != null && pieza.consultarTipoPieza() == tipoPieza && pieza.consultarColor() == color) {
					contador++;
				}
			}
		}
		return contador;
	}

	/**
	 * Consulta el número de piezas en una fila especifica del tablero.
	 * 
	 * @param coordenada la coordenada de una celda en la fila a consultar
	 * @return el numero de pieza en la fila especificada
	 */
	public int consultarNumeroPiezasEnHorizontal(Coordenada coordenada) {
		int contador = 0;
		int fila = coordenada.fila();

		for (int columna = 0; columna < tablero.consultarNumeroColumnas(); columna++) {
			Coordenada posicionActual = new Coordenada(fila, columna);
			Pieza pieza = tablero.consultarCelda(posicionActual).consultarPieza();

			if (pieza != null) {
				contador++;
			}
		}
		return contador;
	}

	/**
	 * Consulta el número de pieza en una columna especifica del tablero.
	 * 
	 * @param coordenada la coordenada de una celda en la columna a consultar
	 * @return el número de pieza en la columna especificada
	 */
	public int consultarNumeroPiezasEnVertical(Coordenada coordenada) {
		int contador = 0;
		int columna = coordenada.columna();

		for (int fila = 0; fila < tablero.consultarNumeroFilas(); fila++) {
			Coordenada posicionActual = new Coordenada(fila, columna);
			Pieza pieza = tablero.consultarCelda(posicionActual).consultarPieza();

			if (pieza != null) {
				contador++;
			}
		}
		return contador;
	}

	/**
	 * Verifica si una reina de un color especifico esta en el centro del tablero.
	 * 
	 * @param color el color de la reina a verificar
	 * @return {@code true} si una reina del color especificado esta en el centro,
	 *         en caso contrario {@code false}
	 */
	public boolean estaReinaEnElCentro(Color color) {
		int filaCentral = tablero.consultarNumeroFilas() / 2;
		int columnaCentral = tablero.consultarNumeroColumnas() / 2;
		Coordenada posicionCentral = new Coordenada(filaCentral, columnaCentral);

		Pieza pieza = tablero.consultarCelda(posicionCentral).consultarPieza();

		return pieza != null && pieza.consultarTipoPieza() == TipoPieza.REINA && pieza.consultarColor() == color;
	}

	/**
	 * Verifica si existe al menos una reina de un color especifico en el tablero.
	 * 
	 * @param color el color de la reina a buscar
	 * @return {@code true} si hay una reina del color especifico, {@code false} en
	 *         caso contrario.
	 */
	public boolean hayReina(Color color) {
		for (Celda celda : tablero.consultarCeldas()) {
			Pieza pieza = celda.consultarPieza();

			if (pieza != null && pieza.consultarTipoPieza() == TipoPieza.REINA && pieza.consultarColor() == color) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Devuelve una representacion en forma de texto de cadena de la clase
	 * TableroConsultor
	 * 
	 * @return una representacion en cadena de la clase TableroConsultor
	 */
	@Override
	public String toString() {
		return "TableroConsultor [tablero=" + tablero + "]";
	}

}
