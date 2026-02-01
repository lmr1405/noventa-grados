package noventagrados.control.undo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import noventagrados.control.Arbitro;
import noventagrados.modelo.Jugada;
import noventagrados.modelo.Tablero;

/**
 * Clase que implementa un mecanismo de deshacer basado en la clonación completa
 * del estado del arbitro despues de cada jugada.
 * <p>
 * Se utiliza una lista para almacenar los estados completos del arbitroen cada
 * momento del historico.
 * </p>
 * 
 * @author <a href="mailto:lmr1027@alu.ubu.es">Luis Menéndez Ramos</a>
 * @since 1.0
 * @version 1.0
 * @see MecanismoDeDeshacer
 * @see MecanismoDeDeshacerAbstracto
 */
public class MaquinaDelTiempoConArbitros extends MecanismoDeDeshacerAbstracto {

	/**
	 * Histórico de estados clonados del árbitro.
	 */
	private final List<Arbitro> historicoArbitros;

	/**
	 * Constructor que inicializa la máquina del tiempo con la configuración inicial
	 * del árbitro.
	 * 
	 * @param fecha Fecha de inicio del mecanismo de deshacer.
	 */
	public MaquinaDelTiempoConArbitros(Date fecha) {
		super(fecha);
		this.historicoArbitros = new ArrayList<>();
		Arbitro arbitroInicial = new Arbitro(new Tablero());
		arbitroInicial.colocarPiezasConfiguracionInicial();
		historicoArbitros.add(arbitroInicial.clonar());

	}

	/**
	 * Consulta el estado actual del árbitro basado en el último estado del
	 * histórico.
	 * 
	 * @return Clon del árbitro en su estado actual.
	 */
	@Override
	public Arbitro consultarArbitroActual() {
		return historicoArbitros.get(historicoArbitros.size() - 1).clonar();
	}

	/**
	 * Consulta el número de jugadas realizadas que están almacenadas en el
	 * histórico.
	 * 
	 * @return Número de jugadas en el histórico (excluyendo el estado inicial).
	 */
	@Override
	public int consultarNumeroJugadasEnHistorico() {
				
		return Math.max(historicoArbitros.size() - 1, 0);
	}

	/**
	 * Deshace la última jugada realizada eliminando el último estado del histórico.
	 * Si no hay jugadas para deshacer, no realiza ninguna acción.
	 */
	@Override
	public void deshacerJugada() {
		if (historicoArbitros.size() > 1) {
			historicoArbitros.remove(historicoArbitros.size() - 1);
		}

	}

	/**
	 * Realiza una nueva jugada actualizando el árbitro con el nuevo estado y
	 * almacenando un clon del mismo en el histórico.
	 * 
	 * @param jugada La jugada a realizar.
	 */
	@Override
	public void hacerJugada(Jugada jugada) {
		Arbitro arbitroActual = consultarArbitroActual();
		arbitroActual.empujar(jugada);
		arbitroActual.cambiarTurno();
		historicoArbitros.add(arbitroActual);
	}

}