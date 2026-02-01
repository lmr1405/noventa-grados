package noventagrados.control.undo;

import java.util.Date;

import noventagrados.control.Arbitro;
import noventagrados.modelo.Jugada;

/**
 * La interfaz define los metodos necesarios para implementar un mecanismo de
 * deshacer jugadas en un juego.
 * <p>
 * Proporciona metodos para gestionar el estado actual del arbitro, asi como
 * para realizar y deshacer jugadas y mantener un historico de estas acciones.
 * </p>
 * 
 * <p>
 * <b>Responsabilidades:</b>
 * </p>
 * <ul>
 * <li>Almacenar el estado del arbitro o las jugadas realizadas</li>
 * <li>Permitir deshacer jugadas para retroceder a un estado anterior del
 * juego.</li>
 * <li>Provee la fecha de inicio del mecanismo de deshacer.</li>
 * </ul>
 * 
 * @author <a href="lmr1027@alu.ubu.es">Luis Menendez Ramos</a>
 * @version 1.0
 * @since 1.0
 */
public interface MecanismoDeDeshacer {

	/**
	 * Devuelve el arbitro actual en el estado tras la jugada y deshacer jugadas.
	 * 
	 * @return Un clon del arbitro en el estado actual.
	 */
	Arbitro consultarArbitroActual();

	/**
	 * Devuelve el numero de jugadas en el historico que puedan deshacerse.
	 * 
	 * @return Numero de jugadas en el historico.
	 */
	int consultarNumeroJugadasEnHistorico();

	/**
	 * Deshace la ultima jugada realizada.
	 */
	void deshacerJugada();

	/**
	 * Registra una jugada en el historico.
	 * 
	 * @param jugada La jugada a registrar, que contiene las celdas de origen y
	 *               destino del movimiento.
	 */
	void hacerJugada(Jugada jugada);

	/**
	 * Devuelve la fecha de inicio del mecanismo.
	 * 
	 * @return Fecha de inicio
	 */
	Date obtenerFechaInicio();
}
