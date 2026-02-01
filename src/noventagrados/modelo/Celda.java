package noventagrados.modelo;

import java.util.Objects;

import noventagrados.util.Color;
import noventagrados.util.Coordenada;

/**
 * Representa una celda en el tablero del juego Noventa grados.
 * <p>
 * Cada celda tine una coordenada y puede contener una pieza.
 * 
 * @author <a href="lmr1027@alu.ubu.es">Luis Menendez</a>
 * @version 2.0
 * @since 1.0
 */

public class Celda {

	/**
	 * Coordenada de la celda del tablero.
	 */
	private Coordenada coordenada;

	/**
	 * Pieza actual que será colocada en el tablero.
	 */
	private Pieza pieza;

	/**
	 * Constructor, asocia una coordenada a la celda.
	 * 
	 * @param coordenada La coordenada de la celda
	 */
	public Celda(Coordenada coordenada) {
		this.coordenada = coordenada;
	}

	/**
	 * Devuelve un clon en profundidad de la pieza en la celda actual. La coordenada
	 * se mantiene igual y se realiza una copia de la pieza si esta presente.
	 * 
	 * @return La celda clonada en profundidad
	 */
	public Celda clonar() {
		Celda celdaClon = new Celda(this.coordenada);
		if (this.pieza != null) {
			celdaClon.colocar(this.pieza.clonar());
		}
		return celdaClon;
	}

	/**
	 * Coloca una pieza en la celda actual.
	 * 
	 * @param pieza La pìeza se coloca en la celda actual
	 */
	public void colocar(Pieza pieza) {
		this.pieza = pieza;
	}

	/**
	 * Consula el color de la pieza actual en la celda.
	 * 
	 * @return El color de la pieza actual, si no esta vacia, o nulo en caso
	 *         contrario.
	 */
	public Color consultarColorDePieza() {
		return (pieza != null) ? pieza.consultarColor() : null;
	}

	/**
	 * Devuelve la coordenada que esta en una celda.
	 * 
	 * @return La coordenada en la celda.
	 */
	public Coordenada consultarCoordenada() {
		return coordenada;
	}

	/**
	 * Devuelve la pieza que se encuentra en la celda.
	 * 
	 * @return La pieza que esta en la celda.
	 */
	public Pieza consultarPieza() {
		return pieza;
	}

	/**
	 * Elimina la asignacion actual de la pieza en la celda dejando en su lugar un
	 * valor nulo.
	 */
	public void eliminarPieza() {
		this.pieza = null;
	}

	/**
	 * Indica si la celda esta vacía.
	 * 
	 * @return {@code true} si no hay pieza y {@code false} en caso contrario.
	 */
	public boolean estaVacia() {
		return pieza == null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(coordenada, pieza);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Celda other = (Celda) obj;
		return Objects.equals(coordenada, other.coordenada) && Objects.equals(pieza, other.pieza);
	}

	@Override
	public String toString() {
		return "Celda [coordenada=" + coordenada + ", pieza=" + pieza + "]";
	}

}
