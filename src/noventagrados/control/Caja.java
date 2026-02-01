package noventagrados.control;


import java.util.Arrays;
import java.util.Objects;

import noventagrados.modelo.Pieza;
import noventagrados.util.Color;
import noventagrados.util.TipoPieza;

/**
 * La clase caja representa un contenedor para piezas en el juego de noventa grados.
 * Cada caja tiene un color especifico y puede contener hasta 7 piezas de ese color.
 * No permite añadir piezas de un color contrario al de la caja.
 * 
 * @author Luis Menendez Ramos
 * @version 1.0
 * @since 04/11/2024
 */


public class Caja {

	/**
	 * Capacidad maxima de pieza que puede contener la caja.
	 */
	private final int PIEZASENCAJA = 7;
	/**
	 * Color de la caja, determina el color de la piezas que puede contener.
	 */
	private Color color;
	/**
	 * Array que almacena las piezas contenida en la caja.
	 */
	private Pieza[] piezas;
	/**
	 * Numero actual de piezas en la caja.
	 */
	private int numPiezas;
	
 
	/**
	 * Constructor que inicializa la caja vacía con su color asignado.
	 * 
	 * @param color El color de la caja
	 */
	public Caja(Color color) {
		this.color = color;
		piezas = new Pieza[PIEZASENCAJA];
		numPiezas =0;
	}

	/**
	 * Añade una pieza a la caja si el color de la pieza coincide con el color de la caja
	 * y si aún hay espacio en la caja.
	 * 
	 * @param pieza La pieza a añadir
	 */
	public void añadir(Pieza pieza) {
		if(pieza!=null && pieza.consultarColor() == color && numPiezas < PIEZASENCAJA) {
			piezas[numPiezas++] = pieza;
		}
	}

	/**
	 * Devuelve un clon de la caja con todas las pieas contenida en ella.
	 * 
	 * @return una nueva caja con los mismos elementos
	 */
	public Caja clonar() {
		Caja cajaClon = new Caja(this.color);
		for (int i = 0; i < numPiezas; i++) {
			cajaClon.añadir(this.piezas[i].clonar());
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
	 * Devuelve un array con clones profundos en todas las piezas contenidas en la caja.
	 * 
	 * @return Un array de piezas clonadas.
	 */
	public Pieza[] consultarPiezas() {
		Pieza[] piezaClon = new Pieza[numPiezas];
		for (int i = 0; i < numPiezas; i++) {
			piezaClon[i] = piezas[i].clonar();
		}
		return piezaClon;
	}

	/**
	 * Devuelve el numero de piezas contenida en la caja.
	 * 
	 * @return El numero total de piezas
	 */
	public int contarPiezas() {
		return numPiezas;
	}

	/**
	 * Devuelve el numerode pieza de un tipo especifico en la caja.
	 * 
	 * @param tipoPieza El tipo de pieza a contar
	 * @return El numero de pieza de tipo especifico
	 */
	public int contarPiezas(TipoPieza tipoPieza) {
		int cont = 0;
		for (int i = 0; i < numPiezas; i++) {
			if (piezas[i] != null && piezas[i].consultarTipoPieza() == tipoPieza)  {
				cont++;
			}
		}
		return cont;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(piezas);
		result = prime * result + Objects.hash(PIEZASENCAJA, color, numPiezas);
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
		Caja other = (Caja) obj;
		return PIEZASENCAJA == other.PIEZASENCAJA && color == other.color && numPiezas == other.numPiezas
				&& Arrays.equals(piezas, other.piezas);
	}

	@Override
	public String toString() {
		return "Caja [PIEZASENCAJA=" + PIEZASENCAJA + ", color=" + color + ", piezas=" + Arrays.toString(piezas)
				+ ", numPiezas=" + numPiezas + "]";
	}
	
	
	
}