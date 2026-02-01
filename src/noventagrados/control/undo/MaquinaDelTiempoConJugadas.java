package noventagrados.control.undo;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import noventagrados.control.Arbitro;
import noventagrados.modelo.Jugada;
import noventagrados.modelo.Tablero;

/**
 * Clase que implementa un mecanismo de deshacer basado en el histórico de
 * jugadas realizadas.
 * <p>
 * En esta implementación, se almacena una lista de jugadas realizadas y se
 * reconstruye el estado actual del árbitro aplicando las jugadas al estado
 * inicial. Este enfoque es eficiente en términos de memoria, pero puede
 * requerir más tiempo para reconstruir el estado actual si hay muchas jugadas.
 * </p>
 *
 * @author <a href="mailto:lmr1027@alu.ubu.es">Luis Menéndez Ramos</a>
 * @since 1.0
 * @version 1.0
 * @see MecanismoDeDeshacer
 * @see MecanismoDeDeshacerAbstracto
 */

public class MaquinaDelTiempoConJugadas extends MecanismoDeDeshacerAbstracto {

	/**
     * Histórico de jugadas realizadas.
     */
	private final List<Jugada> historicoJugadas;
	
	/**
     * Estado inicial del árbitro.
     */
	private final Arbitro arbitroInicial;

	/**
     * Constructor que inicializa la máquina del tiempo con la configuración inicial del árbitro.
     * 
     * @param fecha Fecha de inicio del mecanismo de deshacer.
     */
	public MaquinaDelTiempoConJugadas(Date fecha) {
		super(fecha);
		historicoJugadas = new ArrayList<>();
		arbitroInicial = new Arbitro(new Tablero());
		arbitroInicial.colocarPiezasConfiguracionInicial();

	}

	 /**
     * Consulta el estado actual del árbitro aplicando todas las jugadas del histórico al estado inicial.
     * 
     * @return El árbitro con su estado reconstruido a partir del estado inicial.
     */
	@Override
	public Arbitro consultarArbitroActual() {
		Arbitro arbitroActual = new Arbitro(new Tablero());
		arbitroActual.colocarPiezasConfiguracionInicial();

		for (Jugada jugada : historicoJugadas) {
			arbitroActual.empujar(jugada);
			arbitroActual.cambiarTurno();
		}
		return arbitroActual;
	}

	/**
     * Consulta el número de jugadas realizadas que están almacenadas en el histórico.
     * 
     * @return El número de jugadas en el histórico.
     */
	@Override
	public int consultarNumeroJugadasEnHistorico() {
		return historicoJugadas.size();
	}

	/**
     * Deshace la última jugada realizada eliminándola del histórico.
     * Si no hay jugadas para deshacer, no realiza ninguna acción.
     */
	@Override
	public void deshacerJugada() {
		if (historicoJugadas.isEmpty()) {
			return;
		}
		historicoJugadas.remove(historicoJugadas.size() - 1);
	}

	 /**
     * Realiza una nueva jugada añadiéndola al histórico.
     * 
     * @param jugada La jugada a realizar.
     */
	@Override
	public void hacerJugada(Jugada jugada) {

		historicoJugadas.add(jugada);
	}

}
