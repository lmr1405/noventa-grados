package noventagrados.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import noventagrados.modelo.Pieza;
import noventagrados.util.Color;
import noventagrados.util.TipoPieza;

/**
 * La clase caja representa un contenedor para piezas en el juego de noventa
 * grados. Cada caja tiene un color especifico y puede contener hasta 7 piezas
 * de ese color. No permite añadir piezas de un color contrario al de la caja.
 * 
 * @author <a href="lmr1027@alu.ubu.es">Luis Menendez</a>
 * @version 2.0
 * @since 1.0
 */

public class Caja {

	/**
	 * Capacidad maxima de pieza que puede contener la caja.
	 */
	private final int PIEZAS_EN_CAJA = 7;
	/**
	 * Color de la caja, determina el color de la piezas que puede contener.
	 */
	private Color color;
	/**
	 * List que almacena las piezas contenida en la caja.
	 */
	private List<Pieza> piezas;

	/**
	 * Constructor que inicializa la caja vacía con su color asignado.
	 * 
	 * @param color El color de la caja
	 */
	public Caja(Color color) {
		this.color = color;
		piezas = new ArrayList<>();
	}

	/**
	 * Añade una pieza a la caja si el color de la pieza coincide con el color de la
	 * caja y si aún hay espacio en la caja.
	 * 
	 * @param pieza La pieza a añadir
	 */
	public void añadir(Pieza pieza) {
		if (pieza != null && pieza.consultarColor() == color && piezas.size() < PIEZAS_EN_CAJA) {
			piezas.add(pieza);
		}
	}

	/**
	 * Devuelve un clon de la caja con todas las pieas contenida en ella.
	 * 
	 * @return una nueva caja con los mismos elementos
	 */
	public Caja clonar() {
		Caja cajaClon = new Caja(this.color);
		for (Pieza pieza : piezas) {
			cajaClon.añadir(pieza.clonar());
		}
		return cajaClon;
	}

	/**
	 * Consulta el color de la caja.
	 * 
	 * @return El color de la caja
	 */
	public Color consultarColor() {
		return color;
	}

	/**
	 * Devuelve un arraylist con clones profundos en todas las piezas contenidas en
	 * la caja.
	 * 
	 * @return Un arraylist de piezas clonadas.
	 */
	public List<Pieza> consultarPiezas() {
		List<Pieza> piezaClon = new ArrayList<>();
		for (Pieza pieza : piezas) {
			piezaClon.add(pieza.clonar());
		}
		return piezaClon;
	}

	/**
	 * Devuelve el numero de piezas contenida en la caja.
	 * 
	 * @return El numero total de piezas
	 */
	public int contarPiezas() {
		return piezas.size();
	}

	/**
	 * Devuelve el numerode pieza de un tipo especifico en la caja.
	 * 
	 * @param tipoPieza El tipo de pieza a contar
	 * @return El numero de pieza de tipo especifico
	 */
	public int contarPiezas(TipoPieza tipoPieza) {
		int cont = 0;
		for (Pieza pieza : piezas) {
			if (pieza != null && pieza.consultarTipoPieza() == tipoPieza) {
				cont++;
			}
		}
		return cont;
	}

	@Override
	public String toString() {
		return "Caja [PIEZASENCAJA=" + PIEZAS_EN_CAJA + ", color=" + color + ", piezas=" + piezas + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(PIEZAS_EN_CAJA, color, piezas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Caja other = (Caja) obj;
		return PIEZAS_EN_CAJA == other.PIEZAS_EN_CAJA && color == other.color && Objects.equals(piezas, other.piezas);
	}

}
