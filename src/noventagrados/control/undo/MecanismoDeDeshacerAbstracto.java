package noventagrados.control.undo;

import java.util.Date;

/**
 * Clase abstracta que implementa {@link MecanismoDeDeshacer}.
 * <p>
 * Sirve como base para implementar diferentes mecanismos de deshacer en un
 * juego. Esta clase incluye atributos y métodos comunes que seran compartidos
 * por las implementaciones concretas
 * </p>
 * 
 * @author <a href="lmr1027@alu.ubu.es">Luis Menendez Ramos</a>
 * @version 1.0
 * @since 1.0
 */

public abstract class MecanismoDeDeshacerAbstracto implements MecanismoDeDeshacer {

	/**
	 * La fecha en la que se inicializó el mecanismo a deshacer. Es final porque no
	 * puede ser modificada una vez inicializada.
	 */
	protected final Date fechaInicio;

	/**
	 * Constructor que inicializa el mecanismo de deshacer con la fecha de inicio.
	 * 
	 * @param fecha La fecha en la que se inicializa el mecanismo
	 *                    de deshacer.
	 */
	public MecanismoDeDeshacerAbstracto(Date fecha) {
		this.fechaInicio = new Date(fecha.getTime());
	}

	
	/**
	 * Obtiene la fecha en la que se inicializó el mecanismo de deshacer.
	 * 
	 * @return La fecha de inicio del mecanismo a deshacer.
	 */
	@Override
	public Date obtenerFechaInicio() {
		return new Date(fechaInicio.getTime());
	}
}
