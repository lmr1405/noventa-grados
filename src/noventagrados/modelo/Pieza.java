package noventagrados.modelo;

import java.util.Objects;

import noventagrados.util.Color;
import noventagrados.util.TipoPieza;

/**
 * Representa una pieza en el juego Noventa grados. Cada pieza tiene un tipo de
 * pieza y un color que indifican su funcion y el jugador al que pertenece
 * 
 * @author <a href="lmr1027@alu.ubu.es">Luis Menendez</a>
 * @version 1.0
 * @since 1.0
 */

public class Pieza {

	/**
	 * Tipo de la pieza posible en el tablero.
	 */
	private TipoPieza tipoPieza;
	/**
	 * Tipo de color que representa al jugador.
	 */
	private Color color;

	/**
	 * Crea una nueva pieza con el tipo de pieza y el color especifico.
	 * 
	 * @param tipoPieza El tipo de pieza que se le va a asignar a la pieza.
	 * @param color     El tipo de color que se le va a asignar a la pieza,
	 */
	public Pieza(TipoPieza tipoPieza, Color color) {
		this.tipoPieza = tipoPieza;
		this.color = color;
	}

	/**
	 * Devuelve una cadena de representación en texto correspondiente al tipo de
	 * pieza y color.
	 * 
	 * @return Cadena de texto del tipo de pieza y el color,
	 * @see TipoPieza#toChar()
	 * @see Color#toChar()
	 */
	public String aTexto() {
		return tipoPieza.toChar() + "" + color.toChar();
	}

	/**
	 * Devuelve un clon en profundidad de la pieza actual.
	 * 
	 * @return Clon de la pieza actual
	 */
	public Pieza clonar() {

		return new Pieza(this.tipoPieza, this.color);
	}

	/**
	 * Devuelve el color de la pieza.
	 * 
	 * @return El color de la pieza
	 */
	public Color consultarColor() {
		return color;
	}

	/**
	 * Devuelve el tipo de pieza.
	 * 
	 * @return El tipo de pieza
	 */
	public TipoPieza consultarTipoPieza() {
		return tipoPieza;
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, tipoPieza);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pieza other = (Pieza) obj;
		return color == other.color && tipoPieza == other.tipoPieza;
	}

	@Override
	public String toString() {
		return "Pieza [tipoPieza=" + tipoPieza + ", color=" + color + "]";
	}

}
