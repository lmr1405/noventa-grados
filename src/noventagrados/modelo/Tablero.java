package noventagrados.modelo;

import java.util.Arrays;

import noventagrados.util.Coordenada;

/**
 * Representacion del tablero de juego Noventa grados, compuesto de celdas en
 * una matriz bidimensional.
 * <p>
 * Tablero de tamaño fijo 7x7, las celdas se inicializan vacia al momento de la
 * creacion del tablero.
 * 
 * 
 * @author <a href="lmr1027@alu.ubu.es">Luis Menendez</a>
 * @version 1.0
 * @since 26/10/2024
 */
public class Tablero {

	/**
	 * Matriz bidimensional que representa el tablero de juego, donde cada celda
	 * puede contener una pieza o estar vacía.
	 */
	private Celda[][] matriz;

	/**
	 * Tamaño constante del tablero de juego, representando el número de filas y
	 * columnas (7x7).
	 */
	private final int TAMANO = 7;

	/**
	 * Constructor, crea un tablero de tamaño fijo (7x7) con cada celda inicializada
	 * como vacia.
	 */
	public Tablero() {
		matriz = new Celda[TAMANO][TAMANO];

		for (int i = 0; i < TAMANO; i++) {
			for (int j = 0; j < TAMANO; j++) {
				matriz[i][j] = new Celda(new Coordenada(i, j));
			}
		}

	}

	/**
	 * Devuelve el estado del tablero con las piezas actualmente colocadas en
	 * formato cadena de caracteres, para mostrar en pantalla.
	 * 
	 * @return Una cadena de texto quw representada el estado del tablero
	 * @see Pieza#aTexto() Para la representacion abreviada de cada pieza
	 */
	public String aTexto() {
		String pantalla = "";

		for (int i = 0; i < consultarNumeroFilas(); i++) {
			pantalla = pantalla.concat(Integer.toString(i));
			for (int j = 0; j < consultarNumeroColumnas(); j++) {
				Celda celda = matriz[i][j];
				if (celda.estaVacia()) {
					pantalla = pantalla.concat("-- ");
				} else {
					pantalla = pantalla.concat(celda.consultarPieza().aTexto() + " ");
				}
			}
			pantalla = pantalla.concat("\n");
		}

		pantalla = pantalla.concat(" ");
		for (int j = 0; j < consultarNumeroColumnas(); j++) {
			pantalla += String.format("%-3d", j);
		}

		return pantalla;
	}

	/**
	 * Devuelve un clon en profundidad del tablero actual.
	 * 
	 * @return El clon en profundidd del tablero
	 */
	public Tablero clonar() {
		Tablero tableroClon = new Tablero();

		for (int i = 0; i < consultarNumeroFilas(); i++) {
			for (int j = 0; j < consultarNumeroColumnas(); j++) {
				if (matriz[i][j] != null) {
					tableroClon.matriz[i][j] = matriz[i][j].clonar();
				}
			}
		}
		return tableroClon;
	}

	/**
	 * Coloca en la coordenada indicada la pieza pasada como argumento.
	 * <p>
	 * Si las coordenadas no estan en el tablero o la pieza es nula, no se hace
	 * nada.
	 * 
	 * @param pieza      La pieza a colocar
	 * @param coordenada La cordenada donde colocar la pieza. Esta debe estar en los
	 *                   límites del tablero y no se nula.
	 */
	public void colocar(Pieza pieza, Coordenada coordenada) {
		if (pieza == null || coordenada == null || !estaEnTablero(coordenada)) {
			return;
		}
		matriz[coordenada.fila()][coordenada.columna()].colocar(pieza);
	}

	/**
	 * Devuelve un clon en profundidad de la celda con las coordenadas indicadas. Si
	 * las coordenadas no estan en el tablero devuelve un valor null.
	 * 
	 * @param coordenada La coordenada de la celda a consultar
	 * @return Un clon profundo de la celda en la coordenada indicada, o null si
	 *         esta fuera del tablero
	 */

	public Celda consultarCelda(Coordenada coordenada) {
		return (estaEnTablero(coordenada)) ? matriz[coordenada.fila()][coordenada.columna()].clonar() : null;
	}

	/**
	 * Devuelve un array de una dimension con clones en profundidad de todas las
	 * celdas del tablero, recorriendo la celdas de arriba hacia abajo, y de
	 * izquierda a derecha.
	 * 
	 * @return Un array de una dimensión de tipo {@code Celda[]} con clones
	 *         profundos de las celdas del tablero, siguiendo el orden recorrido.
	 */
	public Celda[] consultarCeldas() {
		int filas = consultarNumeroFilas();
		int columnas = consultarNumeroColumnas();
		Celda[] celdasClonadas = new Celda[filas * columnas];
		int indice = 0;

		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				celdasClonadas[indice++] = (matriz[i][j] != null) ? matriz[i][j].clonar() : null;
			}
		}
		return celdasClonadas;
	}

	/**
	 * Devuelve el numero de columnas del tablero.
	 * 
	 * @return El numero de columnas
	 */
	public int consultarNumeroColumnas() {
		return matriz[0].length;
	}

	/**
	 * Devuelve el numero de filas del tablero.
	 * 
	 * @return El numero de filas
	 */
	public int consultarNumeroFilas() {
		return matriz.length;
	}

	/**
	 * Elimina la pieza en la celda indicada por la coordenada. No se hace nada si
	 * la coordenada es nula o esta fuera del tablero.
	 * 
	 * @param coordenada La coordena de la celda donde se elimina la pieza
	 * @see Celda#eliminarPieza()
	 */
	public void eliminarPieza(Coordenada coordenada) {
		if (coordenada == null || !estaEnTablero(coordenada)) {
			return;
		}
		Celda celda = matriz[coordenada.fila()][coordenada.columna()];
		if (celda != null) {
			celda.eliminarPieza();
		}
	}

	/**
	 * Verifica si la coordenada esta dentro del tablero.
	 * 
	 * @param coordenada La coordena a verificar
	 * @return {@code true} si es esta en el tablero, {@code false} en caso
	 *         contrario
	 */
	public boolean estaEnTablero(Coordenada coordenada) {
		int fila = coordenada.fila();
		int columna = coordenada.columna();

		return (fila >= 0 && fila < TAMANO) && (columna >= 0 && columna < TAMANO);
	}

	/**
	 * Obtiene la celda en la coordenada especificada si esta se encuentra dentro
	 * del tablero.
	 * <p>
	 * Si la coordenada esta fuera del tablero, devuelve {@code null}
	 * </p>
	 * 
	 * @param coordenada La coordenada de la celda que se desea obtener
	 * @return La celda de la coordenada especificada, o {@code null} si no esta en
	 *         el tablero
	 */
	Celda obtenerCelda(Coordenada coordenada) {
		return estaEnTablero(coordenada) ? matriz[coordenada.fila()][coordenada.columna()] : null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(matriz);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tablero other = (Tablero) obj;
		return Arrays.deepEquals(matriz, other.matriz);
	}

	@Override
	public String toString() {
		return "Tablero [matriz=" + Arrays.toString(matriz) + "]";
	}

}